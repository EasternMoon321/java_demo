package headfirst.demo.pattern.game;

/**
 * 弓箭
 */
public class BowAndArrowBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use bow and arrow");
    }
}
