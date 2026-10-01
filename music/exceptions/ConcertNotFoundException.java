package music.exceptions;

// Thrown when a concert ID is not found
public class ConcertNotFoundException extends Exception {
    public ConcertNotFoundException(String id) {
        super("No concert found with ID: " + id);
    }
}