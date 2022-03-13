import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "WeddingPlanner#");

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from jdbc.people where id=1");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));

                System.out.println(resultSet.getString("phonenum"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
