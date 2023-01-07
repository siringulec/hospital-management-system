import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InsertMedicine extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel stockLabel = new JLabel("Stock");
    private JTextField nameTextField = new JTextField();
    private JTextField stockTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    InsertMedicine() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Medicine");
        setVisible(true);
        setSize(420, 285);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 50, 100, 30);
        nameTextField.setBounds(50, 75, 200, 30);

        stockLabel.setBounds(50, 100, 100, 30);
        stockTextField.setBounds(50, 125, 200, 30);

        addButton.setBounds(150, 175, 100, 30);
        backButton.setBounds(50, 175, 100, 30);
        icon.setBounds(260, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(stockLabel);
        container.add(nameTextField);
        container.add(stockTextField);
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
            new Medicine();
            this.dispose();
        }
        if (e.getSource() == addButton) {
            insertMedicine();
        }
    }// end of actionPerformed


    private void insertMedicine(){

        String name = nameTextField.getText();
        String stock = stockTextField.getText();

        if (name.isEmpty() || stock.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        } else {

            final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try{
                int stock_number = Integer.parseInt(stock);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                // Connected to database successfully...

                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO medicine (name, stock) VALUES (?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, stock_number);
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
