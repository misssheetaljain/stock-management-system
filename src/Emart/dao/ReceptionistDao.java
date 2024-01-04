/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emart.dao;

import Emart.dbutil.DbConnection;
import Emart.pojo.ReceptionistPojo;
import Emart.pojo.UserPojo;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class ReceptionistDao {
    
    public static Map<String,String> getNonRegisteredReceptionists()throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select empId,empName from employees where job='Receptionist' and empId not in(Select empId from users where userType='Receptionist')");
        HashMap<String,String> receptionistList = new HashMap<>();
        while(rs.next()){
           String id= rs.getString(1);
           String name = rs.getString(2);
           receptionistList.put(id, name);
           
        }
        return receptionistList;
    }
    
    public static boolean addReceptionist(UserPojo user)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("Insert into users values ?,?,?,?,?");
        psmt.setString(1, user.getUserId());
        psmt.setString(2, user.getEmpId());
        psmt.setString(3, user.getPassword());
        psmt.setString(4, user.getUserType());
        psmt.setString(5, user.getUserName());
        int result = psmt.executeUpdate();
        if(result ==1){   // instead of if statement we can use -->> {return result == 1;}
            return true;
        }
        return false;
    }
    
    public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select users.empId, empName,userId,job , salary from users,employees where userType='Receptionist' and users.empId = employees.empid");
        ArrayList<ReceptionistPojo> al = new ArrayList<>();
        while(rs.next()){
            ReceptionistPojo recp = new ReceptionistPojo();
            recp.setEmpId(rs.getString(1));
            recp.setEmpName(rs.getString(2));
            recp.setUserId(rs.getString(3));
            recp.setJob(rs.getString(4));
            recp.setSalary(rs.getDouble(5));
            al.add(recp);
        }
        return al;
    }
    
    public static Map<String,String> getAllEmpId()throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select userId,userName from users where userType ='Receptionist' order by userId");
        HashMap<String,String> receptionistList = new HashMap<>();
        while(rs.next()){
           String id= rs.getString(1);
           String name = rs.getString(2);
           receptionistList.put(id, name);
           
        }
        return receptionistList;
}
    
    public static boolean updatePassword(String userId,String pwd)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("update users set password=? where userId=?");
        psmt.setString(1, pwd);
        psmt.setString(2, userId);
        int result = psmt.executeUpdate();
        return result==1;
                
    }
    
    public static List<String> getAllUserId()throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select userid, userName from users where userType = 'Receptionist' order by userId");
        List<String> recepId = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString(1);
            recepId.add(id);
            
        }
          return recepId;      
    }
    
    public static boolean deleteReceptionistId(String recId)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("Delete from users where userid =?");
        psmt.setString(1, recId);
        
        int result = psmt.executeUpdate();
        return result==1;
                
    }
}