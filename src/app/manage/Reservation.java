package app.manage;

import java.time.LocalDate;

public class Reservation extends Client{
    private int resId;
    private LocalDate weddingDate;
    private LocalDate resDate;
    private double appPrice;

    
    public Reservation(){
    }

    public Reservation(String name, int[] dOB, String email, String phoneNumber, double appPrice){
        super(name, dOB, email, phoneNumber);
        this.resId=super.getClientId();
        this.appPrice = appPrice;
    }

    public void addVenuetoReservation(int venId){

    }

    public int getResId(){
        return this.resId;
    }

    public double getappPrice(){
        return appPrice;
    }

    /**RETURNS the Date of the wedding of the reservation object */
    public LocalDate getWeddingDate(){
        return this.weddingDate;
    }

    /**RETURNS the Date the reservation was made of the reservation object */
    public LocalDate getResDate(){
        return this.resDate;
    }
}
