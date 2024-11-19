/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import Classes.Brand;
import Classes.Car;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Brand brand = new Brand();
    HashMap<String , Integer> map = brand.brandsHashMap();
    
    //Classes.Brand brand = new Classes.Brand();
    ArrayList<Classes.Brand> brands_list = brand.brandsList();
     int index = 0;
    
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        addResizeListener();
        
        //Radio button
        
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(jRadioButton_Automatic);
        btn_group.add(jRadioButton_Manual);
        
        populateComboBoxBrands();
        
        // populate Jtables
        populateJtableWithBrands();
    }
    
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
    
    // gagawa ng function para ipulate the jTable with brand (id & name )
    public void populateJtableWithBrands(){
        
       // Clear array List
       brands_list.clear();
       // populated arrayList
       brands_list = brand.brandsList();
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
        Classes.Brand selected_brand = brands_list.get(index);
        jSpinner_id.setValue(selected_brand.getId());
        jTextField_name.setText(selected_brand.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), selected_brand.getLogo(), jLabel_logo);
    }

    public void populateComboBoxBrands()
    {
        for ( String s : map.keySet())
        {
            jComboBox_brand.addItem(s);
        }
    }
    
    public boolean verify()
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

    // Method to handle resizing
    private void addResizeListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage();
            }
        });
    }

    // Method to resize the image
    private void resizeImage() {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/homepage.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(jp1.getWidth(), jp1.getHeight(), Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(scaledImage));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public boolean verify(String add_or_edit)
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        tab1 = new javax.swing.JPanel();
        jpanel_home = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel_cars_logo3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel_cars_logo2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_cars_logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jp2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_brand = new javax.swing.JComboBox<>();
        jTextField_Model = new javax.swing.JTextField();
        jComboBox_Fuel = new javax.swing.JComboBox<>();
        jComboBox_Color = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jRadioButton_Features_Aircond = new javax.swing.JRadioButton();
        jRadioButton_Features_AirBag = new javax.swing.JRadioButton();
        jRadioButton_Features_Sunroof = new javax.swing.JRadioButton();
        jRadioButton_Features_HeatedSeat = new javax.swing.JRadioButton();
        jRadioButton_Features_NavSys = new javax.swing.JRadioButton();
        jRadioButton_Features_Bluetooth = new javax.swing.JRadioButton();
        jRadioButton_Features_ElecWin = new javax.swing.JRadioButton();
        jRadioButton_Features_GPS = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jButton_Add_Car_ = new javax.swing.JButton();
        jButton_Add_images_ = new javax.swing.JButton();
        jButton_Add_Brands_List_ = new javax.swing.JButton();
        jButton_Reset_ = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        int max = 10000;
        int min = 10;
        int step = 1;
        int i = 10;
        SpinnerModel spinner_model = new SpinnerNumberModel(i,min,max,step);
        jSpinner_Price = new javax.swing.JSpinner(spinner_model);
        jRadioButton_Automatic = new javax.swing.JRadioButton();
        jRadioButton_Manual = new javax.swing.JRadioButton();
        int max_2 = 14;
        int min_2 = 2;
        int step_2 = 1;
        int i_2 = 2;
        SpinnerModel spinner_model_2 = new SpinnerNumberModel(i_2,min_2,max_2,step_2);
        jSpinner_Passengers = new javax.swing.JSpinner(spinner_model_2);
        jTextField_Class = new javax.swing.JTextField();
        jLabel_Brand_Id = new javax.swing.JLabel();
        jp3 = new javax.swing.JPanel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jLabel_logo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton_Add = new javax.swing.JButton();
        jButton_Edit = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_browse = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Brands = new javax.swing.JTable();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        jLabel_imagePath = new javax.swing.JLabel();
        jp4 = new javax.swing.JPanel();
        jp5 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab1.setOpaque(false);
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });

        jpanel_home.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_home.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jpanel_home.setForeground(new java.awt.Color(255, 255, 255));
        jpanel_home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanel_home.setText("HOME");
        jpanel_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel_home, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel_home)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 6, -1, -1));

        tab2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2.setOpaque(false);
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("VEHICLE");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 6, -1, -1));

        tab3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab3.setOpaque(false);
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("BRAND");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(952, 6, -1, -1));

        tab4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4.setOpaque(false);
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("LOCATION");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1054, 6, -1, -1));

        tab5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab5.setOpaque(false);
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CUSTOMER");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1156, 6, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        jp1.setBackground(new java.awt.Color(255, 255, 255));
        jp1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(158, 158, 196));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel7.setOpaque(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel_cars_logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jp1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        jPanel12.setBackground(new java.awt.Color(158, 158, 196));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel12.setOpaque(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
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
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jp1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jPanel10.setBackground(new java.awt.Color(158, 158, 196));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel10.setOpaque(false);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel11.setOpaque(false);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Vehicles");

        jLabel_cars_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_cars_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jp1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homepage.png"))); // NOI18N
        jp1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 580));

        jPanel2.add(jp1, "card2");

        jp2.setBackground(new java.awt.Color(255, 255, 255));
        jp2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Brand:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Model:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Color:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("Fuel:");

        jComboBox_brand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_brand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brandActionPerformed(evt);
            }
        });

        jTextField_Model.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Model.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ModelActionPerformed(evt);
            }
        });

        jComboBox_Fuel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Fuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gas", "Diesel" }));
        jComboBox_Fuel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_FuelActionPerformed(evt);
            }
        });

        jComboBox_Color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "White", "Black", "Grey", "Red", "Dark Green" }));
        jComboBox_Color.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_AirBag, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_Aircond, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_Bluetooth, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_NavSys))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_HeatedSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_Sunroof, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Features_ElecWin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Features_GPS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton_Features_Aircond)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Features_AirBag))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_NavSys)
                            .addComponent(jRadioButton_Features_ElecWin)
                            .addComponent(jRadioButton_Features_HeatedSeat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_Bluetooth)
                            .addComponent(jRadioButton_Features_GPS)
                            .addComponent(jRadioButton_Features_Sunroof))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel12.setText("Features");

        jButton_Add_Car_.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Add_Car_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Car_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add_Car_.setText("Add");
        jButton_Add_Car_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Car_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Car_ActionPerformed(evt);
            }
        });

        jButton_Add_images_.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Add_images_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_images_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add_images_.setText("Add Car Images");
        jButton_Add_images_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_images_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_images_ActionPerformed(evt);
            }
        });

        jButton_Add_Brands_List_.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Add_Brands_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Brands_List_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add_Brands_List_.setText("Brands");
        jButton_Add_Brands_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Brands_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Brands_List_ActionPerformed(evt);
            }
        });

        jButton_Reset_.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Reset_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Reset_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Reset_.setText("Reset");
        jButton_Reset_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Reset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Reset_ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Plate Number:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel14.setText("Seats:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel15.setText("Transmission:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel16.setText("Price/Day:");

        jSpinner_Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Price.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton_Automatic.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Automatic.setText("Automatic");
        jRadioButton_Automatic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Automatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_AutomaticActionPerformed(evt);
            }
        });

        jRadioButton_Manual.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Manual.setText("Manual");
        jRadioButton_Manual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ManualActionPerformed(evt);
            }
        });

        jSpinner_Passengers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Passengers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextField_Class.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Class.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_Class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ClassActionPerformed(evt);
            }
        });

        jLabel_Brand_Id.setText("#");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Add_images_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_brand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Model)
                            .addComponent(jComboBox_Fuel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox_Color, 0, 153, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 457, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner_Price, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(7, 7, 7)
                                .addComponent(jRadioButton_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(137, 137, 137))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Brand_Id))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Add_images_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jRadioButton_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jp2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 580));

        jPanel2.add(jp2, "card3");

        jp3.setBackground(new java.awt.Color(255, 255, 255));

        jSpinner_id.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("ID:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel17.setText("Name:");

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel_logo.setOpaque(true);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setText("Logo:");

        jButton_Add.setBackground(new java.awt.Color(204, 102, 0));
        jButton_Add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add.setText("Add");
        jButton_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        jButton_Edit.setBackground(new java.awt.Color(204, 102, 0));
        jButton_Edit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Edit.setText("Edit");
        jButton_Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Remove.setBackground(new java.awt.Color(204, 102, 0));
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

        jButton_Refresh.setBackground(new java.awt.Color(0, 0, 0));
        jButton_Refresh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Refresh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Refresh.setText("Refresh");
        jButton_Refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(0, 0, 0));
        jButton_Clear.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Clear.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Clear.setText("Clear");
        jButton_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
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

        jButton_First.setBackground(new java.awt.Color(204, 102, 0));
        jButton_First.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_First.setForeground(new java.awt.Color(255, 255, 255));
        jButton_First.setText("<<");
        jButton_First.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FirstActionPerformed(evt);
            }
        });

        jButton_Next.setBackground(new java.awt.Color(204, 102, 0));
        jButton_Next.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Next.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Next.setText(">");
        jButton_Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setBackground(new java.awt.Color(204, 102, 0));
        jButton_Previous.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Previous.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Previous.setText("<");
        jButton_Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setBackground(new java.awt.Color(204, 102, 0));
        jButton_Last.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Last.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Last.setText(">>");
        jButton_Last.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastActionPerformed(evt);
            }
        });

        jLabel_imagePath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_imagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jp3Layout = new javax.swing.GroupLayout(jp3);
        jp3.setLayout(jp3Layout);
        jp3Layout.setHorizontalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(812, Short.MAX_VALUE))
            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jp3Layout.createSequentialGroup()
                    .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp3Layout.createSequentialGroup()
                            .addContainerGap(606, Short.MAX_VALUE)
                            .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60))
                        .addGroup(jp3Layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(18, 18, 18)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField_name, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jSpinner_id))
                            .addGap(18, 18, 18)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(87, 87, 87)))
        );
        jp3Layout.setVerticalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp3Layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_browse)
                .addGap(27, 27, 27)
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136))
            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jp3Layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jp3Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp3Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSpinner_id)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                    .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(140, 140, 140)))
        );

        jPanel2.add(jp3, "card4");

        jp4.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jp4Layout = new javax.swing.GroupLayout(jp4);
        jp4.setLayout(jp4Layout);
        jp4Layout.setHorizontalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1263, Short.MAX_VALUE)
        );
        jp4Layout.setVerticalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        jPanel2.add(jp4, "card5");

        jp5.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jp5Layout = new javax.swing.GroupLayout(jp5);
        jp5.setLayout(jp5Layout);
        jp5Layout.setHorizontalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1263, Short.MAX_VALUE)
        );
        jp5Layout.setVerticalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        jPanel2.add(jp5, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        jp5.setVisible(true);
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jp4.setVisible(false);
    }//GEN-LAST:event_tab5MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        jp4.setVisible(true);
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jp5.setVisible(false);
    }//GEN-LAST:event_tab4MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        jp3.setVisible(true);
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp4.setVisible(false);
        jp5.setVisible(false);
    }//GEN-LAST:event_tab3MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        jp2.setVisible(true);
        jp1.setVisible(false);
        jp3.setVisible(false);
        jp4.setVisible(false);
        jp5.setVisible(false);
    }//GEN-LAST:event_tab2MouseClicked

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jp4.setVisible(false);
        jp5.setVisible(false);
    }//GEN-LAST:event_tab1MouseClicked

    private void jTextField_ClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ClassActionPerformed

    private void jRadioButton_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ManualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_ManualActionPerformed

    private void jRadioButton_AutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_AutomaticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_AutomaticActionPerformed

    private void jButton_Reset_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Reset_ActionPerformed
        // TODO add your handling code here:
        // To reset all fields

        jTextField_Class.setText("");
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

    private void jButton_Add_Brands_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Brands_List_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Add_Brands_List_ActionPerformed

    private void jButton_Add_images_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_images_ActionPerformed
        // TODO add your handling code here:
        // clear fields
    }//GEN-LAST:event_jButton_Add_images_ActionPerformed

    private void jButton_Add_Car_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Car_ActionPerformed
        // TODO add your handling code here:
        // Add new Car
        // Car info
        int brand = Integer.valueOf(jLabel_Brand_Id.getText());
        String model = jTextField_Model.getText();
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_Class.getText();
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
        if (verify()){
            car.addCar(brand, model, fuel, color, plateNum, passengers, gearbox, price, air_cond,
                airbags, sunroof, heated_seats, nav_sys, bluetooth, elec_win, gps);
        }
    }//GEN-LAST:event_jButton_Add_Car_ActionPerformed

    private void jRadioButton_Features_GPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_GPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_GPSActionPerformed

    private void jRadioButton_Features_ElecWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_ElecWinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_ElecWinActionPerformed

    private void jRadioButton_Features_SunroofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_SunroofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_SunroofActionPerformed

    private void jRadioButton_Features_AircondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_AircondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_AircondActionPerformed

    private void jComboBox_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_FuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_FuelActionPerformed

    private void jTextField_ModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ModelActionPerformed

    private void jComboBox_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brandActionPerformed
        // TODO add your handling code here:
        // Get the selected brand id
        jLabel_Brand_Id.setText(map.get(jComboBox_brand.getSelectedItem().toString()).toString());
    }//GEN-LAST:event_jComboBox_brandActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        // add new brand

        try {
            String name = jTextField_name.getText();
            byte[] logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));

            if (verify("add")){

                brand.addBrand(name,logo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Select Valid Logo" + ex.getMessage() , "Invalid Logo", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
        // edit brand info

        try {
            int id = (int) jSpinner_id.getValue();
            String name = jTextField_name.getText();
            byte[] logo;

            if (jLabel_imagePath.getText().trim().equals("")){
                logo = brand.getBrandById(id).getLogo();
            }
            else {
                logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            }
            if (verify("edit")){
                brand.editBrand(id, name,logo);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" + ex.getMessage() , "Invalid Data", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);;
        }
    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed
        // remove brand

        int id = (int) jSpinner_id.getValue();
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this brand?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            brand.removeBrand(id);
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

    private void jTable_BrandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BrandsMouseClicked
        // get the selected brand
        int index = jTable_Brands.getSelectedRow();
        int id = Integer.valueOf(jTable_Brands.getValueAt(index,0).toString());
        Classes.Brand brd = brand.getBrandById(id);
        jSpinner_id.setValue(brd.getId());
        jTextField_name.setText(brd.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), brd.getLogo(), jLabel_logo);
    }//GEN-LAST:event_jTable_BrandsMouseClicked

    private void jButton_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FirstActionPerformed
        // TODO add your handling code here:
        //  Button first
        index = 0;
        displayBrand();

    }//GEN-LAST:event_jButton_FirstActionPerformed

    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NextActionPerformed
        // TODO add your handling code here:
        // Button Next
        index ++;
        if(index > brands_list.size()-1){ index = brands_list.size()-1;  }
        displayBrand();

    }//GEN-LAST:event_jButton_NextActionPerformed

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        // TODO add your handling code here:
        // Button previous
        index --;
        if(index < 0){ index = 0; }
        displayBrand();
    }//GEN-LAST:event_jButton_PreviousActionPerformed

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        // TODO add your handling code here:
        // button last
        index = brands_list.size()-1;
        displayBrand();
    }//GEN-LAST:event_jButton_LastActionPerformed

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
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Add_Brands_List_;
    private javax.swing.JButton jButton_Add_Car_;
    private javax.swing.JButton jButton_Add_images_;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_Reset_;
    private javax.swing.JButton jButton_browse;
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
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
    private javax.swing.JSpinner jSpinner_Passengers;
    private javax.swing.JSpinner jSpinner_Price;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_Brands;
    private javax.swing.JTextField jTextField_Class;
    private javax.swing.JTextField jTextField_Model;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JPanel jp3;
    private javax.swing.JPanel jp4;
    private javax.swing.JPanel jp5;
    private javax.swing.JLabel jpanel_home;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    // End of variables declaration//GEN-END:variables
}
