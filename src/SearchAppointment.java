import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class SearchAppointment extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel dateLabel = new JLabel("Date (yyyy-mm-dd)");
    private JLabel doctorLabel = new JLabel("Doctor ID");
    private JLabel patientLabel = new JLabel("Patient ID");
    private JTextField dateTextField = new JTextField();
    private JTextField doctorTextField = new JTextField();
    private JTextField patientTextField = new JTextField();
    private JButton dateButton = new JButton("Search by Date");
    private JButton doctorButton = new JButton("Search by Doctor");
    private JButton patientButton = new JButton("Search by Patient");
    private JButton backButton = new JButton("Back");
    private String sql_date = "SELECT * FROM appointments WHERE date=?";
    private String sql_patient = "SELECT * FROM appointments WHERE patientID=?";
    private String sql_doctor = "SELECT * FROM appointments WHERE doctorID=?";
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    SearchAppointment() {
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
        dateLabel.setBounds(50, 25, 200, 30);
        dateTextField.setBounds(50, 50, 205, 30);
        dateButton.setBounds(50, 75, 200, 30);
        doctorLabel.setBounds(50, 100, 100, 30);
        doctorTextField.setBounds(50, 125, 205, 30);
        doctorButton.setBounds(50, 150, 200, 30);
        patientLabel.setBounds(50, 175, 100, 30);
        patientTextField.setBounds(50, 200, 205, 30);
        patientButton.setBounds(50, 225, 200, 30);
        backButton.setBounds(290, 220, 100, 30);
        icon.setBounds(290, 65, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(dateLabel);
        container.add(doctorLabel);
        container.add(dateTextField);
        container.add(doctorTextField);
        container.add(patientLabel);
        container.add(patientTextField);
        container.add(dateButton);
        container.add(doctorButton);
        container.add(patientButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        patientButton.addActionListener(this);
        dateButton.addActionListener(this);
        doctorButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String date = dateTextField.getText();
        String doctor = doctorTextField.getText();
        String patient = patientTextField.getText();

        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
        if (e.getSource() == dateButton) {
            if (!date.isEmpty())
                new List().list(date, sql_date);
            else
                JOptionPane.showMessageDialog(this, "Please enter a date.", null, JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == doctorButton) {
            if (!doctor.isEmpty()){
                long doctor_number = Long.parseLong(doctor);
                new List().list(doctor_number, sql_doctor);
            } else
                JOptionPane.showMessageDialog(this, "Please enter an id.", null, JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == patientButton) {
            if (!patient.isEmpty()){
                long patient_number = Long.parseLong(patient);
                new List().list(patient_number, sql_patient);
            } else
                JOptionPane.showMessageDialog(this, "Please enter an id.", null, JOptionPane.ERROR_MESSAGE);
        }
    }// end of actionPerformed
}// end of class
