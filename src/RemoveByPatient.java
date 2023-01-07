import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class RemoveByPatient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel dateLabel = new JLabel("Date (yyyy-mm-dd)");
    private JLabel idLabel = new JLabel("Patient ID");
    private JTextField dateTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");
    private String sql;

    RemoveByPatient(String sql) {
        this.sql = sql;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Remove");
        setVisible(true);
        setSize(450, 225);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        dateLabel.setBounds(50, 25, 200, 30);
        dateTextField.setBounds(50, 50, 205, 30);
        idLabel.setBounds(50, 75, 100, 30);
        idTextField.setBounds(50, 100, 205, 30);
        removeButton.setBounds(50, 125, 150, 30);
        backButton.setBounds(290, 150, 100, 30);
        icon.setBounds(290, 25, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(dateLabel);
        container.add(idLabel);
        container.add(dateTextField);
        container.add(idTextField);
        container.add(removeButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        removeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean res = false;
        String id = idTextField.getText();
        String date = dateTextField.getText();

        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
        if (e.getSource() == removeButton) {
            if(!id.isEmpty() || !date.isEmpty()){
                res = removeByPatient(id, date, sql);
                if (res)
                    JOptionPane.showMessageDialog(this, "Successful Removal");
                else
                    JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(this, "Please fill all fields.", null, JOptionPane.ERROR_MESSAGE);
        }
    }// end of actionPerformed


    private boolean removeByPatient(String id, String date , String sql){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            long id_number = Long.parseLong(id);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id_number);
            preparedStatement.setString(2, date);
            int del = preparedStatement.executeUpdate();
            stmt.close();
            conn.close();
            if(del > 0)
                return true;
            else
                return false;
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Exception caught.", null, JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
            return false;
        }
    }// end of remove by id

}// end of class
