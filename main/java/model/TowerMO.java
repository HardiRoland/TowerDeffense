package model;

import java.awt.Point;
import java.util.List;

public abstract class TowerMO extends MapObject {
    private int power;
    private Player owner;
    private List<Point> area;

    public TowerMO(Point p, int power, Player owner) {
        super(p);
        this.power = power;
        this.owner = owner;
    }

    public TowerMO(int x, int y, int power, Player owner) {
        super(x, y);
        this.power = power;
        this.owner = owner;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Player getOwner() {
        return this.owner;
    }

    public List<Point> getArea() {
        return area;
    }

    public void setArea(List<Point> area) {
        this.area = area;
    }


}
