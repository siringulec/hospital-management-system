import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateTest extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel idLabel = new JLabel("Patient ID");
    private JLabel dateLabel = new JLabel("Date (yyyy-mm-dd)");
    private JLabel resultsLabel = new JLabel("Results");
    private JTextField idTextField = new JTextField();
    private JTextField dateTextField = new JTextField();
    private JTextArea resultsTextField = new JTextArea();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    UpdateTest() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Diagnosis");
        setVisible(true);
        setSize(420, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 175, 30);

        dateLabel.setBounds(50, 100, 200, 30);
        dateTextField.setBounds(50, 125, 175, 30);

        resultsLabel.setBounds(50, 150, 100, 30);
        resultsTextField.setBounds(50, 175, 175, 250);

        addButton.setBounds(200, 450, 100, 30);
        backButton.setBounds(50, 450, 100, 30);
        icon.setBounds(260, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(idLabel);
        container.add(dateLabel);
        container.add(resultsLabel);
        container.add(resultsTextField);
        container.add(idTextField);
        container.add(dateTextField);
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
            new Test();
            this.dispose();
        }
        if (e.getSource() == addButton) {
            insertDiagnosis();
        }
    }// end of actionPerformed


    private void insertDiagnosis(){

        String id = idTextField.getText();
        String date = dateTextField.getText();
        String results = resultsTextField.getText();

        if (id.isEmpty() || date.isEmpty() || results.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        } else {

            final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try{
                Long patientID = Long.parseLong(id);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                // Connected to database successfully...

                Statement stmt = conn.createStatement();
                String sql = "UPDATE test SET results=? WHERE patientID=? AND date=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, results);
                preparedStatement.setLong(2, patientID);
                preparedStatement.setString(3, date);

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
                exception.printStackTrace();
            }
        }
    }// end of insert
}// end of class
