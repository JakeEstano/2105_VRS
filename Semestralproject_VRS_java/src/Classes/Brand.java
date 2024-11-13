/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Brand {
    
     private int id;
    private String name;
    private byte[] logo;
    
    public Brand() {
        
    }
    
    public Brand(int _id, String _name, byte[] _logo) {
        this.id = _id;
        this.name = _name;
        this.logo = _logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    // function to add, edit, and remove brand
    public void addBrand(String _name, byte[] _logo){
        String insertQuery = "INSERT INTO `brands`( `name`, `logo`) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _name);
            ps.setBytes(2, _logo);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Brand has been Added" , "Add Brand", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Brand Not been Added" , "Add Brand", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // function to edit brand
     public void editBrand(int _id, String _name, byte[] _logo){
        String editQuery = "UPDATE `brands` SET `name`= ? ,`logo`= ? WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _name);
            ps.setBytes(2, _logo);
            ps.setInt(3, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The brand has been edited" , "Edit brand", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Brand not been edited" , "Edit brand", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     // function to remove brand
     public void removeBrand(int _id){
        String removeQuery = "DELETE FROM `brands` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The brand has been Removed" , "Remove brand", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Brand Not been Removed" , "Remove brand", 2);
                //System.out.println("Brand Not Removed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     // function to return a restulset
     
     public ResultSet getData(String query){
         
         PreparedStatement ps;
         ResultSet rs = null;
         try {
             
             
             ps = DB.getConnection().prepareStatement(query);
             rs = ps.executeQuery();
                     } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return rs;
     }
     
     //function to get all brand, return in array an list
     public ArrayList<Brand> brandsList(){
         ArrayList<Brand> brdList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `brands`");
         
         try {
             while(rs.next()){
                 System.out.println(rs.getInt(1));
                 System.out.println(rs.getString(2));
                 Brand brand = new Brand(rs.getInt(1),rs.getString(2),rs.getBytes(3));
                 brdList.add(brand);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return brdList;
     }
     
     // function to get brand ID
     
     public Brand getBrandById(int brand_id){
         String query = "SELECT * FROM `brands` WHERE `id` = "+ brand_id ;
         ResultSet rs = getData(query);
         Brand brand = null;
         try {
             rs.next();
             brand = new Brand(rs.getInt(1), rs.getString(2), rs.getBytes(3));
         } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return brand;
     }
     
     // function to populate a hashmap with brands 

    
     public HashMap<String, Integer> brandsHashMap()
     {
         HashMap<String, Integer> brand_map =  new  HashMap <String, Integer>();
         
         ResultSet rs = getData("SELECT * FROM `brands`");
         
         
         try {
             while (rs.next())
             {
                 brand_map.put(rs.getString(2), rs.getInt(1));
             }
         } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }

         return brand_map;
     }
    
}
