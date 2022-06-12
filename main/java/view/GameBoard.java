package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import model.*;

public class GameBoard extends JPanel {

    private int tileInRow;
    private Model gameModel;
    private int mapSize;
    private int arrowSpellOn = 0;
    private int fireBallSpellOn = 0;
    private int healingSpellOn = 0;
    private Point spellPos = new Point(0,0);
    private static Timer timer = new Timer();
    private int spellLength = 1000;

    /**
     * Látrehozza a játékmező kinézetét, felrazol rá minden és a
     * játékos cselekedetei alapján frissül a kinézete.
     * @param difficulty Játék nehézsége.
     * @param tileInRow Mezők száma egy sorban.
     * @param mapSize Pálya mérete.
     */
    public GameBoard(int difficulty, int tileInRow, int mapSize) {
        this.mapSize = mapSize;
        this.tileInRow = tileInRow;

        gameModel = new Model(difficulty, tileInRow);
        gameModel.createMap();
    }

    /**
     * Ez rajzolja ki a hátteret, tornyokat, akadályokat,
     * seregeket, szörnyeket, spelleket és a sebzéseket
     * mindkét játékosnak. A függvény meghívásával tudjuk 
     * frissíteni a játékmező kinézetét.
     * @param g JPanel grafikája.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        String player1Name = "player1";
        int tileSize = mapSize / tileInRow;
        Graphics2D g2 = (Graphics2D) g;
        Image img;

        g2.drawImage(LoadImage("/img/background.png", mapSize, mapSize), 0, 0, mapSize, mapSize, null);

        Map map = gameModel.getMap();
        for (int i = 0; i < map.getMapSize(); i++) {
            for (int j = 0; j < map.getMapSize(); j++) {
                img = null;
                if(map.getXY(i, j) instanceof CastleMO) {
                    if(((CastleMO)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_castle.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_castle.png", tileSize, tileSize);
                    }
                }

                if(map.getXY(i, j) instanceof BarracksMO) {
                    if(((BarracksMO)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_barrack.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_barrack.png", tileSize, tileSize);
                    }
                }

                if(map.getXY(i, j) instanceof MineMO) {
                    if(((MineMO)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_mine.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_mine.png", tileSize, tileSize);
                    }
                }
                
                if(map.getXY(i, j) instanceof StoneTower) {
                    if(((StoneTower)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_stoneTower.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_stoneTower.png", tileSize, tileSize);
                    }
                }

                if(map.getXY(i, j) instanceof ArrowTower) {
                    if(((ArrowTower)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_arrowTower.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_arrowTower.png", tileSize, tileSize);
                    }
                }

                if(map.getXY(i, j) instanceof CannonTower) {
                    if(((CannonTower)map.getXY(i, j)).getOwner().getName().equals(player1Name)) {
                        img = LoadImage("/img/p1_cannonTower.png", tileSize, tileSize);
                    } else {
                        img = LoadImage("/img/p2_cannonTower.png", tileSize, tileSize);
                    }
                }

                if(map.getXY(i, j) instanceof MountainObs) {
                    img = LoadImage("/img/mountain.png", tileSize, tileSize);
                }

                if(map.getXY(i, j) instanceof LakeObs) {
                    img = LoadImage("/img/lake.png", tileSize, tileSize);
                }

                
                int player1UnitHitCounter;
                String player1HitColor = "0";
                int player2UnitHitCounter;
                String player2HitColor = "0";
                for(int k = 0; k < gameModel.getMap().getUnits().size(); k++) {
                    if(gameModel.getMap().getUnits().get(k).getPosition().equals(new Point(i, j))) {
                        if(gameModel.getMap().getUnits().get(k).getOwner().getName().equals(player1Name)) {
                            player1UnitHitCounter = gameModel.getMap().getUnits().get(k).getTowerDamageCounter();
                            if(player1UnitHitCounter == 0) {
                                player1HitColor = "0";
                            } else if(player1UnitHitCounter < 3) {
                                player1HitColor = "1";
                            } else if(player1UnitHitCounter < 5) {
                                player1HitColor = "2";
                            } else {
                                player1HitColor = "3";
                            }
                        } else {
                            player2UnitHitCounter = gameModel.getMap().getUnits().get(k).getTowerDamageCounter();
                            if(player2UnitHitCounter == 0) {
                                player2HitColor = "0";
                            } else if(player2UnitHitCounter < 3) {
                                player2HitColor = "1";
                            } else if(player2UnitHitCounter < 5) {
                                player2HitColor = "2";
                            } else {
                                player2HitColor = "3";
                            }
                        }
                    }
                }
                switch (player1HitColor + player2HitColor) {
                    case "01":
                        img = LoadImage("/img/damageToUnit/damage01.png", tileSize, tileSize);
                        break;
                    case "02":
                        img = LoadImage("/img/damageToUnit/damage02.png", tileSize, tileSize);
                        break;
                    case "03":
                        img = LoadImage("/img/damageToUnit/damage03.png", tileSize, tileSize);
                        break;
                    case "10":
                        img = LoadImage("/img/damageToUnit/damage10.png", tileSize, tileSize);
                        break;
                    case "11":
                        img = LoadImage("/img/damageToUnit/damage11.png", tileSize, tileSize);
                        break;
                    case "12":
                        img = LoadImage("/img/damageToUnit/damage12.png", tileSize, tileSize);
                        break;
                    case "13":
                        img = LoadImage("/img/damageToUnit/damage13.png", tileSize, tileSize);
                        break;
                    case "20":
                        img = LoadImage("/img/damageToUnit/damage20.png", tileSize, tileSize);
                        break;
                    case "21":
                        img = LoadImage("/img/damageToUnit/damage21.png", tileSize, tileSize);
                        break;
                    case "22":
                        img = LoadImage("/img/damageToUnit/damage22.png", tileSize, tileSize);
                        break;
                    case "23":
                        img = LoadImage("/img/damageToUnit/damage23.png", tileSize, tileSize);
                        break;
                    case "30":
                        img = LoadImage("/img/damageToUnit/damage30.png", tileSize, tileSize);
                        break;
                    case "31":
                        img = LoadImage("/img/damageToUnit/damage31.png", tileSize, tileSize);
                        break;
                    case "32":
                        img = LoadImage("/img/damageToUnit/damage32.png", tileSize, tileSize);
                        break;
                    case "33":
                        img = LoadImage("/img/damageToUnit/damage33.png", tileSize, tileSize);
                        break;
                
                    default:
                        break;
                }
                
                g2.drawImage(img, j * tileSize, i * tileSize, tileSize, tileSize, null);
            }
        }

        for(int i = 0; i < gameModel.getMap().getUnits().size(); i++) {
            img = null;
            if(gameModel.getMap().getUnits().get(i) instanceof WarriorUnit) {
                if(gameModel.getMap().getUnits().get(i).getOwner().getName().equals(player1Name)) {
                    img = LoadImage("/img/p1_warriorUnit.png", tileSize, tileSize);
                } else {
                    img = LoadImage("/img/p2_warriorUnit.png", tileSize, tileSize);
                }
            }

            if(gameModel.getMap().getUnits().get(i) instanceof CavalryUnit) {
                if(gameModel.getMap().getUnits().get(i).getOwner().getName().equals(player1Name)) {
                    img = LoadImage("/img/p1_cavalryUnit.png", tileSize, tileSize);
                } else {
                    img = LoadImage("/img/p2_cavalryUnit.png", tileSize, tileSize);
                }
            }

            if(gameModel.getMap().getUnits().get(i) instanceof TankUnit) {
                if(gameModel.getMap().getUnits().get(i).getOwner().getName().equals(player1Name)) {
                    img = LoadImage("/img/p1_tankUnit.png", tileSize, tileSize);
                } else {
                    img = LoadImage("/img/p2_tankUnit.png", tileSize, tileSize);
                }
            }

            g2.drawImage(img, gameModel.getMap().getUnits().get(i).getPosition().y * tileSize, gameModel.getMap().getUnits().get(i).getPosition().x * tileSize, tileSize, tileSize, null);
        }

        for(int i = 0; i < gameModel.getMap().getMonsters().size(); i++) {
            img = LoadImage("/img/monster.png", tileSize, tileSize);

            g2.drawImage(img, gameModel.getMap().getMonsters().get(i).getPosition().y * tileSize, gameModel.getMap().getMonsters().get(i).getPosition().x * tileSize, tileSize, tileSize, null);
        }

        if(arrowSpellOn == 1) {
            img = LoadImage("/img/arrowSpellAnimation.png", tileSize, tileSize);
            for(int i = spellPos.y - 1; i <= spellPos.y + 1; i++) {
                for(int j = spellPos.x - 1; j <= spellPos.x + 1; j++) {
                    if (i >= 0 && j >= 0 && i < mapSize && j < mapSize) {
                        g2.drawImage(img, i * tileSize, j * tileSize, tileSize, tileSize, null);
                    }
                }
            }
        }

        if(fireBallSpellOn == 1) {
            img = LoadImage("/img/fireBallSpellAnimation.png", tileSize, tileSize);
            g2.drawImage(img, (int)spellPos.y * tileSize, (int)spellPos.x * tileSize, tileSize, tileSize, null);
        }

        if(healingSpellOn == 1) {
            img = LoadImage("/img/healingSpellAnimation.png", tileSize, tileSize);
            for(int i = spellPos.y - 1; i <= spellPos.y + 1; i++) {
                for(int j = spellPos.x - 1; j <= spellPos.x + 1; j++) {
                    if (i >= 0 && j >= 0 && i < mapSize && j < mapSize) {
                        g2.drawImage(img, i * tileSize, j * tileSize, tileSize, tileSize, null);
                    }
                }
            }
        }
    }

    /**
     * Betölti a képet egy megadott fájlnévvel, egy x és y méret alapján
     * és ezt adja vissza.Ha nem sikerül hibaüzenetet ír ki és null értékkel.
     * tér vissza.
     * @param filename Kép elérésének útvonala.
     * @param sizeX A kép szélessége.
     * @param sizeY A kép magassága.
     * @return A képet adja vissza vagy hiba esetén null értéket.
     */
    private Image LoadImage(String filename, int sizeX, int sizeY) {
        try {
            return ImageIO.read(getClass().getResource(filename)).getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Leanimálja a nyílzápor varázsige használatát egy időzítő segítségével.
     * @param pos A varázsige pozíciója.
     */
    public void castArrowSpell(Point pos) {
        arrowSpellOn = 1;
        spellPos = pos;
        repaint();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                arrowSpellOn = 0;
                repaint();
                cancel();
            }
        }, spellLength);
    }

    /**
     * Leanimálja a tűzgolyó varázsige használatát egy időzítő segítségével.
     * @param pos A varázsige pozíciója.
     */
    public void castFireBallSpell(Point pos) {
        fireBallSpellOn = 1;
        spellPos = pos;
        repaint();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fireBallSpellOn = 0;
                repaint();
                cancel();
            }
        }, spellLength);
    }

    /**
     * Leanimálja a gyógyítás varázsige használatát egy időzítő segítségével.
     * @param pos A varázsige pozíciója.
     */
    public void castHealingSpell(Point pos) {
        healingSpellOn = 1;
        spellPos = pos;
        repaint();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                healingSpellOn = 0;
                repaint();
                cancel();
            }
        }, spellLength);
    }

    /**
     * Végleg leállítja az időzítőt hogy be lehessen zárni a programot.
     */
    public static void stopTimer() {
        timer.cancel();
    }

    /**
     * Visszaadja a játék modellt.
     */
    public Model getGameModel() {
        return gameModel;
    }
}
