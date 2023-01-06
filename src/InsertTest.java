import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertTest extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel idLabel = new JLabel("Patient ID");
    private JLabel typeLabel = new JLabel("Type ID");
    private JTextField idTextField = new JTextField();
    private JTextField typeTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    InsertTest() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        listSelection();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Test");
        setVisible(true);
        setSize(420, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 175, 30);

        typeLabel.setBounds(50, 100, 100, 30);
        typeTextField.setBounds(50, 125, 175, 30);

        addButton.setBounds(200, 600, 100, 30);
        backButton.setBounds(50, 600, 100, 30);
        icon.setBounds(260, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(idLabel);
        container.add(typeLabel);
        container.add(idTextField);
        container.add(typeTextField);
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
            insertTest();
        }
    }// end of actionPerformed

    public void listSelection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM type_of_test";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List buildTable = new List();
            JTable table = new JTable(buildTable.buildTableModel(resultSet));
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane sp = new JScrollPane(table);
            container.add(sp);
            sp.setBounds(50, 200, getWidth() - 100, getHeight() - 300);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error", null, JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }// end of remove

    private void insertTest(){

        String id = idTextField.getText();
        String type = typeTextField.getText();
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        if (id.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        }

        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Long typeID = Long.parseLong(type);
            Long patientID = Long.parseLong(id);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO test (patientID, type_of_test, date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, patientID);
            preparedStatement.setLong(2, typeID);
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
    }// end of insert
}// end of class
