package model;

import java.awt.Point;
import java.util.ArrayList;

public class Map {
    private final int mapSize;
    private MapObject[][] objects;
    private ArrayList<Units> units;
    private ArrayList<Monster> monsters;

    public Map(int mapSize) {
        this.mapSize = mapSize;
        objects = new MapObject[mapSize][mapSize];
        units = new ArrayList<>();
        monsters = new ArrayList<>();
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public MapObject getXY(Point p) {
        return this.objects[p.x][p.y];
    }

    public MapObject getXY(int x, int y) {
        return this.objects[x][y];
    }

    public void setXY(Point p, MapObject mo) {
        this.objects[p.x][p.y] = mo;
    }

    public void setXY(int x, int y, MapObject mo) {
        this.objects[x][y] = mo;
    }

    public void addUnits(Units unit) {
        units.add(unit);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void updateUnits() {
        for (int i = 0; i < this.units.size(); i++) {
            if (this.units.get(i).getHealth() <= 0) {
                this.units.remove(i);
            }
        }
    }

    public ArrayList<Units> getUnits() {
        return this.units;
    }

    public Units findUnit(Point p) {
        for (int i = 0; i < this.units.size(); i++) {
            if (units.get(i).getPosition().equals(p)) {
                return units.get(i);
            }
        }
        return null;
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    public Monster findMonster(Point p) {
        for (int i = 0; i < this.monsters.size(); i++) {
            if (monsters.get(i).getPosition().equals(p)) {
                return monsters.get(i);
            }
        }
        return null;
    }
}
