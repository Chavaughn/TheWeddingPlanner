package app.manage;

import java.util.ArrayList;

public class Reservation{
    private int resId;
    private ArrayList<Integer> potentialVenues;

    public Reservation(){
    }

    public Reservation(int resId){
        this.resId = resId;
    }

    public Reservation(int resId, ArrayList<Integer> potentialVenues){
        this.resId = resId;
        this.potentialVenues = potentialVenues;
    }

    public void addVenuetoReservation(int venId){

    }

    public void setPotentialVenue(int venId){
        this.potentialVenues.add(venId);
    }

    public ArrayList<Integer> getPotentialVenues(){
        return this.potentialVenues;
    }

    public int getReservationId(){
        return this.resId;
    }
}
