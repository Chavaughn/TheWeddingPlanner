package app.Management;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.Utility.Spreadsheet;

public class Reservation extends Client{
    private int resId;
    private LocalDate weddingDate;
    private LocalDate resDate;
    private double appPrice;
    private Reservation res;

    
    public Reservation(){
    }

    public Reservation(String name, int[] dOB, String email, String phoneNumber, double appPrice){
        super(name, dOB, email, phoneNumber);
        this.resId=super.getClientId();
        this.appPrice = appPrice;
    }
    public Reservation(int[] weddingDate,int[] resDate, double appPrice){
        this.resId=super.getClientId();
        this.weddingDate = LocalDate.of(weddingDate[0], weddingDate[1], weddingDate[2]);
        this.resDate = LocalDate.of(resDate[0], resDate[1], resDate[2]);
        this.appPrice = appPrice;
    }

    public void addVenuetoReservation(int venId){

    }

    public int getResId(){
        return this.resId;
    }
    public Reservation cReservation(int[] weddingDate,int[] resDate, double appPrice){
        
        res = new Reservation(weddingDate, resDate, appPrice);
        try {
            sp = new Spreadsheet();
            sp.writeReservationSheet(res);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return this.res;
    }

    public double getappPrice(){
        return this.appPrice;
    }

    /**RETURNS the Date of the wedding of the reservation object */
    public LocalDate getWeddingDate(){
        return this.weddingDate;
    }

    public ArrayList<String[]> viewAllReservations(){
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp.readSheet("Reservation");
    }

    /**RETURNS the Date the reservation was made of the reservation object */
    public LocalDate getResDate(){
        return this.resDate;
    }
}
