/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Booking {
    
    int id;
    int car_id;
    int customer_id;
    String pickup_city;
    String pickup_address;
    String pickup_date;
    String dropof_city;
    String dropoff_address;
    String dropoff_date;
    int total_price;

    public Booking() {
    }

    public Booking(int id, int car_id, int customer_id, String pickup_city, String pickup_address, String pickup_date, String dropof_city, String dropoff_address, String dropoff_date, int total_price) {
        this.id = id;
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.pickup_city = pickup_city;
        this.pickup_address = pickup_address;
        this.pickup_date = pickup_date;
        this.dropof_city = dropof_city;
        this.dropoff_address = dropoff_address;
        this.dropoff_date = dropoff_date;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getPickup_city() {
        return pickup_city;
    }

    public void setPickup_city(String pickup_city) {
        this.pickup_city = pickup_city;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getDropof_city() {
        return dropof_city;
    }

    public void setDropof_city(String dropof_city) {
        this.dropof_city = dropof_city;
    }

    public String getDropoff_address() {
        return dropoff_address;
    }

    public void setDropoff_address(String dropoff_address) {
        this.dropoff_address = dropoff_address;
    }

    public String getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(String dropoff_date) {
        this.dropoff_date = dropoff_date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
    
    /*
    int id;
    int car_id;
    int customer_id;
    String pickup_city;
    String pickup_address;
    String pickup_date;
    String dropof_city;
    String dropoff_address;
    String dropoff_date;
    int total_price;
    */
    
    public void addNewBooking(int id, int carid, int customerid, String pickCity, String pickAddress, String pickDate)
    {
    String insertQuery = "INSERT INTO `car_images`(`car_id`, `c_image`) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, car_id);
            ps.setBytes(2, car_image);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The New Image has been Added" , "Add Image", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "(Image Not been Added" , "Add Image", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    
}
