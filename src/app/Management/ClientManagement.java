package app.Management;

import java.util.ArrayList;


public class ClientManagement {

    public Client addClient(String name, int[] dOB, String email, String phoneNumber){
        Client client = new Client(name, dOB, email, phoneNumber);
        return client;
    }
    
    public ArrayList<String[]> viewAllClients(){
        return null;
    }

    public void editClient(String clientId){

    }

    public void removeClient(int clientId){

    }
}
