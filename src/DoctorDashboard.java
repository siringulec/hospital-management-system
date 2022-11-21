import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class DoctorDashboard extends DashboardFrame {
    private long id;
    JButton diagnosisButton = new JButton("Add patient information");
    private JLabel icon = new JLabel("icon");
    private ImageIcon image = new ImageIcon("/Users/siringulec/Documents/Advanced Programming Project/hospital-management-system/icon.png");
    public DoctorDashboard(long id) {
        super.setFrameProperties();
        super.setLocationAndSize();
        super.addComponentsToContainer();
        super.addActionEvent();
        this.id = id;
        setEverything();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setEverything() {
        diagnosisButton.setBounds(75, 500, 200, 30);
        container.add(diagnosisButton);
        diagnosisButton.addActionListener(this);
        icon.setIcon(image);
        icon.setBounds(125, 75, image.getIconWidth(), image.getIconHeight());
        container.add(icon);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appointmentsButton ){
            listAppointments();
        }
        if (e.getSource() == logoutButton){
            new LoginFrame();
            this.dispose();
        }
    }

    public void listAppointments() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            // Connected to database successfully...
            Statement stmt = conn.createStatement();
            String sql = "Select appointments.patientID, appointments.date, patient.name" +
                "                From appointments" +
                "                Inner join patient on appointments.patientID = patient.identification_number" +
                "                Where doctorID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            JTable table = new JTable(buildTableModel(resultSet));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }// end of list appoitments
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
