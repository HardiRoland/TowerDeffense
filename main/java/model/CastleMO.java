package model;

import java.awt.Point;

public class CastleMO extends MapObject {
    private int health, level;
    private Player owner;
    private int bonusMoney, upgradePrice;
    
    public CastleMO(Point p, Player owner) {
        super(p);
        this.health = 1000;
        this.level = 0;
        this.owner = owner;
        this.bonusMoney = 100;
        this.upgradePrice = 600;
    }

    public CastleMO(int x, int y, Player owner) {
        super(x, y);
        this.health = 1000;
        this.level = 0;
        this.owner = owner;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int attack) {
        this.health += attack;
    }

    /*public void incrementHealth() {
        this.health += 250;
    }*/

    public int getLevel() {
        return this.level;
    }

    public void incrementLevel() {
        this.level++;
        this.bonusMoney += 100;
        this.health += 250;
    }

    public Player getOwner() {
        return this.owner;
    }

    public int getBonusMoney() {
        return this.bonusMoney;
    }

    public int getUpgradePrice() {
        return upgradePrice + this.level*200;
    }

}
