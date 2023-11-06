package headfirst.demo.pattern.duck;

/**
 * 不会叫
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
