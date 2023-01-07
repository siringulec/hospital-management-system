import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTest extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel idLabel = new JLabel("Type ID");
    private JTextField idTextField = new JTextField();
    private JButton addButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/hospital-building-small.png");
    private long patientID, doctorID;

    AddTest(long patientID, long doctorID) {
        this.patientID = patientID;
        this.doctorID = doctorID;
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
        setSize(520, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 175, 30);
        addButton.setBounds(200, 400, 100, 30);
        backButton.setBounds(50, 400, 100, 30);
        icon.setBounds(300, 20, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(idLabel);
        container.add(idTextField);
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
            sp.setBounds(50, 150, getWidth() - 100, getHeight() - 300);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
            //ex.printStackTrace();
        }
    }// end of remove

    private void insertTest(){

        String id = idTextField.getText();
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter all fields", "", JOptionPane.ERROR_MESSAGE);
        } else {

            final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try{
                long type = Long.parseLong(id);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                // Connected to database successfully...
                String sql = "INSERT INTO test (patientID, type_of_test, date) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1, patientID);
                preparedStatement.setLong(2, type);
                preparedStatement.setString(3, date);
                //Insert row into the table
                int result = preparedStatement.executeUpdate();
                if (result > 0 ){
                    JOptionPane.showMessageDialog(this, "Successfully added to the database.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
                }
                conn.close();
            }catch(Exception exception){
                JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
                //exception.printStackTrace();
            }
        }
    }// end of insert
}// end of class
