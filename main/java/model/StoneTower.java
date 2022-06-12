package model;

import java.awt.Point;

public class StoneTower extends TowerMO {
    public StoneTower(Point p, Player owner) {        
        super(p, 10, owner);
    }

    public StoneTower(int x, int y, Player owner) {        
        super(x, y, 10, owner);
    }

    public static int getPrice() {
        return 100;
    }

    public static int getLevel() {
        return 0;
    }
}
