package app.manage;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.util.Spreadsheet;

public class ClientManagement {
    private Spreadsheet spreadsheet;

    public Client addClient(String name, int[] dOB, String email, String phoneNumber){
        Client client = new Client(name, dOB, email, phoneNumber);
        try {
            spreadsheet = new Spreadsheet();
            spreadsheet.writeClientSheet(client);
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();
        }
        return client;
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
