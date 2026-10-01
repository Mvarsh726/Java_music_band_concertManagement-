package music.models;

// INHERITANCE - DJ extends Performer
public class DJ extends Performer {

    private String  djStyle;        // "EDM", "Bollywood", "Hip-Hop", "Techno"
    private boolean hasLightShow;

    public DJ(String performerId, String name, String genre,
              double feePerShow, String djStyle, boolean hasLightShow) {
        super(performerId, name, genre, feePerShow);
        this.djStyle      = djStyle;
        this.hasLightShow = hasLightShow;
    }

    // METHOD OVERRIDING
    @Override
    public String getPerformerType() {
        return "DJ";
    }

    @Override
    public String getIntroduction() {
        return "Drop the beat! DJ " + name + " spinning " + djStyle
               + (hasLightShow ? " with a full light show!" : "!");
    }

    @Override
    public String getDetails() {
        return super.getDetails()
            + String.format(" | Style: %-12s | Light Show: %s",
                            djStyle, hasLightShow ? "Yes" : "No");
    }

    public String  getDjStyle()      { return djStyle;      }
    public boolean isHasLightShow()  { return hasLightShow; }
}