package model;

public class HealingSpell extends Spells {

    public HealingSpell() {
        super(30, 3);
    }

    public static int getPrice() {
        return 500;
    }
}
