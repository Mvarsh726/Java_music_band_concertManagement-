package music.models;

// Abstract base class - demonstrates ABSTRACTION and INHERITANCE
public abstract class Performer {

    protected String performerId;
    protected String name;
    protected String genre;
    protected double feePerShow;   // in INR (lakhs)
    protected String status;       // "AVAILABLE", "BOOKED", "ON_STAGE", "COMPLETED"

    public Performer(String performerId, String name, String genre, double feePerShow) {
        this.performerId = performerId;
        this.name        = name;
        this.genre       = genre;
        this.feePerShow  = feePerShow;
        this.status      = "AVAILABLE";
    }

    // Abstract method - must be overridden (POLYMORPHISM)
    public abstract String getPerformerType();

    // Abstract method - each performer introduces differently
    public abstract String getIntroduction();

    // Can be overridden in subclasses
    public String getDetails() {
        return String.format(
            "ID: %-8s | Name: %-20s | Type: %-12s | Genre: %-15s | Fee: Rs%.1fL | Status: %s",
            performerId, name, getPerformerType(), genre, feePerShow, status
        );
    }

    // Getters
    public String getPerformerId() { return performerId; }
    public String getName()        { return name;        }
    public String getGenre()       { return genre;       }
    public double getFeePerShow()  { return feePerShow;  }
    public String getStatus()      { return status;      }

    // Setters
    public void setStatus(String status)        { this.status      = status;      }
    public void setFeePerShow(double fee)       { this.feePerShow  = fee;         }
}