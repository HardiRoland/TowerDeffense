package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Model {
    private final int difficulty;
    private final int mapSize;
    private Player player1, player2, actualPlayer, winner;
    private Map map;

    public Model(int difficulty, int mapSize) {
        this.difficulty = difficulty;
        this.player1 = new Player("player1");
        this.player2 = new Player("player2");
        this.actualPlayer = player1;
        this.mapSize = mapSize;
        this.map = new Map(mapSize);
        this.winner = null;
    }

    /**
     * Cycle contains:
     * add money from the castle and the mines to the player
     * unit(s) and mosnter(s) step towards their target (calculate shortest path
     * before every step)
     * units if the castle next to them attack it
     * monster steps and attack units if any nearby
     * player can build, buy units, cast spells
     * castle gets damage from units
     * units get damage from tower(s) and monster(s)
     * 1 cycle ends by passing it to the another player
     * the whole game ends if 1 castle's health become 0 or less
     */
    public void switchCycle() {
        actualPlayer = actualPlayer == player1 ? player2 : player1;
        moveAndAttackWithUnits();
        moveAndAttackWithAllMonsters();
        attackWithTowers();
        actualPlayer.updateMoney(actualPlayer.getCastle().getBonusMoney());
        actualPlayer.updateMoney(actualPlayer.getAllMines().size() * MineMO.getBonusMoney());
        removeDeadUnits();
        this.winner = findWinner();
    }

    /**
     * if there is a winner return true
     */
    public boolean isGameOver() {
        if (this.winner != null) {
            return true;
        }
        return false;
    }

    /**
     * which player is the winner
     */
    public Player findWinner() {
        if (player1.getCastle().getHealth() <= 0) {
            return player2;
        }
        if (player2.getCastle().getHealth() <= 0) {
            return player1;
        }
        return null;
    }

    /**
     * remove defeated units
     */
    public void removeDeadUnits() {
        this.map.updateUnits();
        this.player1.updateUnits();
        this.player2.updateUnits();
    }

    /**
     * creates the buildings and the obstacles, the whole map. Add the buildings to
     * its player
     * create the castles and barracks (upper half of the map: player1's territory
     * at the moment of the creation, lower half is player2's)
     */
    public void createMap()  {
        boolean notBlocking = false;
        while (!notBlocking) {
            createCastleAndBarracks(0, ((this.mapSize / 2) - 1), 0, this.mapSize, player1);
            createCastleAndBarracks(((this.mapSize / 2) + 1), this.mapSize, 0, this.mapSize, player2);

            createObstacles();

            createRoad();

            player1.setEnemyCastle(player2.getCastle());
            player2.setEnemyCastle(player1.getCastle());
            notBlocking = notBlocking();
        }
        createMonsters();
    }

    /**
     * creates castles and barracks for a given player, updates the map
     */
    public void createCastleAndBarracks(int minX, int maxX, int minY, int maxY, Player player) {
        Point castlePos, barrack1Pos, barrack2Pos;

        boolean goodCastlePos = false;
        boolean goodBarrack1Pos = false;
        boolean goodBarrack2Pos = false;

        while (!goodCastlePos) {
            castlePos = new Point(getRandomNumber(minX, maxX), getRandomNumber(minY, maxY));
            if (map.getXY(castlePos) == null) {
                CastleMO castle = new CastleMO(castlePos, player);
                player.setCastle(castle);
                this.map.setXY(castlePos, castle);
                goodCastlePos = true;
            }
        }

        while (!goodBarrack1Pos) {
            barrack1Pos = new Point(getRandomNumber(minX, maxX), getRandomNumber(minY, maxY));
            if (map.getXY(barrack1Pos) == null) {
                BarracksMO barracks1 = new BarracksMO(barrack1Pos, player);
                player.setBarrack1(barracks1);
                this.map.setXY(barrack1Pos, barracks1);
                goodBarrack1Pos = true;
            }
        }

        while (!goodBarrack2Pos) {
            barrack2Pos = new Point(getRandomNumber(minX, maxX), getRandomNumber(minY, maxY));
            if (map.getXY(barrack2Pos) == null) {
                BarracksMO barracks2 = new BarracksMO(barrack2Pos, player);
                player.setBarrack2(barracks2);
                this.map.setXY(barrack2Pos, barracks2);
                goodBarrack2Pos = true;
            }
        }
    }

    /**
     * sets the obstacle positions without blocking a castle or a barrack completely
     */
    public void createObstacles() {
        int counter = 0;
        int limit;

        switch (difficulty) {
            case 0:
                limit = mapSize - 2;
                break;
            case 1:
                limit = mapSize;
                break;
            case 2:
                limit = mapSize + 2;
                break;
            default:
                limit = mapSize;
                break;
        }

        while (counter < limit) {
            Point obsPoint = new Point(getRandomNumber(0, mapSize), getRandomNumber(0, mapSize));
            if (isAvailableObstaclePosition(obsPoint)) {

                ObstacleMO newObs;
                if (getRandomNumber(0, 2) == 0) {
                    newObs = new MountainObs(obsPoint);
                } else {
                    newObs = new LakeObs(obsPoint);
                }

                map.setXY(obsPoint, newObs);
                counter++;
            }
        }
    }

    /**
     * - 2 mapobject dont be in 1 place
     * - no index out of bounds
     * - Obstaclas can't be next to eac other (not in x or y)
     * - obstacles can't block 2 sides of a barrack or a castle
     */
    public boolean isAvailableObstaclePosition(Point p) {
        if (this.map.getXY(p) != null) {
            return false;
        }

        if (p.x > this.mapSize || p.x < 0 || p.y > this.mapSize || p.y < 0) {
            return false;
        }

        if (p.x > 0) {
            MapObject above = this.map.getXY((int) p.x - 1, (int) p.y);
            if (above != null && !checkNeighbouringPosition(p, above.getPosition())) {
                return false;
            }
        }

        if (p.x < this.mapSize - 1) {
            MapObject below = this.map.getXY((int) p.x + 1, (int) p.y);
            if (below != null && !checkNeighbouringPosition(p, below.getPosition())) {
                return false;
            }
        }

        if (p.y < this.mapSize - 1) {
            MapObject right = this.map.getXY((int) p.x, (int) p.y + 1);
            if (right != null && !checkNeighbouringPosition(p, right.getPosition())) {
                return false;
            }
        }

        if (p.y > 0) {
            MapObject left = this.map.getXY((int) p.x, (int) p.y - 1);
            if (left != null && !checkNeighbouringPosition(p, left.getPosition())) {
                return false;
            }
        }

        return true;
    }

    /**
     * check if the neighbour position isn't blocked on all sides by the obstacle
     * obstacles can't be neighbours
     * if a side is free, counter is incremented
     * there must be 2 free sides if the building is on the edge of the map,
     * otherwise 3
     */
    public boolean checkNeighbouringPosition(Point point, Point neighbourPoint) {
        MapObject neighbour = this.map.getXY(neighbourPoint);

        if (neighbour instanceof MountainObs || neighbour instanceof LakeObs) {
            return false;
        } else {
            
            int counter = 0;
            if (neighbourPoint.x < this.mapSize - 1 &&
                    this.map.getXY((int) neighbourPoint.x + 1, (int) neighbourPoint.y) == null) {
                counter++;
            }
            if (neighbourPoint.x > 0 &&
                    this.map.getXY((int) neighbourPoint.x - 1, (int) neighbourPoint.y) == null) {
                counter++;
            }
            if (neighbourPoint.y < this.mapSize - 1 &&
                    this.map.getXY((int) neighbourPoint.x, (int) neighbourPoint.y + 1) == null) {
                counter++;
            }
            if (neighbourPoint.y > 0 &&
                    this.map.getXY((int) neighbourPoint.x, (int) neighbourPoint.y - 1) == null) {
                counter++;
            }

            if ((neighbourPoint.x == 0 || neighbourPoint.x == this.mapSize - 1 ||
                    neighbourPoint.y == 0 || neighbourPoint.y == this.mapSize - 1) && counter < 2) {
                return false;
            } else if (counter < 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * creates roads where no buildings or obstacles are found
     */
    public void createRoad() {
        for (int i = 0; i < this.mapSize; i++) {
            for (int j = 0; j < this.mapSize; j++) {
                if (this.map.getXY(i, j) == null) {
                    RoadMO road = new RoadMO(i, j);
                    this.map.setXY(i, j, road);
                }
            }
        }
    }

    /**
     * place a tower in an available position
     * first tries the position and if it's good, places the tower
     * update players money
     */
    public boolean placeTower(Point p, String type) {
        if (!(map.getXY(p) instanceof RoadMO))
            return false;
        for (int i = 0; i < this.map.getUnits().size(); i++) {
            if (p.equals(this.map.getUnits().get(i).getPosition())) {
                return false;
            }
        }
        for (int i = 0; i < this.map.getMonsters().size(); i++) {
            if (p.equals(this.map.getMonsters().get(i).getPosition())) {
                return false;
            }
        }

        if (actualPlayer == player1) {
            if (p.x > mapSize / 2) {
                return false;
            }
        } else {
            if (p.x < mapSize / 2) {
                return false;
            }
        }
        map.setXY(p, new ObstacleMO(p));

        boolean notblocked = notBlocking();

        map.setXY(p, new RoadMO(p));
        if (notblocked) {
            TowerMO tower = null;
            if (type.equals("stone")) {
                if (actualPlayer.getMoney() >= StoneTower.getPrice()) {
                    tower = new StoneTower(p, actualPlayer);
                    tower.setArea(getArea(p));
                    actualPlayer.updateMoney(-StoneTower.getPrice());
                    actualPlayer.setTowersAndMines(tower);
                    map.setXY(p, tower);
                } else {
                    return false;
                }
            } else if (type.equals("arrow")) {
                if (actualPlayer.getCastle().getLevel() >= ArrowTower.getLevel()) {
                    if (actualPlayer.getMoney() >= ArrowTower.getPrice()) {
                        tower = new ArrowTower(p, actualPlayer);
                        tower.setArea(getArea(p));
                        actualPlayer.updateMoney(-ArrowTower.getPrice());
                        actualPlayer.setTowersAndMines(tower);
                        map.setXY(p, tower);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (type.equals("cannon")) {
                if (actualPlayer.getCastle().getLevel() >= CannonTower.getLevel()) {
                    if (actualPlayer.getMoney() >= CannonTower.getPrice()) {
                        tower = new CannonTower(p, actualPlayer);
                        tower.setArea(getArea(p));
                        actualPlayer.updateMoney(-CannonTower.getPrice());
                        actualPlayer.setTowersAndMines(tower);
                        map.setXY(p, tower);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Attacks with every tower on the map at once
     * xxx
     * xTx = towers range
     * xxx
     */
    public void attackWithTowers() {
        ArrayList<TowerMO> towers = player1.getAllTowers();
        towers.addAll(player2.getAllTowers());

        for (int i = 0; i < map.getUnits().size(); i++) {
            this.map.getUnits().get(i).setNullTowerDamageCounter();
        }

        for (int i = 0; i < towers.size(); i++) {
            List<Units> attacked = getTowerTargets(towers.get(i));
            for (Units unit : attacked) {
                unit.setHealth(-towers.get(i).getPower());
                unit.IncreaseTowerDamageCounter();
            }
        }
    }

    /**
     * find all the targets of a tower, and set the units towerDamageCounter
     * the towerDamageCounter counts how many towers attack the unit
     */
    public List<Units> getTowerTargets(TowerMO tower) {
        List<Units> attacked = new ArrayList<>();

        for (int i = 0; i < tower.getArea().size(); i++) {
            for (int j = 0; j < map.getUnits().size(); j++) {
                if (this.map.getUnits().get(j).getPosition().equals(tower.getArea().get(i))) {
                    if (tower.getOwner() != this.map.getUnits().get(j).getOwner()) {
                        attacked.add(this.map.getUnits().get(j));
                    }
                }
            }
        }
        return attacked;
    }

    /**
     * place a mine in an available position
     * first tries the position and if it's good, places the mine
     */
    public boolean placeMine(Point p) {
        if (!(map.getXY(p) instanceof RoadMO))
            return false;

        for (int i = 0; i < this.map.getUnits().size(); i++) {
            if (p.equals(this.map.getUnits().get(i).getPosition())) {
                return false;
            }
        }

        if (actualPlayer == player1) {
            if (p.x > mapSize / 2) {
                return false;
            }
        } else {
            if (p.x < mapSize / 2) {
                return false;
            }
        }

        map.setXY(p, new ObstacleMO(p));

        boolean notblocked = notBlocking();

        map.setXY(p, new RoadMO(p));

        if (notblocked) {
            MineMO mine = null;
            if (actualPlayer.getMoney() >= MineMO.getPrice()) {
                mine = new MineMO(p, actualPlayer);
                actualPlayer.updateMoney(-MineMO.getPrice());
                actualPlayer.setTowersAndMines(mine);
                map.setXY(p, mine);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * path cheker function
     * if the units can go from player1 barrack1 and barrack2 to player2 castle and
     * the units can go from player2 barrack1 and barrack2 to player1 castle it
     * returns true
     * 1 enemy barrack1 to castle
     * 2 enemy barrack2 to castle
     * 3 barrack1 to enemy castle
     * 4 barrack2 to enemy castle
     */
    public boolean notBlocking() {
        List<Point> result1 = shortestPath(player1.getBarrack1().getPosition(), player2.getCastle().getPosition());
        List<Point> result2 = shortestPath(player1.getBarrack2().getPosition(), player2.getCastle().getPosition());
        List<Point> result3 = shortestPath(player2.getBarrack1().getPosition(), player1.getCastle().getPosition());
        List<Point> result4 = shortestPath(player2.getBarrack2().getPosition(), player1.getCastle().getPosition());

        if (result1 == null) {
            return false;
        }
        if (result2 == null) {
            return false;
        }
        if (result3 == null) {
            return false;
        }
        if (result4 == null) {
            return false;
        }

        return true;
    }

    /**
     * create units
     */
    public boolean createUnits(Point BarrackPos, String unitName) {
        Units unit = null;
        BarracksMO barracks = null;
        if (BarrackPos.equals(actualPlayer.getBarrack1().getPosition())) {
            barracks = actualPlayer.getBarrack1();
        } else if (BarrackPos.equals(actualPlayer.getBarrack2().getPosition())) {
            barracks = actualPlayer.getBarrack2();
        } else {
            return false; 
        }

        List<Point> path = shortestPath(barracks.getPosition(), actualPlayer.getEnemyCastle().getPosition());
        Point startPoint = path.get(1);

        if (unitName.equals("warrior")) {
            if (actualPlayer.getMoney() >= WarriorUnit.getPrice()) {
                unit = new WarriorUnit(startPoint, actualPlayer);
                actualPlayer.updateMoney(-WarriorUnit.getPrice());
            } else {
                return false;
            }
        } else if (unitName.equals("cavalry")) {
            if (actualPlayer.getMoney() >= CavalryUnit.getPrice()) {
                unit = new CavalryUnit(startPoint, actualPlayer);
                actualPlayer.updateMoney(-CavalryUnit.getPrice());
            } else {
                return false;
            }
        } else if (unitName.equals("tank")) {
            if (actualPlayer.getMoney() >= TankUnit.getPrice()) {
                unit = new TankUnit(startPoint, actualPlayer);
                actualPlayer.updateMoney(-TankUnit.getPrice());
            } else {
                return false;
            }
        }

        unit.setShortestPath(path);
        actualPlayer.addUnits(unit);
        this.map.addUnits(unit);
        return true;
    }

    /**
     * if the unit is next to the target position
     * (the target is the castle, but the unit only will be next to castle, not
     * iside it, so thats's why -1)
     * else calculates shortest path again, and steps to the new shortest path
     * second element, which is 1 step
     */
    public void moveAndAttackWithUnits() {
        for (int i = 0; i < this.map.getUnits().size(); i++) {
            Point unitPos = this.map.getUnits().get(i).getPosition();
            Point nextToCastle = this.map.getUnits().get(i).getShortestPath()
                    .get(this.map.getUnits().get(i).getShortestPathSize() - 2);
            if (unitPos.equals(nextToCastle)) {
                if (this.map.getUnits().get(i).getOwner() == player1) {
                    player1.getEnemyCastle().setHealth(-this.map.getUnits().get(i).getAttackPower());
                } else {
                    player2.getEnemyCastle().setHealth(-this.map.getUnits().get(i).getAttackPower());
                }
            } else {
                List<Point> newShortestPath = null;
                if (this.map.getUnits().get(i).getOwner() == player1) {
                    newShortestPath = shortestPath(this.map.getUnits().get(i).getPosition(),
                            player1.getEnemyCastle().getPosition());
                } else {
                    newShortestPath = shortestPath(this.map.getUnits().get(i).getPosition(),
                            player2.getEnemyCastle().getPosition());
                }
                this.map.getUnits().get(i).setPosition(newShortestPath.get(1));
                this.map.getUnits().get(i).setShortestPath(newShortestPath);
            }
        }
    }

    /**
     * create nodes for the shortest path algorythm
     * we create nodes to every cell, even to the obstacles, because the shortest
     * path, uses the is's.
     * Without this step, the algorythm would break. We dont add branches to the
     * obstacles, so there is no case,
     * that a unit would step on that
     *  Cases:
     * - first row, and last row: only 3 way checking
     * - first column and last column: only 3 way checking
     * - 4 corners: only 2 way checking
     * - all the other cells: 4 way checking
     */
    public List<Point> shortestPath(Point head, Point target) {
        Node[][] nodeList = new Node[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Point point = new Point(i, j);
                nodeList[i][j] = new Node(mapSize - 1 - i, point);
                if (head.x == i && head.y == j) {
                    nodeList[i][j].g = 0;
                }
            }
        }

        BiConsumer<Node, Node> branchAdderRoad = (n1, n2) -> {
            if (map.getXY(n2.id.x, n2.id.y) instanceof RoadMO) {
                n1.addBranch(n2);
            }
        };

        BiConsumer<Node, Node> branchAdderHeadTarget = (n1, n2) -> {
            if (map.getXY(n2.id.x, n2.id.y) instanceof RoadMO) {
                n1.addBranch(n2);
                n2.addBranch(n1);
            }
        };

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                BiConsumer<Node, Node> branchAdder;
                if ((head.x == i && head.y == j) || (target.x == i && target.y == j)) {
                    branchAdder = branchAdderHeadTarget;
                } else {
                    branchAdder = branchAdderRoad;
                }
                /**
                 * 4 corners
                 *
                 * top left
                 */
                if (i == 0 && j == 0) {
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                    /**
                     * top right
                     */
                } else if (i == 0 && j == mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    /**
                     * bottom left
                     */
                } else if (i == mapSize - 1 && j == 0) {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                    /**
                     * bottom right
                     */
                } else if (i == mapSize - 1 && j == mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    /**
                     * first row
                     */
                } else if (i == 0 && j != 0 && j != mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    /**
                     * last row
                     */
                } else if (i == mapSize - 1 && j != 0 && j != mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    /**
                     * first column
                     */
                } else if (j == 0 && i != 0 && i != mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                    /**
                     * last column
                     */
                } else if (j == mapSize - 1 && i != 0 && i != mapSize - 1) {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    /**
                     * rest of the matrix (middle)
                     */
                } else {
                    branchAdder.accept(nodeList[i][j], nodeList[i - 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i + 1][j]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j - 1]);
                    branchAdder.accept(nodeList[i][j], nodeList[i][j + 1]);
                }
            }
        }

        Node res = Node.aStar(nodeList[head.x][head.y], nodeList[target.x][target.y]);
        return Node.getPath(res);
    }

    /**
     * FEATURES
     */

    /**
     * creating a monster
     * the monster(s) is/are going between the 2 castles on the shortest path
     * they will spawned randomly on this path on the map making
     * 0 difficulty 0 monster
     */
    public void createMonsters() {
        if (this.difficulty == 0) {
            return;
        }
        if (this.difficulty == 1) {
            /**
             * create 1 monster
             */
            initMonster(player1.getCastle().getPosition(), player2.getCastle().getPosition());
        }
        if (this.difficulty == 2) {
            /**
             * create 2 monsters
             */
            initMonster(player1.getCastle().getPosition(), player2.getCastle().getPosition());
            initMonster(player2.getCastle().getPosition(), player1.getCastle().getPosition());
        }
    }

    /**
     * setting monster path
     */
    public void initMonster(Point head, Point target) {
        List<Point> monsterPath = shortestPath(head, target);
        int randomStartingPosition = getRandomNumber(2, monsterPath.size() - 3);
        Monster monster = new Monster(monsterPath.get(randomStartingPosition), monsterPath.get(0),
                monsterPath.get(monsterPath.size() - 1));
        monster.setPath(monsterPath);
        this.map.addMonster(monster);
    }

    /**
     * Move and attack with all monsters
     */
    public void moveAndAttackWithAllMonsters() {
        if (this.difficulty == 0) {
            return;
        }

        if (this.difficulty == 1) {
            moveAndAttackWithMonster(0);
        }

        if (this.difficulty == 2) {
            moveAndAttackWithMonster(0);
            moveAndAttackWithMonster(1);
        }

        removeDeadUnits();
    }

    /**
     * move 1 monster
     */
    public void moveAndAttackWithMonster(int ind) {
        Monster monster = this.map.getMonsters().get(ind);

        if (monster.getPosition().equals(monster.getPath().get(monster.getPath().size() - 2))) {
            monster.switchTarget();
        }

        List<Point> monsterPath = shortestPath(monster.getPosition(), monster.getTarget1());
        monster.setPath(monsterPath);
        monster.setPosition(monsterPath.get(1));

        for (int i = 0; i < this.map.getUnits().size(); i++) {
            Units unit = this.map.getUnits().get(i);
            if (AreThereUnitsNeraby(monster, unit.getPosition())) {
                unit.setHealth(-monster.getPower());
            }
        }
    }

    /**
     * if on the position where the monster stands, or next to it
     * (up, down, left, right) it returns true, else false
     */
    public boolean AreThereUnitsNeraby(Monster monster, Point unitPos) {
        if (unitPos == monster.getPosition()) {
            return true;
        }
        if (unitPos.x == monster.getPosition().x + 1 && unitPos.y == monster.getPosition().y) {
            return true;
        }
        if (unitPos.x == monster.getPosition().x - 1 && unitPos.y == monster.getPosition().y) {
            return true;
        }
        if (unitPos.x == monster.getPosition().x && unitPos.y == monster.getPosition().y + 1) {
            return true;
        }
        if (unitPos.x == monster.getPosition().x - 1 && unitPos.y == monster.getPosition().y - 1) {
            return true;
        }
        return false;
    }

    /**
     * Spells
     */
    public List<Point> getArea(Point center) {
        List<Point> area = new ArrayList<>();
        area.add(center);
        area.add(new Point(center.x - 1, center.y - 1));
        area.add(new Point(center.x - 1, center.y));
        area.add(new Point(center.x - 1, center.y + 1));
        area.add(new Point(center.x, center.y - 1));
        area.add(new Point(center.x, center.y + 1));
        area.add(new Point(center.x + 1, center.y - 1));
        area.add(new Point(center.x + 1, center.y));
        area.add(new Point(center.x + 1, center.y + 1));

        List<Point> goodArea = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Point p = area.get(i);
            if (p.x >= 0 && p.y >= 0 && p.x < mapSize && p.y < mapSize) {
                goodArea.add(area.get(i));
            }
        }

        return goodArea;
    }

    /**
     * damages the enemy units in a 3x3 area 
     */
    public boolean placeArrowSpell(Point pos){
        Player player = actualPlayer == player1 ? player2 : player1;
        ArrowSpell spell = new ArrowSpell();
        if (actualPlayer.getMoney() >= ArrowSpell.getPrice()) {
            List<Point> area = getArea(pos);
            actualPlayer.updateMoney(-ArrowSpell.getPrice());
            for (int i = 0; i < area.size(); i++) {
                Point p = area.get(i);
                List<Units> units = player.getUnits();
                for (int j = 0; j < units.size(); j++) {
                    Units unit = units.get(j);
                    if (p.x == unit.getPosition().x && p.y == unit.getPosition().y) {
                        unit.setHealth(spell.getPower());
                    }
                }
            }
            removeDeadUnits();
        } else {
            return false;
        }
        return true;
    }

    /**
     * heals player's units in a 3x3 area
     */
    public boolean placeHealingSpell(Point pos) {
        HealingSpell spell = new HealingSpell();
        if (actualPlayer.getMoney() >= HealingSpell.getPrice()) {
            List<Point> area = getArea(pos);
            actualPlayer.updateMoney(-HealingSpell.getPrice());
            for (int i = 0; i < area.size(); i++) {
                Point p = area.get(i);
                List<Units> units = actualPlayer.getUnits();
                for (int j = 0; j < units.size(); j++) {
                    Units unit = units.get(j);
                    if (p.x == unit.getPosition().x && p.y == unit.getPosition().y) {
                        unit.setHealth(spell.getPower());
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * damages enemy units in a given position
     */
    public boolean placeFireballSpell(Point pos){
        Player player = actualPlayer == player1 ? player2 : player1;
        FireballSpell spell = new FireballSpell();
        if (actualPlayer.getMoney() >= FireballSpell.getPrice()) {
                List<Units> units = player.getUnits();
                actualPlayer.updateMoney(-FireballSpell.getPrice());
                for (int i = 0; i < units.size(); i++) {
                    Units unit = units.get(i);
                    if (pos.x == unit.getPosition().x && pos.y == unit.getPosition().y) {
                        unit.setHealth(spell.getPower());
                    }
                }
                removeDeadUnits();
        } else {
            return false;
        }
        return true;
    }

    /**
     * Castle upgrade
     */
    public void upgradeCastle() {
        if (actualPlayer.getMoney() >= actualPlayer.getCastle().getUpgradePrice() && actualPlayer.getCastle().getLevel() < 2) {
            actualPlayer.updateMoney(-actualPlayer.getCastle().getUpgradePrice());
            actualPlayer.getCastle().incrementLevel();
        } else {
            return;
        }

    }

    /**
     * returns a string about what is on the clicked position
     * (units/castle/tower/mine/monster)
     */
    public String listClickedPosition(Point pos) {
        String list = null;
        int p1Cnt = 0;
        int p2Cnt = 0;
        int monsterCnt = 0;
        if (pos.equals(this.player1.getCastle().getPosition())) {
            return "Player1 kastélya.";
        }
        if (pos.equals(this.player2.getCastle().getPosition())) {
            return "Player2 kastélya";
        }
        if (pos.equals(this.player1.getBarrack1().getPosition())) {
            return "Player1 első barakkja.";
        }
        if (pos.equals(this.player2.getBarrack1().getPosition())) {
            return "Player2 első barakkja.";
        }
        if (pos.equals(this.player1.getBarrack2().getPosition())) {
            return "Player1 második barakkja.";
        }
        if (pos.equals(this.player2.getBarrack2().getPosition())) {
            return "Player2 második barakkja.";
        }
        for (MineMO mine : this.player1.getAllMines()) {
            if (pos.equals(mine.getPosition())) {
                return "Player1 bányája.";
            }
        }
        for (MineMO mine : this.player2.getAllMines()) {
            if (pos.equals(mine.getPosition())) {
                return "Player2 bányája";
            }
        }
        for (TowerMO tower : this.player1.getAllTowers()) {
            if (pos.equals(tower.getPosition())) {
                return "Player1 tornya.";
            }
        }
        for (TowerMO tower : this.player2.getAllTowers()) {
            if (pos.equals(tower.getPosition())) {
                return "Player2 tornya.";
            }
        }

        for (Units unit : this.player1.getUnits()) {
            if (pos.equals(unit.getPosition())) {
                p1Cnt++;
            }
        }
        for (Units unit : this.player2.getUnits()) {
            if (pos.equals(unit.getPosition())) {
                p2Cnt++;
            }
        }
        for (Monster monster : this.map.getMonsters()) {
            if (pos.equals(monster.getPosition())) {
                monsterCnt++;
            }
        }

        if (map.getXY(pos) instanceof MountainObs || map.getXY(pos) instanceof LakeObs) {
            return "Ezen a mezőn egy terepakadály van.";
        }

        if (monsterCnt == 0 && p1Cnt == 0 && p2Cnt == 0) {
            return "Ez a mező üres.";
        }

        list = "A mezőn van ";
        if (monsterCnt > 0) {
            list += monsterCnt + " szörny";
        }
        if (p1Cnt > 0) {
            if (monsterCnt == 0) {
                list += p1Cnt + " katona Player1-től";
            } else {
                list += ", " + p1Cnt + " katona Player1-től";
            }
        }
        if (p2Cnt > 0) {
            if (monsterCnt == 0 && p1Cnt == 0) {
                list += p2Cnt + " katona Player2-től";
            } else {
                list += ", " + p2Cnt + " katona Player2-től";
            }
        }
        list += ".";

        return list;
    }

    /**
     * returns the map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * returns the actual player
     */
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    /**
     * returns player1
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * returns player2
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * returns the winner, the winner is null until
     * player1 or player2 wins the game
     */
    public Player getWinner() {
        return this.winner;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    /**
     * generates a random number between min and max
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * print the map out like a matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.mapSize; i++) {
            for (int j = 0; j < this.mapSize; j++) {
                if (this.map.getXY(i, j) == null) {
                    sb.append(" null");
                }
                if (this.map.getXY(i, j) instanceof CastleMO) {
                    if (((CastleMO) this.map.getXY(i, j)).getOwner().getName() == "player1") {
                        sb.append(" p1ca");
                    } else {
                        sb.append(" p2ca");
                    }
                }
                if (this.map.getXY(i, j) instanceof BarracksMO) {
                    if (((BarracksMO) this.map.getXY(i, j)).getOwner().getName() == "player1") {
                        sb.append(" p1ba");
                    } else {
                        sb.append(" p2ba");
                    }
                }
                if (this.map.getXY(i, j) instanceof MountainObs) {
                    sb.append(" moun");
                }
                if (this.map.getXY(i, j) instanceof LakeObs) {
                    sb.append(" lake");
                }
                if (this.map.getXY(i, j) instanceof RoadMO) {
                    sb.append(" ____");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
