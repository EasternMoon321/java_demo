package headfirst.demo.pattern.game;

/**
 * 特征
 */
public abstract class Character {
    protected WeaponBehavior weapon;

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }

    public abstract void fight();

}
