
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class transectionControl {
    public static void main(String[] args)throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/banking";    // Write mysql url link with database name
        String user_Id = "root";    // Write user ID
        String password = "Viku2407";    // Write password
        String withdrawquery ="UPDATE CUS_DTLS SET Cus_balance = Cus_balance + ? WHERE Cus_acc = ? ";   // Write query for withdraw amount
        String depositequery= "UPDATE CUS_DTLS SET Cus_balance = Cus_balance - ? WHERE Cus_acc = ? ";   //Write query for deposit amount

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver connected successfully");

        }catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, user_Id, password);
            System.out.println("Connection Establish successfully");
            connection.setAutoCommit(false); // We use false because we put manually everything
            try{
                PreparedStatement withdraw = connection.prepareStatement(withdrawquery);
                PreparedStatement deposite = connection.prepareStatement(depositequery);
                withdraw.setDouble(1,6870);
                withdraw.setString(2,"SUD9911");
                deposite.setDouble(1,6259);
                deposite.setString(2,"VIV7065");
                withdraw.executeUpdate();
                deposite.executeUpdate();

                connection.commit();
                System.out.println("Transaction Completed successfully");
            }catch (SQLException s){
                connection.rollback();
                System.out.println("Transaction failed ");
            }

        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
    }
}
