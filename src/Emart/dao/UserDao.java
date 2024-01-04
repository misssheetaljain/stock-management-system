
package Emart.dao;

import Emart.dbutil.DbConnection;
import Emart.pojo.UserPojo;
import Emart.pojo.UserProfile;
import java.sql.*;

public class UserDao {
    public static boolean ValidateInput(UserPojo user)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from users where userId =? & password =? & userType =?");
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs =ps.executeQuery();
        if(rs.next()){
            UserProfile.setUsername(rs.getString(5));
            return true;
        }
        return false;
    }
    
    public static boolean isUserPresent(String empId)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("Select 1 from users where empId=?");
        psmt.setString(1, empId);
        ResultSet rs = psmt.executeQuery();
        return rs.next();
        
}
}