package app.Utility;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.jar.Attributes.Name;

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
    + "   ResID           VARCHAR(20),"
    + "   DateOfBirth           DATE)";

    private String initReservationTable = "CREATE TABLE IF NOT EXISTS Reservations"
    + "  (Id           VARCHAR(32),"
    + "   Date          DATE,"
    + "   ClientID           VARCHAR(32),"
    + "   VenueID           VARCHAR(32),"
    + "   ApproximatePrice           DECIMAL(10,2))";

    private String initItemsTable = "CREATE TABLE IF NOT EXISTS Items"
    + "  (Id           VARCHAR(32),"
    + "   ItemName          VARCHAR(32),"
    + "   Quantity           INTEGER,"
    + "   ItemType           VARCHAR(32))";

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
    private String lastresid = "0";
    private String lastitemid = "0";
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
            statement.execute(initReservationTable);
            statement.execute(initItemsTable);

            //TODO: Lower time it takes to init
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Venue WHERE Id=(SELECT max(Id) FROM Venue)");

            while (resultSet.next()) {
                lastvenueid = resultSet.getString("Id");    
            }
            
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE Id=(SELECT max(Id) FROM Clients)");

            while (resultSet.next()) {
                lastclientid = resultSet.getString("Id");    
            }
            resultSet = statement.executeQuery("SELECT * FROM Reservations WHERE Id=(SELECT max(Id) FROM Reservations)");

            while (resultSet.next()) {
                lastresid = resultSet.getString("Id");    
            }

            resultSet = statement.executeQuery("SELECT * FROM Items WHERE Id=(SELECT max(Id) FROM Items)");

            while (resultSet.next()) {
                lastitemid = resultSet.getString("Id");    
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
                    resultSet.getString("ResID"),
                    resultSet.getString("DateOfBirth")});    
            }
            return viewClients;
        }
        catch(Exception e){
            System.out.println(e);
            return viewValues;
        }
    }

    public ArrayList<String[]> viewReservations(){
        viewValues = new ArrayList<>();

        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Reservations");
            

            while (resultSet.next()) {
                lastresid = resultSet.getString("Id");
                viewValues.add(new String[]{
                    resultSet.getString("Id"),
                    resultSet.getString("Date"),
                    resultSet.getString("ClientID"),
                    resultSet.getString("VenueID"),
                    resultSet.getString("ApproximatePrice")});    
            }
            return viewValues;
        }
        catch(Exception e){
            System.out.println(e);
            return viewValues;
        }
    }

    public ArrayList<String[]> viewInventory(){
        viewValues = new ArrayList<>();

        try{
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Items");
            

            while (resultSet.next()) {
                viewValues.add(new String[]{
                    resultSet.getString("ItemName"),
                    resultSet.getString("Quantity"),
                    resultSet.getString("ItemType")});    
            }
            return viewValues;
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
        + client.getReservation().getResId()+"', '"
        + client.getDateOfBirth().toString()+"')") 
        == true?true:false); 
    }

    public void AddToReservationTable(Reservation res){
        System.out.println(executeQ("INSERT INTO Reservations "
        + "Values ( '"
        + (Integer.parseInt(lastresid)+1) +"','"
        + res.getResDate()+"','"
        + res.getResClientId()+"', '"
        + res.getResVenueId()+"', '"
        + res.getappPrice()+"')") 
        == true?true:false); 
    }

    public void AddToItemsTable(Item item){
        System.out.println(executeQ("INSERT INTO Items "
        + "Values ( '"
        + (Integer.parseInt(lastitemid)+1) +"','"
        + item.getName()+"','"
        + item.getQuantity()+"', '"
        + item.getItemType()+"')") 
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

    public void updateReservationTable(Reservation res, int Id){
        System.out.println(executeQ("UPDATE Reservations "
        + "Set" 
        +" Date = '"
        + res.getResDate()+"',"
        +" ClientID = '"
        + res.getResClientId()+"',"
        +" VenueID = '"
        + res.getResVenueId()+"',"
        +" ApproximatePrice = '"
        + res.getappPrice()+"' WHERE Id = '"+ Id + "'") 
        == true?"Successfully Executed":"Failed to Execute");  
    }

    public void updateItemsTable(Item item, int Id){
        System.out.println(executeQ("UPDATE items "
        + "Set" 
        +" ItemName = '"
        + item.getName()+"',"
        +" Quantity = '"
        + item.getQuantity()+"',"
        +" ItemType = '"
        + item.getItemType()+"' WHERE Id = '"+ Id + "'") 
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
        Client Client = getClientById(Id+"");
        System.out.println("Deleting Id: "+ Id+" From table Clients");
        System.out.println(executeQ("DELETE FROM Clients "
        + "WHERE "
        + "Id = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
        removeFromReservationTableDB(Integer.parseInt(Client.getClientId()));
    }

    public void removeFromReservationTable(int Id){
        System.out.println("Deleting Id: "+ Id+" From table Reservations");
        System.out.println(executeQ("DELETE FROM Reservations "
        + "WHERE "
        + "Id = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
    }

    public void removeFromReservationTableDB(int Id){
        System.out.println("Deleting Id: "+ Id+" From table Reservations");
        System.out.println(executeQ("DELETE FROM Reservations "
        + "WHERE "
        + "ClientID = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
    }

    public void removeFromItemTable(int Id){
        System.out.println("Deleting Id: "+ Id+" From table Items");
        System.out.println(executeQ("DELETE FROM Items "
        + "WHERE "
        + "Id = '" +Id +"'") 
        == true?"Successfully Executed":"Failed to Execute");
    }
    
    public Client getClientById(String id){
        Client nClient;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);
    
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE Id = '"+id+"'");
    
                while (resultSet.next()) {
                    nClient = new Client(resultSet.getString("Id"),resultSet.getString("FirstName")+" "+resultSet.getString("LastName"),
                     LocalDate.parse(resultSet.getString("DateOfBirth")), resultSet.getString("email"), resultSet.getString("phoneNumber"),null); 
                     return nClient;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Client();
    }

    public Venue getVenueById(String id){
        Venue nVenue;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress, userName, password);
    
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE Id = '"+id+"'");
    
                while (resultSet.next()) {
                    nVenue = new Venue(resultSet.getString("Id"),resultSet.getString("VenueName"),
                     LocalDate.parse(resultSet.getString("Date")), resultSet.getString("Type"), resultSet.getString("Parish")); 
                     return nVenue;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Venue();
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
