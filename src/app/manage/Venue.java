package app.manage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Venue {
    private String venueName;
    private LocalDate date;
    private String location;
    private String venueID;
    private List<String> itemsNeeded = new ArrayList<String>();// Format: Item name, Amount needed, Grouping

    /**Constants */
    public final static String[] VENUE_TYPES = {"Hotel", "Beach-side", "Waterfall", "Church"};
    public final static String[] PARISHES = {"Kingston", "Saint Andrew", "Saint Elizabeth", "Hanover", "Saint James", "Trelawny", "	Westmoreland", "Clarendon", "Manchester", "Saint Ann", "Saint Catherine", "Saint Mary", "Portland", "Saint Thomas"};

    /**Default Constructor */
    public Venue(){

    }

    /**Constructor */
    public Venue(String venueName, String venueID, int[] date, String location){
        this.venueName = venueName;
        this.venueID = venueID;
        this.date = LocalDate.of(date[0], date[1], date[2]);
        setLocation(location);
    }
    /**Alt constructor */
    public Venue(String venueName, int[] date, String location){
        this.venueName = venueName;
        this.date = LocalDate.of(date[0], date[1], date[2]);
        setLocation(location);
    }

    /**Alt constructor */
    public Venue(String venueName, String venueID, int[] date, String location, List<String> itemsNeeded){
        this.venueName = venueName;
        this.venueID = venueID;
        this.date = LocalDate.of(date[0], date[1], date[2]);
        setLocation(location);
        this.itemsNeeded = itemsNeeded;
    }

    /**RETURNS the date of the venue object */
    public LocalDate getDate(){
        return this.date;
    }

    /**RETURNS the name of the venue object */
    public String getVenueName(){
        return this.venueName;
    }
    
    /**RETURNS the location of the venue object */
    public String getLocation(){
        return this.location;
    }
    
    /**RETURNS the ID of the venue object */
    public String getVenueId(){
        return this.venueID;
    }
    
    /**RETURNS the items needed of the venue object */
    public List<String> getItemsNeeded(){
        return this.itemsNeeded;
    }

    /**Sets the date of the venue object */
    public void setDate(int[] date){
        this.date = LocalDate.of(date[0],date[1],date[2]);
    }
    

    /**sets location of the venue object */
    public void setLocation(String location){
        if(isValidLocation(location)){
            this.location = location;
        }else{
            this.location = "INVALID_INPUT";
        }
    }

    /**Checks if the location entered matches the locations int he system */
    private boolean isValidLocation(String location){
        return Arrays.stream(PARISHES).anyMatch(location::contains);
    }
}
