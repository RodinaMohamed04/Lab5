package Frontend;

import Backend.Records;
import Backend.Student;
import Backend.StudentDataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchUpdatePanel extends JPanel {

    private StudentDataBase studentDB;

    private JLabel lblSearch;
    private JTextField txtSearch;
    private JButton btnViewAll, btnSearch;
    private JTable jTable1;
    private JScrollPane jScrollPane1;

    private JPanel panelEdit;
    private JLabel lblId, lblName, lblAge, lblGender, lblDept, lblGPA;
    private JTextField txtId, txtName, txtAge, txtDept, txtGPA;
    private JComboBox<String> comboGender;
    private JButton btnUpdate, btnClear;

    public SearchUpdatePanel() {
        initComponents();
        studentDB = new StudentDataBase("Students");
        studentDB.readFromFile();
        loadTableData();

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked();
            }
        });
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Records record : studentDB.returnAllrecords()) {
            Student s = (Student) record;
            model.addRow(new Object[]{
                    s.getId(), s.getFullName(), s.getAge(),
                    s.getGender(), s.getDepartment(), s.getGpa()
            });
        }
    }

    private void searchAction() {
        String key = txtSearch.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ID or Name to search!");
            return;
        }

        boolean found = false;
        for (Records record : studentDB.returnAllrecords()) {
            Student s = (Student) record;
            if (String.valueOf(s.getId()).equalsIgnoreCase(key)
                    || s.getFullName().equalsIgnoreCase(key)) {
                model.addRow(new Object[]{
                        s.getId(), s.getFullName(), s.getAge(),
                        s.getGender(), s.getDepartment(), s.getGpa()
                });
                found = true;
            }
        }

        if (!found)
            JOptionPane.showMessageDialog(this, "No student found with that ID or Name.");
    }

    private void updateAction() {
        try {
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student first!");
                return;
            }

            int id = Integer.parseInt(idText);
            Student updatedStudent = new Student(
                    id,
                    txtName.getText().trim(),
                    Integer.parseInt(txtAge.getText().trim()),
                    comboGender.getSelectedItem().toString(),
                    txtDept.getText().trim(),
                    Double.parseDouble(txtGPA.getText().trim())
            );

            studentDB.updateRecord(String.valueOf(id), updatedStudent);
            studentDB.saveToFile();
            loadTableData();
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid data format!");
        }
    }

    private void deleteAction() {
        try {
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student first!");
                return;
            }

            studentDB.deleteRecord(idText);
            studentDB.saveToFile();
            loadTableData();
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            clearAction();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting student!");
        }
    }

    private void clearAction() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtDept.setText("");
        txtGPA.setText("");
        comboGender.setSelectedIndex(0);
    }

    private void tableMouseClicked() {
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            txtId.setText(jTable1.getValueAt(row, 0).toString());
            txtName.setText(jTable1.getValueAt(row, 1).toString());
            txtAge.setText(jTable1.getValueAt(row, 2).toString());
            comboGender.setSelectedItem(jTable1.getValueAt(row, 3).toString());
            txtDept.setText(jTable1.getValueAt(row, 4).toString());
            txtGPA.setText(jTable1.getValueAt(row, 5).toString());
        }
    }

    private void btnViewAllAction() { loadTableData(); }
    private void btnSearchAction() { searchAction(); }
    private void btnUpdateAction() { updateAction(); }
    private void btnClearAction() { clearAction(); }

    private void initComponents() {
        lblSearch = new JLabel("Search by ID or Name:");
        txtSearch = new JTextField();
        btnViewAll = new JButton("View All");
        btnSearch = new JButton("Search");
        jTable1 = new JTable();
        jScrollPane1 = new JScrollPane(jTable1);

        panelEdit = new JPanel();
        lblId = new JLabel("ID:");
        lblName = new JLabel("Full Name:");
        lblAge = new JLabel("Age:");
        lblGender = new JLabel("Gender:");
        lblDept = new JLabel("Department:");
        lblGPA = new JLabel("GPA:");
        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        txtDept = new JTextField();
        txtGPA = new JTextField();
        comboGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        btnUpdate = new JButton("Update");
        btnClear = new JButton("Clear");

        txtId.setEditable(false);

        btnViewAll.addActionListener(e -> btnViewAllAction());
        btnSearch.addActionListener(e -> btnSearchAction());
        btnUpdate.addActionListener(e -> btnUpdateAction());
        btnClear.addActionListener(e -> btnClearAction());

        panelEdit.setBorder(BorderFactory.createTitledBorder("Edit Student"));

        GroupLayout panelLayout = new GroupLayout(panelEdit);
        panelEdit.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lblId)
                            .addComponent(lblName)
                            .addComponent(lblAge)
                            .addComponent(lblGender)
                            .addComponent(lblDept)
                            .addComponent(lblGPA))
                        .addGap(10)
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAge, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboGender, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDept, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGPA, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnClear))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(10)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAge)
                    .addComponent(txtAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(comboGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDept)
                    .addComponent(txtDept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGPA)
                    .addComponent(txtGPA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10)
                .addComponent(btnUpdate)
                .addGap(5)
                .addComponent(btnClear)
                .addGap(10))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(lblSearch)
                .addGap(5)
                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(btnViewAll)
                .addGap(5)
                .addComponent(btnSearch)
                .addContainerGap(10, Short.MAX_VALUE))
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(panelEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewAll)
                    .addComponent(btnSearch))
                .addGap(10)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(panelEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }
}
