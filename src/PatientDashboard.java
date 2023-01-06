import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientDashboard extends JFrame implements ActionListener{

    Container container = getContentPane();
    JButton appointmentsButton = new JButton("Appointments");
    JButton contactButton = new JButton("Contact Us");
    JButton logoutButton = new JButton("Log Out");
    private long id;
    JButton Button = new JButton();
    private JLabel icon = new JLabel("icon");
    private ImageIcon image = new ImageIcon("images/hospital-building.png");

    public PatientDashboard(long id) {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        icon.setIcon(image);
        icon.setBounds(115, 75, image.getIconWidth(), image.getIconHeight());
    }


    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(contactButton);
        container.add(appointmentsButton);
        container.add(icon);
    }

    public void addActionEvent() {
        appointmentsButton.addActionListener(this);
        contactButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appointmentsButton ){
             String sql = "Select appointments.date, appointments.time, doctor.name, doctor.subspecialty" +
                "                From appointments" +
                "                Inner join doctor on appointments.doctorID = doctor.identification_number" +
                "                Where patientID=?";
             new List().list(id, sql);
        }
    }// end of actionPerformed
 }// end of class
