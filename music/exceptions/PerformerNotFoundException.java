package music.exceptions;

// Thrown when a performer ID is not found
public class PerformerNotFoundException extends Exception {
    public PerformerNotFoundException(String id) {
        super("No performer found with ID: " + id);
    }
}