package app.manage;

import java.util.ArrayList;
import app.util.Spreadsheet;

public class VenueManagement {
    private Venue venue;
    private Spreadsheet spreadsheet;

    public Venue createVenue(String name, String Location,int[] date){
        venue = new Venue(name, date, Location);
        return this.venue;
    }

    public ArrayList<String> viewAllVenues(){
        return spreadsheet.readSheet("Venue");
    }

    public void editVenue(String venueId){

    }

    public void removeVenue(String venueId){

    }
}
