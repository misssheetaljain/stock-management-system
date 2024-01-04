
package Emart.dao;
import Emart.dbutil.DbConnection;
import Emart.pojo.EmployeePojo;
import com.mysql.cj.protocol.Resultset;
import java.sql.*;  
import java.util.ArrayList;
import java.util.List;


public class EmployeeDao {

   
    public static String getNextEmpId()throws SQLException{
        Connection conn = DbConnection.getConnection();
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery("Select max(empId) from employees");
        rs.next();
        String empId = rs.getString(1);
        int empNo = Integer.parseInt(empId.substring(1));
        empNo = empNo +1;
        return "E" + empNo;
    }
    
    public static boolean addEmp(EmployeePojo e)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("insert into employees value(?,?,?,?)");
        
        psmt.setString(1, e.getEmpId());
        psmt.setString(2, e.getEmpName());
        psmt.setString(3, e.getJob());
        psmt.setDouble(4, e.getSalary());
        int result = psmt.executeUpdate();
        if(result == 1)
            return true ;
        return false;
      
    }
    
    public static List<EmployeePojo> getAllEmp()throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from employees order by empId");
        ArrayList<EmployeePojo> empList = new ArrayList<>();
        
        while(rs.next()){
            EmployeePojo emp = new EmployeePojo();
            emp.setEmpId(rs.getString(1));
            emp.setEmpName(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));
            empList.add(emp);
        }
        
        return empList;
    }
    
    public static List<String> getAllEmpId() throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select empId from employees order by empId");
       ArrayList<String> allId = new ArrayList<>();
       
        while(rs.next()){
            allId.add(rs.getString(1));
        }
        
          return allId;
    }
    

    public static EmployeePojo findEmpById(String empId) throws SQLException{
        
        Connection con =DbConnection.getConnection();
        PreparedStatement psmt =con.prepareStatement("select * from employees where empId=?");
        psmt.setString(1, empId);
        
        ResultSet rs = psmt.executeQuery();
        rs.next();
        EmployeePojo e = new EmployeePojo();
        e.setEmpId(rs.getString(1));
        e.setEmpName(rs.getString(2));
        e.setJob(rs.getString(3));
        e.setSalary(rs.getDouble(4));
        
        return e;
    }
    
    
    public static boolean updateEmp(EmployeePojo e) throws SQLException {
      Connection con = DbConnection.getConnection();
      PreparedStatement psmt = con.prepareStatement("update employees set empName=?, job=?,salary=? where empId=?");
      psmt.setString(1, e.getEmpName());
      psmt.setString(2, e.getJob());
      psmt.setDouble(3, e.getSalary());
      psmt.setString(4, e.getEmpId());
      
      int x = psmt.executeUpdate();
      
      if (x==0){
          return false;
      }else{
          boolean result = UserDao.isUserPresent(e.getEmpId());
          if(result == false)
              return true;
          
      psmt= con.prepareStatement("update users set userName=?, userType=? where empId=?");
      psmt.setString(1, e.getEmpName());
      psmt.setString(2, e.getJob());
      psmt.setString(3, e.getEmpId());
      int y = psmt.executeUpdate();
      return y==1;
                                                
      }
    } 
    
    public static boolean deletEmp(String empId)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("delete from employees where empId=?");
        psmt.setString(1, empId);
        
        int result = psmt.executeUpdate();
        return result ==1;
}
}