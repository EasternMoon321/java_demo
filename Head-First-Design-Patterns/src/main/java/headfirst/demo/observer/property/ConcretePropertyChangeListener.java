package headfirst.demo.observer.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConcretePropertyChangeListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt);
    }
}
