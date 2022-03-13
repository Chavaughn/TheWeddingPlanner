package app.manage;

import java.sql.*;

public class DatabaseMng {
    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "WeddingPlanner#");

            Statement statement = conn.createStatement();


            ResultSet resultSet = statement.executeQuery("Select id, user, password from jdbc.security");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("user"));
                System.out.println(resultSet.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
