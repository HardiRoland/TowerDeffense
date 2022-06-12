package model;

import java.awt.Point;

public class TankUnit extends Units {
    private static int id = 0;

    public TankUnit(Point position, Player owner) {
        super(position, 70, 100, owner);
        id++;
    }
    
    public TankUnit(int x, int y, Player owner) {
        super(x, y, 70, 100, owner);
        id++;
    }

    public static int getPrice() {
        return 70;
    }

    public static int getId() {
        return id;
    }
}
