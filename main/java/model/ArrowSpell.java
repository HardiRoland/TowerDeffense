package model;

public class ArrowSpell extends Spells {

    public ArrowSpell() {
        super(-15, 3);
    }

    public static int getPrice() {
        return 400;
    }
}
