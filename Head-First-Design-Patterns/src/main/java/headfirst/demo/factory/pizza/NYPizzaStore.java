package headfirst.demo.factory.pizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizze = null;
        if ("cheese".equals(type)) {
            pizze = new NYStyleCheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizze = new NYStylePepperoniPizza();
        } else if ("clam".equals(type)) {
            pizze = new NYStyleClamPizza();
        } else if ("veggie".equals(type)) {
            pizze = new NYStyleVeggiePizza();
        }
        return pizze;
    }
}
