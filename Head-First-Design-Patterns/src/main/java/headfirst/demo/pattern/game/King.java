package headfirst.demo.pattern.game;

public class King extends Character {
    public void fight() {
        System.out.print("King fight: ");
        weapon.useWeapon();
    }
}
