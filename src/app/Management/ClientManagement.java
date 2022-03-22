package app.Management;

import java.util.ArrayList;

import app.Utility.DatabaseMng;


public class ClientManagement {

    private DatabaseMng dBM;
    private Client client;

    
    public void addClient(String name, int[] dOB, String email, String phoneNumber){
        client = new Client(name, dOB, email, phoneNumber);
        dBM = new DatabaseMng();
        dBM.AddToClientTable(client);
    }
    
    public ArrayList<String[]> viewAllClients(){
        dBM = new DatabaseMng();
        return dBM.viewClients();
    }

    public void editClient(Client client, int id){
        dBM = new DatabaseMng();
        dBM.updateClientTable(client, id);
    }

    public void removeClient(String record){
        dBM = new DatabaseMng();
        int Id = Integer.parseInt(record.split(",")[0].replace("[", ""));
        dBM.removeFromClientTable(Id);
    }
}
