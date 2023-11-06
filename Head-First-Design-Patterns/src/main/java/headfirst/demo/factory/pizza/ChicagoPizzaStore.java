package headfirst.demo.factory.pizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizze = null;
        if ("cheese".equals(type)) {
            pizze = new ChicagoStyleCheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizze = new ChicagoStylePepperoniPizza();
        } else if ("clam".equals(type)) {
            pizze = new ChicagoStyleClamPizza();
        } else if ("veggie".equals(type)) {
            pizze = new ChicagoStyleVeggiePizza();
        }
        return pizze;
    }
}
