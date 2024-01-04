/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emart.dao;

import Emart.dbutil.DbConnection;
import Emart.pojo.ProductPojo;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class ProductsDao {
    
    public static String getNextProductId()throws SQLException
    {
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select max(p_id) from products");
        rs.next();
        String productId = rs.getString(1);
        if(productId == null)
            return "P101";
        int productNo = Integer.parseInt(productId.substring(1));
        productNo = productNo + 1;
        return "P" + productNo;
        
    }
    
    public static boolean addProduct(ProductPojo p) throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("Insert into products values(?,?,?,?,?,?,?,'Y')");
        psmt.setString(1, p.getProductId());
        psmt.setString(2, p.getProductName());
        psmt.setString(3, p.getCompanyName());
        psmt.setDouble(4, p.getProductPrice());
        psmt.setDouble(5, p.getOurPrice());
        psmt.setInt(6, p.getTax());
        psmt.setInt(7, p.getQuantity());
        return psmt.executeUpdate() ==1;     
    }
    
    public static List<ProductPojo> getProductDetails() throws SQLException{
        Connection con = DbConnection.getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select * from products where status ='Y' order by=p_id");
        ArrayList<ProductPojo> al = new ArrayList<>();
        while(rs.next()){
            ProductPojo pl = new ProductPojo();
           pl.setProductId(rs.getString(1));
           pl.setProductName(rs.getString(2));
           pl.setCompanyName(rs.getString(3));
           pl.setProductPrice(rs.getDouble(4));
           pl.setOurPrice(rs.getDouble(5));
           pl.setTax(rs.getInt(6));
           pl.setQuantity(rs.getInt(7));
           al.add(pl);
        }
        return al;
    }
    
    public static boolean deleteProduct(String productId)throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("update products set status ='N' where p_id=?");
        psmt.setString(1, productId);
        return psmt.executeUpdate()==1;
        
    }
    
    public static boolean  updateProduct(ProductPojo p) throws SQLException{
        Connection con = DbConnection.getConnection();
        PreparedStatement psmt = con.prepareStatement("update products set p_name=?,p_companyName=?, p_price=?,ourPrice=?,p_tax=?,quantity=?where p_id=?");
         
        psmt.setString(1, p.getProductName());
        psmt.setString(2, p.getCompanyName());
        psmt.setDouble(3, p.getProductPrice());
        psmt.setDouble(4, p.getOurPrice());
        psmt.setInt(5, p.getTax());
        psmt.setInt(6, p.getQuantity());
        psmt.setString(7, p.getProductId());
        return psmt.executeUpdate() ==1;      
    }
}
