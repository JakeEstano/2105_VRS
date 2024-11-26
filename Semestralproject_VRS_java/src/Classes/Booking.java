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
    public Booking() {
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

    // Add New Booking Method
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
            ps.setString(3, startDate);      // Booking start date
            ps.setString(4, endDate);        // Booking end date
            ps.setInt(5, totalPrice);        // Total rental price
            ps.setString(6, driver);         // Driver option ("With Driver" or "Self Drive")
            ps.setString(7, driverName);     // Driver name (if applicable)

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
}

