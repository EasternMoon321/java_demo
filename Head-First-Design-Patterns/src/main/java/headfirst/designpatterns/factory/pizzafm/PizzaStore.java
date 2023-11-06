package headfirst.designpatterns.factory.pizzafm;

public abstract class PizzaStore {

	/**
	 * 并非子类在运行时决定实例化（那个子类），而是编写时不知道决定使用那个子类。
	 * 子类实例化在使用工厂方式时已经决定。
	 * @param item
	 * @return
	 */
	abstract Pizza createPizza(String item);
 
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
