import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddPrescription extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel medicineLabel = new JLabel("Medicine ID");
    private JLabel dosageLabel = new JLabel("Dosage");
    private JTextField medicineTextField = new JTextField();
    private JTextField dosageTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/hospital-building-small.png");
    private long patientID, doctorID;

    AddPrescription(long patientID, long doctorID) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Prescription");
        setVisible(true);
        setSize(420, 275);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        medicineLabel.setBounds(50, 50, 100, 30);
        medicineTextField.setBounds(50, 75, 175, 30);

        dosageLabel.setBounds(50, 100, 100, 30);
        dosageTextField.setBounds(50, 125, 175, 30);

        addButton.setBounds(200, 170, 100, 30);
        backButton.setBounds(50, 170, 100, 30);
        icon.setBounds(260, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(medicineLabel);
        container.add(dosageLabel);
        container.add(medicineTextField);
        container.add(dosageTextField);
        container.add(addButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new InfoBoard(patientID, doctorID);
            this.dispose();
        }
        if (e.getSource() == addButton) {
            insertPrescription();
        }
    }// end of actionPerformed


    private void insertPrescription(){

        String medicine = medicineTextField.getText();
        String dosage = dosageTextField.getText();
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        if (medicine.isEmpty() || dosage.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        }
        else {
            final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try{
                Long medicineID = Long.parseLong(medicine);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                // Connected to database successfully...

                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO prescription (patientID, medicineID, dosage, date) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1, patientID);
                preparedStatement.setLong(2, medicineID);
                preparedStatement.setString(3, dosage);
                preparedStatement.setString(4, date);
                //Insert row into the table
                int result = preparedStatement.executeUpdate();
                if (result > 0 ){
                    JOptionPane.showMessageDialog(this, "Successfully added to the database.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
                }
                stmt.close();
                conn.close();
            }catch(Exception exception){
                JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
                //exception.printStackTrace();
            }
        }
    }// end of insert
}// end of class
