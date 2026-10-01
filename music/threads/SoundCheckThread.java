package music.threads;

import music.models.Performer;

// MULTITHREADING - simulates a pre-show soundcheck in a separate thread
public class SoundCheckThread extends Thread {

    private Performer performer;
    private String    venue;

    public SoundCheckThread(Performer performer, String venue) {
        this.performer = performer;
        this.venue     = venue;
    }

    @Override
    public void run() {
        String[] steps = {
            "Setting up microphones...",
            "Adjusting bass levels...",
            "Testing guitar output...",
            "Checking stage monitors...",
            "Final volume test — 1, 2, 3!",
            "Soundcheck COMPLETE. Stage is ready!"
        };

        System.out.println("\n  [SOUNDCHECK] Starting soundcheck for: "
                           + performer.getName() + " at " + venue);
        try {
            for (String step : steps) {
                Thread.sleep(400);
                System.out.println("  [SOUNDCHECK] " + step);
            }
            performer.setStatus("ON_STAGE");
            System.out.println("  [SOUNDCHECK] " + performer.getName()
                               + " status is now: ON_STAGE");
        } catch (InterruptedException e) {
            System.out.println("  [SOUNDCHECK] Interrupted — technical issue!");
            Thread.currentThread().interrupt();
        }
    }
}