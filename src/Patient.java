import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Patient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JButton addButton = new JButton("ADD PATIENT");
    private JButton removeButton = new JButton("REMOVE PATIENT");
    private JButton listButton = new JButton("LIST PATIENT");
    private JButton searchButton = new JButton("SEARCH PATIENT");
    private JButton backButton = new JButton("BACK");
    public String sql =  "DELETE FROM patient WHERE identification_number=?";
    public String sql1 = "SELECT identification_number FROM patient WHERE name=?";
    private String sql_name = "FROM patient WHERE name=?";
    private String sql_phone = "FROM patient WHERE phone=?";
    private String sql_id = "SELECT * FROM patient WHERE identification_number=?";
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    Patient() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }// end of costructor

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("PATIENT");
        setVisible(true);
        setSize(400, 505);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }
    public void setLocationAndSize() {
        addButton.setBounds(50, 225, 300, 30);
        removeButton.setBounds(50, 275, 300, 30);
        listButton.setBounds(50, 325 , 300 , 30);
        searchButton.setBounds(50, 375 , 300 , 30);
        backButton.setBounds(50, 425, 300, 30);
        icon.setBounds(136, 50, image.getIconWidth(), image.getIconHeight());
    }

    public void addComponentsToContainer() {
        container.add(addButton);
        container.add(removeButton);
        container.add(listButton);
        container.add(searchButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        listButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton ) {
            new InsertPatient();
            this.dispose();
        }
        if (e.getSource() == removeButton ) {
            new RemoveDoctorPatient(sql1, sql, true);
            this.dispose();
        }
        if (e.getSource() == listButton ) {
            String sql2 = "SELECT * FROM patient";
            new List().list(sql2);
        }
        if (e.getSource() == searchButton ) {
            new SearchDoctorPatient(sql_name, sql_id, sql_phone);
            this.dispose();
        }
        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
    }// end of actionperformed
}// end of class
