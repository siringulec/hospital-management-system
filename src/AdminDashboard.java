import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class AdminDashboard extends JFrame implements ActionListener{

    Container container = getContentPane();
    private JLabel label = new JLabel("Welcome Admin");
    JButton appointmentsButton = new JButton("Appointments");
    JButton logoutButton = new JButton("Log Out");
    JButton doctorButton = new JButton("Doctors");
    JButton patientButton = new JButton("Patients");
    JButton diagnosisButton = new JButton("Diagnosis");
    JButton medicineButton = new JButton("Medicines");
    JButton prescriptionButton = new JButton("Prescriptions");
    JButton testButton = new JButton("Tests");
    JButton billsButton = new JButton("Bills");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    public AdminDashboard() {
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
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setBounds(90, 35, 200, 30);
        appointmentsButton.setBounds(75, 550, 200, 30);
        logoutButton.setBounds(75, 650, 200, 30);
        doctorButton.setBounds(75, 250, 200, 30);
        patientButton.setBounds(75, 300, 200, 30);
        diagnosisButton.setBounds(75, 350, 200, 30);
        medicineButton.setBounds(75, 400, 200, 30);
        prescriptionButton.setBounds(75, 450, 200, 30);
        testButton.setBounds(75, 500, 200, 30);
        billsButton.setBounds(75, 600, 200, 30);
        icon.setBounds(115, 75, image.getIconWidth(), image.getIconHeight());
        icon.setIcon(image);
    }

    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(appointmentsButton);
        container.add(doctorButton);
        container.add(patientButton);
        container.add(diagnosisButton);
        container.add(medicineButton);
        container.add(prescriptionButton);
        container.add(testButton);
        container.add(billsButton);
        container.add(icon);
        container.add(label);
    }

    public void addActionEvent() {
        appointmentsButton.addActionListener(this);
        logoutButton.addActionListener(this);
        doctorButton.addActionListener(this);
        patientButton.addActionListener(this);
        diagnosisButton.addActionListener(this);
        medicineButton.addActionListener(this);
        prescriptionButton.addActionListener(this);
        testButton.addActionListener(this);
        billsButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appointmentsButton){
            new Appointment();
            this.dispose();
        }
        if (e.getSource() == doctorButton ){
            new Doctor();
            this.dispose();
        }
        if (e.getSource() == patientButton){
            new Patient();
            this.dispose();
        }
        if (e.getSource() == diagnosisButton){
            new Diagnosis();
            this.dispose();
        }
        if (e.getSource() == medicineButton){
            new Medicine();
            this.dispose();
        }
        if (e.getSource() == prescriptionButton){
            new Prescription();
            this.dispose();
        }
        if (e.getSource() == testButton){
            new Test();
            this.dispose();
        }
        if (e.getSource() == billsButton){
            new Bill();
            this.dispose();
        }
        if (e.getSource() == logoutButton){
            new LoginFrame();
            this.dispose();
        }
    }// end of actionperformned
}// end of class
