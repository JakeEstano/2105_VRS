/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Classes.Car;
import Classes.Customer;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

//transaction
/**
 *
 * @author estan
 */
public class transaction extends JFrame {

    // Labels for displaying transaction information
    private JLabel lblCarModel, lblCarBrand, lblPricePerDay, lblCustomerName, lblCustomerId;
    private JLabel lblPickupDate, lblDropoffDate, lblDriverOption, lblDriverName, lblTotalPrice;
    
    // Constructor
    public transaction(int carId, int customerId, String startDate, String endDate, int totalPrice,
                                String driver, String driverName) {
        
        // Set up the frame
        setTitle("Transaction Information");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));  // Layout with 7 rows and 2 columns
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the labels
        lblCarModel = new JLabel();
        lblCarBrand = new JLabel();
        lblPricePerDay = new JLabel();
        lblCustomerName = new JLabel();
        lblCustomerId = new JLabel();
        lblPickupDate = new JLabel();
        lblDropoffDate = new JLabel();
        lblDriverOption = new JLabel();
        lblDriverName = new JLabel();
        lblTotalPrice = new JLabel();
        
        // Fetch car and customer details
        Car car = new Car();
        car = car.getCarById(carId);  // Fetch car details by ID
        Customer customer = new Customer();
        customer = customer.getFullname(customerId);  // Fetch customer details by ID
        
        // Fetch car and customer details
        String carModel = car.getModel();
        String carBrand = car.getBrand();
        int pricePerDay = car.getPrice();
        
        String customerName = customer.getName();
        
        // Set values to labels
        lblCarModel.setText("Car Model: " + carModel);
        lblCarBrand.setText("Brand: " + carBrand);
        lblPricePerDay.setText("Price Per Day: $" + pricePerDay);
        lblCustomerName.setText("Customer: " + customerName);
        lblCustomerId.setText("Customer ID: " + customerId);
        
        // Format dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblPickupDate.setText("Pickup Date: " + startDate);
        lblDropoffDate.setText("Dropoff Date: " + endDate);
        
        // Set driver details
        lblDriverOption.setText("Driver: " + driver);
        lblDriverName.setText("Driver Name: " + (driver.equals("Self Drive") ? "N/A" : driverName));
        
        // Set the total price
        lblTotalPrice.setText("Total Price: $" + totalPrice);
        
        // Add labels to the frame
        add(new JLabel("Car Model: "));
        add(lblCarModel);
        add(new JLabel("Car Brand: "));
        add(lblCarBrand);
        add(new JLabel("Price Per Day: "));
        add(lblPricePerDay);
        add(new JLabel("Customer Name: "));
        add(lblCustomerName);
        add(new JLabel("Customer ID: "));
        add(lblCustomerId);
        add(new JLabel("Pickup Date: "));
        add(lblPickupDate);
        add(new JLabel("Dropoff Date: "));
        add(lblDropoffDate);
        add(new JLabel("Driver Option: "));
        add(lblDriverOption);
        add(new JLabel("Driver Name: "));
        add(lblDriverName);
        add(new JLabel("Total Price: "));
        add(lblTotalPrice);
    }
    
    // Method to display the form
    public void displayForm() {
        setVisible(true);
    }
    
    // Main method to test the form
    public static void main(String[] args) {
        // Test values (these would typically come from the booking system)
        int carId = 1;
        int customerId = 100;
        String startDate = "2024-11-26";
        String endDate = "2024-11-28";
        int totalPrice = 200;
        String driver = "With Driver";
        String driverName = "John Doe";
        
        // Create and display the form
        transaction form = new transaction(carId, customerId, startDate, endDate, totalPrice, driver, driverName);
        form.displayForm();
    }
}
