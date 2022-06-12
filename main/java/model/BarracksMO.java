package model;

import java.awt.Point;

public class BarracksMO extends MapObject {
    private Player owner;
    public BarracksMO(Point p, Player owner) {
        super(p);
        this.owner = owner;
    }

    public BarracksMO(int x, int y, Player owner) {
        super(x, y);
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }
}
