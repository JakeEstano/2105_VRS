/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import Classes.Brand;
import Classes.Car;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**  
 *
 * @author estan
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    Car car = new Car();
    Brand brands = new Brand();
    HashMap<Integer ,String> map = brands.brandsHashMap();
    
    Classes.Brand brand = new Classes.Brand();
    ArrayList<Classes.Brand> brands_list = brands.brandsList();
     int locationIndex = 0;
     
    Classes.Location location = new Classes.Location();
    ArrayList<Classes.Location>locations_list = location.locationList();
    int index = 0;
    
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        //Radio button
        
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(jRadioButton_Automatic);
        btn_group.add(jRadioButton_Manual);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithLocations();
    }
    
    // pupulated the jComboBox_brands

    
    public void populateComboBoxBrands()
    {
        for ( String s : map.values())
        {
            jComboBox_brand.addItem(s);
        }
    }
    
    public boolean carVerify()
    { 
        String model = jTextField_Model.getText();
        String _plateNum = jTextField_Model.getText();
        
        if(!model.trim().equals("") && !_plateNum.trim().equals(""))
        {
            return true;
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter a Valid Model and Plate Number" , "Invalid Info", 2);
             return false;
        }
    }
    
    // create resizable image to fit in jLabel
    public void displayImage(int width, int height, String image_path, JLabel label){

        // get the image
        var imageIco = new ImageIcon(image_path);
        // resize the icon
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
      // create resizable byte image to fit in jLabel
    public void displayByteImage(int width, int height, byte[] image_byte, JLabel label){

        // get the image
        var imageIco = new ImageIcon(image_byte);
        // resize the icon
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
    // create a function to select image
    // will return the image path
    // use images with a low size
    public String selectImage(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Picture");
        
        fileChooser.setCurrentDirectory(new File("c:\\images"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", ".png", ".jpg");
        fileChooser.addChoosableFileFilter(filter);
        
        int state = fileChooser.showSaveDialog(null);
        String path = "";
        
        if(state == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        
        
        return path;
    }
    
    // gagawa ng function para ipulate the jTable with brands (id & name )
    public void populateJtableWithBrands(){
        
       // Clear array List
       brands_list.clear();
       // populated arrayList
       brands_list = brands.brandsList();
       // JTable Columns
        String[] columnsName = {"ID", "Name"};
       // Rows
       Object[][] rows = new Object[brands_list.size()][columnsName.length];
       
       for (int i = 0; i < brands_list.size(); i++){
           
           rows[i][0] = brands_list.get(i).getId();
           rows[i][1] = brands_list.get(i).getName();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
       jTable_Brands.setModel(model);
       
    }
    
    public void displayBrand(){
        Classes.Brand selected_brand = brands_list.get(locationIndex);
        jSpinner_id.setValue(selected_brand.getId());
        jTextField_name.setText(selected_brand.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), selected_brand.getLogo(), jLabel_logo);
    }
    
    // gagawa ng function para ipulate the jTable with brand (id & name )
    public void populateJtableWithLocations(){
        
       // Clear array List
       locations_list.clear();
       // populated arrayList
       locations_list = location.locationList();
       // JTable Columns
        String[] columnsName = {"ID", "City","Address"};
       // Rows
       Object[][] rows = new Object[locations_list.size()][columnsName.length];
       
       for (int i = 0; i < locations_list.size(); i++){
           
           rows[i][0] = locations_list.get(i).getId();
           rows[i][1] = locations_list.get(i).getCity();
           rows[i][2] = locations_list.get(i).getAddress();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
       jTable_Locations.setModel(model);
       
    }
    
    // Display Location
    public void displayLocation(){
        Classes.Location selected_location = locations_list.get(locationIndex);
        jSpinner_id.setValue(selected_location.getId());
        jComboBox_City.setSelectedItem(selected_location.getCity());
        jTextArea_Address.setText(selected_location.getAddress());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // To Verify the empty fields
    public boolean brandVerify(String add_or_edit)
    {
        
        String name = jTextField_name.getText();
        String pic_path = jLabel_imagePath.getText();
        boolean val = false;
        
        if(!name.trim().equals(""))
        {
            if ("add".equals(add_or_edit))
            {
                
                if(pic_path.trim().equals(""))
                {
                    JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
                    val = false;
                }
                else 
                {
                     val = true;    
                 }
            }
            if ("edit".equals(add_or_edit))
            {
                if (jLabel_logo.getIcon() == null)
                {
                    JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
                    val = false;
                }
                else 
                {
                     val = true;    
                 }
                }
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
             val = false;
        }
        return val;
    }
    
    // To Verify the empty fields
    public boolean locationVerify()
    { 
        String address = jTextArea_Address.getText();

        if(!address.trim().equals(""))
        {
            return true;
            
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter a Valid Location Address" , "Invalid Info", 2);
             return false;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        homeTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vehicleTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        brandTab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        locationTab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        customerTab = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        bookCarTab = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel_cars_logo3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_cars_logo = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel_cars_logo2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vehiclePanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner_Id = new javax.swing.JSpinner();
        jButton_Search_ = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_brand = new javax.swing.JComboBox<>();
        jLabel_Brand_Id = new javax.swing.JLabel();
        jTextField_Model = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_Fuel = new javax.swing.JComboBox<>();
        jComboBox_Color = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton_Features_Aircond = new javax.swing.JRadioButton();
        jRadioButton_Features_AirBag = new javax.swing.JRadioButton();
        jRadioButton_Features_Sunroof = new javax.swing.JRadioButton();
        jRadioButton_Features_HeatedSeat = new javax.swing.JRadioButton();
        jRadioButton_Features_NavSys = new javax.swing.JRadioButton();
        jRadioButton_Features_Bluetooth = new javax.swing.JRadioButton();
        jRadioButton_Features_ElecWin = new javax.swing.JRadioButton();
        jRadioButton_Features_GPS = new javax.swing.JRadioButton();
        jButton_Add_Car_ = new javax.swing.JButton();
        jButton_Add_images_ = new javax.swing.JButton();
        jButton_Add_Brands_List_ = new javax.swing.JButton();
        jButton_Reset_ = new javax.swing.JButton();
        jButton_Cars_List_ = new javax.swing.JButton();
        jButton_Remove_ = new javax.swing.JButton();
        jButton_Edit_ = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField_PlateNum = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        int max_2 = 14;
        int min_2 = 2;
        int step_2 = 1;
        int i_2 = 2;
        SpinnerModel spinner_model_2 = new SpinnerNumberModel(i_2,min_2,max_2,step_2);
        jSpinner_Passengers = new javax.swing.JSpinner(spinner_model_2);
        jLabel17 = new javax.swing.JLabel();
        jRadioButton_Automatic = new javax.swing.JRadioButton();
        jRadioButton_Manual = new javax.swing.JRadioButton();
        int max = 10000;
        int min = 10;
        int step = 1;
        int i = 10;
        SpinnerModel spinner_model = new SpinnerNumberModel(i,min,max,step);
        jSpinner_Price = new javax.swing.JSpinner(spinner_model);
        jLabel18 = new javax.swing.JLabel();
        brandPanel = new javax.swing.JPanel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel_logo = new javax.swing.JLabel();
        jButton_Edit = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_browse = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Brands = new javax.swing.JTable();
        jButton_Add = new javax.swing.JButton();
        jLabel_imagePath = new javax.swing.JLabel();
        locationPanel = new javax.swing.JPanel();
        jSpinner_id1 = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        jComboBox_City = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Address = new javax.swing.JTextArea();
        jButton_Edit1 = new javax.swing.JButton();
        jButton_Add1 = new javax.swing.JButton();
        jButton_Refresh1 = new javax.swing.JButton();
        jButton_Clear1 = new javax.swing.JButton();
        jButton_Remove1 = new javax.swing.JButton();
        jButton_First1 = new javax.swing.JButton();
        jButton_Next1 = new javax.swing.JButton();
        jButton_Previous1 = new javax.swing.JButton();
        jButton_Last1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Locations = new javax.swing.JTable();
        customerPanel = new javax.swing.JPanel();
        bookCarPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        homeTab.setOpaque(false);
        homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTabMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOME");

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehicleTab.setOpaque(false);
        vehicleTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleTabMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VEHICLE");

        javax.swing.GroupLayout vehicleTabLayout = new javax.swing.GroupLayout(vehicleTab);
        vehicleTab.setLayout(vehicleTabLayout);
        vehicleTabLayout.setHorizontalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleTabLayout.setVerticalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        brandTab.setOpaque(false);
        brandTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brandTabMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BRAND");

        javax.swing.GroupLayout brandTabLayout = new javax.swing.GroupLayout(brandTab);
        brandTab.setLayout(brandTabLayout);
        brandTabLayout.setHorizontalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        brandTabLayout.setVerticalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        locationTab.setOpaque(false);
        locationTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationTabMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOCATION");

        javax.swing.GroupLayout locationTabLayout = new javax.swing.GroupLayout(locationTab);
        locationTab.setLayout(locationTabLayout);
        locationTabLayout.setHorizontalGroup(
            locationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        locationTabLayout.setVerticalGroup(
            locationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        customerTab.setOpaque(false);
        customerTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTabMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CUSTOMER");

        javax.swing.GroupLayout customerTabLayout = new javax.swing.GroupLayout(customerTab);
        customerTab.setLayout(customerTabLayout);
        customerTabLayout.setHorizontalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        customerTabLayout.setVerticalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        bookCarTab.setOpaque(false);
        bookCarTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookCarTabMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BOOK CAR");

        javax.swing.GroupLayout bookCarTabLayout = new javax.swing.GroupLayout(bookCarTab);
        bookCarTab.setLayout(bookCarTabLayout);
        bookCarTabLayout.setHorizontalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        bookCarTabLayout.setVerticalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehicleTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(255, 255, 255));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel7.setOpaque(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel9.setOpaque(false);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Booked");

        jLabel_cars_logo3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_cars_logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_cars_logo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        homePanel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 250, 170));

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel10.setOpaque(false);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel11.setOpaque(false);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vehicles");

        jLabel_cars_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_cars_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        homePanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 250, 170));

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel12.setOpaque(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel13.setOpaque(false);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Customers");

        jLabel_cars_logo2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        homePanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 250, 170));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homepage.png"))); // NOI18N
        homePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 590));

        jPanel2.add(homePanel, "card2");

        vehiclePanel.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID:");

        jSpinner_Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Id.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton_Search_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Search_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Search_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Search_.setText("Search");
        jButton_Search_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Search_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Search_ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Brand:");

        jComboBox_brand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_brand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brandActionPerformed(evt);
            }
        });

        jLabel_Brand_Id.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel_Brand_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Brand_Id.setText("#");
        jLabel_Brand_Id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField_Model.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Model.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ModelActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Model:");

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fuel:");

        jComboBox_Fuel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Fuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gas", "Diesel" }));
        jComboBox_Fuel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox_Color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "White", "Black", "Grey", "Red", "Dark Green" }));
        jComboBox_Color.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Color:");

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Features");

        jRadioButton_Features_Aircond.setText("Air Conditioning");
        jRadioButton_Features_Aircond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_AircondActionPerformed(evt);
            }
        });

        jRadioButton_Features_AirBag.setText("Air Bags");

        jRadioButton_Features_Sunroof.setText("Convertible");
        jRadioButton_Features_Sunroof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_SunroofActionPerformed(evt);
            }
        });

        jRadioButton_Features_HeatedSeat.setText("Heated Seats");

        jRadioButton_Features_NavSys.setText("Navigation System");

        jRadioButton_Features_Bluetooth.setText("Bluetooth");

        jRadioButton_Features_ElecWin.setText("Electric Windows");
        jRadioButton_Features_ElecWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_ElecWinActionPerformed(evt);
            }
        });

        jRadioButton_Features_GPS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_Features_GPS.setText("GPS");
        jRadioButton_Features_GPS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Features_GPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_GPSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_AirBag, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_Aircond, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_Bluetooth, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_NavSys))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_HeatedSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_Sunroof, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_ElecWin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_GPS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton_Features_Aircond)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Features_AirBag))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_NavSys)
                            .addComponent(jRadioButton_Features_ElecWin)
                            .addComponent(jRadioButton_Features_HeatedSeat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_Bluetooth)
                            .addComponent(jRadioButton_Features_GPS)
                            .addComponent(jRadioButton_Features_Sunroof))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jButton_Add_Car_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Add_Car_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Car_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Add_Car_.setText("Add");
        jButton_Add_Car_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Car_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Car_ActionPerformed(evt);
            }
        });

        jButton_Add_images_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Add_images_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_images_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Add_images_.setText("Add Car Images");
        jButton_Add_images_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_images_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_Add_images_MouseClicked(evt);
            }
        });
        jButton_Add_images_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_images_ActionPerformed(evt);
            }
        });

        jButton_Add_Brands_List_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Add_Brands_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Brands_List_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Add_Brands_List_.setText("Brands");
        jButton_Add_Brands_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Brands_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Brands_List_ActionPerformed(evt);
            }
        });

        jButton_Reset_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Reset_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Reset_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Reset_.setText("Reset");
        jButton_Reset_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Reset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Reset_ActionPerformed(evt);
            }
        });

        jButton_Cars_List_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Cars_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Cars_List_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Cars_List_.setText("Car List");
        jButton_Cars_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cars_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cars_List_ActionPerformed(evt);
            }
        });

        jButton_Remove_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Remove_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Remove_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Remove_.setText("Remove");
        jButton_Remove_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_Remove_MouseClicked(evt);
            }
        });
        jButton_Remove_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove_ActionPerformed(evt);
            }
        });

        jButton_Edit_.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Edit_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Edit_.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Edit_.setText("Edit");
        jButton_Edit_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Plate Number:");

        jTextField_PlateNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_PlateNum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_PlateNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_PlateNumActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Seats:");

        jSpinner_Passengers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Passengers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Transmission:");

        jRadioButton_Automatic.setText("Automatic");
        jRadioButton_Automatic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Automatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_AutomaticActionPerformed(evt);
            }
        });

        jRadioButton_Manual.setText("Manual");
        jRadioButton_Manual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ManualActionPerformed(evt);
            }
        });

        jSpinner_Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Price.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Price/Day:");

        javax.swing.GroupLayout vehiclePanelLayout = new javax.swing.GroupLayout(vehiclePanel);
        vehiclePanel.setLayout(vehiclePanelLayout);
        vehiclePanelLayout.setHorizontalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox_Color, javax.swing.GroupLayout.Alignment.LEADING, 0, 126, Short.MAX_VALUE)
                                    .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18))))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jButton_Edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Cars_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Add_images_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_PlateNum, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        vehiclePanelLayout.setVerticalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField_PlateNum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehiclePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehiclePanelLayout.createSequentialGroup()
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(jLabel13))
                            .addComponent(jComboBox_Color, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Add_images_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_Edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Cars_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton_Manual))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.add(vehiclePanel, "card5");

        brandPanel.setBackground(new java.awt.Color(204, 204, 204));

        jSpinner_id.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel20.setText("ID:");

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel21.setText("Name:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel23.setText("Logo:");

        jLabel_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel_logo.setOpaque(true);

        jButton_Edit.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Edit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Edit.setText("Edit");
        jButton_Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Remove.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Remove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Remove.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Remove.setText("Remove");
        jButton_Remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveActionPerformed(evt);
            }
        });

        jButton_browse.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton_browse.setText("Browse");
        jButton_browse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
            }
        });

        jButton_Refresh.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Refresh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Refresh.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Refresh.setText("Refresh");
        jButton_Refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Clear.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Clear.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Clear.setText("Clear");
        jButton_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        jButton_First.setBackground(new java.awt.Color(153, 153, 153));
        jButton_First.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_First.setForeground(new java.awt.Color(255, 255, 255));
        jButton_First.setText("<<");
        jButton_First.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FirstActionPerformed(evt);
            }
        });

        jButton_Next.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Next.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Next.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Next.setText(">");
        jButton_Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Previous.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Previous.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Previous.setText("<");
        jButton_Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Last.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Last.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Last.setText(">>");
        jButton_Last.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastActionPerformed(evt);
            }
        });

        jTable_Brands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Brands.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BrandsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Brands);

        jButton_Add.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add.setText("Add");
        jButton_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout brandPanelLayout = new javax.swing.GroupLayout(brandPanel);
        brandPanel.setLayout(brandPanelLayout);
        brandPanelLayout.setHorizontalGroup(
            brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandPanelLayout.createSequentialGroup()
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(866, Short.MAX_VALUE))
            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(brandPanelLayout.createSequentialGroup()
                    .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                            .addGap(109, 594, Short.MAX_VALUE)
                            .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60))
                        .addGroup(brandPanelLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(18, 18, 18)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField_name, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jSpinner_id))
                            .addGap(18, 18, 18)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(76, 76, 76)))
        );
        brandPanelLayout.setVerticalGroup(
            brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandPanelLayout.createSequentialGroup()
                .addContainerGap(344, Short.MAX_VALUE)
                .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_browse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(brandPanelLayout.createSequentialGroup()
                    .addGap(133, 133, 133)
                    .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(brandPanelLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(brandPanelLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSpinner_id)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23)
                                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(142, 142, 142)))
        );

        jPanel2.add(brandPanel, "card3");

        locationPanel.setBackground(new java.awt.Color(204, 204, 204));

        jSpinner_id1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel24.setText("ID:");

        jComboBox_City.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox_City.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balayan", "Calaca", "Nasugbu", "Batangas" }));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel25.setText("City:");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel26.setText("Address:");

        jTextArea_Address.setColumns(20);
        jTextArea_Address.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextArea_Address.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Address);

        jButton_Edit1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Edit1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Edit1.setText("Edit");
        jButton_Edit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit1ActionPerformed(evt);
            }
        });

        jButton_Add1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Add1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Add1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Add1.setText("Add");
        jButton_Add1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add1ActionPerformed(evt);
            }
        });

        jButton_Refresh1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Refresh1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Refresh1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Refresh1.setText("Refresh");
        jButton_Refresh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Refresh1ActionPerformed(evt);
            }
        });

        jButton_Clear1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Clear1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Clear1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Clear1.setText("Clear");
        jButton_Clear1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Clear1ActionPerformed(evt);
            }
        });

        jButton_Remove1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Remove1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Remove1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Remove1.setText("Remove");
        jButton_Remove1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove1ActionPerformed(evt);
            }
        });

        jButton_First1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_First1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_First1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_First1.setText("<<");
        jButton_First1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_First1ActionPerformed(evt);
            }
        });

        jButton_Next1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Next1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Next1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Next1.setText(">");
        jButton_Next1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Next1ActionPerformed(evt);
            }
        });

        jButton_Previous1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Previous1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Previous1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Previous1.setText("<");
        jButton_Previous1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Previous1ActionPerformed(evt);
            }
        });

        jButton_Last1.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Last1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Last1.setForeground(new java.awt.Color(204, 204, 204));
        jButton_Last1.setText(">>");
        jButton_Last1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Last1ActionPerformed(evt);
            }
        });

        jTable_Locations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Locations.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Locations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_LocationsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_Locations);

        javax.swing.GroupLayout locationPanelLayout = new javax.swing.GroupLayout(locationPanel);
        locationPanel.setLayout(locationPanelLayout);
        locationPanelLayout.setHorizontalGroup(
            locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationPanelLayout.createSequentialGroup()
                .addContainerGap(527, Short.MAX_VALUE)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationPanelLayout.createSequentialGroup()
                        .addComponent(jButton_First1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Next1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Previous1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Last1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)))
                .addGap(61, 61, 61))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(locationPanelLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jButton_Refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_Clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(locationPanelLayout.createSequentialGroup()
                            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(locationPanelLayout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(jButton_Add1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton_Edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(jButton_Remove1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(locationPanelLayout.createSequentialGroup()
                                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSpinner_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox_City, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(781, Short.MAX_VALUE)))
        );
        locationPanelLayout.setVerticalGroup(
            locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationPanelLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Last1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Next1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_First1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Previous1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSpinner_id1)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_City, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Add1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Remove1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(105, 105, 105)))
        );

        jPanel2.add(locationPanel, "card4");

        customerPanel.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        jPanel2.add(customerPanel, "card6");

        bookCarPanel.setBackground(new java.awt.Color(51, 102, 0));

        javax.swing.GroupLayout bookCarPanelLayout = new javax.swing.GroupLayout(bookCarPanel);
        bookCarPanel.setLayout(bookCarPanelLayout);
        bookCarPanelLayout.setHorizontalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        bookCarPanelLayout.setVerticalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        jPanel2.add(bookCarPanel, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTabMouseClicked
        homePanel.setVisible(true);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_homeTabMouseClicked

    private void vehicleTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleTabMouseClicked
        vehiclePanel.setVisible(true);
        homePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_vehicleTabMouseClicked

    private void brandTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandTabMouseClicked
        brandPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_brandTabMouseClicked

    private void locationTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationTabMouseClicked
        locationPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_locationTabMouseClicked

    private void customerTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTabMouseClicked
        customerPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_customerTabMouseClicked

    private void bookCarTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookCarTabMouseClicked
        bookCarPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
    }//GEN-LAST:event_bookCarTabMouseClicked

    private void jButton_Search_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Search_ActionPerformed
        // Search by ID
        int car_id = (int) jSpinner_Id.getValue();
        Car new_car = car.getCarById(car_id);

        jButton_Reset_ActionPerformed(null);

        if (new_car != null)
        {
            jTextField_PlateNum.setText(new_car.getplateNum_());
            jTextField_Model.setText(new_car.getModel());
            jSpinner_Passengers.setValue(new_car.getPassengers());
            jSpinner_Price.setValue(new_car.getPrice());

            jComboBox_brand.setSelectedItem(map.get(new_car.getBrand()));

            jComboBox_Fuel.setSelectedItem(new_car.getFuel());
            jComboBox_Color.setSelectedItem(new_car.getColor());

            if (new_car.getGearbox().equals("manual")){jRadioButton_Manual.setSelected(true);}
            if (new_car.getAir_cond().equals("yes")){jRadioButton_Features_Aircond.setSelected(true);}
            if (new_car.getAirbag().equals("yes")){jRadioButton_Features_AirBag.setSelected(true);}
            if (new_car.getElec_window().equals("yes")){jRadioButton_Features_ElecWin.setSelected(true);}
            if (new_car.getGps().equals("yes")){jRadioButton_Features_GPS.setSelected(true);}
            if (new_car.getHeated_seats().equals("yes")){jRadioButton_Features_HeatedSeat.setSelected(true);}
            if (new_car.getNav_sys().equals("yes")){jRadioButton_Features_NavSys.setSelected(true);}
            if (new_car.getSunroof().equals("yes")){jRadioButton_Features_Sunroof.setSelected(true);}

        }
    }//GEN-LAST:event_jButton_Search_ActionPerformed

    private void jComboBox_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brandActionPerformed
        // TODO add your handling code here:
        // Get the selected brands id
        int brand_id = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet())
        {
            if(entry.getValue().equals(jComboBox_brand.getSelectedItem().toString()))
            {
                brand_id = entry.getKey();
            }
        }

        jLabel_Brand_Id.setText(String.valueOf(brand_id));

    }//GEN-LAST:event_jComboBox_brandActionPerformed

    private void jTextField_ModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ModelActionPerformed

    private void jRadioButton_Features_AircondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_AircondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_AircondActionPerformed

    private void jRadioButton_Features_SunroofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_SunroofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_SunroofActionPerformed

    private void jRadioButton_Features_ElecWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_ElecWinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_ElecWinActionPerformed

    private void jRadioButton_Features_GPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_GPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_GPSActionPerformed

    private void jButton_Add_Car_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Car_ActionPerformed
        // TODO add your handling code here:
        // Add new Car
        // Car info
        int brand = (int)(jSpinner_Id.getValue());
        String model = jTextField_Model.getText();
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_PlateNum.getText();
        int passengers = (int)jSpinner_Passengers .getValue();
        String gearbox = "automatic";
        //int price = Integer.valueOf(jSpinner_Price.getValue());
        int price = (int)(jSpinner_Price.getValue());
        // car features
        String air_cond = "no";
        String airbags = "no";
        String sunroof = "no";
        String heated_seats = "no";
        String nav_sys = "no";
        String bluetooth = "no";
        String elec_win = "no";
        String gps = "no";

        if (jRadioButton_Manual.isSelected()){gearbox = "manual";}

        if (jRadioButton_Features_AirBag.isSelected()){air_cond = "yes";}
        if (jRadioButton_Features_AirBag.isSelected()){airbags = "yes";}
        if (jRadioButton_Features_Sunroof.isSelected()){sunroof = "yes";}
        if (jRadioButton_Features_HeatedSeat.isSelected()){heated_seats = "yes";}
        if (jRadioButton_Features_Bluetooth.isSelected()){bluetooth = "yes";}
        if (jRadioButton_Features_NavSys.isSelected()){nav_sys = "yes";}
        if (jRadioButton_Features_ElecWin.isSelected()){elec_win = "yes";}
        if (jRadioButton_Features_GPS.isSelected()){gps = "yes";}

        /*
        */
        if (carVerify()){
            car.addCar(brand, model, fuel, color, plateNum, passengers, gearbox, price, air_cond,
                airbags, sunroof, heated_seats, nav_sys, bluetooth, elec_win, gps);
        }
    }//GEN-LAST:event_jButton_Add_Car_ActionPerformed

    private void jButton_Add_images_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_Add_images_MouseClicked
        // TODO add your handling code here:

        Form_CarImages form_crimgs = new Form_CarImages();
        form_crimgs.setVisible(true);
    }//GEN-LAST:event_jButton_Add_images_MouseClicked

    private void jButton_Add_images_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_images_ActionPerformed
        // TODO add your handling code here:
        // clear fields
    }//GEN-LAST:event_jButton_Add_images_ActionPerformed

    private void jButton_Add_Brands_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Brands_List_ActionPerformed
        // TODO add your handling code here:
        Form_BrandsList form_brdlist = new Form_BrandsList();
        form_brdlist.setVisible(true);
    }//GEN-LAST:event_jButton_Add_Brands_List_ActionPerformed

    private void jButton_Reset_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Reset_ActionPerformed
        // TODO add your handling code here:
        // To reset all fields

        jTextField_PlateNum.setText("");
        jTextField_Model.setText("");
        jSpinner_Price.setValue(10);
        jLabel_Brand_Id.setText("#");
        jSpinner_Passengers.setValue(0);
        jComboBox_Fuel.setSelectedIndex(0);
        jComboBox_Color.setSelectedIndex(0);
        jComboBox_brand.setSelectedIndex(0);
        jRadioButton_Automatic.setSelected(true);

        jRadioButton_Features_AirBag.setSelected(false);
        jRadioButton_Features_Aircond.setSelected(false);
        jRadioButton_Features_Bluetooth.setSelected(false);
        jRadioButton_Features_ElecWin.setSelected(false);
        jRadioButton_Features_GPS.setSelected(false);
        jRadioButton_Features_HeatedSeat.setSelected(false);
        jRadioButton_Features_NavSys.setSelected(false);
        jRadioButton_Features_Sunroof.setSelected(false);

    }//GEN-LAST:event_jButton_Reset_ActionPerformed

    private void jButton_Cars_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cars_List_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Cars_List_ActionPerformed

    private void jButton_Remove_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_Remove_MouseClicked
        // Delete Car
        int id = (int)jSpinner_Id.getValue();
    }//GEN-LAST:event_jButton_Remove_MouseClicked

    private void jButton_Remove_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove_ActionPerformed
        // Delete Car

        int id = (int)jSpinner_Id.getValue();
        car.removeCar(id);
    }//GEN-LAST:event_jButton_Remove_ActionPerformed

    private void jButton_Edit_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_ActionPerformed
        // Edit Car info
        // Car info
        int id = (int)jSpinner_Id.getValue();
        int brand = Integer.valueOf(jLabel_Brand_Id.getText());
        String model = jTextField_Model.getText();
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_PlateNum.getText();
        int passengers = (int)jSpinner_Passengers .getValue();
        String gearbox = "automatic";
        //int price = Integer.valueOf(jSpinner_Price.getValue());
        int price = (int)(jSpinner_Price.getValue());
        // car features
        String air_cond = "no";
        String airbags = "no";
        String sunroof = "no";
        String heated_seats = "no";
        String nav_sys = "no";
        String bluetooth = "no";
        String elec_win = "no";
        String gps = "no";

        if (jRadioButton_Manual.isSelected()){gearbox = "manual";}

        if (jRadioButton_Features_AirBag.isSelected()){air_cond = "yes";}
        if (jRadioButton_Features_AirBag.isSelected()){airbags = "yes";}
        if (jRadioButton_Features_Sunroof.isSelected()){sunroof = "yes";}
        if (jRadioButton_Features_HeatedSeat.isSelected()){heated_seats = "yes";}
        if (jRadioButton_Features_Bluetooth.isSelected()){bluetooth = "yes";}
        if (jRadioButton_Features_NavSys.isSelected()){nav_sys = "yes";}
        if (jRadioButton_Features_ElecWin.isSelected()){elec_win = "yes";}
        if (jRadioButton_Features_GPS.isSelected()){gps = "yes";}

        /*
        */
        if (carVerify()){
            car.editCar(id, brand, model, fuel, color, plateNum, passengers, gearbox, price, air_cond,
                airbags, sunroof, heated_seats, nav_sys, bluetooth, elec_win, gps);
        }
    }//GEN-LAST:event_jButton_Edit_ActionPerformed

    private void jTextField_PlateNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_PlateNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_PlateNumActionPerformed

    private void jRadioButton_AutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_AutomaticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_AutomaticActionPerformed

    private void jRadioButton_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ManualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_ManualActionPerformed

    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
        // edit brands info

        try {
            int id = (int) jSpinner_id.getValue();
            String name = jTextField_name.getText();
            byte[] logo;

            if (jLabel_imagePath.getText().trim().equals("")){
                logo = brands.getBrandById(id).getLogo();
            }
            else {
                logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            }
            if (brandVerify("edit")){
                brands.editBrand(id, name,logo);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" + ex.getMessage() , "Invalid Data", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);;
        }
    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed
        // remove brands

        int id = (int) jSpinner_id.getValue();
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this brand?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            brands.removeBrand(id);
        }

    }//GEN-LAST:event_jButton_RemoveActionPerformed

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        // Browse and display image
        String imagePath = selectImage();
        displayImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), imagePath, jLabel_logo);
        // Display image path
        jLabel_imagePath.setText(imagePath);

    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jButton_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RefreshActionPerformed
        // TODO add your handling code here:
        // refresh jTable
        populateJtableWithBrands();
    }//GEN-LAST:event_jButton_RefreshActionPerformed

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        // TODO add your handling code here:
        // clear fields
        jSpinner_id.setValue(0);
        jTextField_name.setText("");
        jLabel_logo.setIcon(null);
        jLabel_imagePath.setText("");
    }//GEN-LAST:event_jButton_ClearActionPerformed

    private void jButton_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FirstActionPerformed
        // TODO add your handling code here:
        //  Button first
        locationIndex = 0;
        displayBrand();

    }//GEN-LAST:event_jButton_FirstActionPerformed

    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NextActionPerformed
        // TODO add your handling code here:
        // Button Next
        locationIndex ++;
        if(locationIndex > brands_list.size()-1){ locationIndex = brands_list.size()-1;  }
        displayBrand();

    }//GEN-LAST:event_jButton_NextActionPerformed

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        // TODO add your handling code here:
        // Button previous
        locationIndex --;
        if(locationIndex < 0){ locationIndex = 0; }
        displayBrand();
    }//GEN-LAST:event_jButton_PreviousActionPerformed

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        // TODO add your handling code here:
        // button last
        locationIndex = brands_list.size()-1;
        displayBrand();
    }//GEN-LAST:event_jButton_LastActionPerformed

    private void jTable_BrandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BrandsMouseClicked
        // get the selected brands
        int index = jTable_Brands.getSelectedRow();
        int id = Integer.valueOf(jTable_Brands.getValueAt(index,0).toString());
        Classes.Brand brd = brands.getBrandById(id);
        jSpinner_id.setValue(brd.getId());
        jTextField_name.setText(brd.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), brd.getLogo(), jLabel_logo);
    }//GEN-LAST:event_jTable_BrandsMouseClicked

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        // add new brands

        try {
            String name = jTextField_name.getText();
            byte[] logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));

            if (brandVerify("add")){

                brands.addBrand(name,logo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Select Valid Logo" + ex.getMessage() , "Invalid Logo", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jButton_Edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit1ActionPerformed
        // edit Location info
        int id = (int) jSpinner_id.getValue();
        String city = jComboBox_City.getSelectedItem().toString();
        String address = jTextArea_Address.getText();

        if (locationVerify()){
            location.editLocation(id, city, address);
        }
    }//GEN-LAST:event_jButton_Edit1ActionPerformed

    private void jButton_Add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add1ActionPerformed
        // add new Location
        String city = jComboBox_City.getSelectedItem().toString();
        String address = jTextArea_Address.getText();

        if (locationVerify()){
            location.addLocation(city, address);
        }
    }//GEN-LAST:event_jButton_Add1ActionPerformed

    private void jButton_Refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Refresh1ActionPerformed
        // TODO add your handling code here:
        // refresh jTable

        populateJtableWithLocations();
    }//GEN-LAST:event_jButton_Refresh1ActionPerformed

    private void jButton_Clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Clear1ActionPerformed
        // TODO add your handling code here:
        // clear fields
        jSpinner_id.setValue(0);
        jComboBox_City.setSelectedIndex(0);
        jTextArea_Address.setText("");

    }//GEN-LAST:event_jButton_Clear1ActionPerformed

    private void jButton_Remove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove1ActionPerformed
        // remove Location
        int id = (int) jSpinner_id.getValue();
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this brand?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            location.removeLocation(id);

        }

    }//GEN-LAST:event_jButton_Remove1ActionPerformed

    private void jButton_First1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_First1ActionPerformed
        // TODO add your handling code here:
        //  Button first
        locationIndex = 0;
        displayLocation();
    }//GEN-LAST:event_jButton_First1ActionPerformed

    private void jButton_Next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Next1ActionPerformed
        // TODO add your handling code here:
        // Button Next
        locationIndex ++;
        if(locationIndex > locations_list.size()-1){ locationIndex = locations_list.size()-1;  }
        displayLocation();
    }//GEN-LAST:event_jButton_Next1ActionPerformed

    private void jButton_Previous1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Previous1ActionPerformed
        // TODO add your handling code here:
        // Button previous

        locationIndex --;
        if(locationIndex < 0){ locationIndex = 0;  }
        displayLocation();

    }//GEN-LAST:event_jButton_Previous1ActionPerformed

    private void jButton_Last1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Last1ActionPerformed
        // TODO add your handling code here:
        // button last
        locationIndex = locations_list.size()-1;
        displayLocation();
    }//GEN-LAST:event_jButton_Last1ActionPerformed

    private void jTable_LocationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_LocationsMouseClicked
        // get the selected brand

        int index = jTable_Locations.getSelectedRow();
        //int id = Integer.valueOf(jTable_Brands.getValueAt(locationIndex,0).toString());
        //Classes.Brand brd = brand.getBrandById(id);
        jSpinner_id.setValue(Integer.valueOf(jTable_Locations.getValueAt(index,0).toString()));
        jComboBox_City.setSelectedItem(jTable_Locations.getValueAt(index,1  ).toString());
        jTextArea_Address.setText(jTable_Locations.getValueAt(index, 2).toString());
    }//GEN-LAST:event_jTable_LocationsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookCarPanel;
    private javax.swing.JPanel bookCarTab;
    private javax.swing.JPanel brandPanel;
    private javax.swing.JPanel brandTab;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel customerTab;
    private javax.swing.JPanel homePanel;
    private javax.swing.JPanel homeTab;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Add1;
    private javax.swing.JButton jButton_Add_Brands_List_;
    private javax.swing.JButton jButton_Add_Car_;
    private javax.swing.JButton jButton_Add_images_;
    private javax.swing.JButton jButton_Cars_List_;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Clear1;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_Edit1;
    private javax.swing.JButton jButton_Edit_;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_First1;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Last1;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Next1;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_Previous1;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JButton jButton_Refresh1;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_Remove1;
    private javax.swing.JButton jButton_Remove_;
    private javax.swing.JButton jButton_Reset_;
    private javax.swing.JButton jButton_Search_;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JComboBox<String> jComboBox_City;
    private javax.swing.JComboBox<String> jComboBox_Color;
    private javax.swing.JComboBox<String> jComboBox_Fuel;
    private javax.swing.JComboBox<String> jComboBox_brand;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Brand_Id;
    private javax.swing.JLabel jLabel_cars_logo;
    private javax.swing.JLabel jLabel_cars_logo2;
    private javax.swing.JLabel jLabel_cars_logo3;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton_Automatic;
    private javax.swing.JRadioButton jRadioButton_Features_AirBag;
    private javax.swing.JRadioButton jRadioButton_Features_Aircond;
    private javax.swing.JRadioButton jRadioButton_Features_Bluetooth;
    private javax.swing.JRadioButton jRadioButton_Features_ElecWin;
    private javax.swing.JRadioButton jRadioButton_Features_GPS;
    private javax.swing.JRadioButton jRadioButton_Features_HeatedSeat;
    private javax.swing.JRadioButton jRadioButton_Features_NavSys;
    private javax.swing.JRadioButton jRadioButton_Features_Sunroof;
    private javax.swing.JRadioButton jRadioButton_Manual;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner_Id;
    private javax.swing.JSpinner jSpinner_Passengers;
    private javax.swing.JSpinner jSpinner_Price;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JSpinner jSpinner_id1;
    private javax.swing.JTable jTable_Brands;
    private javax.swing.JTable jTable_Locations;
    private javax.swing.JTextArea jTextArea_Address;
    private javax.swing.JTextField jTextField_Model;
    private javax.swing.JTextField jTextField_PlateNum;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JPanel locationPanel;
    private javax.swing.JPanel locationTab;
    private javax.swing.JPanel vehiclePanel;
    private javax.swing.JPanel vehicleTab;
    // End of variables declaration//GEN-END:variables
}
