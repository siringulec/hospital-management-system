import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class RemoveDoctorPatient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JButton nameButton = new JButton("Remove by Name");
    private JButton idButton = new JButton("Remove by ID");
    private JButton backButton = new JButton("Back");
    private String sql;
    private String sql1;
    private boolean isPatient = true;
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    RemoveDoctorPatient(String sql1, String sql, boolean isPatient) {
        this.sql1 = sql1;
        this.sql = sql;
        this.isPatient = isPatient;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Remove");
        setVisible(true);
        setSize(450, 325);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 25, 100, 30);
        nameTextField.setBounds(50, 50, 205, 30);
        nameButton.setBounds(50, 75, 150, 30);
        idLabel.setBounds(50, 100, 100, 30);
        idTextField.setBounds(50, 125, 205, 30);
        idButton.setBounds(50, 150, 150, 30);
        backButton.setBounds(290, 200, 100, 30);
        icon.setBounds(290, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(idLabel);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(nameButton);
        container.add(idButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        nameButton.addActionListener(this);
        idButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameTextField.getText();
        String id = idTextField.getText();

        if (e.getSource() == backButton) {
            if(isPatient){
                new Patient();
                this.dispose();
            } else {
                new Doctor();
                this.dispose();
            }
        }
        if (e.getSource() == nameButton) {
            if(!name.isEmpty()){
                boolean result = removeByName(name, sql1, sql);
                if(result)
                    JOptionPane.showMessageDialog(this, "Successful Removal");
                else
                    JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == idButton) {
            if(!id.isEmpty()){
                boolean res = removeByID(id, sql);
                if (res)
                    JOptionPane.showMessageDialog(this, "Successful Removal");
                else
                    JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }// end of actionPerformed



    private boolean removeByName(String name, String sql1, String sql){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println(sql);
        System.out.println(sql1);

        try{
            long id = 0;
            String id_query_s = null;
            long id_query = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt1 = conn.createStatement();
            //String sql1 = "SELECT identification_number FROM patient WHERE name=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setString(1, name);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if(resultSet1.next()){
                id_query_s = resultSet1.getString(1);
                id_query = Long.parseLong(id_query_s);
            }
            // code to get dictor id using name
            Statement stmt2 = conn.createStatement();
            //String sql2 = "DELETE FROM patient WHERE identification_number=?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql);
            preparedStatement2.setLong(1, id_query);
            int del = preparedStatement2.executeUpdate();
            stmt2.close();
            stmt1.close();
            conn.close();
            if(del > 0)
                return true;
            else
                return false;
        }catch(Exception exception){
            JOptionPane.showMessageDialog(this, "Exception caught.", null, JOptionPane.ERROR_MESSAGE);
            //exception.printStackTrace();
            return false;
        }
    }// end of remove by name


    private boolean removeByID(String id, String sql){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            long id_number = Long.parseLong(id);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...
            Statement stmt = conn.createStatement();
            //String sql =  "DELETE FROM patient WHERE identification_number=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id_number);
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
