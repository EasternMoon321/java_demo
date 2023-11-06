package headfirst.demo.pattern.duck;

/**
 * 绿头鸭
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        // 叫的行为委托给quack
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I`am a mallard duck");
    }

}
