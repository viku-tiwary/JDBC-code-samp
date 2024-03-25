import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class BatchProcessing {
    public static void main(String[] args)throws ClassNotFoundException, SQLException{

        String url = "jdbc:mysql://localhost:3306/banking";    // Write mysql url link with database name
        String user_Id = "root";    // Write user ID
        String password = "Viku2407";    // Write password

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Connected Successfully");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, user_Id, password);
            System.out.println("Connection Establish Successfully");
            String query = "INSERT INTO CUS_DTLS (Cus_name,  Cus_acc,  Cus_balance) VALUES (? ,?, ?)";
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);

            while (true)
            {
                System.out.print("Cus_Name : ");
                String name = scanner.next();
                System.out.print("Cus_acc : ");
                String Cus_acc = scanner.next();
                System.out.print("Cus_balance : ");
                double balance = scanner.nextDouble();

                preparedStatement.setString(1,name);
                preparedStatement.setString(2,Cus_acc);
                preparedStatement.setDouble(3,balance);
                preparedStatement.addBatch();

                System.out.println("Add more values Y/N: ");
                String decision = scanner.next();
                if (decision.toUpperCase().equals("N")){
                    break;
                }
            }
            int[] batch = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch processed successfully");

        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
    }
}
