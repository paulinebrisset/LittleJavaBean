import java.io.Serializable;
import java.beans.*;

public class MonBean03 implements Serializable {
    protected int valeur;

    PropertyChangeSupport changeSupport;

    public MonBean03() {
        valeur = 0;

        changeSupport = new PropertyChangeSupport(this);
    }

    public synchronized void setValeur(int val) {
        int oldValeur = valeur;
        valeur = val;
        changeSupport.firePropertyChange("valeur", oldValeur, valeur);
    }

    public synchronized int getValeur() {
        return valeur;
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}