import music.utils.ConcertManager;
import music.models.*;
import music.exceptions.*;
import music.threads.SoundCheckThread;
import music.threads.TicketBookingThread;

import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════╗
 *   MUSIC BAND & CONCERT MANAGER
 *   4th Semester Java Mini Project
 *   Covers: OOP, Inheritance, Polymorphism,
 *           Exception Handling, Multithreading,
 *           ArrayList, String Operations, Packages
 * ╚══════════════════════════════════════════════════╝
 */
public class MusicConcertMain {

    static Scanner        sc = new Scanner(System.in);
    static ConcertManager cm = new ConcertManager();

    public static void main(String[] args) {
        printBanner();
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("  Enter choice: ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "1":  addPerformer();      break;
                case "2":  addConcert();        break;
                case "3":  cm.viewAllPerformers(); break;
                case "4":  cm.viewAllConcerts();   break;
                case "5":  searchMenu();        break;
                case "6":  bookTickets();       break;
                case "7":  soundcheckMenu();    break;
                case "8":  updateMenu();        break;
                case "9":  cm.revenueReport();  break;
                case "10": cm.showStats();      break;
                case "11": removeMenu();        break;
                case "0":
                    System.out.println("\n  Thanks for using Music Concert Manager! Rock on! 🎸\n");
                    running = false;
                    break;
                default:
                    System.out.println("  Invalid choice. Try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    // ------------------------------------------------------------------ BANNER & MENU
    static void printBanner() {
        System.out.println("  ====================================================");
        System.out.println("   ***  MUSIC BAND & CONCERT MANAGER  ***");
        System.out.println("   Event Management Division - India Tour 2025");
        System.out.println("  ====================================================");
        System.out.println("   6 performers & 3 concerts pre-loaded.");
        System.out.println("  ====================================================\n");
    }

    static void printMenu() {
        System.out.println("  ----------- MAIN MENU -----------");
        System.out.println("  1.  Add Performer");
        System.out.println("  2.  Schedule a Concert");
        System.out.println("  3.  View All Performers");
        System.out.println("  4.  View All Concerts");
        System.out.println("  5.  Search");
        System.out.println("  6.  Book Tickets (Multithreading)");
        System.out.println("  7.  Soundcheck (Multithreading)");
        System.out.println("  8.  Update (Fee / Status)");
        System.out.println("  9.  Revenue Report");
        System.out.println("  10. Statistics");
        System.out.println("  11. Remove Performer / Concert");
        System.out.println("  0.  Exit");
        System.out.println("  ---------------------------------");
    }

    // ------------------------------------------------------------------ 1. ADD PERFORMER
    static void addPerformer() {
        System.out.println("\n  Performer type:");
        System.out.println("  A. Solo Artist   B. Band   C. DJ");
        System.out.print("  Enter A/B/C: ");
        String type = sc.nextLine().trim().toUpperCase();

        try {
            System.out.print("  Performer ID (e.g. PER-007): ");
            String id = sc.nextLine().trim();
            System.out.print("  Name                       : ");
            String name = sc.nextLine().trim();
            System.out.print("  Genre (e.g. Bollywood/Rock): ");
            String genre = sc.nextLine().trim();
            System.out.print("  Fee per show (in Lakhs)    : ");
            double fee = Double.parseDouble(sc.nextLine().trim());

            // STRING VALIDATION using isEmpty()
            if (id.isEmpty() || name.isEmpty() || genre.isEmpty()) {
                throw new IllegalArgumentException("ID, Name, and Genre cannot be empty.");
            }

            switch (type) {
                case "A":
                    System.out.print("  Primary Instrument: ");
                    String instrument = sc.nextLine().trim();
                    System.out.print("  Albums Released   : ");
                    int albums = Integer.parseInt(sc.nextLine().trim());
                    cm.addSoloArtist(id, name, genre, fee, instrument, albums);
                    break;
                case "B":
                    System.out.print("  Number of Members : ");
                    int members = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("  Band Lead Name    : ");
                    String lead = sc.nextLine().trim();
                    System.out.print("  Origin City       : ");
                    String city = sc.nextLine().trim();
                    cm.addBand(id, name, genre, fee, members, lead, city);
                    break;
                case "C":
                    System.out.print("  DJ Style (EDM/Bollywood/etc): ");
                    String style = sc.nextLine().trim();
                    System.out.print("  Has Light Show? (Y/N)       : ");
                    boolean lights = sc.nextLine().trim().equalsIgnoreCase("Y");
                    cm.addDJ(id, name, genre, fee, style, lights);
                    break;
                default:
                    System.out.println("  Invalid type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------ 2. ADD CONCERT
    static void addConcert() {
        try {
            System.out.print("\n  Concert ID (e.g. CON-004)    : ");
            String id = sc.nextLine().trim();
            System.out.print("  Venue Name                   : ");
            String venue = sc.nextLine().trim();
            System.out.print("  City                         : ");
            String city = sc.nextLine().trim();
            System.out.print("  Date (DD-MM-YYYY)            : ");
            String date = sc.nextLine().trim();
            System.out.print("  Total Seats                  : ");
            int seats = Integer.parseInt(sc.nextLine().trim());
            System.out.print("  Ticket Price (INR)           : ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("  Performer ID                 : ");
            String pid = sc.nextLine().trim();

            cm.addConcert(id, venue, city, date, seats, price, pid);

        } catch (PerformerNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Enter valid number for seats/price.");
        }
    }

    // ------------------------------------------------------------------ 5. SEARCH
    static void searchMenu() {
        System.out.println("\n  Search by:");
        System.out.println("  A. Performer ID (exact)");
        System.out.println("  B. Performer keyword (name/genre/type)");
        System.out.println("  C. Concert by city");
        System.out.println("  D. Performer intro (Polymorphism demo)");
        System.out.print("  Enter A/B/C/D: ");
        String choice = sc.nextLine().trim().toUpperCase();

        try {
            switch (choice) {
                case "A":
                    System.out.print("  Performer ID: ");
                    Performer p = cm.findPerformerById(sc.nextLine().trim());
                    System.out.println("  " + p.getDetails());
                    break;
                case "B":
                    System.out.print("  Keyword: ");
                    cm.searchPerformerByKeyword(sc.nextLine().trim());
                    break;
                case "C":
                    System.out.print("  City: ");
                    cm.searchConcertByCity(sc.nextLine().trim());
                    break;
                case "D":
                    System.out.print("  Performer ID: ");
                    cm.introducePerformer(sc.nextLine().trim());
                    break;
                default:
                    System.out.println("  Invalid option.");
            }
        } catch (PerformerNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------ 6. BOOK TICKETS (Thread)
    static void bookTickets() {
        try {
            System.out.print("\n  Concert ID     : ");
            String cid = sc.nextLine().trim();
            Concert concert = cm.getBookableConcert(cid);

            System.out.println("  Available seats: "
                + (concert.getTotalSeats() - concert.getBookedSeats()));
            System.out.print("  Customer Name  : ");
            String customer = sc.nextLine().trim();
            System.out.print("  Tickets needed : ");
            int count = Integer.parseInt(sc.nextLine().trim());

            int available = concert.getTotalSeats() - concert.getBookedSeats();
            if (count > available) {
                throw new SoldOutException(cid, count, available);
            }

            // MULTITHREADING - booking runs in a separate thread
            TicketBookingThread bookingThread = new TicketBookingThread(concert, count, customer);
            bookingThread.start();
            bookingThread.join();   // wait for booking to finish

        } catch (ConcertNotFoundException | SoldOutException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Enter a valid number.");
        } catch (InterruptedException e) {
            System.out.println("  [ERROR] Booking thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    // ------------------------------------------------------------------ 7. SOUNDCHECK (Thread)
    static void soundcheckMenu() {
        try {
            System.out.print("\n  Performer ID : ");
            String pid = sc.nextLine().trim();
            Performer p = cm.findPerformerById(pid);

            System.out.print("  Venue Name   : ");
            String venue = sc.nextLine().trim();

            // MULTITHREADING - soundcheck runs in a separate thread
            SoundCheckThread soundThread = new SoundCheckThread(p, venue);
            soundThread.start();
            soundThread.join();

        } catch (PerformerNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("  [ERROR] Soundcheck thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    // ------------------------------------------------------------------ 8. UPDATE
    static void updateMenu() {
        System.out.println("\n  Update:");
        System.out.println("  A. Performer fee");
        System.out.println("  B. Concert status");
        System.out.print("  Enter A/B: ");
        String choice = sc.nextLine().trim().toUpperCase();

        try {
            if (choice.equals("A")) {
                System.out.print("  Performer ID   : ");
                String pid = sc.nextLine().trim();
                System.out.print("  New fee (Lakhs): ");
                double fee = Double.parseDouble(sc.nextLine().trim());
                cm.updatePerformerFee(pid, fee);

            } else if (choice.equals("B")) {
                System.out.print("  Concert ID: ");
                String cid = sc.nextLine().trim();
                System.out.println("  Statuses: SCHEDULED | LIVE | COMPLETED | CANCELLED");
                System.out.print("  New status: ");
                String status = sc.nextLine().trim();
                cm.updateConcertStatus(cid, status);

            } else {
                System.out.println("  Invalid option.");
            }
        } catch (PerformerNotFoundException | ConcertNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Enter a valid number.");
        }
    }

    // ------------------------------------------------------------------ 11. REMOVE
    static void removeMenu() {
        System.out.println("\n  Remove:");
        System.out.println("  A. Performer   B. Concert");
        System.out.print("  Enter A/B: ");
        String choice = sc.nextLine().trim().toUpperCase();

        try {
            if (choice.equals("A")) {
                System.out.print("  Performer ID: ");
                cm.removePerformer(sc.nextLine().trim());
            } else if (choice.equals("B")) {
                System.out.print("  Concert ID: ");
                cm.removeConcert(sc.nextLine().trim());
            } else {
                System.out.println("  Invalid option.");
            }
        } catch (PerformerNotFoundException | ConcertNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }
}