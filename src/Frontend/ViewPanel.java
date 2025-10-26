package Frontend;

import Backend.ManagerRole;
import Backend.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewPanel extends JPanel {

    private ManagerRole managerRole;

    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JButton btnRefresh;

    public ViewPanel() {
        initComponents();
        managerRole = new ManagerRole();
        loadTableData();
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // تعديل هنا لاستخدام displayStudents() بدل getAllStudents()
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

    private void btnRefreshActionPerformed() {
        loadTableData();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        btnRefresh = new JButton();

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Full Name", "Age", "Gender", "Department", "GPA"}
        ));
        jScrollPane1.setViewportView(jTable1);

        btnRefresh.setText("Refresh");
        btnRefresh.setBackground(new java.awt.Color(0, 204, 255));
        btnRefresh.addActionListener(evt -> btnRefreshActionPerformed());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                .addGap(20))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(300))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
    }
}
