import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class List{

    private ImageIcon image = new ImageIcon("images/medical-appointment.png");

    public void list(String sql) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            // Connected to database successfully...
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JScrollPane sp = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, sp, "List", JOptionPane.PLAIN_MESSAGE, image);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error", null, JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
    }

    public void list(long id, String sql){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JScrollPane sp = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, sp, "List", JOptionPane.PLAIN_MESSAGE, image);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error", null, JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
    }
    public void list(long id, String sql, String query){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, query);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JScrollPane sp = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, sp, "List", JOptionPane.PLAIN_MESSAGE, image);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error", null, JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
    }// end of list

    public void list(String query, String sql){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, query);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JScrollPane sp = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, sp, "List", JOptionPane.PLAIN_MESSAGE, image);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error", null, JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }

    }// end of list

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
