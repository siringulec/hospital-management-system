import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class LoginFrame extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel userLabel = new JLabel("ID NUMBER");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton registerButton = new JButton("REGISTER");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private JLabel icon = new JLabel("icon");
    private ImageIcon image = new ImageIcon("Users/siringulec/Documents/Advanced Programming Project/hospital-management-system/icon.png");


    LoginFrame() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Login");
        setVisible(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registerButton.setBounds(200, 300, 100, 30);
        icon.setBounds(200, 50, WIDTH, HEIGHT);
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(registerButton);
        container.add(icon);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean checkDoctor, checkPatient;
        if (e.getSource() == loginButton) {
            String id = userTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
            try {
                if (id.equals("admin") && password.equals("admin")){
                    DashboardFrame ad = new AdminDashboard();
                    this.dispose();
                }
                else {
                    long id_number = Long.parseLong(id);
                    checkDoctor = checkDoctor(id_number, password);
                    checkPatient = checkPatient(id_number, password);

                    if (checkPatient) {
                        //new PatientDashboard();
                        this.dispose();
                    }
                    else if (checkDoctor){
                        new DoctorDashboard(id_number);
                        this.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "ID or Password Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
                    }
                }// end of else not admin
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Try again", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        }// end of login button


        if (e.getSource() == registerButton) {
            new RegistrationFrame();
            this.dispose();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }// end of if show password is clicked
    }// end of actionPerformed




    private boolean checkPatient(long id_number, String password) {
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";
        boolean checkUser = false;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM patient WHERE identification_number=? AND pass=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id_number);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                checkUser = true;
                //System.out.println(resultSet.getLong(1)+"  "+resultSet.getString(2));
            }
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return checkUser;

    }

    private boolean checkDoctor(long id_number, String password) {
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";
        boolean checkUser = false;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM doctor WHERE identification_number=? AND pass=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id_number);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                checkUser = true;
                //System.out.println(resultSet.getLong(1)+"  "+resultSet.getString(2));
            }
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return checkUser;
    }// end of checkAuthentication

}// end of class
