/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jake
 * @author Saipoden
 */
public class Form_Brands extends javax.swing.JFrame {
    JFrame frame = new JFrame();
    /**
     * Creates new form Form_Brands
     */
    
    Classes.Brand brand = new Classes.Brand();
    ArrayList<Classes.Brand> brands_list = brand.brandsList();
     int index = 0;
    public Form_Brands() {
        initComponents();
        
        // center the form
        this.setLocationRelativeTo(null);
        
        // populate Jtables
        populateJtableWithBrands();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // To Verify the empty fields
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

        jLabel_close = new javax.swing.JLabel();
        jLabel_imagePath = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Brands = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner_id = new javax.swing.JSpinner();
        jTextField_name = new javax.swing.JTextField();
        jLabel_logo = new javax.swing.JLabel();
        jButton_browse = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_Edit = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();

        jLabel_close.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.setText("X");
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        jLabel_imagePath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_imagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_imagePath.setText("#########");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1142, 592));

        jPanel3.setBackground(java.awt.Color.black);

        jLabel4.setBackground(java.awt.Color.black);
        jLabel4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Brands");

        jLabel_brands_logo.setBackground(java.awt.Color.black);

        jLabel_close1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_close1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close1.setText("X");
        jLabel_close1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_close1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(435, 435, 435)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Logo:");

        jSpinner_id.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel_logo.setOpaque(true);

        jButton_browse.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton_browse.setText("Browse");
        jButton_browse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jSpinner_id))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner_id)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_browse)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        // TODO add your handling code here:
        // Button previous
        index --;
        if(index < 0){ index = 0; }
        displayBrand();
    }//GEN-LAST:event_jButton_PreviousActionPerformed

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

    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed
        // remove brand

        int id = (int) jSpinner_id.getValue();
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this brand?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            brand.removeBrand(id);   
        }
       

    }//GEN-LAST:event_jButton_RemoveActionPerformed

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

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        // TODO add your handling code here:
        // button last
        index = brands_list.size()-1;
        displayBrand();
    }//GEN-LAST:event_jButton_LastActionPerformed

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // close the form
        this.dispose();
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
        this.dispose();
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        // Browse and display image
         String imagePath = selectImage();
         displayImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), imagePath, jLabel_logo);
         // Display image path
         jLabel_imagePath.setText(imagePath);
        
    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jTable_BrandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BrandsMouseClicked
        // get the selected brand
        int index = jTable_Brands.getSelectedRow();
        int id = Integer.valueOf(jTable_Brands.getValueAt(index,0).toString());
        Classes.Brand brd = brand.getBrandById(id);
        jSpinner_id.setValue(brd.getId());
        jTextField_name.setText(brd.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), brd.getLogo(), jLabel_logo);
    }//GEN-LAST:event_jTable_BrandsMouseClicked

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
            java.util.logging.Logger.getLogger(Form_Brands.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Brands.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Brands.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Brands.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Brands().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_brands_logo;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_close1;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_Brands;
    private javax.swing.JTextField jTextField_name;
    // End of variables declaration//GEN-END:variables
}
