import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class imageInsertionFetching {
    public static  void main(String[] args)throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner("System.in");

        String url = " ";        //Write Here your SQL URL with Database Name.
        String user_Id = " ";    // Write your SQl User ID.
        String password = " ";  //Write your SQL password.
        String query = " ";     //Write Query Here.
        String image_path = " "; // Write here your image path.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Managements Loaded Successfully");
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url, user_Id, password);
            System.out.println("Connection Established Successfully");

            // Write here Code all you wants.

            FileInputStream fileInputStream = new FileInputStream( image_path );
            // use byte array  type variable for image Data
            byte[] image_data = new byte[fileInputStream.available()];

            fileInputStream.read(image_data);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Pass Placeholder value of Data
            preparedStatement.setBytes(1,image_data);

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0){
                System.out.println("Image insertion Successfully");
            }else{
                System.out.println("Image not insertion Successfully");
            }

            // write here query data for image processing.

            preparedStatement.close();
            fileInputStream.close();
            connection.close();

            System.out.println("Connection closed Successfully");

        }catch (SQLException s){
            s.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    }
