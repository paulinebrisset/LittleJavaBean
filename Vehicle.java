import java.io.Serializable;
import java.beans.*;

public class Vehicle implements Serializable {
    private String brand;
    private String model;
    private String color;

    PropertyChangeSupport changeSupport;

    public Vehicle() {
        brand = "Default";
        model = "Default";
        color = "Default";
        changeSupport = new PropertyChangeSupport(this);
    }

    // Setters
    public synchronized void setBrand(String val) {
        String oldBrand = brand;
        brand = val;
        changeSupport.firePropertyChange("brand", oldBrand, brand);
    }

    public synchronized void setColor(String val) {
        String oldColor = color;
        color = val;
        changeSupport.firePropertyChange("color", oldColor, color);
    }

    public synchronized void setModel(String val) {
        String oldModel = model;
        model = val;
        changeSupport.firePropertyChange("model", oldModel, model);
    }

    // Getters
    public synchronized String getBrand() {
        return brand;
    }

    public synchronized String getModel() {
        return model;
    }

    public String getDescription() {
        // Returns String with brand, model, color
        StringBuilder message = new StringBuilder();

        if (!brand.equals("Default")) {
            message.append("\nBrand: ").append(brand);
        }

        if (!model.equals("Default")) {
            message.append("\nModel: ").append(model);
        }

        if (!color.equals("Default")) {
            message.append("\nColor: ").append(color);
        }

        return message.toString();
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
