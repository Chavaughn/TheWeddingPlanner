package app.manage;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.util.Spreadsheet;

public class ClientManagement {
    private Spreadsheet spreadsheet;

    public Client addClient(String name, int[] dOB, int age, String email, String phoneNumber){
        return new Client(name, dOB, age, email, phoneNumber);
    }
    
    public ArrayList<String[]> viewAllClients(){
        try {
            spreadsheet = new Spreadsheet();
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();
        }
        return spreadsheet.readSheet("Client");
    }

    public void editClient(String clientId){

    }

    public void removeClient(String clientId){

    }
}
