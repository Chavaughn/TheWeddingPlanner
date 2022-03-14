package app.Management;

import java.util.ArrayList;

import app.Utility.Spreadsheet;

public class ClientManagement {
    private Spreadsheet sp;

    public Client addClient(String name, int[] dOB, String email, String phoneNumber){
        Client client = new Client(name, dOB, email, phoneNumber);
        try {
            sp = new Spreadsheet();
            sp.writeClientSheet(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public ArrayList<String[]> viewAllClients(){
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp.readSheet("Client");
    }

    public void editClient(String clientId){

    }

    public void removeClient(int clientId){
        try {
            sp = new Spreadsheet();
            // sp.delete(clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
