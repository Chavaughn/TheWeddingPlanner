package app.manage;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.util.Spreadsheet;

public class VenueManagement {
    private Venue venue;
    private Spreadsheet spreadsheet;

    public Venue createVenue(String name, String Location,int[] date){
        venue = new Venue(name, date, Location);
        return this.venue;
    }

    public ArrayList<String[]> viewAllVenues(){
        try {
            spreadsheet = new Spreadsheet();
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();
        }
        return spreadsheet.readSheet("Venue");
    }

    public void editVenue(String venueId){

    }

    public void removeVenue(String venueId){

    }
}
