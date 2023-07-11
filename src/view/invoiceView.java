
package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import database.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import controller.*;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import model.*;

/**
 *
 * @author Go
 */
public class invoiceView extends javax.swing.JPanel {
    
    public static void main(String[] args) {
        invoiceView myFrame = new invoiceView();
    }

    /**
     * Creates new form Invoice
     */
    public invoiceView() {
        initComponents();
        loadInvoiceData();

        
    }

public void loadInvoiceData(){
        
        ArrayList<paymentModel> allinvoice = saleController.getAllPayments();
        
        DefaultTableModel dtm = (DefaultTableModel) tblInvoice.getModel();
        dtm.setRowCount(0);
        
        double totalSales = 0.00;
        double inFlow = 0.00;
        double outFlow = 0.00;
        
        for (paymentModel invoice : allinvoice){
            Object[] rowData = {invoice.getInv_id(),
           invoice.getCustomer(),
           invoice.getCust_id(),
           invoice.getTotal_qty(),
           invoice.getTotal_amount(),
           invoice.getPaid_amount(),
           invoice.getChange(),
           invoice.getInvoicedate()};
            
           dtm.addRow(rowData);
           totalSales += invoice.getTotal_amount();
           inFlow += invoice.getPaid_amount();
           outFlow += invoice.getChange();
        }
        lblSales.setText(String.valueOf(totalSales));
        lblIn.setText(String.valueOf(totalSales));
        lblOut.setText(String.valueOf(totalSales));
    }

 public void specificInvoiceData(String searchRow){

            ArrayList<paymentModel> specificPayment = saleController.getAllPayments();

            DefaultTableModel dtm = (DefaultTableModel) tblInvoice.getModel();
            TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dtm);

            tblInvoice.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(searchRow));

        }
  

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtSearchInvoiceField = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblNote = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        lblSalesTitle = new javax.swing.JLabel();
        lblSales = new javax.swing.JLabel();
        lblInflowTitle = new javax.swing.JLabel();
        lblIn = new javax.swing.JLabel();
        lblOutflowTitle = new javax.swing.JLabel();
        lblOut = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inv. ID", "Cust Name", "Cust ID", "Total Qty", "Total Bill", "Paid Amount", "Change", "Invoice Date"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);
        if (tblInvoice.getColumnModel().getColumnCount() > 0) {
            tblInvoice.getColumnModel().getColumn(0).setResizable(false);
            tblInvoice.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblInvoice.getColumnModel().getColumn(1).setResizable(false);
            tblInvoice.getColumnModel().getColumn(1).setPreferredWidth(5);
            tblInvoice.getColumnModel().getColumn(2).setResizable(false);
            tblInvoice.getColumnModel().getColumn(2).setPreferredWidth(15);
            tblInvoice.getColumnModel().getColumn(3).setResizable(false);
            tblInvoice.getColumnModel().getColumn(3).setPreferredWidth(5);
            tblInvoice.getColumnModel().getColumn(4).setResizable(false);
            tblInvoice.getColumnModel().getColumn(4).setPreferredWidth(5);
            tblInvoice.getColumnModel().getColumn(5).setResizable(false);
            tblInvoice.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSearchInvoiceField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSearchInvoiceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchInvoiceFieldActionPerformed(evt);
            }
        });
        txtSearchInvoiceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchInvoiceFieldKeyReleased(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblSearch.setText("Search:");

        lblNote.setText("**Note: Start typing to search using any information.");

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/print.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblSearch)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSearchInvoiceField, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNote)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearchInvoiceField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNote)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lblSalesTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSalesTitle.setForeground(new java.awt.Color(204, 0, 0));
        lblSalesTitle.setText("Total Sales:");

        lblSales.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSales.setForeground(new java.awt.Color(204, 0, 0));
        lblSales.setText("0.00");

        lblInflowTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblInflowTitle.setText("Total Cash Inflow:");

        lblIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIn.setText("0.00");

        lblOutflowTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOutflowTitle.setText("Total Cash Outflow(Change):");

        lblOut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOut.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblSalesTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(lblInflowTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblOutflowTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalesTitle)
                    .addComponent(lblSales)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblInflowTitle)
                        .addComponent(lblIn)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOutflowTitle)
                            .addComponent(lblOut))))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchInvoiceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchInvoiceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchInvoiceFieldActionPerformed

    private void txtSearchInvoiceFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchInvoiceFieldKeyReleased

        String search = txtSearchInvoiceField.getText();
        if (search==null){
            loadInvoiceData();
        }
        else{
            specificInvoiceData(search);
        }
    }//GEN-LAST:event_txtSearchInvoiceFieldKeyReleased

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Sales Report"+"Total Sales:"+lblSales.getText());
        MessageFormat footer = new MessageFormat("Bhat-Bhateni POS Management System");
        
        try{
            tblInvoice.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        }
        catch (PrinterException e){
            JOptionPane.showMessageDialog(null,"Error! Cannot Print"+e.getMessage());

        }
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIn;
    private javax.swing.JLabel lblInflowTitle;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblOut;
    private javax.swing.JLabel lblOutflowTitle;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblSalesTitle;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtSearchInvoiceField;
    // End of variables declaration//GEN-END:variables
}
