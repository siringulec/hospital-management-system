import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class InsertAppointment extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel monthLabel  = new JLabel("Month");
    private JLabel dayLabel = new JLabel("Day");
    private JLabel timeLabel = new JLabel("Time");
    private JLabel patientLabel = new JLabel("Patient ID");
    private JLabel doctorLabel = new JLabel("Doctor ID");
    private JTextField  patientTextField = new JTextField();
    private JTextField  doctorTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private String[] months = new String[12];
    private String[] days = new String[31];
    private String[] time = new String[36];
    private JComboBox<String> monthBox= new JComboBox<String>();
    private JComboBox<String> dayBox = new JComboBox<String>();
    private JComboBox<String> timeBox = new JComboBox<String>();
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    InsertAppointment() {
        setFrameProperties();
        setDateAndTime();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        icon.setIcon(image);
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Appointment");
        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        monthBox.setBounds(50, 175, 100, 30);
        monthLabel.setBounds(50, 150, 100, 30);
        dayBox.setBounds(150, 175, 100, 30);
        dayLabel.setBounds(150, 150, 100, 30);

        patientLabel.setBounds(50, 100, 100, 30);
        patientTextField.setBounds(50, 125, 175, 30);

        doctorLabel.setBounds(50, 50, 200, 30);
        doctorTextField.setBounds(50, 75, 175, 30);

        timeLabel.setBounds(50, 200, 100, 30);
        timeBox.setBounds(50, 225, 100, 30);

        addButton.setBounds(200, 275, 100, 30);
        backButton.setBounds(50, 275, 100, 30);
        icon.setBounds(250, 75, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(addButton);
        container.add(backButton);
        container.add(monthBox);
        container.add(dayBox);
        container.add(timeBox);
        container.add(doctorLabel);
        container.add(doctorTextField);
        container.add(patientLabel);
        container.add(patientTextField);
        container.add(dayLabel);
        container.add(monthLabel);
        container.add(timeLabel);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        dayBox.addActionListener(this);
        monthBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            int n = JOptionPane.showConfirmDialog(InsertAppointment.this,"Are you sure go back?", "Back", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new Appointment();
                this.dispose();
            }
        }
        if (e.getSource() == addButton)
            insertAppoitment();
    }// end of actionPerformed


    private void insertAppoitment(){
        String patient = patientTextField.getText();
        String doctor = doctorTextField.getText();
        int month = monthBox.getSelectedIndex();
        int day = dayBox.getSelectedIndex();
        int time_of_appoitment = timeBox.getSelectedIndex();
        if (month == 3 || month == 5 || month == 8 || month == 10) {
            if (day == 30)
                JOptionPane.showMessageDialog(this,"Please enter a valid day.", "", JOptionPane.ERROR_MESSAGE);
        } else if (month == 1){
            if (day > 28)
                JOptionPane.showMessageDialog(this,"Please enter a valid day.", "", JOptionPane.ERROR_MESSAGE);
        }
        else if (patient.isEmpty() || doctor.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String date = "2023-"+months[month]+"-"+days[day];
            String time_selected = time[time_of_appoitment]+":00";
            final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
            final String USERNAME = "root";
            final String PASSWORD = "";
            try{
                long patientID = Long.parseLong(patient);
                long doctorID = Long.parseLong(doctor);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                // Connected to database
                String sql1 = "SELECT * FROM appointments WHERE doctorID=? AND date=? AND time=?";
                PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
                preparedStatement1.setLong(1, doctorID);
                preparedStatement1.setString(2, date);
                preparedStatement1.setString(3, time_selected);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                String sql2 = "SELECT * FROM appointments WHERE patientID=? AND date=? AND time=?";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                preparedStatement2.setLong(1, patientID);
                preparedStatement2.setString(2, date);
                preparedStatement2.setString(3, time_selected);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (!resultSet1.next() || !resultSet2.next()){
                    String sql = "INSERT INTO appointments (patientID, doctorID, date, time) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setLong(1, patientID);
                    preparedStatement.setLong(2, doctorID);
                    preparedStatement.setString(3, date);
                    preparedStatement.setString(4, time_selected);
                    //Insert row into the table
                    int result = preparedStatement.executeUpdate();
                    if (result > 0 )
                        JOptionPane.showMessageDialog(this, "Successfully added to the database.");
                    else
                        JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(this, "Patient or Doctor already have appointment at the time entered.", null, JOptionPane.ERROR_MESSAGE);
                conn.close();
            }catch(Exception exception){
                JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
                //exception.printStackTrace();
            }// end of catch
        } // end of else
    }// end of insert



    public void setDateAndTime(){
        int[] minutes = {00, 15, 30, 45};
        String t = null;
        int time_index = 0;

        for (int i = 0; i < 12; i++) {
             String m = null;
            if ((i + 1) < 10)
               m = "0"+Integer.toString(i+1);
            else
                m = Integer.toString(1+i);
            months[i] = m;
        }// adds months to the string array
        for (int i = 0; i < 31; i++) {
            if ((i + 1) < 10){
                String day = "0"+Integer.toString(i+1);
                days[i] = day;
            }
            else
                days[i] = Integer.toString(i+1);
        }// adds days to the string array
        for (int i = 0; i <= 8 ; i++) {
            for (int j = 0; j < 4; j++) {
                if (i+9 == 9){
                    if (j == 0)
                        t = "0"+Integer.toString(i+9)+":"+Integer.toString(minutes[j])+"0";
                    else
                        t = "0"+Integer.toString(i+9)+":"+Integer.toString(minutes[j]);
                }
                else{
                    if (j == 0)
                        t = Integer.toString(i+9)+":"+Integer.toString(minutes[j])+"0";
                    else
                        t = Integer.toString(i+9)+":"+Integer.toString(minutes[j]);
                }
                time[time_index] = t;
                time_index++;
            }
        }// adds time to the string array
        dayBox.setModel(new DefaultComboBoxModel<String>(days));
        monthBox.setModel(new DefaultComboBoxModel<String>(months));
        timeBox.setModel(new DefaultComboBoxModel<String>(time));
    }

}// end of class
