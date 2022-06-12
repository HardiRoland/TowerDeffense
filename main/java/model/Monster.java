package model;

import java.awt.Point;
import java.util.List;

public class Monster {
    private Point position;
    private int power;
    private List<Point> path;
    private Point target1, target2;

    public Monster(Point position, Point target1, Point target2) {
        this.position = position;
        this.power = 5;
        this.target1 = target1;
        this.target2 = target2;
    }

    public Monster(int x, int y) {
        this.position = new Point(x, y);
        this.power = 5;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getPower() {
        return this.power;
    }

    public List<Point> getPath() {
        return this.path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public Point getTarget1() {
        return this.target1;
    }

    public void setTarget1(Point target1) {
        this.target1 = target1;
    }

    public Point getTarget2() {
        return this.target2;
    }

    public void setTarget2(Point target2) {
        this.target2 = target2;
    }

    public void switchTarget() {
        Point tmp = this.target1;
        this.target1 = this.target2;
        this.target2 = tmp;
    }
}
