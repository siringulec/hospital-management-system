import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InfoBoard extends JFrame implements ActionListener {

    private Container container = getContentPane();
    private JButton addTestButton = new JButton("Add Test");
    private JButton listTestButton = new JButton("List Tests");
    private JButton addPrescriptionButton = new JButton("Add Prescription");
    private JButton listPrescriptionButton = new JButton("List Prescription");
    private JButton addDiagnosisButton = new JButton("Add Diagnosis");
    private JButton listDiagnosisButton = new JButton("List Diagnosis");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/hospital-building.png");
    private long patientID, doctorID;

    InfoBoard(long patientID, long doctorID){
        this.patientID = patientID;
        this.doctorID = doctorID;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Information");
        setVisible(true);
        setSize(400, 555);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }
    public void setLocationAndSize() {
        addTestButton.setBounds(50, 225, 300, 30);
        listTestButton.setBounds(50, 260, 300, 30);
        addPrescriptionButton.setBounds(50, 295, 300 , 30);
        listPrescriptionButton.setBounds(50, 330, 300 , 30);
        addDiagnosisButton.setBounds(50, 365, 300 , 30);
        listDiagnosisButton.setBounds(50, 400, 300 , 30);
        backButton.setBounds(50, 435, 300, 30);
        icon.setBounds(136, 50, image.getIconWidth(), image.getIconHeight());
    }


    public void addComponentsToContainer() {
        container.add(addTestButton);
        container.add(listTestButton);
        container.add(addPrescriptionButton);
        container.add(listPrescriptionButton);
        container.add(backButton);
        container.add(addDiagnosisButton);
        container.add(listDiagnosisButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addTestButton.addActionListener(this);
        listTestButton.addActionListener(this);
        addPrescriptionButton.addActionListener(this);
        listPrescriptionButton.addActionListener(this);
        addDiagnosisButton.addActionListener(this);
        listDiagnosisButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTestButton) {
            this.dispose();
            new AddTest(doctorID, patientID);
        }
        if (e.getSource() == listTestButton ) {
            String sql = "SELECT type_of_test.type_of_test, test.results, test.date FROM"
                +" test INNER JOIN type_of_test ON test.type_of_test  = type_of_test.typeID"
                +" WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == addPrescriptionButton ) {
            new AddPrescription(doctorID, patientID);
            this.dispose();
        }
        if (e.getSource() == listPrescriptionButton ) {
            String sql = "SELECT medicine.name, prescription.dosage, prescription.date FROM"
                +" prescription INNER JOIN medicine ON prescription.medicineID = medicine.medicineID"
                +" WHERE patientID=?";
            new List().list(patientID, sql);
        }
        if (e.getSource() == backButton) {
            new PatientInfo(doctorID);
            this.dispose();
        }
        if (e.getSource() == addDiagnosisButton ) {
            new AddDiagnosis(doctorID, patientID);
            this.dispose();
        }
        if (e.getSource() == listDiagnosisButton ) {
            String sql = "SELECT * FROM diagnosis WHERE patientID=?";
            new List().list(patientID, sql);
        }
    }// end of actionperformed
}
