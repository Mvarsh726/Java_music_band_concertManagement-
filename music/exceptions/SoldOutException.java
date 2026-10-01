package music.exceptions;

// Thrown when tickets are unavailable
public class SoldOutException extends Exception {
    public SoldOutException(String concertId, int requested, int available) {
        super("Concert " + concertId + " — Requested: " + requested
              + " tickets, but only " + available + " seats left!");
    }
}