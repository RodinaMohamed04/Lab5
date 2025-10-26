package Frontend;

import Backend.Records;
import Backend.Student;
import Backend.StudentDataBase;
import javax.swing.table.DefaultTableModel;


public class ViewPanel extends javax.swing.JPanel {


    private StudentDataBase studentDB;

    public ViewPanel() {
        initComponents();
        studentDB = new StudentDataBase("Students");
        studentDB.readFromFile();
        loadTableData();
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) tableStudents.getModel();
        model.setRowCount(0);
        for (Records record : studentDB.returnAllrecords()) {
            Student s = (Student) record;
            model.addRow(new Object[]{
                s.getId(), s.getFullName(), s.getAge(),
                s.getGender(), s.getDepartment(), s.getGpa()
            });
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        studentDB.readFromFile();
        loadTableData();
    }

}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "Age", "Gender", "Department", "GPA"
            }
        ));
        jTable1.setName("tableStudents"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setText("Refresh");
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.setName("btnRefresh"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
