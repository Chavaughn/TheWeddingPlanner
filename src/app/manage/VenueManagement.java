package app.manage;

import java.util.List;

public class VenueManagement {
    private Venue venue;
    List<String> x;

    public Venue createVenue(String name, String Location,int[] date){
        venue = new Venue(name, date, Location);
        return this.venue;
    }

    public List<String> viewAllVenues(){
        return x;
    }

    public void editVenue(String venueId){

    }

    public void removeVenue(String venueId){

    }
}
