package taskers;

@FunctionalInterface
public interface Notification {
    public void handle(String message);
}