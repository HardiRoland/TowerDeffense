package model;

import java.awt.Point;

public abstract class MapObject {
    private Point position;
    private boolean isRoad;
    
    public MapObject(Point p) {
        this.position = p;
        this.isRoad = false;
    }

    public MapObject(int x, int y) {
        this.position = new Point(x, y);
        this.isRoad = false;
    }

    public Point getPosition() {
        return this.position;
    }

    public boolean isRoad() {
        return this.isRoad;
    }

    public void setRoad(boolean bool) {
        this.isRoad = bool;
    }
}
