package headfirst.demo.observer.forecast;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
