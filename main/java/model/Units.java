package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Units {
    private Point position;
    private int attackPower, health;
    private Player owner;
    private List<Point> shortestPath;
    private int towerDamageCounter;
    
    public Units(Point position, int attackPower, int health, Player owner) {
        this.position = position;
        this.attackPower = attackPower;
        this.health = health;
        this.owner = owner;
        this.shortestPath = new ArrayList<>();
        this.towerDamageCounter = 0;
    }

    public Units(int x, int y, int attackPower, int health, Player owner) {
        this.position = new Point(x, y);
        this.attackPower = attackPower;
        this.health = health;
        this.owner = owner;
        this.shortestPath = new ArrayList<>();
        this.towerDamageCounter = 0;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Player getOwner() {
        return this.owner;
    }

    public List<Point> getShortestPath() {
        return this.shortestPath;
    }

    public void setShortestPath(List<Point> newShortestPath) {
        this.shortestPath = newShortestPath;
    }

    public int getShortestPathSize() {
        return this.shortestPath.size();
    }

    public void printShortestPath() {
        System.out.println("shortest path: ");
        for (int i = 0; i < getShortestPathSize(); i++) {
            System.out.print("(" + shortestPath.get(i).x + ", " + shortestPath.get(i).y + ") ");
        }
        System.out.println();
    }

    public int getTowerDamageCounter() {
        return this.towerDamageCounter;
    }

    public void IncreaseTowerDamageCounter() {
        this.towerDamageCounter++;
    }

    public void setNullTowerDamageCounter() {
        this.towerDamageCounter = 0;
    }
}
