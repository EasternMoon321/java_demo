package headfirst.demo.decorator.starbuzz;

/**
 * 摩卡
 */
public class Mocha extends CondimentDecorator {
    /**
     * 持有被装饰者
     */
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
