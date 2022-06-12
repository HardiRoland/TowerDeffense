package model;

public class FireballSpell extends Spells {

    public FireballSpell() {
        super(-35, 1);
    }

    public static int getPrice() {
        return 400;
    }
}
