import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class SearchDoctor extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JLabel phoneLabel = new JLabel("Cell Phone");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JButton nameButton = new JButton("Search by Name");
    private JButton idButton = new JButton("Search by ID");
    private JButton phoneButton = new JButton("Search by Phone Number");
    private JButton backButton = new JButton("Back");


    SearchDoctor() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Search Doctor");
        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 25, 100, 30);
        nameTextField.setBounds(50, 50, 205, 30);
        nameButton.setBounds(50, 75, 200, 30);
        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 205, 30);
        idButton.setBounds(50, 150, 200, 30);
        phoneLabel.setBounds(50, 175, 100, 30);
        phoneTextField.setBounds(50, 200, 205, 30);
        phoneButton.setBounds(50, 225, 200, 30);
        backButton.setBounds(150, 250, 100, 30);
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(phoneLabel);
        container.add(phoneTextField);
        container.add(nameButton);
        container.add(idButton);
        container.add(phoneButton);
        container.add(backButton);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        phoneButton.addActionListener(this);
        nameButton.addActionListener(this);
        idButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameTextField.getText();
        String id = idTextField.getText();
        String phone = phoneTextField.getText();

        if (e.getSource() == backButton) {
            int n = JOptionPane.showConfirmDialog(SearchDoctor.this,"Are you sure go back?", "Back", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new Doctor();
                this.dispose();
            }
        }
        if (e.getSource() == nameButton) {
            searchDocByName(name);
        }
        if (e.getSource() == idButton) {
            searchDocByID(id);
        }
        if (e.getSource() == phoneButton) {
            searchDocByPhone(phone);
        }// end 
    }// end of actionPerformed


    private void searchDocByName(String name){

        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM doctor WHERE name=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            stmt.close();
            conn.close();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
    }// end of search by name


    private void searchDocByID(String id){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";
        long id_number = Long.parseLong(id);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM doctor WHERE identification_number=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            stmt.close();
            conn.close();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
    }// end of search by id


    private void searchDocByPhone(String phone){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM doctor WHERE phone=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            stmt.close();
            conn.close();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
    }// end of search by phone

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }// end of buildTableModel

}// end of class
