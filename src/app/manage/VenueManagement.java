package app.manage;

import java.util.ArrayList;

import app.util.Spreadsheet;

public class VenueManagement {
    private Venue venue;
    private Spreadsheet sp;

    public Venue createVenue(String name, String Location,int[] date, String venType){
        venue = new Venue(name, date, venType,Location);
        try {
            sp = new Spreadsheet();
            sp.writeVenueSheet(venue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.venue;
    }

    public ArrayList<String[]> viewAllVenues(){
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp.readSheet("Venue");
    }

    public void editVenue(String venueId){

    }

    public void removeVenue(String venueId){

    }
}
