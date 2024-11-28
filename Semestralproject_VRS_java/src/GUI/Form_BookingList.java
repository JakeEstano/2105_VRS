/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Booking;
import Classes.Car;
import Classes.Customer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estan
 */
public class Form_BookingList extends javax.swing.JFrame {

    /**
     * Creates new form Form_BookingList
     */
    
    
    Classes.Booking booking = new Classes.Booking();
    ArrayList<Classes.Booking> book_list = booking.bookingList();
    public Form_BookingList() {
        initComponents();
        
        //center the form
        this.setLocationRelativeTo(null);
        
        //populate jtable
        populateJtableWithBooking();
        // set jtable row height 
        jTable_booking_.setRowHeight(30);
    }
    
    
//    public void loadBookingData() {
//    Booking booking = new Booking();  // Create a Booking instance
//   // ArrayList<Booking> bookingList = booking.getAllBookings();  // Fetch updated booking data
//
//    // Get the table model and clear any existing data
//    DefaultTableModel model = (DefaultTableModel) jTable_booking_.getModel();
//    model.setRowCount(0);  // Clear existing rows
//
//    // Populate the table with new booking data
//    for (Booking b : bookingList) {
//        model.addRow(new Object[]{
//            b.getId(),         // Booking ID
//            b.getCar_id(),     // Car ID
//            b.getCustomer_id(),// Customer ID
//            b.getStart_date(), // Pickup Date
//            b.getEnd_date(),   // Dropoff Date
//            b.getTotal_price(),// Total Price
//            b.getDriver(),     // Driver Option
//            b.getDriverName()  // Driver Name
//        });
//    }
//}
//
//    
    
    
    public void populateJtableWithBooking() {
    // Get the list of bookings
    ArrayList<Classes.Booking> booking_list = booking.bookingList();

    // Debugging: print the number of bookings fetched
    System.out.println("Number of bookings: " + booking_list.size());

    String[] columnsName = {"Car Model", "Customer Name", "Start Date", "End Date", "Total Price", "Driver", "Driver Name", "Plate Number"};

    // If there are no bookings, show a message and clear the table
    if (booking_list.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No bookings to display!", "Information", JOptionPane.INFORMATION_MESSAGE);
        jTable_booking_.setModel(new DefaultTableModel(new Object[0][columnsName.length], columnsName));
        return;
    }

    // Prepare the rows for the table
    Object[][] rows = new Object[booking_list.size()][columnsName.length];
    for (int i = 0; i < booking_list.size(); i++) {
        Classes.Booking booking = booking_list.get(i);
        
        // Get Car Model (assuming you have a method to fetch the car by ID)
        Car car = new Car();
        car = car.getCarById(booking.getCar_id());  // Assuming the booking has car_id
        String carModel = car != null ? car.getModel() : "Unknown";
        String plateNumber = car != null ? car.getplateNum_() : "Unknown";
        // Get Customer Name (assuming you have a method to fetch the customer by ID)
        Customer customer = new Customer();
        customer = customer.getCustomerById(booking.getCustomer_id());  // Assuming the booking has customer_id
        String customerName = customer != null ? customer.getFullname(): "Unknown";

        // Get Driver Name (assuming the driver is a reference or ID that you can fetch)
        String driverName = booking.getDriverName();
        if (booking.getDriver().equals("With Driver") && driverName != null) {
            driverName = driverName; // use driverName if available
        } else {
            driverName = "Self Drive";
        }

        // Populate rows for the table
        rows[i][0] = carModel;           // Car Model
        rows[i][1] = customerName;       // Customer Name
        rows[i][2] = booking.getStart_date();   // Start Date
        rows[i][3] = booking.getEnd_date();     // End Date
        rows[i][4] = booking.getTotal_price();  // Total Price
        rows[i][5] = booking.getDriver();       // Driver (Self Drive / With Driver)
        rows[i][6] = driverName;   // Driver Name
        rows[i][7] = plateNumber;
    }

    // Set table model with the updated rows
    jTable_booking_.setModel(new DefaultTableModel(rows, columnsName));
}

    
    
    
//    public void populateJtableWithBooking() {
//    // Get the list of bookings
//    ArrayList<Classes.Booking> booking_list = booking.bookingList();
//
//    // Debugging: print the number of bookings fetched
//    System.out.println("Number of bookings: " + booking_list.size());
//
//    String[] columnsName = {"ID", "Car ID", "Customer ID", "Start Date", "End Date", "Total Price", "Driver", "Driver Name"};
//
//    // If there are no bookings, show a message and clear the table
//    if (booking_list.isEmpty()) {
//        JOptionPane.showMessageDialog(null, "No bookings to display!", "Information", JOptionPane.INFORMATION_MESSAGE);
//        jTable_booking_.setModel(new DefaultTableModel(new Object[0][columnsName.length], columnsName));
//        return;
//    }
//
//    // Prepare the rows for the table
//    Object[][] rows = new Object[booking_list.size()][columnsName.length];
//    for (int i = 0; i < booking_list.size(); i++) {
//    Classes.Booking booking = booking_list.get(i);
//    rows[i][0] = booking.getId();           // ID
//    rows[i][1] = booking.getCar_id();       // Car ID
//    rows[i][2] = booking.getCustomer_id();  // Customer ID
//    rows[i][3] = booking.getStart_date();   // Start Date
//    rows[i][4] = booking.getEnd_date();     // End Date
//    rows[i][5] = booking.getTotal_price();  // Total Price
//    rows[i][6] = booking.getDriver();       // Driver
//    rows[i][7] = booking.getDriverName();   // Driver Name
//}
//    
////    for (int i = 0; i < booking_list.size(); i++) {
////        rows[i][0] = booking_list.get(i).getId();
////        rows[i][1] = booking_list.get(i).getCar_id();
////        rows[i][2] = booking_list.get(i).getCustomer_id();
////        rows[i][3] = booking_list.get(i).getStart_date();
////        rows[i][4] = booking_list.get(i).getEnd_date();
////        rows[i][5] = booking_list.get(i).getTotal_price();
////        rows[i][6] = booking_list.get(i).getDriver();
////        rows[i][7] = booking_list.get(i).getDriverName();
////    }
//
//    // Update the table model
//    DefaultTableModel model = new DefaultTableModel(rows, columnsName);
//    jTable_booking_.setModel(model);
//}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_booking_ = new javax.swing.JTable();
        jButton_Select_Booking_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1142, 592));

        jPanel3.setBackground(new java.awt.Color(255, 212, 60));

        jLabel4.setBackground(java.awt.Color.black);
        jLabel4.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Booking List");

        jLabel_brands_logo.setBackground(java.awt.Color.black);

        jLabel_close1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
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
                .addGap(254, 254, 254)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        jTable_booking_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_booking_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_booking_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_booking_MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_booking_);

        jButton_Select_Booking_.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_Select_Booking_.setText("Select Boooking");
        jButton_Select_Booking_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Select_Booking_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addComponent(jButton_Select_Booking_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(jButton_Select_Booking_, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jTable_booking_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_booking_MouseClicked
        // get the selected customer

    }//GEN-LAST:event_jTable_booking_MouseClicked

    private void jButton_Select_Booking_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Select_Booking_ActionPerformed
        // get the selected customer booking
        int index = jTable_booking_.getSelectedRow();
        String id = jTable_booking_.getValueAt(index, 0).toString();
        String car_id = jTable_booking_.getValueAt(index, 1).toString();
        String customer_id = jTable_booking_.getValueAt(index, 5).toString();
        String start_date = jTable_booking_.getValueAt(index, 0).toString();
        String end_date = jTable_booking_.getValueAt(index, 1).toString();
        String total_price = jTable_booking_.getValueAt(index, 5).toString();
        String driver = jTable_booking_.getValueAt(index, 1).toString();
        String driverName = jTable_booking_.getValueAt(index, 5).toString();

        Form_Booking_Edit_Remove.displayBooking(id, car_id, customer_id, start_date, end_date, total_price, driver, driverName);

        this.dispose();
    }//GEN-LAST:event_jButton_Select_Booking_ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_BookingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_BookingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_BookingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_BookingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_BookingList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Select_Booking_;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_brands_logo;
    private javax.swing.JLabel jLabel_close1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_booking_;
    // End of variables declaration//GEN-END:variables
}
