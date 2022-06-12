package model;

import java.awt.Point;

public class ArrowTower extends TowerMO {

    public ArrowTower(Point p, Player owner) {
        super(p, 15, owner);
    }

    public ArrowTower(int x, int y, Player owner) {
        super(x, y, 15, owner);
    }

    public static int getPrice() {
        return 200;
    }

    public static int getLevel() {
        return 1;
    }
}
