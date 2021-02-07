/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.view;

import bachnv.entity.ArmorDTO;
import bachnv.service.ArmorInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngvba
 */
public class ArmorManagementClient extends javax.swing.JFrame {

    /**
     * Creates new form ArmorClient
     */
    String serviceName = "rmi://127.0.0.1:12340/ArmorManagement";
    ArmorInterface stub = null;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ArmorDTO dto;
    List<ArmorDTO> armor;
    Vector data;
    Vector<String> header = new Vector<>();

    public ArmorManagementClient() {
        initComponents();

        checkConnection();

        data = new Vector();
        armor = new ArrayList<>();

        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
        } catch (NotBoundException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        header.add("ArmorID");
        header.add("Classification");
        header.add("TimeOfCreate");
        header.add("Defense");

        loadData();
        DefaultTableModel defaultModel;
        defaultModel = (DefaultTableModel) (tblArmor.getModel());
        defaultModel.setDataVector(data, header);
        tblArmor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        txtTOC.setEditable(false);
        tblArmor.updateUI();
    }

    private void checkConnection() {
        try {
            Naming.lookup(serviceName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please turn on server!");
            System.exit(0);
        }
    }

    private void loadData() {
        try {
            data.removeAllElements();
            armor.clear();
            armor = stub.findAllArmor();
            for (int i = 0; i < armor.size(); i++) {
                Vector v = new Vector();
                v.add(armor.get(i).getArmorId());
                v.add(armor.get(i).getClassification());
                v.add(formatter.format(armor.get(i).getTimeOfCreate()));
                v.add(armor.get(i).getDefense());
                data.add(v);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean CheckArmorID() {
        try {
            String id = txtArmorId.getText().trim();
            for (int i = 0; i < data.size(); i++) {
                if (armor.get(i).getArmorId().equals(id)) {
                    return false;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean validData() {

        String code = txtArmorId.getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Armor ID cannot empty!");
            txtArmorId.requestFocus();
            return false;
        } else if (!code.matches("\\w{0,10}")) {
            JOptionPane.showMessageDialog(this, "Armor ID max length is 10, not contains special characters!");
            txtArmorId.requestFocus();
            return false;
        }

        String classification = txtClassification.getText().trim();
        if (classification.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input classification");
            txtClassification.requestFocus();
            return false;
        }
        if (classification.contains("|")) {
            JOptionPane.showMessageDialog(this, "Cannot input character '|'");
            txtClassification.requestFocus();
            return false;
        }
        if (!classification.matches(".{0,30}")) {
            JOptionPane.showMessageDialog(this, "Classification max length is 30!");
            txtClassification.requestFocus();
            return false;
        }

        String defense = txtDefense.getText().trim();
        if (defense.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input defense");
            txtDefense.requestFocus();
            return false;
        }
        if (!defense.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Defence must be integer number!");
            txtDefense.requestFocus();
            return false;
        }
        if (defense.equals("0")) {
            JOptionPane.showMessageDialog(this, "Defence must be greater than 0!");
            return false;
        }
        if (defense.length() > 9) {
            JOptionPane.showMessageDialog(this, "Defence must be less than 1000000000!");
            return false;
        }

        String description = txtDescription.getText().trim().replace("\n", "n");
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input description");
            txtDescription.requestFocus();
            return false;
        }
        if (description.contains("|")) {
            JOptionPane.showMessageDialog(this, "Cannot input character '|'");
            txtDescription.requestFocus();
            return false;
        }
        if (!description.matches(".{0,300}")) {
            JOptionPane.showMessageDialog(this, "Description max length is 300!");
            txtDescription.requestFocus();
            return false;
        }

        String status = txtStatus.getText().trim();
        if (status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input status");
            txtStatus.requestFocus();
            return false;
        }
        if (status.contains("|")) {
            JOptionPane.showMessageDialog(this, "Cannot input character '|'");
            txtStatus.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblArmor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtArmorId = new javax.swing.JTextField();
        txtClassification = new javax.swing.JTextField();
        txtTOC = new javax.swing.JTextField();
        txtDefense = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnCreate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblArmorMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblArmor);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Armor's Detail:"));

        jLabel2.setText("ArmorID:");

        jLabel3.setText("Classification:");

        jLabel4.setText("Description:");

        jLabel5.setText("Status:");

        jLabel6.setText("TimeOfCreate:");

        jLabel7.setText("Defense:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnFind.setText("Find by ArmorID");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtClassification, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTOC, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDefense, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtArmorId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFind))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArmorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Armor Client");

        btnGetAll.setText("GetAll");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(btnGetAll)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGetAll)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            checkConnection();
            if (!validData()) {
                return;
            }
            String ArmorId = txtArmorId.getText();
            String Classification = txtClassification.getText();
            String Description = txtDescription.getText();
            String Status = txtStatus.getText();
            txtTOC.setText(formatter.format(Calendar.getInstance().getTime()));
            Date TimeOfCreate = formatter.parse(txtTOC.getText());
            int Defense = Integer.parseInt(txtDefense.getText());
            dto = new ArmorDTO(ArmorId, Classification, Description, Status, TimeOfCreate, Defense);
            if (!CheckArmorID()) {
                JOptionPane.showMessageDialog(this, "Armor ID is duplicate");
                return;
            } else {
                if (stub.createArmor(dto) == true && stub.saveToFile() == true) {
                    armor.add(dto);
                    Vector v = new Vector();
                    v.add(ArmorId);
                    v.add(Classification);
                    v.add(formatter.format(TimeOfCreate));
                    v.add(Defense);
                    data.add(v);
                    JOptionPane.showMessageDialog(this, "Save Successfull!");

                    txtArmorId.setText("");
                    txtClassification.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtTOC.setText("");
                    txtDefense.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Save Failed!");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblArmor.updateUI();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            checkConnection();
            if (tblArmor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please select row to update!");
                return;
            }
            if (!validData()) {
                return;
            }
            String ArmorId = txtArmorId.getText();
            String Classification = txtClassification.getText();
            String Description = txtDescription.getText();
            String Status = txtStatus.getText();
            txtTOC.setText(formatter.format(Calendar.getInstance().getTime()));
            Date TimeOfCreate = formatter.parse(txtTOC.getText());
            int Defense = Integer.parseInt(txtDefense.getText());
            dto = new ArmorDTO(ArmorId, Classification, Description, Status, TimeOfCreate, Defense);
            if (stub.updateArmor(dto) == true && stub.saveToFile() == true) {
                int pos = -1;
                for (int i = 0; i < armor.size(); i++) {
                    if (armor.get(i).getArmorId().equals(ArmorId)) {
                        pos = i;
                    }
                }
                armor.set(pos, dto);
                Vector v = new Vector();
                v.add(ArmorId);
                v.add(Classification);
                v.add(formatter.format(TimeOfCreate));
                v.add(Defense);
                data.set(pos, v);
                JOptionPane.showMessageDialog(this, "Save Successfull!");
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtTOC.setEditable(false);
        tblArmor.updateUI();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            checkConnection();
            if (tblArmor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please select row to delete!");
                return;
            }
            String ArmorId = txtArmorId.getText();
            int r = JOptionPane.showConfirmDialog(this, "Do you want to delete " + ArmorId, "Delete", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                if (stub.removeArmor(ArmorId) == true && stub.saveToFile() == true) {
                    int pos = -1;
                    for (int i = 0; i < armor.size(); i++) {
                        if (armor.get(i).getArmorId().equals(ArmorId)) {
                            pos = i;
                        }
                    }
                    armor.remove(pos);
                    data.remove(pos);
                    JOptionPane.showMessageDialog(this, "Delete Successfull!");
                    txtArmorId.setEditable(true);
                    txtArmorId.setText("");
                    txtClassification.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtTOC.setText("");
                    txtDefense.setText("");
                    tblArmor.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Failed!");
                }
            } else {
                return;
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblArmor.updateUI();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        try {
            checkConnection();
            loadData();
            String ArmorId = txtArmorId.getText();
            if (ArmorId.isEmpty()) {
                loadData();
                tblArmor.updateUI();
                return;
            } else if (CheckArmorID()) {
                JOptionPane.showMessageDialog(this, "Armor ID not found in data!");
                data.removeAllElements();
                armor.clear();
                tblArmor.updateUI();
                return;
            } else {
                Vector<ArmorDTO> find = new Vector<>();
                Vector v = new Vector();
                find.add(stub.findByArmorID(ArmorId));
                v.add(find.get(0).getArmorId());
                v.add(find.get(0).getClassification());
                String TOC = formatter.format(find.get(0).getTimeOfCreate());
                v.add(TOC);
                v.add(find.get(0).getDefense());
                armor.clear();
                data.removeAllElements();
                data.add(v);
                armor.add(find.get(0));
                txtArmorId.setText("");
                txtClassification.setText("");
                txtDescription.setText("");
                txtStatus.setText("");
                txtTOC.setText("");
                txtDefense.setText("");
                tblArmor.clearSelection();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblArmor.updateUI();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        checkConnection();
        txtArmorId.setEditable(true);
        txtArmorId.setText("");
        txtClassification.setText("");
        txtDescription.setText("");
        txtStatus.setText("");
        txtTOC.setText("");
        txtDefense.setText("");
        tblArmor.clearSelection();
        loadData();
        tblArmor.updateUI();
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getClickCount() == 2) {
            checkConnection();
            txtArmorId.setEditable(true);
            txtArmorId.setText("");
            txtClassification.setText("");
            txtDescription.setText("");
            txtStatus.setText("");
            txtTOC.setText("");
            txtDefense.setText("");
            tblArmor.clearSelection();
        }
    }//GEN-LAST:event_formMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        checkConnection();
        int r = JOptionPane.showConfirmDialog(this, "Do you want to exit without saving?", "Exit", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void tblArmorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseReleased
        checkConnection();
        int pos = tblArmor.getSelectedRow();
        txtArmorId.setEditable(false);
        String id = armor.get(pos).getArmorId();
        if (id != null) {
            try {
                ArmorDTO dto = stub.findByArmorID(id);
                txtArmorId.setText(dto.getArmorId());
                txtClassification.setText(dto.getClassification());
                txtDescription.setText(dto.getDescription());
                txtStatus.setText(dto.getStatus());
                txtTOC.setText(formatter.format(dto.getTimeOfCreate()));
                txtDefense.setText("" + dto.getDefense());
            } catch (RemoteException ex) {
                Logger.getLogger(ArmorManagementClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblArmorMouseReleased

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
            java.util.logging.Logger.getLogger(ArmorManagementClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArmorManagementClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArmorManagementClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArmorManagementClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArmorManagementClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblArmor;
    private javax.swing.JTextField txtArmorId;
    private javax.swing.JTextField txtClassification;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTOC;
    // End of variables declaration//GEN-END:variables
}
