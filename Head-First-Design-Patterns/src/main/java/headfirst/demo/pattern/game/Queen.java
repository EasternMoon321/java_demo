package headfirst.demo.pattern.game;

public class Queen extends Character{
    public void fight() {
        System.out.print("Queen fight: ");
        weapon.useWeapon();
    }
}
