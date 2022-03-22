package app.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import app.Management.*;

public class DatabaseMng {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/WeddingPlannerPlus?createDatabaseIfNotExist=true";
    private String userName = "root";
    private String password = "NilArie12";

    /*  ID Generation
        String expenseUUID = UUID.randomUUID().toString();
        expenseUUID = expenseUUID.replaceAll("-", "");
        System.out.print(expenseUUID); // 0757c2666e934e2eb303df68bb3c9761

        Last Id Generation
        SELECT * FROM TableName WHERE id=(SELECT max(id) FROM TableName);
    */

    private String initVenueTable =  "CREATE TABLE IF NOT EXISTS Venue"
    + "  (Id           VARCHAR(32),"
    + "   VenueName            VARCHAR(50),"
    + "   Type          VARCHAR(25),"
    + "   Parish           VARCHAR(50),"
    + "   Date           DATE)";

    private String initClientTable = "CREATE TABLE IF NOT EXISTS Clients"
    + "  (Id           VARCHAR(32),"
    + "   FirstName            VARCHAR(50),"
    + "   LastName          VARCHAR(50),"
    + "   Email           VARCHAR(50),"
    + "   PhoneNumber           VARCHAR(20),"
    + "   DateOfBirth           DATE)";

    private String VenueTableInit = "INSERT INTO Venue "
    + "Values ( '"
    + "1','"
    +" Waterfall Hostel','"
    +" Hotel_Package','"
    +" Saint Elizabeth', '"
    +"2019-08-08'),"
    +"( '"
    + "2','"
    +" Beach Party','"
    +" Church_Package','"
    +" Trelawny', '"
    +"2021-12-18')";


    private Connection con;
    private String lastvenueid = "0";
    private String lastclientid = "0";
    private ArrayList<String[]> viewValues;
    private ArrayList<String[]> viewClients;
    

    /**
     * TODO: Link Reservation to a Client and Reservation to a Vennue with new fields to the database table
     */
    public DatabaseMng(){
        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();

            statement.execute(initVenueTable);
            statement.execute(initClientTable);
            // statement.execute(VenueTableInit);

            //TODO: Lower time it takes to init
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Venue WHERE Id=(SELECT max(Id) FROM Venue)");

            while (resultSet.next()) {
                lastvenueid = resultSet.getString("Id");    
            }
            
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE Id=(SELECT max(Id) FROM Clients)");

            while (resultSet.next()) {
                lastclientid = resultSet.getString("Id");    
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<String[]> viewVenues(){
        viewValues = new ArrayList<>();

        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Venue");
            

            while (resultSet.next()) {
                lastvenueid = resultSet.getString("Id");
                viewValues.add(new String[]{
                    resultSet.getString("Id"),
                    resultSet.getString("VenueName"),
                    resultSet.getString("Type"),
                    resultSet.getString("Parish"),
                    resultSet.getString("Date")});    
            }
            return viewValues;
        }
        catch(Exception e){
            System.out.println(e);
            return viewValues;
        }
    }

    public ArrayList<String[]> viewClients(){
        viewClients = new ArrayList<>();

        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients");
            

            while (resultSet.next()) {
                lastclientid = resultSet.getString("Id");
                viewClients.add(new String[]{
                    resultSet.getString("Id"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("PhoneNumber"),
                    resultSet.getString("DateOfBirth")});    
            }
            return viewClients;
        }
        catch(Exception e){
            System.out.println(e);
            return viewValues;
        }
    }

    public void AddToVenueTable(Venue venue){
        System.out.println(executeQ("INSERT INTO Venue "
        + "Values ( '"
        + (Integer.parseInt(lastvenueid)+1) +"','"
        + venue.getVenueName()+"','"
        + venue.getVenueType()+"','"
        + venue.getLocation()+"', '"
        + venue.getDate().toString()+"')") 
        == true?true:false); 
    }

    public void AddToClientTable(Client client){
        System.out.println(executeQ("INSERT INTO Clients "
        + "Values ( '"
        + (Integer.parseInt(lastclientid)+1) +"','"
        + client.getClientName().split(" ")[0]+"','"
        + client.getClientName().split(" ")[1]+"','"
        + client.getEmail()+"', '"
        + client.getPhoneNumber()+"', '"
        + client.getDateOfBirth().toString()+"')") 
        == true?true:false); 
    }

    public void updateVenueTable(Venue venue, int Id){
        System.out.println("----------------------------------------");
        System.out.println("Editing Id: "+ Id+" From table Venue");
        System.out.println("Name: "+ venue.getVenueName());
        System.out.println("Type: "+ venue.getVenueType());
        System.out.println("Parish: "+ venue.getLocation());
        System.out.println("Date: "+ venue.getDate().toString());
        System.out.println("----------------------------------------");
        System.out.println(executeQ("UPDATE Venue "
        + "Set" 
        +" VenueName = '"
        + venue.getVenueName()+"',"
        +" Type = '"
        + venue.getVenueType()+"',"
        +" Parish = '"
        + venue.getLocation()+"',"
        +" Date = '"
        + venue.getDate().toString()+"' WHERE Id = '"+ Id + "'") 
        == true?"Successfully Executed":"Failed to Execute");  
    }

    public void updateClientTable(Client client, int Id){
        System.out.println("----------------------------------------");
        System.out.println("Editing Id: "+ Id+" From table Clients");
        System.out.println("Name: "+ client.getClientName());
        System.out.println("Email: "+  client.getEmail());
        System.out.println("Phone Number: "+ client.getPhoneNumber());
        System.out.println("Date of Birth: "+ client.getDateOfBirth().toString());
        System.out.println("----------------------------------------");
        System.out.println(executeQ("UPDATE Venue "
        + "Set" 
        +" FirstName = '"
        + client.getClientName().split(" ")[0]+"',"
        +" LastName = '"
        + client.getClientName().split(" ")[1]+"',"
        +" Email = '"
        + client.getEmail()+"',"
        +" PhoneNumber = '"
        + client.getPhoneNumber()+"',"
        +" Date = '"
        + client.getDateOfBirth().toString()+"' WHERE Id = '"+ Id + "'") 
        == true?"Successfully Executed":"Failed to Execute");  
    }

    public void removeFromVenueTable(int Id){
        System.out.println("Deleting Id: "+ Id+" From table Venue");
        System.out.println(executeQ("DELETE FROM Venue "
        + "WHERE "
        + "Id = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
    }

    public void removeFromClientTable(int Id){
        System.out.println("Deleting Id: "+ Id+" From table Clients");
        System.out.println(executeQ("DELETE FROM Client "
        + "WHERE "
        + "Id = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
    }

    public Boolean executeQ(String query){
        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();
            statement.execute(query);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
