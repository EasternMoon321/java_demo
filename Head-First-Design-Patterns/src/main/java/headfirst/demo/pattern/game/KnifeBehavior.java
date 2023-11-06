package headfirst.demo.pattern.game;

/**
 * 匕首
 */
public class KnifeBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use knife");
    }
}
