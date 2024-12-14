package utils;

public class PositionNotFoundException extends Exception {
    private final String message;

    public PositionNotFoundException() {
        this.message = "No initial position found";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
