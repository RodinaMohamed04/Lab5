package Frontend;

import Backend.ManagerRole;
import Backend.Records;
import Backend.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeletePanel extends JPanel {

    private ManagerRole managerRole;

    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JButton btnSearch, btnDelete, btnViewAll;
    // تم تغيير الأسماء لـ txtGpa و txtDepartment
    private JTextField txtSearchId, txtId, txtName, txtAge, txtGpa, txtDepartment; 
    private JComboBox<String> comboGender;
    private JLabel lblSearchId, lblID, lblName, lblAge, lblGender, lblGPA, lblDept;

    public DeletePanel() {
        managerRole = new ManagerRole();
        initComponents();
        // 💡 تم إضافة: تحميل البيانات عند إنشاء اللوحة
        loadTableData(); 
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Student s : managerRole.displayStudents()) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getFullName(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGpa()
            });
        }
    }

    private void btnViewAllActionPerformed() {
        loadTableData();
    }

    private void btnSearchActionPerformed() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        String idStr = txtSearchId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID.");
            return;
        }

        Records r = managerRole.searchByID(idStr);
        if (r instanceof Student s) {
            // عرض الطالب الذي تم البحث عنه
            model.addRow(new Object[]{
                    s.getId(),
                    s.getFullName(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGpa()
            });
            // تحديث حقول المعلومات
            txtId.setText(String.valueOf(s.getId()));
            txtName.setText(s.getFullName());
            txtAge.setText(String.valueOf(s.getAge()));
            comboGender.setSelectedItem(s.getGender());
            txtGpa.setText(String.valueOf(s.getGpa())); 
            txtDepartment.setText(s.getDepartment());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
            clearFields();
            // model.setRowCount(0); // إبقاء الجدول فارغاً
        }
    }

    private void btnDeleteActionPerformed() {
        String idStr = txtSearchId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Student ID to delete.");
            return;
        }

        Records r = managerRole.searchByID(idStr);
        if (r instanceof Student) {
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete Student ID: " + idStr + "?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirmation == JOptionPane.YES_OPTION) {
                managerRole.deleteStudent(idStr);
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                clearFields();
                loadTableData(); // إعادة تحميل الجدول بعد الحذف
            }
        } else {
            JOptionPane.showMessageDialog(this, "Student not found. Cannot delete.");
        }
    }

    private void clearFields() {
        txtSearchId.setText("");
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        comboGender.setSelectedIndex(0);
        txtGpa.setText(""); 
        txtDepartment.setText("");
    }

    private void initComponents() {
        lblSearchId = new JLabel("Search ID:");
        txtSearchId = new JTextField(10);
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnViewAll = new JButton("View All");

        lblID = new JLabel("ID:");
        lblName = new JLabel("Name:");
        lblAge = new JLabel("Age:");
        lblGender = new JLabel("Gender:");
        lblGPA = new JLabel("GPA:");
        lblDept = new JLabel("Department:");

        txtId = new JTextField(10);
        txtName = new JTextField(15);
        txtAge = new JTextField(5);
        comboGender = new JComboBox<>(new String[]{"Male", "Female"});
        txtGpa = new JTextField(15); 
        txtDepartment = new JTextField(15);

        txtId.setEditable(false);
        txtName.setEditable(false);
        txtAge.setEditable(false);
        txtGpa.setEditable(false); 
        txtDepartment.setEditable(false);

        btnSearch.addActionListener(evt -> btnSearchActionPerformed());
        btnDelete.addActionListener(evt -> btnDeleteActionPerformed());
        btnViewAll.addActionListener(evt -> btnViewAllActionPerformed());

        // الجدول
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Full Name", "Age", "Gender", "Department", "GPA"}
        ));
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 300));

        // Layout الرئيسي
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchId)
                                .addComponent(txtSearchId, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch)
                                .addComponent(btnDelete)
                                .addComponent(btnViewAll))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblID)
                                        .addComponent(lblName)
                                        .addComponent(lblAge)
                                        .addComponent(lblGender)
                                        .addComponent(lblGPA)
                                        .addComponent(lblDept))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtId)
                                        .addComponent(txtName)
                                        .addComponent(txtAge)
                                        .addComponent(comboGender)
                                        .addComponent(txtGpa) 
                                        .addComponent(txtDepartment)))
                        .addComponent(jScrollPane1)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearchId)
                                .addComponent(txtSearchId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch)
                                .addComponent(btnDelete)
                                .addComponent(btnViewAll))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(txtId))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(txtName))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAge)
                                .addComponent(txtAge))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGender)
                                .addComponent(comboGender))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGPA)
                                .addComponent(txtGpa)) 
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDept)
                                .addComponent(txtDepartment))
                        .addComponent(jScrollPane1)
        );
    }
}