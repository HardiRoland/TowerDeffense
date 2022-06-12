package model;

public abstract class Spells {
    private int power, circle;

    public Spells(int power, int circle) {
        this.power = power;
        /**
         * ? balance circles
         */
        this.circle = circle;
    }
    
    public int getPower() {
        return this.power;
    }

    public int getCircle() {
        return this.circle;
    }
}
