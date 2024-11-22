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

/**
 *
 * @author PC
 */
public class Car {
    
    private int id;
    private int brand;
    private String model;
    private String fuel;
    private String color;
    private String plateNum;
    private int passengers;
    private String gearbox;
    private int price;
    private String air_cond;
    private String airbag;
    private String sunroof;
    private String heated_seats;
    private String nav_sys;
    private String bluetooth;
    private String elec_window;
    private String gps;

    public Car() {
    }

    public Car(int id, int brand, String model, String fuel, String color, String plateNum, int passengers, String gearbox, int price, String air_cond, String airbag, String sunroof, String heated_seats, String nav_sys, String bluetooth, String elec_window, String gps) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.color = color;
        this.plateNum = plateNum;
        this.passengers = passengers;
        this.gearbox = gearbox;
        this.price = price;
        this.air_cond = air_cond;
        this.airbag = airbag;
        this.sunroof = sunroof;
        this.heated_seats = heated_seats;
        this.nav_sys = nav_sys;
        this.bluetooth = bluetooth;
        this.elec_window = elec_window;
        this.gps = gps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getplateNum_() {
        return plateNum;
    }

    public void setplateNum_(String plateNum) {
        this.plateNum = plateNum;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAir_cond() {
        return air_cond;
    }

    public void setAir_cond(String air_cond) {
        this.air_cond = air_cond;
    }

    public String getAirbag() {
        return airbag;
    }

    public void setAirbag(String airbag) {
        this.airbag = airbag;
    }

    public String getSunroof() {
        return sunroof;
    }

    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    public String getHeated_seats() {
        return heated_seats;
    }

    public void setHeated_seats(String heated_seats) {
        this.heated_seats = heated_seats;
    }

    public String getNav_sys() {
        return nav_sys;
    }

    public void setNav_sys(String nav_sys) {
        this.nav_sys = nav_sys;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getElec_window() {
        return elec_window;
    }

    public void setElec_window(String elec_window) {
        this.elec_window = elec_window;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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
     
    
    
         //function to get all cars, return in array an list
        public ArrayList<Car> carsList()
        {
         ArrayList<Car> carsList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `cars`");
         
         try {
             while(rs.next()){
                 
                 Car car = new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                                    rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),
                                    rs.getInt(9),rs.getString(10),rs.getString(11), rs.getString(12),
                                    rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),
                                    rs.getString(17)
                 );
                 
                 carsList.add(car);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
         }
         return carsList;
     }
    
    
    
    // function to add new car
    public void addCar(int _brand, String _model, String _fuel, String _color, String _plateNum, int _passengers, 
                        String _gearbox, int _price, String _air_cond, String _airbag, String _sunroof,
                        String _heated_seats, String _nav_sys, String _bluetooth, String _elec_window, String _gps){
        
        String insertQuery = "INSERT INTO `cars`(`brand`, `model`, `fuel`, `color`, `plateNum`, `passengers`, `gearbox`, `price`, `air_conditioning`, `air_bag`, `sunroof`, `heated_seats`, `nav_system`, `bluetooth`, `electric_windows`, `gps`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
       
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, _brand);
            ps.setString(2, _model);
            ps.setString(3, _fuel);
            ps.setString(4, _color);
            ps.setString(5, _plateNum);
            ps.setInt(6, _passengers);
            ps.setString(7, _gearbox);
            ps.setInt(8, _price);
            ps.setString(9, _air_cond);
            ps.setString(10, _airbag);
            ps.setString(11, _sunroof);
            ps.setString(12, _heated_seats);
            ps.setString(13, _nav_sys);
            ps.setString(14, _bluetooth);
            ps.setString(15, _elec_window);
            ps.setString(16, _gps);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The new Vehicle has been Added" , "Add Vehicle", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle Not been Added" , "Add Vehicle", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // add car images
     public void addCarImage(int car_id, byte[] car_image){
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
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     
     // function to edit brand
     public void editCar(int _id, int _brand, String _model, String _fuel, String _color, String _plateNum, int _passengers, 
                        String _gearbox, int _price, String _air_cond, String _airbag, String _sunroof,
                        String _heated_seats, String _nav_sys, String _bluetooth, String _elec_window, String _gps){
        String editQuery = "UPDATE `cars` SET `brand`=?,`model`=?,`fuel`=?,`color`=?,`plateNum`=?,`passengers`=?,`gearbox`=?,`price`=?,`air_conditioning`=?,`air_bag`=?,`sunroof`=?,`heated_seats`=?,`nav_system`=?,`bluetooth`=?,`electric_windows`=?,`gps`=? WHERE `id` =?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _brand);
            ps.setString(2, _model);
            ps.setString(3, _fuel);
            ps.setString(4, _color);
            ps.setString(5, _plateNum);
            ps.setInt(6, _passengers);
            ps.setString(7, _gearbox);
            ps.setInt(8, _price);
            ps.setString(9, _air_cond);
            ps.setString(10, _airbag);
            ps.setString(11, _sunroof);
            ps.setString(12, _heated_seats);
            ps.setString(13, _nav_sys);
            ps.setString(14, _bluetooth);
            ps.setString(15, _elec_window);
            ps.setString(16, _gps);
            ps.setInt(17, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle has been edited" , "Edit Vehicle info", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle not been edited" , "Edit Vehicle info", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     // function to remove car
     public void removeCar(int _id){
        String removeQuery = "DELETE FROM `cars` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle has been Removed" , "Remove Vehicle", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle Not been Removed" , "Remove Vehicle", 2);
                //System.out.println("Brand Not Removed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     // START Class for image
     
    public class CarImage{  
        private int img_id;
        private int car_id;
        private byte[] car_img; // Store binary image data
         
        public CarImage(){}   

        public CarImage(int img_id, int car_id, byte[] car_img){
            this.img_id = img_id;
            this.car_id = car_id;
            this.car_img = car_img;
        }
        
            // Getters and setters
            public int getImg_id() 
                {
                return img_id;
                }
                public void setImg_id(int img_id) 
                {
                this.img_id = img_id;
                }
                public int getCar_id() 
                {
                return car_id;
                }

            public void setCar_id(int car_id) 
                {
                this.car_id = car_id;
                }

            public byte[] getCar_img() 
            {
                return car_img;
            }

            public void setCar_img(byte[] car_img) 
            {
                this.car_img = car_img;
            }
    }
 // END Class for image
     
      //function to get car images
            public ArrayList<CarImage> carImagesList(int car_id) 
                {
                ArrayList<CarImage> images = new ArrayList<>();
                ResultSet rs = getData("SELECT `id`, `car_id`, `c_image` FROM `car_images` WHERE `car_id` =" + car_id);
                CarImage car_image;

                    try 
                    {
                        while (rs.next()) 
                        {
                        car_image = new CarImage();
                        car_image.setImg_id(rs.getInt(1)); // Image ID
                        car_image.setCar_id(rs.getInt(2)); // Car ID
                        // Retrieve the image data as a byte array
                        car_image.setCar_img(rs.getBytes(3)); 
                        images.add(car_image);
                        //car_image.setCar_img(imageData); // Set the image data in CarImage object
                        
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return images;
                    }
        
        //function to search Id
        
        public Car getCarById(int car_id){
         String query = "SELECT * FROM `cars` WHERE `id` = "+ car_id ;
         ResultSet rs = getData(query);
         Car car = null;
         try {
             if (rs.next())
             {
             /*
             (int id, int brand, String model, String fuel, String color, 
             String plateNum, int passengers, String gearbox, int price, 
             String air_cond, String airbag, String sunroof, 
             String heated_seats, String nav_sys, String bluetooth, 
             String elec_window, String gps)
             */
             
             car = new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                                    rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),
                                    rs.getInt(9),rs.getString(10),rs.getString(11), rs.getString(12),
                                    rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),
                                    rs.getString(17));
             }
             else
             {
                 JOptionPane.showMessageDialog(null , "ID not found!" , "Invalid ID", 2);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
         }
         return car;
     }
    
}
