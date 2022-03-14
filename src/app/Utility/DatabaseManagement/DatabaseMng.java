package app.Utility.DatabaseManagement;

import java.sql.*;

public class DatabaseMng {

    private static String ID;

    public static String venueID(){
        return ID;
    }

    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "WeddingPlanner#");

            Statement statement = conn.createStatement();

            //statement.executeQuery("UPdate db");

            ResultSet resultSet = statement.executeQuery("Select * from jdbc.venue");
            
            

            while (resultSet.next()) {
                System.out.println(resultSet.getString("VenueID"));    
                ID = resultSet.getString("VenueID");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
