package headfirst.demo.factory.pizza;

public class NYStyleCheesePizza extends CheesePizza {

    public NYStyleCheesePizza() {
        // 酱汁和奶酪披萨
        name = "NY Style Sauce and Cheese Pizza";
        // 薄饼
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");
    }
}
