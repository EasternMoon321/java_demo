package headfirst.demo.decorator.starbuzz;

/**
 * 深烘咖啡
 */
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast";
    }
    @Override
    public double cost() {
        return 2.99;
    }
}
