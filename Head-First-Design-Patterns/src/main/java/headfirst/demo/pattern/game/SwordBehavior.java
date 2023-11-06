package headfirst.demo.pattern.game;

/**
 * 宝剑
 */
public class SwordBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use sword");
    }
}
