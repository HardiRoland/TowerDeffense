package model;

import java.awt.Point;

public class MineMO extends MapObject {
    private Player owner;

    public MineMO(Point p, Player owner) {
        super(p);
        this.owner = owner;
    }

    public MineMO(int x, int y, Player owner) {
        super(x, y);
        this.owner = owner;
    }

    public static int getPrice() {
        return 500;
    }

    public static int getBonusMoney() {
        return 100;
    }

    public Player getOwner() {
        return owner;
    }
}
