import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class DashboardFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JButton appointmentsButton = new JButton("Appointments");
    JButton contactButton = new JButton("Contact Us");
    JButton logoutButton = new JButton("Log Out");


    DashboardFrame() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle(null);
        setVisible(true);
        setSize(350, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        appointmentsButton.setBounds(75, 550, 200, 30);
        contactButton.setBounds(75, 600, 200, 30);
        logoutButton.setBounds(75, 650, 200, 30);
    }


    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(contactButton);
        container.add(appointmentsButton);

    }

    public void addActionEvent() {
        logoutButton.addActionListener(this);
        contactButton.addActionListener(this);
        appointmentsButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == logoutButton ) {
        //     // dispose();
        // }
        // if (e.getSource() == contactButton ) {

       // }
    }// end of actionListener
}// end of class
