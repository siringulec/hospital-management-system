import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class DoctorDashboard extends JFrame implements ActionListener{

    private Container container = getContentPane();
    private JButton appointmentsButton = new JButton("Appointments");
    private JButton contactButton = new JButton("Contact Us");
    private JButton logoutButton = new JButton("Log Out");
    private long id;
    private JButton addInfoButton = new JButton("Add patient information");
    private JLabel icon = new JLabel("icon");
    private ImageIcon image = new ImageIcon("images/medical-team.png");

    public DoctorDashboard(long id) {
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
        icon.setBounds(115, 75, image.getIconWidth(), image.getIconHeight());
        icon.setIcon(image);
        addInfoButton.setBounds(75, 500, 200, 30);
    }

    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(contactButton);
        container.add(appointmentsButton);
        container.add(icon);
        container.add(addInfoButton);
    }

    public void addActionEvent() {
        appointmentsButton.addActionListener(this);
        contactButton.addActionListener(this);
        logoutButton.addActionListener(this);
        addInfoButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton){
            new LoginFrame();
            this.dispose();
        }
        if (e.getSource() == contactButton){

        }
        if (e.getSource() == appointmentsButton ){
            String sql = "Select appointments.patientID, appointments.date, appointments.time, patient.name" +
                "                From appointments" +
                "                Inner join patient on appointments.patientID = patient.identification_number" +
                "                Where doctorID=?";
            new List().list(id, sql);
        }
        if (e.getSource() == addInfoButton){
            new PatientInfo(id);
            this.dispose();
        }
    }
}// end of class
