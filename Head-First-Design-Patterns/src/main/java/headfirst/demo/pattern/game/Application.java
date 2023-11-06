package headfirst.demo.pattern.game;

public class Application {
    public static void main(String[] args) {
        King king = new King();
        king.setWeapon(new AxeBehavior());
        king.fight();

        king.setWeapon(new BowAndArrowBehavior());
        king.fight();

        Queen queen = new Queen();
        queen.setWeapon(new SwordBehavior());
        queen.fight();
        queen.setWeapon(new KnifeBehavior());
        queen.fight();

    }
}
