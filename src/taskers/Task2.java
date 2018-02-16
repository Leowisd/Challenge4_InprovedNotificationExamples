package taskers;

import java.util.ArrayList;
import javafx.application.Platform;

public class Task2 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    
    private ArrayList<Notification> notifications = new ArrayList<>();
    
    public Task2(int maxValue, int notifyEvery)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
    }
    
    @Override
    public void run() {
        doNotify("Started Task2!");
        
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task2: " + i);
            }
            
            if (exit) {
                return;
            }
        }
        doNotify("Task2 done.");
    }
    
    public void end() {
        exit = true;
    }
    
  
    public void setOnNotification(Notification notification) {
        this.notifications.add(notification);
    }
    
    private void doNotify(String message) {  
        for (Notification notification : notifications) {
            Platform.runLater(() -> {
                notification.handle(message);
            });
        }
    }
}