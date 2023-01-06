import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertBill extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel idLabel = new JLabel("Patient ID");
    private JLabel insuranceLabel = new JLabel("Insured Amount");
    private JLabel moneyLabel = new JLabel("Normal Amount");
    private JTextField idTextField = new JTextField();
    private JTextField insuranceTextField = new JTextField();
    private JTextField moneyTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    InsertBill() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Bill");
        setVisible(true);
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 175, 30);

        insuranceLabel.setBounds(50, 100, 200, 30);
        insuranceTextField.setBounds(50, 125, 175, 30);

        moneyLabel.setBounds(50, 150, 100, 30);
        moneyTextField.setBounds(50, 175, 175, 30);

        addButton.setBounds(200, 250, 100, 30);
        backButton.setBounds(50, 250, 100, 30);
        icon.setBounds(260, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(idLabel);
        container.add(moneyLabel);
        container.add(insuranceLabel);
        container.add(insuranceTextField);
        container.add(idTextField);
        container.add(moneyTextField);
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
            new Bill();
            this.dispose();
        }
        if (e.getSource() == addButton) {
            insertBill();
        }
    }// end of actionPerformed


    private void insertBill(){

        String id = idTextField.getText();
        String insurance = insuranceTextField.getText();
        String money = moneyTextField.getText();
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        if (id.isEmpty() || insurance.isEmpty() || money.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        }

        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Long patientID = Long.parseLong(id);
            double money_n = Double.parseDouble(money);
            double insurance_n = Double.parseDouble(insurance);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO bills (patientID, basic_amount, date, amount_assured) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, patientID);
            preparedStatement.setDouble(2, money_n);
            preparedStatement.setString(3, date);
            preparedStatement.setDouble(4, insurance_n);
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
    }// end of insert
}// end of class
