import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class test extends JFrame {

    private JTextField nameField, ageField;
    private JComboBox<String> deptBox;
    private JRadioButton maleBtn, femaleBtn;
    private JButton addBtn, clearBtn;
    private JTable table;
    private DefaultTableModel model;

    public test() {
        setTitle("Mini Student Manager 🎓");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---------- Top Panel (Form) ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Age
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Age:"), gbc);
        ageField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Department:"), gbc);
        String[] depts = {"CCE", "CS", "IT", "EE", "ME", "CE"};
        deptBox = new JComboBox<>(depts);
        gbc.gridx = 1;
        formPanel.add(deptBox, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Gender:"), gbc);
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(maleBtn);
        group.add(femaleBtn);
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        // Buttons
        addBtn = new JButton("Add Student");
        clearBtn = new JButton("Clear Fields");
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(clearBtn);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // ---------- Table ----------
        String[] columns = {"Name", "Age", "Gender", "Department"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---------- Button Actions ----------
        addBtn.addActionListener(this::addStudent);
        clearBtn.addActionListener(e -> clearFields());
    }

    private void addStudent(ActionEvent e) {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String dept = (String) deptBox.getSelectedItem();
        String gender = maleBtn.isSelected() ? "Male" : femaleBtn.isSelected() ? "Female" : "";

        if (name.isEmpty() || ageText.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Vector<String> row = new Vector<>();
            row.add(name);
            row.add(String.valueOf(age));
            row.add(gender);
            row.add(dept);
            model.addRow(row);

            JOptionPane.showMessageDialog(this, "Student Added Successfully 🎉");
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        deptBox.setSelectedIndex(0);
        maleBtn.setSelected(false);
        femaleBtn.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test().setVisible(true));
    }
}
