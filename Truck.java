import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//Superinterface Serializable for the type Truck, already defined by VehicleJava, so no need to implement it again
public class Truck extends Vehicle {
    private double loadCapacity;
    private PropertyChangeSupport changeSupport;

    public Truck() {
        super();
        this.loadCapacity = 0.0;
        changeSupport = new PropertyChangeSupport(this);
    }

    // Setter for loadCapacity
    public synchronized void setLoadCapacity(double val) {
        double oldLoadCapacity = loadCapacity;
        loadCapacity = val;
        changeSupport.firePropertyChange("loadCapacity", oldLoadCapacity, loadCapacity);
    }

    // Getter for loadCapacity
    public synchronized double getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String getDescription() {
        String vehicleDescription = super.getDescription();
        return vehicleDescription + "\nLoad Capacity: " + loadCapacity + " tons";
    }

    // Add property change listeners
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    // Remove property change listeners
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}