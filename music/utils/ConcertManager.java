package music.utils;

import music.models.*;
import music.exceptions.*;

import java.util.ArrayList;

// Central manager class - uses ArrayList for both performers and concerts
public class ConcertManager {

    private ArrayList<Performer> performers;
    private ArrayList<Concert>   concerts;

    public ConcertManager() {
        performers = new ArrayList<>();
        concerts   = new ArrayList<>();
        seedData();
    }

    // Pre-load sample data
    private void seedData() {
        performers.add(new SoloArtist("PER-001", "Arijit Singh",    "Bollywood", 15.0, "Vocals",  12));
        performers.add(new SoloArtist("PER-002", "Shankar Mahadevan","Classical", 10.0, "Vocals",   8));
        performers.add(new Band(      "PER-003", "Strings Band",    "Rock",       8.0, 5, "Bilal",   "Karachi"));
        performers.add(new Band(      "PER-004", "Local Train",     "Indie Rock", 6.0, 4, "Ramit",   "Mumbai"));
        performers.add(new DJ(        "PER-005", "DJ Nucleya",      "Electronic", 5.0, "EDM",     true));
        performers.add(new DJ(        "PER-006", "DJ Chetas",       "Bollywood",  4.0, "Bollywood", false));

        concerts.add(new Concert("CON-001", "Jawaharlal Nehru Stadium", "Delhi",   "15-06-2025", 5000, 1500, "PER-001"));
        concerts.add(new Concert("CON-002", "Palace Grounds",           "Bengaluru","22-07-2025", 3000,  800, "PER-003"));
        concerts.add(new Concert("CON-003", "Dome NSCI",                "Mumbai",  "10-08-2025", 4000, 1200, "PER-005"));
    }

    // ------------------------------------------------------------------ ADD PERFORMER
    public void addSoloArtist(String id, String name, String genre, double fee,
                              String instrument, int albums) {
        performers.add(new SoloArtist(id, name, genre, fee, instrument, albums));
        System.out.println("  >> Solo Artist '" + name + "' registered.");
    }

    public void addBand(String id, String name, String genre, double fee,
                        int members, String lead, String city) {
        performers.add(new Band(id, name, genre, fee, members, lead, city));
        System.out.println("  >> Band '" + name + "' registered.");
    }

    public void addDJ(String id, String name, String genre, double fee,
                      String style, boolean lightShow) {
        performers.add(new DJ(id, name, genre, fee, style, lightShow));
        System.out.println("  >> DJ '" + name + "' registered.");
    }

    // ------------------------------------------------------------------ ADD CONCERT
    public void addConcert(String id, String venue, String city, String date,
                           int seats, double price, String performerId)
            throws PerformerNotFoundException {
        findPerformerById(performerId);   // validates performer exists
        concerts.add(new Concert(id, venue, city, date, seats, price, performerId));
        System.out.println("  >> Concert '" + id + "' scheduled at " + venue + ".");
    }

    // ------------------------------------------------------------------ VIEW
    public void viewAllPerformers() {
        if (performers.isEmpty()) { System.out.println("  No performers registered."); return; }
        System.out.println("\n  ===== PERFORMER ROSTER =====");
        for (Performer p : performers) {          // POLYMORPHISM: calls correct getDetails()
            System.out.println("  " + p.getDetails());
        }
        System.out.println("  Total: " + performers.size());
    }

    public void viewAllConcerts() {
        if (concerts.isEmpty()) { System.out.println("  No concerts scheduled."); return; }
        System.out.println("\n  ===== CONCERT SCHEDULE =====");
        for (Concert c : concerts) {
            System.out.println("  " + c.getDetails());
        }
        System.out.println("  Total: " + concerts.size());
    }

    // ------------------------------------------------------------------ SEARCH
    public Performer findPerformerById(String id) throws PerformerNotFoundException {
        // STRING OPERATIONS: trim + toUpperCase for safe comparison
        String query = id.trim().toUpperCase();
        for (Performer p : performers) {
            if (p.getPerformerId().toUpperCase().equals(query)) return p;
        }
        throw new PerformerNotFoundException(id);
    }

    public Concert findConcertById(String id) throws ConcertNotFoundException {
        String query = id.trim().toUpperCase();
        for (Concert c : concerts) {
            if (c.getConcertId().toUpperCase().equals(query)) return c;
        }
        throw new ConcertNotFoundException(id);
    }

    // STRING OPERATIONS: keyword search using contains() + toLowerCase()
    public void searchPerformerByKeyword(String keyword) {
        String kw = keyword.trim().toLowerCase();
        boolean found = false;
        System.out.println("\n  Search results for: \"" + keyword + "\"");
        for (Performer p : performers) {
            if (p.getName().toLowerCase().contains(kw)
                    || p.getGenre().toLowerCase().contains(kw)
                    || p.getPerformerType().toLowerCase().contains(kw)) {
                System.out.println("  " + p.getDetails());
                found = true;
            }
        }
        if (!found) System.out.println("  No performer matched \"" + keyword + "\".");
    }

    public void searchConcertByCity(String city) {
        String kw = city.trim().toLowerCase();
        boolean found = false;
        System.out.println("\n  Concerts in city: \"" + city + "\"");
        for (Concert c : concerts) {
            if (c.getCity().toLowerCase().contains(kw)) {
                System.out.println("  " + c.getDetails());
                found = true;
            }
        }
        if (!found) System.out.println("  No concerts found in \"" + city + "\".");
    }

    // ------------------------------------------------------------------ BOOK TICKETS
    public Concert getBookableConcert(String concertId)
            throws ConcertNotFoundException, SoldOutException {
        Concert c = findConcertById(concertId);
        int available = c.getTotalSeats() - c.getBookedSeats();
        if (available <= 0) {
            throw new SoldOutException(concertId, 0, 0);
        }
        return c;
    }

    // ------------------------------------------------------------------ UPDATE
    public void updatePerformerFee(String id, double newFee) throws PerformerNotFoundException {
        Performer p = findPerformerById(id);
        p.setFeePerShow(newFee);
        System.out.println("  >> Fee for '" + p.getName() + "' updated to Rs" + newFee + "L.");
    }

    public void updateConcertStatus(String id, String newStatus) throws ConcertNotFoundException {
        Concert c = findConcertById(id);
        // STRING comparison with equals()
        String s = newStatus.trim().toUpperCase();
        c.setConcertStatus(s);
        System.out.println("  >> Concert '" + id + "' status → " + s);
    }

    // ------------------------------------------------------------------ REVENUE REPORT
    public void revenueReport() {
        System.out.println("\n  ===== REVENUE REPORT =====");
        double total = 0;
        for (Concert c : concerts) {
            double rev = c.getRevenue();
            total += rev;
            System.out.printf("  %-8s | %-20s | Booked: %4d | Revenue: Rs%.0f%n",
                c.getConcertId(), c.getVenue(), c.getBookedSeats(), rev);
        }
        System.out.printf("  %s%n  Total Revenue: Rs%.0f%n",
            "─".repeat(60), total);
    }

    // ------------------------------------------------------------------ STATS
    public void showStats() {
        int solos = 0, bands = 0, djs = 0, available = 0, onStage = 0;
        for (Performer p : performers) {
            // STRING equals() comparisons
            if (p.getPerformerType().equals("Solo Artist")) solos++;
            else if (p.getPerformerType().equals("Band"))   bands++;
            else if (p.getPerformerType().equals("DJ"))     djs++;
            if (p.getStatus().equals("AVAILABLE"))          available++;
            if (p.getStatus().equals("ON_STAGE"))           onStage++;
        }
        int scheduled = 0, completed = 0;
        for (Concert c : concerts) {
            if (c.getConcertStatus().equals("SCHEDULED"))   scheduled++;
            if (c.getConcertStatus().equals("COMPLETED"))   completed++;
        }
        System.out.println("\n  ===== SYSTEM STATS =====");
        System.out.println("  Total Performers  : " + performers.size());
        System.out.println("  Solo Artists      : " + solos);
        System.out.println("  Bands             : " + bands);
        System.out.println("  DJs               : " + djs);
        System.out.println("  Available         : " + available);
        System.out.println("  On Stage          : " + onStage);
        System.out.println("  Total Concerts    : " + concerts.size());
        System.out.println("  Scheduled         : " + scheduled);
        System.out.println("  Completed         : " + completed);
    }

    // ------------------------------------------------------------------ REMOVE
    public void removePerformer(String id) throws PerformerNotFoundException {
        Performer p = findPerformerById(id);
        performers.remove(p);
        System.out.println("  >> '" + p.getName() + "' removed from roster.");
    }

    public void removeConcert(String id) throws ConcertNotFoundException {
        Concert c = findConcertById(id);
        concerts.remove(c);
        System.out.println("  >> Concert '" + id + "' removed from schedule.");
    }

    // ------------------------------------------------------------------ INTRODUCE (Polymorphism demo)
    public void introducePerformer(String id) throws PerformerNotFoundException {
        Performer p = findPerformerById(id);
        System.out.println("\n  🎤 " + p.getIntroduction());   // POLYMORPHISM
    }
}