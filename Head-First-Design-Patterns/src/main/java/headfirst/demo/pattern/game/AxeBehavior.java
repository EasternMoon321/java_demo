package headfirst.demo.pattern.game;

/**
 * 斧头
 */
public class AxeBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use axe");
    }
}
