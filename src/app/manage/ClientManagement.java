package app.manage;

import java.util.ArrayList;
import app.util.Spreadsheet;

public class ClientManagement {
    private Spreadsheet spreadsheet;

    public Client addClient(String name, int[] dOB, int age, String email, String phoneNumber){
        return new Client(name, dOB, age, email, phoneNumber);
    }
    
    public ArrayList<String> viewAllClients(){
        return spreadsheet.readSheet("Client");
    }

    public void editClient(String clientId){

    }

    public void removeClient(String clientId){

    }
}
