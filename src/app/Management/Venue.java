package app.Management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.Utility.Spreadsheet;
import app.Utility.DatabaseManagement.DatabaseMng;

public class Venue extends Client{
    private String venueName;
    private LocalDate date;
    private String location;
    private int venueID;
    private String venType;
    private List<String> itemsNeeded = new ArrayList<String>();// Format: Item name, Amount needed, Grouping
    private Spreadsheet sp;
    private DatabaseMng db;

    /**Constants */
    public final static String[] VENUE_TYPES = {"Hotel", "Beach-side", "Waterfall", "Church"};
    public final static String[] PARISHES = {"Kingston", "Saint Andrew", "Saint Elizabeth", "Hanover", "Saint James", "Trelawny", "	Westmoreland", "Clarendon", "Manchester", "Saint Ann", "Saint Catherine", "Saint Mary", "Portland", "Saint Thomas"};

    /**Default Constructor */
    public Venue(){

    }

    /**Constructor */
    public Venue(String venueName, int[] date, String venType,String location){
        try {
            sp = new Spreadsheet();
            db = new DatabaseMng();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.venueID = sp.getLastId()+1;
        this.venueID = Integer.parseInt(DatabaseMng.venueID())+1; 
        this.venueName = venueName;
        this.date = LocalDate.of(date[0], date[1], date[2]);
        setVenueType(venType);
        setLocation(location);
    }

    // /**Alt constructor */
    // public Venue(String venueName, String venueID, int[] date, String venType, String location, List<String> itemsNeeded){
    //     this.venueName = venueName;
    //     this.venueID = venueID;
    //     this.date = LocalDate.of(date[0], date[1], date[2]);
    //     setLocation(location);
    //     this.itemsNeeded = itemsNeeded;
    // }

    /**RETURNS the date of the venue object */
    public LocalDate getDate(){
        return this.date;
    }

    /**RETURNS the name of the venue object */
    public String getVenueName(){
        return this.venueName;
    }
    
    /**RETURNS the type of the venue object */
    public String getVenueType(){
        return this.venType;
    }

    /**RETURNS the location of the venue object */
    public String getLocation(){
        return this.location;
    }
    
    /**RETURNS the ID of the venue object */
    public int getVenueId(){
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

    /**sets type of the venue object */
    public void setVenueType(String venType){
        if(isValidType(venType)){
            this.venType = venType;
        }else{
            this.venType = "INVALID_INPUT";
        }
    }

    /**Checks if the location entered matches the locations in the system */
    private boolean isValidLocation(String location){
        return Arrays.stream(PARISHES).anyMatch(location::contains);
    }

    /**Checks if the type entered matches the types in the system */
    private boolean isValidType(String ventype){
        return Arrays.stream(VENUE_TYPES).anyMatch(ventype::contains);
    }
}
