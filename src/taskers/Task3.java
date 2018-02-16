
package taskers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;

public class Task3 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public Task3(int maxValue, int notifyEvery)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
    }
    
    @Override
    public void run() {
        doNotify("Task3 start.");
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task3: " + i);
            }
            
            if (exit) {
                return;
            }
        }
        doNotify("Task3 done.");
    }
    
    public void end() {
        exit = true;
    }
   
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         pcs.removePropertyChangeListener(listener);
     }
    
    private void doNotify(String message) {      
        Platform.runLater(() -> {         
            pcs.firePropertyChange("message", "", message);
        });
    }
}