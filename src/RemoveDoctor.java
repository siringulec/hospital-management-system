import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class RemoveDoctor extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JButton nameButton = new JButton("Remove by Name");
    private JButton idButton = new JButton("Remove by ID");
    private JButton backButton = new JButton("Back");

    RemoveDoctor() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Remove Doctor");
        setVisible(true);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 25, 100, 30);
        nameTextField.setBounds(50, 50, 205, 30);
        nameButton.setBounds(50, 75, 200, 30);
        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 205, 30);
        idButton.setBounds(50, 150, 200, 30);
        backButton.setBounds(150, 250, 100, 30);
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(nameButton);
        container.add(idButton);
        container.add(backButton);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        nameButton.addActionListener(this);
        idButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameTextField.getText();
        String id = idTextField.getText();

        if (e.getSource() == backButton) {
            int n = JOptionPane.showConfirmDialog(RemoveDoctor.this,"Are you sure go back?", "Back", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new Doctor();
                this.dispose();
            }
        }
        if (e.getSource() == nameButton) {
            removeDocByName(name);
        }
        if (e.getSource() == idButton) {
            removeDocByID(id);
        }
    }// end of actionPerformed



    private void removeDocByName(String name){
        long id;
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT identification_number FROM doctor WHERE name=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setString(1, name);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            String id_query_s = resultSet1.getString(1);
            long id_query = Long.parseLong(id_query_s);
            stmt1.close();
            // code to get dictor id using name

            Statement stmt2 = conn.createStatement();
            String sql2 = "Select appointments.patientID, appointments.date, patient.name" +
                "                From appointments" +
                "                Inner join patient on appointments.patientID = patient.identification_number" +
                "                Where doctorID=?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setLong(1, id_query);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            // code to check for appiontments

            if (!resultSet2.next()){
                Statement stmt3 = conn.createStatement();
                String sql3 = "DELETE FROM doctor WHERE name=?";
                PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
                preparedStatement3.setString(1, name);
                preparedStatement3.execute();
                stmt3.close();
            }// delete if they dont have apoitmnets
            else {
                JOptionPane.showMessageDialog(this, "This doctor has apointments.", "Try again", JOptionPane.ERROR_MESSAGE);
            }
            stmt2.close();
            conn.close();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
    }// end of remove by name


    private void removeDocByID(String id){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            long id_number = Long.parseLong(id);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt1 = conn.createStatement();
            String sql1 = "Select appointments.patientID, appointments.date, patient.name" +
                "                From appointments" +
                "                Inner join patient on appointments.patientID = patient.identification_number" +
                "                Where doctorID=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setLong(1, id_number);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            // code to check for appiontments

            if (!resultSet1.next()){
                Statement stmt2 = conn.createStatement();
                String sql2 = "DELETE FROM doctor WHERE identification_number=?";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                preparedStatement2.setLong(1, id_number);
                preparedStatement2.execute();
                stmt2.close();
            }// delete if they dont have apoitmnets
            else {
                JOptionPane.showMessageDialog(this, "This doctor has apointments.", "Try again", JOptionPane.ERROR_MESSAGE);
            }
            stmt1.close();
            conn.close();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
    }// end of remove by id

}// end of class
