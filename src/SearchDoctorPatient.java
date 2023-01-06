import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class SearchDoctorPatient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JLabel phoneLabel = new JLabel("Cell Phone");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JButton nameButton = new JButton("Search by Name");
    private JButton idButton = new JButton("Search by ID");
    private JButton phoneButton = new JButton("Search by Phone Number");
    private JButton backButton = new JButton("Back");
    private String sql_name;
    private String sql_phone;
    private String sql_id;
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    SearchDoctorPatient(String sql_name, String sql_id, String sql_phone) {
        this.sql_name = sql_name;
        this.sql_id = sql_id;
        this.sql_phone = sql_phone;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Search");
        setVisible(true);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 25, 100, 30);
        nameTextField.setBounds(50, 50, 205, 30);
        nameButton.setBounds(50, 75, 200, 30);
        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 205, 30);
        idButton.setBounds(50, 150, 200, 30);
        phoneLabel.setBounds(50, 175, 100, 30);
        phoneTextField.setBounds(50, 200, 205, 30);
        phoneButton.setBounds(50, 225, 200, 30);
        backButton.setBounds(290, 220, 100, 30);
        icon.setBounds(290, 65, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(phoneLabel);
        container.add(phoneTextField);
        container.add(nameButton);
        container.add(idButton);
        container.add(phoneButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        phoneButton.addActionListener(this);
        nameButton.addActionListener(this);
        idButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameTextField.getText();
        String id = idTextField.getText();
        String phone = phoneTextField.getText();

        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
        if (e.getSource() == nameButton) {
            new List().list(name, sql_name);
        }
        if (e.getSource() == idButton) {
            long id_number = Long.parseLong(id);
            new List().list(id_number, sql_id);
        }
        if (e.getSource() == phoneButton) {
            new List().list(phone, sql_phone);
        }// end
    }// end of actionPerformed
}// end of class
