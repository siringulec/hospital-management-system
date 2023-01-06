import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InsertPatient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JLabel phoneLabel = new JLabel("Cell Phone");
    private JLabel ageLabel = new JLabel("Age");
    private JLabel genderLabel = new JLabel("Select Gender");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JTextField ageTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private JRadioButton maleButton = new JRadioButton("Male");
    private JRadioButton femaleButton = new JRadioButton("Female");
    private JRadioButton otherButton = new JRadioButton("Other");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    InsertPatient() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Patient");
        setVisible(true);
        setSize(420, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 50, 100, 30);
        nameTextField.setBounds(50, 75, 175, 30);

        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 175, 30);

        phoneLabel.setBounds(50, 150, 100, 30);
        phoneTextField.setBounds(50, 175, 175, 30);

        ageLabel.setBounds(50, 200, 200, 30);
        ageTextField.setBounds(50, 225, 175, 30);

        genderLabel.setBounds(50, 250, 100, 30);
        maleButton.setBounds(50, 275, 100, 30);
        femaleButton.setBounds(150, 275, 100, 30);
        otherButton.setBounds(250, 275, 100, 30);

        passwordLabel.setBounds(50, 300, 100, 30);
        passwordField.setBounds(50, 325, 150, 30);

        showPassword.setBounds(50, 350, 200, 30);
        addButton.setBounds(200, 400, 100, 30);
        backButton.setBounds(50, 400, 100, 30);
        icon.setBounds(260, 100, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(passwordLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(addButton);
        container.add(backButton);
        container.add(phoneLabel);
        container.add(ageLabel);
        container.add(genderLabel);
        container.add(phoneTextField);
        container.add(ageTextField);
        container.add(maleButton);
        container.add(femaleButton);
        container.add(otherButton);
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        buttonGroup.add(otherButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        showPassword.addActionListener(this);
        maleButton.addActionListener(this);
        femaleButton.addActionListener(this);
        otherButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int gender = 0;
        if(maleButton.isSelected()){
            gender = 1;
        }
        if(femaleButton.isSelected()){
            gender = 2;
        }
        if(otherButton.isSelected()){
            gender = 3;
        }
        if (e.getSource() == backButton) {
            int n = JOptionPane.showConfirmDialog(InsertPatient.this,"Are you sure go back?", "Back", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new Patient();
                this.dispose();
            }
        }
        if (e.getSource() == addButton) {
            insertPatient(gender);
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }// end of if show password
    }// end of actionPerformed


    private void insertPatient(int isGender){

        String name = nameTextField.getText();
        String age = ageTextField.getText();
        int age_num = Integer.parseInt(age);
        String id = idTextField.getText();
        long id_number = Long.parseLong(id);
        String phone = phoneTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String gender = null;

        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || password.isEmpty() || age.isEmpty() || isGender == 0) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        }
        if(isGender == 1){
            gender = "Male";
        }
        else if (isGender == 2){
            gender = "Female";
        }
        else if (isGender == 3){
            gender = "Other";
        }

        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO patient (name, identification_number, phone, age, pass, gender) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id_number);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, age_num);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, gender);

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
        }
    }// end of insert doc
}// end of class
