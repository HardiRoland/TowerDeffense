package model;

import java.awt.Point;

public class CannonTower extends TowerMO {

    public CannonTower(Point p, Player owner) {
        super(p, 20, owner);
    }

    public CannonTower(int x, int y, Player owner) {
        super(x, y, 20, owner);
    }

    public static int getPrice() {
        return 300;
    }

    public static int getLevel() {
        return 2;
    }
}
