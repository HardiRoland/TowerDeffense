package model;

import java.awt.Point;

public class WarriorUnit extends Units {
    private static int id = 0;

    public WarriorUnit(Point position, Player owner) {
        super(position, 50, 70, owner);
        id++;
    }

    public WarriorUnit(int x, int y, Player owner) {
        super(x, y, 50, 70, owner);
        id++;
    }

    public static int getPrice() {
        return 40;
    }

    public static int getId() {
        return id;
    }
}
