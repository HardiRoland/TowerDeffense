package model;

import java.awt.Point;

public class CavalryUnit extends Units {
    private static int id = 0;

    public CavalryUnit(Point position, Player owner) {
        super(position, 40, 80, owner);
        id++;
    }

    public CavalryUnit(int x, int y, Player owner) {
        super(x, y, 40, 80, owner);
        id++;
    }

    public static int getPrice() {
        return 50;
    }

    public static int getId() {
        return id;
    }
}
