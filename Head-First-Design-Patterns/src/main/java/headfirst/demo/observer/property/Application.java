package headfirst.demo.observer.property;

import java.beans.PropertyEditorSupport;

public class Application {
    public static void main(String[] args) {
        PropertyEditorSupport editor = new PropertyEditorSupport();
        editor.addPropertyChangeListener(new ConcretePropertyChangeListener());
        editor.setValue("123");
        String asText = editor.getAsText();
        System.out.println(asText);
    }
}
