package music.models;

// INHERITANCE - Band extends Performer
public class Band extends Performer {

    private int    memberCount;
    private String bandLead;
    private String originCity;

    public Band(String performerId, String name, String genre,
                double feePerShow, int memberCount, String bandLead, String originCity) {
        super(performerId, name, genre, feePerShow);
        this.memberCount = memberCount;
        this.bandLead    = bandLead;
        this.originCity  = originCity;
    }

    // METHOD OVERRIDING
    @Override
    public String getPerformerType() {
        return "Band";
    }

    @Override
    public String getIntroduction() {
        return "Performing live — " + name + " from " + originCity
               + ", led by " + bandLead + " with " + memberCount + " members!";
    }

    @Override
    public String getDetails() {
        return super.getDetails()
            + String.format(" | Members: %d | Lead: %-12s | City: %s",
                            memberCount, bandLead, originCity);
    }

    public int    getMemberCount() { return memberCount; }
    public String getBandLead()    { return bandLead;    }
    public String getOriginCity()  { return originCity;  }
}