package app.Management;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.Utility.DatabaseMng;

public class Reservation extends Client{
    private String resId;
    private String clientId;
    private Client client;
    private String venueId;
    private Venue venue;
    private LocalDate weddingDate;
    private LocalDate resDate;
    private double appPrice;
    private Reservation res;
    private DatabaseMng dbm;

    
    public Reservation(){
    }

    public Reservation(String clientId, int[] weddingDate,String venueId, double appPrice){
        this.clientId = clientId;
        this.weddingDate = LocalDate.of(weddingDate[0], weddingDate[1], weddingDate[2]);
        this.resDate = LocalDate.now();
        this.venueId = venueId;
        this.client = addClientToReservation(this.clientId);
        this.appPrice = appPrice;
    }

    public Client addClientToReservation(String cid){
        dbm = new DatabaseMng();
        return dbm.getClientById(cid);
    }
    public Venue addVenuetoReservation(String venId){
        dbm = new DatabaseMng();
        return dbm.getVenueById(venId);
    }

    public String getResId(){
        return this.resId;
    }

    
    public String getResClientId(){
        return this.clientId;
    }

    
    public String getResVenueId(){
        return this.venueId;
    }

    public void cReservation(String clientId, int[] weddingDate,String venueId, double appPrice){
        dbm = new DatabaseMng();
        res = new Reservation(clientId, weddingDate, venueId, appPrice);
        dbm.AddToReservationTable(res);
    }

    public void eReservation(Reservation res, int id){
        dbm = new DatabaseMng();
        dbm.updateReservationTable(res, id);
    }

    public void dReservation(String record){
        dbm = new DatabaseMng();
        int Id = Integer.parseInt(record.split(",")[0].replace("[", ""));
        dbm.removeFromReservationTable(Id);
    }

    public double getappPrice(){
        return this.appPrice;
    }

    /**RETURNS the Date of the wedding of the reservation object */
    public LocalDate getWeddingDate(){
        return this.weddingDate;
    }

    public ArrayList<String[]> viewAllReservations(){
        dbm = new DatabaseMng();
        return dbm.viewReservations();
    }

    /**RETURNS the Date the reservation was made of the reservation object */
    public LocalDate getResDate(){
        return this.resDate;
    }
}
