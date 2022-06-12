package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private CastleMO castle;
    private CastleMO enemyCastle;
    private BarracksMO barrack1;
    private BarracksMO barrack2;
    private ArrayList<Units> units;
    private ArrayList<MapObject> towersAndMines;

    public Player(String name) {
        this.name = name;
        this.money = 500;
        this.units = new ArrayList<>();
        this.towersAndMines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void updateMoney(int money) {
        this.money += money;
    }

    public ArrayList<Units> getUnits() {
        return units;
    }

    public void addUnits(Units unit) {
        this.units.add(unit);
    }

    public void updateUnits() {
        for (int i = 0; i < this.units.size(); i++) {
            if (this.units.get(i).getHealth() <= 0) {
                this.units.remove(i);
            }
        }
    }

    public CastleMO getCastle() {
        return this.castle;
    }

    public void setCastle(CastleMO castle) {
        this.castle = castle;
    }

    public CastleMO getEnemyCastle() {
        return this.enemyCastle;
    }

    public void setEnemyCastle(CastleMO enemyCastle) {
        this.enemyCastle = enemyCastle;
    }

    public BarracksMO getBarrack1() {
        return this.barrack1;
    }

    public void setBarrack1(BarracksMO barrack1) {
        this.barrack1 = barrack1;
    }

    public BarracksMO getBarrack2() {
        return this.barrack2;
    }

    public void setBarrack2(BarracksMO barrack2) {
        this.barrack2 = barrack2;
    }

    public ArrayList<MapObject> getTowersAndMines() {
        return this.towersAndMines;
    }

    public ArrayList<TowerMO> getAllTowers() {
        ArrayList<TowerMO> towers = new ArrayList<>();
        for(int i = 0; i < towersAndMines.size(); i++) {
            if(towersAndMines.get(i) instanceof TowerMO) {
                towers.add((TowerMO)towersAndMines.get(i));
            }
        }
        return towers;
    }

    public ArrayList<MineMO> getAllMines() {
        ArrayList<MineMO> mines = new ArrayList<>();
        for(int i = 0; i < towersAndMines.size(); i++) {
            if(towersAndMines.get(i) instanceof MineMO) {
                mines.add((MineMO)towersAndMines.get(i));
            }
        }
        return mines;
    }

    public ArrayList<MapObject> getAllBuildings() {
        ArrayList<MapObject> buildings = new ArrayList<>();
        buildings = towersAndMines;
        buildings.add(castle);
        buildings.add(barrack1);
        buildings.add(barrack2);
        return buildings;
    }

    public void setTowersAndMines(ArrayList<MapObject> towersAndMines) {
        this.towersAndMines = towersAndMines;
    }

    public void setTowersAndMines(MineMO mine) {
        this.towersAndMines.add(mine);
    }

    public void setTowersAndMines(TowerMO tower) {
        this.towersAndMines.add(tower);
    }

    public void removeBuilding(MapObject mo) {
        this.towersAndMines.remove(mo);
    }
}
