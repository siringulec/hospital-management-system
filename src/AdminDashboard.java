import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;

public class AdminDashboard extends DashboardFrame{
    JButton doctorButton = new JButton("Doctors");
    JButton patientButton = new JButton("Patients");
    JButton diagnosisButton = new JButton("Diagnosis");
    JButton medicineButton = new JButton("Medicines");
    JButton prescriptionButton = new JButton("Prescriptions");
    JButton roomButton = new JButton("Rooms");
    JButton stayButton = new JButton("Stays");
    JButton testButton = new JButton("Tests");
    JButton billsButton = new JButton("Bills");

    public AdminDashboard() {
        super.setFrameProperties();
        super.setLocationAndSize();
        super.addComponentsToContainer();
        super.addActionEvent();
        setEverthing();
    }

     public void setEverthing() {
         doctorButton.setBounds(75, 150, 200, 30);
         patientButton.setBounds(75, 200, 200, 30);
         diagnosisButton.setBounds(75, 250, 200, 30);
         medicineButton.setBounds(75, 300, 200, 30);
         prescriptionButton.setBounds(75, 350, 200, 30);
         roomButton.setBounds(75, 400, 200, 30);
         stayButton.setBounds(75, 450, 200, 30);
         testButton.setBounds(75, 500, 200, 30);
         billsButton.setBounds(75, 100, 200, 30);
         container.add(doctorButton);
         container.add(patientButton);
         container.add(diagnosisButton);
         container.add(medicineButton);
         container.add(prescriptionButton);
         container.add(roomButton);
         container.add(stayButton);
         container.add(testButton);
         container.add(billsButton);
         doctorButton.addActionListener(this);
         patientButton.addActionListener(this);
         diagnosisButton.addActionListener(this);
         medicineButton.addActionListener(this);
         prescriptionButton.addActionListener(this);
         roomButton.addActionListener(this);
         stayButton.addActionListener(this);
         testButton.addActionListener(this);
         billsButton.addActionListener(this);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doctorButton ){
            new Doctor();
            this.dispose();
        }
        if (e.getSource() == patientButton){
            //new LoginFrame();
            this.dispose();
        }
        if (e.getSource() == logoutButton){
            new LoginFrame();
            this.dispose();
        }
    }

}
