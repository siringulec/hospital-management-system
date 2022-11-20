import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class Doctor extends JFrame implements ActionListener {
    Container container = getContentPane();
    JButton addButton = new JButton("ADD DOCTOR");
    JButton removeButton = new JButton("REMOVE DOCTOR ");
    JButton listButton = new JButton("LIST DOCTORS");
    JButton searchButton = new JButton("SEARCH IN DOCTORS");
    private JButton backButton = new JButton("BACK");

    Doctor() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }// end of costructor

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("DOCTOR");
        setVisible(true);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    public void setLocationAndSize() {
        addButton.setBounds(50, 50, 300, 30);
        removeButton.setBounds(50, 150, 300, 30);
        listButton.setBounds(50, 250 , 300 , 30);
        searchButton.setBounds(50, 350 , 300 , 30);
        backButton.setBounds(50, 450, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(addButton);
        container.add(removeButton);
        container.add(listButton);
        container.add(searchButton);
        container.add(backButton);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        listButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton ) {
            new InsertDoctor();
            this.dispose();
        }
        if (e.getSource() == removeButton ) {
            new RemoveDoctor();
            this.dispose();
        }
        if (e.getSource() == listButton ) {
            listDoctors();
        
        }
        if (e.getSource() == searchButton ) {
            new SearchDoctor();
            this.dispose();
        }
        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
    }

    public void listDoctors(){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM doctor";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
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
}
