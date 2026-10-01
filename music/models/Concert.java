package music.models;

// Plain class (no inheritance) - represents a Concert event
public class Concert {

    private String   concertId;
    private String   venue;
    private String   city;
    private String   date;           // "DD-MM-YYYY"
    private int      totalSeats;
    private int      bookedSeats;
    private double   ticketPrice;    // in INR
    private String   performerId;    // linked to a Performer
    private String   concertStatus;  // "SCHEDULED", "LIVE", "COMPLETED", "CANCELLED"

    public Concert(String concertId, String venue, String city, String date,
                   int totalSeats, double ticketPrice, String performerId) {
        this.concertId     = concertId;
        this.venue         = venue;
        this.city          = city;
        this.date          = date;
        this.totalSeats    = totalSeats;
        this.bookedSeats   = 0;
        this.ticketPrice   = ticketPrice;
        this.performerId   = performerId;
        this.concertStatus = "SCHEDULED";
    }

    public String getDetails() {
        return String.format(
            "ConcertID: %-8s | Venue: %-20s | City: %-12s | Date: %s | Seats: %d/%d | Price: Rs%.0f | Performer: %-8s | Status: %s",
            concertId, venue, city, date, bookedSeats, totalSeats,
            ticketPrice, performerId, concertStatus
        );
    }

    public double getRevenue() {
        return bookedSeats * ticketPrice;
    }

    public boolean bookTickets(int count) {
        if (bookedSeats + count <= totalSeats) {
            bookedSeats += count;
            return true;
        }
        return false;
    }

    // Getters
    public String getConcertId()     { return concertId;     }
    public String getVenue()         { return venue;         }
    public String getCity()          { return city;          }
    public String getDate()          { return date;          }
    public int    getTotalSeats()    { return totalSeats;    }
    public int    getBookedSeats()   { return bookedSeats;   }
    public double getTicketPrice()   { return ticketPrice;   }
    public String getPerformerId()   { return performerId;   }
    public String getConcertStatus() { return concertStatus; }

    // Setters
    public void setConcertStatus(String s) { this.concertStatus = s; }
}