package music.threads;

import music.models.Concert;

// MULTITHREADING - simulates a real-time ticket booking process
public class TicketBookingThread extends Thread {

    private Concert concert;
    private int     ticketsToBook;
    private String  customerName;

    public TicketBookingThread(Concert concert, int ticketsToBook, String customerName) {
        this.concert       = concert;
        this.ticketsToBook = ticketsToBook;
        this.customerName  = customerName;
    }

    @Override
    public void run() {
        System.out.println("\n  [BOOKING] Processing request for: " + customerName);
        try {
            System.out.println("  [BOOKING] Connecting to ticket server...");
            Thread.sleep(300);
            System.out.println("  [BOOKING] Verifying seat availability...");
            Thread.sleep(300);

            boolean success = concert.bookTickets(ticketsToBook);

            Thread.sleep(300);
            if (success) {
                double total = ticketsToBook * concert.getTicketPrice();
                System.out.printf(
                    "  [BOOKING] SUCCESS! %d ticket(s) booked for %s | Total: Rs%.0f%n",
                    ticketsToBook, customerName, total
                );
                System.out.println("  [BOOKING] Confirmation sent. Seats left: "
                                   + (concert.getTotalSeats() - concert.getBookedSeats()));
            } else {
                System.out.println("  [BOOKING] FAILED — Not enough seats for " + customerName);
            }
        } catch (InterruptedException e) {
            System.out.println("  [BOOKING] Booking process interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}