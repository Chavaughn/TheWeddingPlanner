package app.manage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Venue {
    String venueName;
    LocalDate date;
    String location;
    String venueID;
    List<String> itemsNeeded = new ArrayList<String>();// Format: Item name, Amount needed, Grouping

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
    }

    /**RETURNS the date of the venue object */
    public LocalDate getDate(){
        return this.date;
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
