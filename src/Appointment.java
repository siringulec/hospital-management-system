import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Appointment extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JButton addButton = new JButton("ADD APPOINTMENT");
    private JButton removeButton = new JButton("REMOVE APPOINTMENT");
    private JButton listButton = new JButton("LIST APPOINTMENTS");
    private JButton searchButton = new JButton("SEARCH APPOINTMENT");
    private JButton backButton = new JButton("BACK");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    Appointment() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }// end of costructor

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("APPOINTMENT");
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
            new InsertAppointment();
            this.dispose();
        }
        if (e.getSource() == removeButton ) {
            new RemoveAppointment();
            this.dispose();
        }
        if (e.getSource() == listButton ) {
            String sql = "SELECT * FROM appointments";
            new List().list(sql);
        }
        if (e.getSource() == searchButton ) {
            new SearchAppointment();
            this.dispose();
        }
        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
    }// end of actionperformed
}// end of class
