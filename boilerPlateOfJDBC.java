import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class boilerPlateOfJDBC {
    public static  void main(String[] args)throws ClassNotFoundException, SQLException {

        Scanner scanner =new Scanner("System.in");

        String url =" ";        //Write Here your SQL URL with Database Name.
        String user_Id =" ";    // Write your SQl User ID.
        String password = " ";  //Write your SQL password.
        String query = " ";     //Write Query Here
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Managements Loaded Successfully");
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url, user_Id, password);
            System.out.println("Connection Established Successfully");

            // Write here Code all you wants.

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Write here all the codes that
            // You want to create here is Driver Established and connection successfully.
            preparedStatement.close();
            connection.close();

            System.out.println("Connection closed successfully");
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

}
