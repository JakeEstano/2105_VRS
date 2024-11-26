/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Booking {

    private int id;
    private int car_id;
    private int customer_id;
    private String start_date;
    private String end_date;
    private int total_price;
    private String driver;
    private String driverName;

    // Constructor
    public Booking(){
    }

   public Booking(int id, int car_id, int customer_id, String start_date, String end_date, int total_price, String driver, String driverName){
       
   }
    // Getters and Setters
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void addNewBooking(int carId, int customerId, String startDate, String endDate, int totalPrice, 
                          String driver, String driverName) {
    // SQL query to insert booking data
    String insertQuery = "INSERT INTO `reservation`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement ps;

    try {
        // Get database connection
        ps = DB.getConnection().prepareStatement(insertQuery);

        // Set the parameters in the query
        ps.setInt(1, carId);
        ps.setInt(2, customerId);
        ps.setString(3, startDate);  
        ps.setString(4, endDate);    
        ps.setInt(5, totalPrice);   
        ps.setString(6, driver);     
        ps.setString(7, driverName); 

        // Execute the query and check the result
        if (ps.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "Booking added successfully!", "Add Booking", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add the booking. Please try again.", "Add Booking", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "An error occurred while adding the booking: " + ex.getMessage(), "Add Booking", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    public void editBooking(int id, int carId, int customerId, String startDate, String endDate, int totalPrice, 
                          String driver, String driverName) {
    // SQL query to insert booking data
    String editQuery = "UPDATE `reservation` SET `car_id`= ?,`customer_id`= ?,`start_date`= ?,`end_date`= ?,`total_price`= ?,`driver`= ?,`driverName`= ? WHERE `id`= ?";
    PreparedStatement ps;

    try {
        // Get database connection
        ps = DB.getConnection().prepareStatement(editQuery);

        // Set the parameters in the query
        ps.setInt(1, carId);
        ps.setInt(2, customerId);
        ps.setString(3, startDate);  
        ps.setString(4, endDate);    
        ps.setInt(5, totalPrice);   
        ps.setString(6, driver);     
        ps.setString(7, driverName); 
        ps.setInt(8, id); 

        // Execute the query and check the result
        if (ps.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "Edited", "Booking", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Unable To Edit", "Booking", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "An error occurred while adding the booking: " + ex.getMessage(), "Add Booking", JOptionPane.ERROR_MESSAGE);
    }
}

         // function to return a resultset
     
     public ResultSet getData(String query){
         
         PreparedStatement ps;
         ResultSet rs = null;
         try {
             
             
             ps = DB.getConnection().prepareStatement(query);
             rs = ps.executeQuery();
                     } catch (SQLException ex) {
             Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
         }
         return rs;
     }
     
    // Function to get all bookings and return an array list
     public ArrayList<Booking> bookingList() {
    ArrayList<Booking> bookList = new ArrayList<>();
    ResultSet rs = getData("SELECT * FROM `reservation`"); // Assume this method fetches the data from DB

    try {
        if (rs == null) {
            System.out.println("No data returned from the query.");
            return bookList;
        }

        while (rs.next()) {
            // Debugging: Print the data being fetched from the ResultSet
            System.out.println("Booking Retrieved: " +
                "ID: " + rs.getInt(1) +
                ", Car ID: " + rs.getInt(2) +
                ", Customer ID: " + rs.getInt(3) +
                ", Start Date: " + rs.getString(4) +
                ", End Date: " + rs.getString(5) +
                ", Total Price: " + rs.getInt(6) +
                ", Driver: " + rs.getString(7) +
                ", Driver Name: " + rs.getString(8)
            );

            // Create a booking object
            Booking booking = new Booking(rs.getInt(1),
                                          rs.getInt(2),
                                          rs.getInt(3),
                                          rs.getString(4),
                                          rs.getString(5),
                                          rs.getInt(6),
                                          rs.getString(7),
                                          rs.getString(8));
                                
            bookList.add(booking);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, "Error while retrieving bookings", ex);
        JOptionPane.showMessageDialog(null, "Error retrieving bookings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Return the list of bookings
    System.out.println("Total bookings retrieved: " + bookList.size());
    return bookList;
    
}


     
     
     
     
     
//public ArrayList<Booking> bookingList() {
//    ArrayList<Booking> bookList = new ArrayList<>();
//    ResultSet rs = getData("SELECT * FROM `reservation`");
//    try {
//        if (rs == null) {
//            System.out.println("No data returned from the query.");
//            return bookList;
//        }
//        while (rs.next()) {
//            Booking booking = new Booking(rs.getInt(1),
//                                            rs.getInt(2),
//                                            rs.getInt(3),
//                                            rs.getString(4),
//                                            rs.getString(5),
//                                            rs.getInt(6),
//                                            rs.getString(7),
//                                            rs.getString(8));
//            bookList.add(booking);
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, "Error while retrieving bookings", ex);
//        JOptionPane.showMessageDialog(null, "Error retrieving bookings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    //System.out.println("Total bookings retrieved: " + bookList.size());
//    return bookList;
//    }

    public void removeBooking(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


