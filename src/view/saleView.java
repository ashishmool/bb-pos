/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.*;
import java.util.*;
import javax.swing.*;
import controller.*;
import database.*;
import model.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.time.*;
import java.time.format.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Go
 */
public class saleView extends javax.swing.JPanel {

    LocalDate currentDate = LocalDate.now();
    // Format the date using a desired pattern
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = currentDate.format(formatter);
    
    public saleView() {
       initComponents();
       
       retrieveLastInvoice(); //Retrieve Last Invoice ID
       loadCustomers(); //load Data for Customer ComboBox
       loadProducts(); //load Data for Product ComboBox
       
       //Date Formatting
       txtDate.setText(formattedDate);
       txtDate.setEnabled(false);
       
       

    }
    
    public void retrieveLastInvoice(){
        saleController controller = new saleController();
        int lastInvoice = controller.loadLastInvoiceNumber();
        processLastInvoice(lastInvoice);
    }
    
    private void processLastInvoice(int lastInvoice){
        
        lblInvoiceid.setText(String.valueOf(lastInvoice+1));
    }
    
    private void loadCustomers(){
        ArrayList<customerModel> allCustomer = customerController.getAllCustomers();
        
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        
        
        for (customerModel customer : allCustomer) {
        String customerInfo = customer.getCustomer_name();
        comboBoxModel.addElement(customerInfo);
        comboCustomer.addItem(customerInfo);
        }

        JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);
        
    }
    
    private void loadProducts(){
         ArrayList<productModel> allProduct = productController.getAllProducts();
        
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        
        
        for (productModel product : allProduct) {
        String productInfo = product.getName();
        comboBoxModel.addElement(productInfo);
        comboProduct.addItem(productInfo);
        }

        JComboBox<String> comboBox2 = new JComboBox<>(comboBoxModel);
       
    }
    
    private void loadProductData(){
        
    ArrayList<productModel> productList = productController.getAllProducts();

    if (!productList.isEmpty()) {
        productModel product = productList.get(4); // Assuming you want to display data from the first product in the list

        lblUnitPrice.setText(String.valueOf(product.getPrice()));
        
       
    }
    else {
    
    }
        
    }
    
    private void calculateProductTotal(){
        String qtyText = txtQty.getText();
        if (!qtyText.isEmpty()) {
        Double qt = Double.valueOf(qtyText);
        Double price = Double.valueOf(lblUnitPrice.getText());
        Double tot = qt * price;
        
        lblTotalPrice.setText(String.valueOf(tot));
        }
    }
    
    
    
     public double calculateCartTotal(DefaultTableModel model){
        
         
         if (txtPaidamt.getText()!=null){
        double total = 0.0;
        int rowCount = model.getRowCount();
        int totalPriceColumIndex = model.findColumn("Amount");
        
        for (int i = 0;i<rowCount;i++){
            double totalPrice = Double.parseDouble(model.getValueAt(i, totalPriceColumIndex).toString());
            total += totalPrice;
        }
        return total;
        }
        else{
            txtPaidamt.setText("0");
            
        }
        return 0;
        
    }
     
     
     public int calculateCartItems(DefaultTableModel model){
        int cartitems = 0;
        int rowCount = model.getRowCount();
        int totalQtyColumIndex = model.findColumn("Qty");
        
        for (int i = 0;i<rowCount;i++){
            int totalQty = Integer.parseInt(model.getValueAt(i, totalQtyColumIndex).toString());
            cartitems += totalQty;
        }
        return cartitems;
 
    }
     
     public double calculatePaymentChange(){
         double paidAmount;
         double change=0.0;
         String paidAmountString = txtPaidamt.getText();
         if (paidAmountString.isEmpty()){
             
           
            paidAmount=0.0;
            change = paidAmount-Double.parseDouble(lblCarttotal.getText());
            lblChangeamount.setText(String.valueOf(change));
            

         }
         else{
             paidAmount = Double.parseDouble(paidAmountString);
             change = paidAmount-Double.parseDouble(lblCarttotal.getText());
            lblChangeamount.setText(String.valueOf(change));
         }
         return change;

         }
         
     
     
     
   public void clearText(){
       comboCustomer.setEnabled(true);
       comboProduct.setSelectedItem(null);
       txtQty.setText("0");
       lblProductcode.setText(null);
       lblUnitPrice.setText("00.00");
       lblTotalPrice.setText("00.00");
       lblUnitget.setText(null);
       lblTotalqtydisplay.setText(null);
       lblChangeamount.setText("00.00");
       txtPaidamt.setText("0");
       billArea.setText("");
       
       // remove all
        DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
        dt.setRowCount(0);
        
        double totalAmount = calculateCartTotal(dt);
        System.out.println("Total Amount: " + totalAmount);
        lblCarttotal.setText(String.valueOf(totalAmount));

        double totalQty = calculateCartItems(dt);
        System.out.println("Total Quantity: " + totalQty);
        
       
    }  
    
   
   private void updateStock(){
       
       DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
    int rowCount = dt.getRowCount();

    for (int row = 0; row < rowCount; row++) {
        String productId = dt.getValueAt(row, 0).toString(); // Assuming the product ID is in the first column

        // Fetch the product quantity from the database
        int currentQuantity = productController.getQuantity(productId);
        System.out.println("Current Quantity"+currentQuantity);

        if (currentQuantity != 0) {
            int quantityPurchased = Integer.parseInt(dt.getValueAt(row, 4).toString()); // Assuming the quantity is in the third column
            System.out.println("Quantity Purchased Retrieved: "+quantityPurchased);
            int updatedQuantity = currentQuantity - quantityPurchased;
            System.out.println("Updated Quantity Calculated::: "+updatedQuantity);

            // Update the product's quantity in the database
            productModel product = new productModel("", "", productId, "", 0.0, updatedQuantity, "");
            boolean success = productController.updateProductAfterSale(product);

            if (!success) {
                JOptionPane.showMessageDialog(this, "Failed to update product stock for product ID: " + productId);
                // Handle the error or notify the user appropriately
            }
        }
    }
       
       
   }
   
    private void paymentPrint(){
       
        updateStock();
        
        //Bill Format
       String storeName = "Bhat-Bhateni Super Store";
       String phone = "Phone: +977-1-4420665, 4420666";
       String vatNo = "VAT No. 606222024";
       
       String billText = "\t"+storeName+"\n"
               + "\t"+phone + "\n"
               + "\t"+vatNo + "\n\n\n"
               ;
       billArea.setText(billText);
       billArea.setEditable(false);
       
        DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
        int rowCount = dt.getRowCount();
        int columnCount = dt.getColumnCount();
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                Object value = dt.getValueAt(row, column);
                sb.append(value).append("\t");
            }
            sb.append("\n");
        }
                    

        // Display the table in the JTextArea
        billArea.setText(billText+sb.toString());
        
        paymentModel payment;
        
     
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(txtDate.getText());
        } catch (ParseException ex) {
            Logger.getLogger(saleView.class.getName()).log(Level.SEVERE, null, ex);
        }
        payment = new paymentModel(Integer.parseInt(lblInvoiceid.getText()),
                comboCustomer.getSelectedItem().toString(),
                Integer.parseInt(lblTotalqtydisplay.getText()),
                Double.parseDouble(lblCarttotal.getText()),
                Double.parseDouble(txtPaidamt.getText()),
                Double.parseDouble(lblChangeamount.getText()),
                date);
        
        boolean result = saleController.addSale(payment);
        
        
        if (result){
            
            JOptionPane.showMessageDialog(this, "payment Added Successfully!");
            

                     // pluss new invoice
      int i = Integer.valueOf(lblInvoiceid.getText());
      i++;
      lblInvoiceid.setText(String.valueOf(i));
            clearText();

        }
        else{
            JOptionPane.showMessageDialog(this, "Error! payment could not be added...");
        }
        

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_invoiceid = new javax.swing.JPanel();
        lblInvoiceid = new javax.swing.JLabel();
        lblOrderId = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        pnlTop = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        lblProduct = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        lblUnitprice = new javax.swing.JLabel();
        lblUnitPrice = new javax.swing.JLabel();
        comboCustomer = new javax.swing.JComboBox<>();
        comboProduct = new javax.swing.JComboBox<>();
        txtQty = new javax.swing.JTextField();
        lblTotalprice = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        lblProductcode = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblUnit = new javax.swing.JLabel();
        lblUnitget = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        panel_sales = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();
        panel_buttons = new javax.swing.JPanel();
        txtPaidamt = new javax.swing.JTextField();
        lblPaidamt = new javax.swing.JLabel();
        btnPay = new javax.swing.JButton();
        panel_bottom = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblCarttotal = new javax.swing.JLabel();
        lblTotalamount = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        lblChangeamount = new javax.swing.JLabel();
        lblTotalqty = new javax.swing.JLabel();
        lblTotalqtydisplay = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        btnRemoveFromCart = new javax.swing.JButton();
        btnRemoveAll = new javax.swing.JButton();
        panel_bill = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        billArea = new javax.swing.JTextArea();

        panel_invoiceid.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblInvoiceid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInvoiceid.setText("01");

        lblOrderId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOrderId.setText("INVOICE NO :");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setText("Date:");

        txtDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDateKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_invoiceidLayout = new javax.swing.GroupLayout(panel_invoiceid);
        panel_invoiceid.setLayout(panel_invoiceidLayout);
        panel_invoiceidLayout.setHorizontalGroup(
            panel_invoiceidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_invoiceidLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOrderId)
                .addGap(18, 18, 18)
                .addComponent(lblInvoiceid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panel_invoiceidLayout.setVerticalGroup(
            panel_invoiceidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_invoiceidLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(panel_invoiceidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_invoiceidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDate))
                    .addGroup(panel_invoiceidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblInvoiceid)
                        .addComponent(lblOrderId)))
                .addContainerGap())
        );

        pnlTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCustomer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCustomer.setText("Customer :");

        lblProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProduct.setText("Product :");

        lblQty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQty.setText("Qty :");

        lblUnitprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUnitprice.setText("Unit Price :");

        lblUnitPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUnitPrice.setText("00.00");

        comboCustomer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select " }));
        comboCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCustomerActionPerformed(evt);
            }
        });

        comboProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select " }));
        comboProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductActionPerformed(evt);
            }
        });

        txtQty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtQty.setText("0");
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });

        lblTotalprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalprice.setText("Total Price :");

        lblTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalPrice.setText("00.00");

        lblProductcode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Product Code:");

        lblUnit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUnit.setText("Unit :");

        lblUnitget.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReset.setText("Clear Form");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(lblCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(lblProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblQty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProductcode, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(lblUnitprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(lblUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUnitget)))
                .addGap(32, 32, 32))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCustomer)
                        .addComponent(lblProduct)
                        .addComponent(lblQty)
                        .addComponent(comboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUnitprice)
                            .addComponent(lblUnitPrice))
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalprice)
                            .addComponent(lblTotalPrice))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductcode)
                    .addComponent(jLabel8)
                    .addComponent(lblUnit)
                    .addComponent(lblUnitget)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        panel_sales.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSale.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Description", "Unit", "Unit Price", "Qty", "Amount"
            }
        ));
        jScrollPane1.setViewportView(tblSale);

        javax.swing.GroupLayout panel_salesLayout = new javax.swing.GroupLayout(panel_sales);
        panel_sales.setLayout(panel_salesLayout);
        panel_salesLayout.setHorizontalGroup(
            panel_salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_salesLayout.setVerticalGroup(
            panel_salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_buttons.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPaidamt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtPaidamt.setText("0");
        txtPaidamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaidamtActionPerformed(evt);
            }
        });
        txtPaidamt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaidamtKeyReleased(evt);
            }
        });

        lblPaidamt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPaidamt.setText("Paid Amount :");

        btnPay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPay.setForeground(new java.awt.Color(204, 0, 0));
        btnPay.setText("Pay & Print");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_buttonsLayout = new javax.swing.GroupLayout(panel_buttons);
        panel_buttons.setLayout(panel_buttonsLayout);
        panel_buttonsLayout.setHorizontalGroup(
            panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_buttonsLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaidamt, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(panel_buttonsLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblPaidamt)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_buttonsLayout.setVerticalGroup(
            panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_buttonsLayout.createSequentialGroup()
                .addComponent(lblPaidamt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPaidamt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPay)
                .addGap(18, 18, 18))
        );

        panel_bottom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCarttotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCarttotal.setText("00.00");
        lblCarttotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotalamount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalamount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalamount.setText("Total Amount :");

        lblChange.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChange.setText("Change :");

        lblChangeamount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChangeamount.setText("00.00");
        lblChangeamount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalamount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChangeamount, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(lblCarttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalamount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCarttotal, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChangeamount, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        lblTotalqty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalqty.setText("Total Qty :");

        lblTotalqtydisplay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnRemoveFromCart.setText("Remove");
        btnRemoveFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromCartActionPerformed(evt);
            }
        });

        btnRemoveAll.setText("Remove All");
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_bottomLayout = new javax.swing.GroupLayout(panel_bottom);
        panel_bottom.setLayout(panel_bottomLayout);
        panel_bottomLayout.setHorizontalGroup(
            panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bottomLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_bottomLayout.createSequentialGroup()
                        .addComponent(lblTotalqty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalqtydisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemoveAll, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_bottomLayout.setVerticalGroup(
            panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bottomLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_bottomLayout.createSequentialGroup()
                        .addGroup(panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalqty, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(lblTotalqtydisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveAll, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panel_bill.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        billArea.setColumns(20);
        billArea.setRows(5);
        jScrollPane2.setViewportView(billArea);

        javax.swing.GroupLayout panel_billLayout = new javax.swing.GroupLayout(panel_bill);
        panel_bill.setLayout(panel_billLayout);
        panel_billLayout.setHorizontalGroup(
            panel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_billLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_billLayout.setVerticalGroup(
            panel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_billLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_invoiceid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_sales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_bill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_invoiceid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_sales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_bill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_buttons, javax.swing.GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductActionPerformed
        // load unit price
        
        productModel product = productController.getProduct((String) comboProduct.getSelectedItem());
        if(product != null){
           double price = product.getPrice();
            lblUnitPrice.setText(String.valueOf(price));
            lblUnitget.setText(product.getUnit());
            lblProductcode.setText(product.getBarcode());
            calculateProductTotal();
        }
        

    }//GEN-LAST:event_comboProductActionPerformed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
    if (txtQty.getText()!=null){    
    calculateProductTotal();}
    
    }//GEN-LAST:event_txtQtyKeyReleased

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed

        //add  cart to product details 
       String quantity = txtQty.getText();
       if("0".equals(quantity)){
          
       System.out.println("Quanity is Zero");
       JOptionPane.showMessageDialog(null, "Quanity is Zero");
       }
       else{
        
           productModel product2 = productController.getProduct((String) comboProduct.getSelectedItem());
           String productId = product2.getBarcode();

        DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
        
        

        // Fetch the product quantity from the database
        int currentQuantity = productController.getQuantity(productId);
            System.out.println("Current Quantity at Stock:: "+currentQuantity);
        int requestedQuantity = Integer.parseInt(txtQty.getText());
        System.out.println("Requested Quantity:: "+requestedQuantity);
        
        
        if (requestedQuantity>currentQuantity){
            JOptionPane.showMessageDialog(null, "Not Enough Stock");
        }
        
        else{
        Vector v = new Vector();
        
        v.add(lblProductcode.getText()); // barcode
        v.add(comboProduct.getSelectedItem().toString()); // product name
        v.add(lblUnitget.getText());
        v.add(lblUnitPrice.getText()); // unit price
        v.add(txtQty.getText()); // p qyt
        v.add(lblTotalPrice.getText()); // get totle price
        
        dt.addRow(v);
        
        double totalAmount = calculateCartTotal(dt);
        System.out.println("Total Amount: " + totalAmount);
        lblCarttotal.setText(String.valueOf(totalAmount));
        
        int totalQty = calculateCartItems(dt);
        System.out.println("Total Quantity: " + totalQty);
        lblTotalqtydisplay.setText(String.valueOf(totalQty));
        }
       }
        
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnRemoveFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromCartActionPerformed
        // selected remove
        try {
            
            DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
            int rw = tblSale.getSelectedRow();
           
            dt.removeRow(rw);
            double totalAmount = calculateCartTotal(dt);
            System.out.println("Total Amount: " + totalAmount);
            lblCarttotal.setText(String.valueOf(totalAmount));
            
            int totalQty = calculateCartItems(dt);
            System.out.println("Total Quantity: " + totalQty);
            lblTotalqtydisplay.setText(String.valueOf(totalQty));
            
            
        } catch (Exception e) {
            
        }
        
        
    }//GEN-LAST:event_btnRemoveFromCartActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        // remove all
        DefaultTableModel dt = (DefaultTableModel) tblSale.getModel();
        dt.setRowCount(0);
        
        double totalAmount = calculateCartTotal(dt);
        System.out.println("Total Amount: " + totalAmount);
        lblCarttotal.setText(String.valueOf(totalAmount));

        int totalQty = calculateCartItems(dt);
        System.out.println("Total Quantity: " + totalQty);
        lblTotalqtydisplay.setText(String.valueOf(totalQty));
        
    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void txtPaidamtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaidamtKeyReleased
        
        calculatePaymentChange();

    }//GEN-LAST:event_txtPaidamtKeyReleased

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // data send to databace
        paymentPrint();
    }//GEN-LAST:event_btnPayActionPerformed

    private void comboCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCustomerActionPerformed
        comboCustomer.setEnabled(false);
    }//GEN-LAST:event_comboCustomerActionPerformed

    private void txtPaidamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaidamtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaidamtActionPerformed

    private void txtDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        
        clearText();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea billArea;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JButton btnRemoveFromCart;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> comboCustomer;
    private javax.swing.JComboBox<String> comboProduct;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCarttotal;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblChangeamount;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblInvoiceid;
    private javax.swing.JLabel lblOrderId;
    private javax.swing.JLabel lblPaidamt;
    private javax.swing.JLabel lblProduct;
    private javax.swing.JLabel lblProductcode;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblTotalamount;
    private javax.swing.JLabel lblTotalprice;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JLabel lblTotalqtydisplay;
    private javax.swing.JLabel lblUnit;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JLabel lblUnitget;
    private javax.swing.JLabel lblUnitprice;
    private javax.swing.JPanel panel_bill;
    private javax.swing.JPanel panel_bottom;
    private javax.swing.JPanel panel_buttons;
    private javax.swing.JPanel panel_invoiceid;
    private javax.swing.JPanel panel_sales;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblSale;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtPaidamt;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables


   


}
