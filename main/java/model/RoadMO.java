package model;

import java.awt.Point;

public class RoadMO extends MapObject {
    public RoadMO(Point p) {
        super(p);
        setRoad(true);
    }

    public RoadMO(int x, int y) {
        super(x, y);
        setRoad(true);
    }
}
