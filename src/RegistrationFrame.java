import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationFrame extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JLabel phoneLabel = new JLabel("Cell Phone");
    private JLabel ageLabel = new JLabel("Age");
    private JLabel genderLabel = new JLabel("Select Gender");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel confirmLabel = new JLabel("Confirm Password");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JTextField ageTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmPasswordField = new JPasswordField();
    private JButton registerButton = new JButton("Register");
    private JButton cancelButton = new JButton("Cancel");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private JRadioButton maleButton = new JRadioButton("Male");
    private JRadioButton femaleButton = new JRadioButton("Female");
    private JRadioButton otherButton = new JRadioButton("Other");
    private ButtonGroup buttonGroup = new ButtonGroup();
    //private JPanel registerPanel;

    RegistrationFrame() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Register");
        setVisible(true);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 50, 100, 30);
        nameTextField.setBounds(50, 75, 175, 30);

        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 175, 30);

        phoneLabel.setBounds(50, 150, 100, 30);
        phoneTextField.setBounds(50, 175, 175, 30);

        ageLabel.setBounds(50, 200, 100, 30);
        ageTextField.setBounds(50, 225, 175, 30);

        genderLabel.setBounds(50, 250, 100, 30);
        maleButton.setBounds(50, 275, 100, 30);
        femaleButton.setBounds(150, 275, 100, 30);
        otherButton.setBounds(250, 275, 100, 30);

        passwordLabel.setBounds(50, 300, 100, 30);
        confirmLabel.setBounds(220, 300, 200, 30);
        passwordField.setBounds(50, 325, 150, 30);
        confirmPasswordField.setBounds(220, 325, 150, 30);

        showPassword.setBounds(50, 350, 200, 30);
        registerButton.setBounds(200, 450, 100, 30);
        cancelButton.setBounds(50, 450, 100, 30);
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(passwordLabel);
        container.add(confirmLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(passwordField);
        container.add(confirmPasswordField);
        container.add(showPassword);
        container.add(registerButton);
        container.add(cancelButton);
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
    }

    public void addActionEvent() {
        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
        maleButton.addActionListener(this);
        femaleButton.addActionListener(this);
        otherButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if(e.getSource() ){

        // }
        if (e.getSource() == cancelButton) {
            int n = JOptionPane.showConfirmDialog(RegistrationFrame.this,"Are you sure to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new LoginFrame();
                this.dispose();
            }
        }
        if (e.getSource() == registerButton) {

        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                confirmPasswordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
                confirmPasswordField.setEchoChar('*');
            }
        }// end of if show password
    }// end of actionPerformed
}

    //  public RegistrationForm(JFrame parent) {
    //         super(parent);
    //         setTitle("Create a new account");
    //         setContentPane(JPanel registerPanel);
    //         setMinimumSize(new Dimension(450, 474));
    //         setModal(true);
    //         setLocationRelativeTo(parent);
    //         setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    //         btnRegister.addActionListener(new ActionListener() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     registerUser();
    //                 }
    //             });
    //         btnCancel.addActionListener(new ActionListener() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     dispose();
    //                 }
    //             });

    //         setVisible(true);
    //     }

    //     private void registerPatient() {
    //         String name = tfName.getText();
    //         String phone = tfPhone.getText();
    //         String address = tfAddress.getText();
    //         String password = String.valueOf(pfPassword.getPassword());
    //         String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

    //         if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
    //             JOptionPane.showMessageDialog(this,
    //                                           "Please enter all fields",
    //                                           "Try again",
    //                                           JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         if (!password.equals(confirmPassword)) {
    //             JOptionPane.showMessageDialog(this,
    //                                           "Confirm Password does not match",
    //                                           "Try again",
    //                                           JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         user = addUserToDatabase(name, email, phone, address, password);
    //         if (user != null) {
    //             dispose();
    //         }
    //         else {
    //             JOptionPane.showMessageDialog(this,
    //                                           "Failed to register new user",
    //                                           "Try again",
    //                                           JOptionPane.ERROR_MESSAGE);
    //         }
    //     }

    //     public User user;
    //     private User addUserToDatabase(String name, String email, String phone, String address, String password) {
    //         User user = null;
    //         final String DB_URL = "jdbc:mysql://localhost/RegCredentials?server";
    //         final String USERNAME = "root";
    //         final String PASSWORD = "";

    //         try{
    //             Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    //             // Connected to database successfully...

    //             Statement stmt = conn.createStatement();
    //             String sql = "INSERT INTO users (name, email, phone, address, password) " +
    //                 "VALUES (?, ?, ?, ?, ?)";
    //             PreparedStatement preparedStatement = conn.prepareStatement(sql);
    //             preparedStatement.setString(1, name);
    //             preparedStatement.setString(2, email);
    //             preparedStatement.setString(3, phone);
    //             preparedStatement.setString(4, address);
    //             preparedStatement.setString(5, password);

    //             //Insert row into the table
    //             int addedRows = preparedStatement.executeUpdate();
    //             if (addedRows > 0) {
    //                 user = new User();
    //                 user.name = name;
    //                 user.email = email;
    //                 user.phone = phone;
    //                 user.address = address;
    //                 user.password = password;
    //             }

    //             stmt.close();
    //             conn.close();
    //         }catch(Exception e){
    //             e.printStackTrace();
    //         }

    //         return user;
    //     }