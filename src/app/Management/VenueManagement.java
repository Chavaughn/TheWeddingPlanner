package app.Management;

import java.util.ArrayList;

import app.Utility.DatabaseMng;


public class VenueManagement {
    private Venue venue;
    private DatabaseMng dBM;

    public void createVenue(String name, String Location,int[] date, String venType){
        venue = new Venue(name, date, venType, Location);
        dBM = new DatabaseMng();
        dBM.AddToVenueTable(venue);
    }

    public ArrayList<String[]> viewAllVenues(){
        dBM = new DatabaseMng();
        return dBM.viewVenues();
    }

    public void editVenue(Venue ven, int id){
        dBM = new DatabaseMng();
        dBM.updateVenueTable(ven, id);
    }

    public void removeVenue(String record){
        dBM = new DatabaseMng();
        int Id = Integer.parseInt(record.split(",")[0].replace("[", ""));
        dBM.removeFromVenueTable(Id);
    }
}
