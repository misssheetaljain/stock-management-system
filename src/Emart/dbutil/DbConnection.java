
package Emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class DbConnection {
    private static Connection con;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocery","root","2310");
            JOptionPane.showMessageDialog(null, "Connection established successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error in loading the driver","Driver Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
        catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error in establishing the Connection","Connection Error!",JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
             System.exit(1);
        }
    }
        public static Connection getConnection(){
           return con; 
        }
        public static void closeConnection(){
            try{
                con.close();
                JOptionPane.showMessageDialog(null, "Connection closed Successfullly","Success",JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, "Error in closing the Connection","Error!",JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
            }
        }
    
    
}
