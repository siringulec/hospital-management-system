import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientDashboard extends JFrame implements ActionListener{

    private Container container = getContentPane();
    private JButton bookButton = new JButton("Book Appointment");
    private JButton prescriptionButton = new JButton("Prescription");
    private JButton billsButton = new JButton("Bills");
    private JButton diagnosisButton = new JButton("Diagnosis");
    private JButton testsButton = new JButton("Tests");
    private JButton appointmentsButton = new JButton("Appointments");
    private JButton contactButton = new JButton("Contact Us");
    private JButton logoutButton = new JButton("Log Out");
    private long patientID;
    private JButton Button = new JButton();
    private JLabel icon = new JLabel("icon");
    private ImageIcon image = new ImageIcon("images/hospital-building.png");

    public PatientDashboard(long patientID) {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.patientID = patientID;
    }
    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Patient Dashboard");
        setVisible(true);
        setSize(350, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        bookButton.setBounds(75, 300, 200, 30);
        prescriptionButton.setBounds(75, 350, 200, 30);
        diagnosisButton.setBounds(75, 400, 200, 30);
        billsButton.setBounds(75, 450, 200, 30);
        testsButton.setBounds(75, 500, 200, 30);
        appointmentsButton.setBounds(75, 550, 200, 30);
        contactButton.setBounds(75, 600, 200, 30);
        logoutButton.setBounds(75, 650, 200, 30);
        icon.setIcon(image);
        icon.setBounds(115, 75, image.getIconWidth(), image.getIconHeight());
    }


    public void addComponentsToContainer() {
        container.add(bookButton);
        container.add(prescriptionButton);
        container.add(diagnosisButton);
        container.add(billsButton);
        container.add(testsButton);
        container.add(logoutButton);
        container.add(contactButton);
        container.add(appointmentsButton);
        container.add(icon);
    }

    public void addActionEvent() {
        bookButton.addActionListener(this);
        prescriptionButton.addActionListener(this);
        diagnosisButton.addActionListener(this);
        billsButton.addActionListener(this);
        testsButton.addActionListener(this);
        appointmentsButton.addActionListener(this);
        contactButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appointmentsButton){
             String sql = "Select appointments.date, appointments.time, doctor.name, doctor.subspecialty" +
                "                From appointments" +
                "                Inner join doctor on appointments.doctorID = doctor.identification_number" +
                "                Where patientID=?";
             new List().list(patientID, sql);
        }
        if (e.getSource() == prescriptionButton){
             String sql = "SELECT medicine.name, prescription.dosage, prescription.date FROM"
                +" prescription INNER JOIN medicine ON prescription.medicineID = medicine.medicineID"
                +" WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == diagnosisButton){
            String sql = "SELECT * FROM diagnosis WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == billsButton){
            String sql = "SELECT * FROM bills WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == testsButton){
            String sql = "SELECT type_of_test.type_of_test, test.results, test.date FROM"
                +" test INNER JOIN type_of_test ON test.type_of_test  = type_of_test.typeID"
                +" WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == bookButton) {
            new BookAppointment(patientID);
            this.dispose();
        }
        if (e.getSource() == logoutButton) {
            new LoginFrame();
            this.dispose();
        }
    }// end of actionPerformed
 }// end of class
