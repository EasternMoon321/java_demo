package headfirst.demo.factory.pizza;

public class Application {
    public static void main(String[] args) {
        Pizza pizza = SimplePizzaFactory.createPizza("cheese");


        /*NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");*/
    }
}
