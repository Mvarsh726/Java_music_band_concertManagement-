package music.models;

// INHERITANCE - SoloArtist extends Performer
public class SoloArtist extends Performer {

    private String instrument;
    private int    albumsReleased;

    public SoloArtist(String performerId, String name, String genre,
                      double feePerShow, String instrument, int albumsReleased) {
        super(performerId, name, genre, feePerShow);
        this.instrument     = instrument;
        this.albumsReleased = albumsReleased;
    }

    // METHOD OVERRIDING - overrides abstract method
    @Override
    public String getPerformerType() {
        return "Solo Artist";
    }

    @Override
    public String getIntroduction() {
        return "Please welcome " + name + ", a solo " + instrument
               + " artist with " + albumsReleased + " albums!";
    }

    // METHOD OVERRIDING - extends parent getDetails()
    @Override
    public String getDetails() {
        return super.getDetails()
            + String.format(" | Instrument: %-12s | Albums: %d", instrument, albumsReleased);
    }

    public String getInstrument()     { return instrument;     }
    public int    getAlbumsReleased() { return albumsReleased; }
}