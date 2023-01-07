import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class RemoveMedicine extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");
    private JLabel idLabel = new JLabel("ID");
    private JTextField idTextField = new JTextField();
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    RemoveMedicine() {
        setFrameProperties();
        setLocationAndSize();
        listSelection();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Remove");
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 200, 30);
        removeButton.setBounds(50, 100, 200, 30);
        backButton.setBounds(50, 400, 200, 30);
        icon.setBounds(300, 50, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(removeButton);
        container.add(backButton);
        container.add(idLabel);
        container.add(idTextField);
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
        if (e.getSource() == backButton) {
            new Medicine();
            this.dispose();
        }
        if(e.getSource() == removeButton){
            if(!id.isEmpty()){
                res = removeMed(id);
                if (res){
                    JOptionPane.showMessageDialog(this, "Successful Removal");
                }
                else
                    JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            }else
                JOptionPane.showMessageDialog(this, "Please fill id field.", null, JOptionPane.ERROR_MESSAGE);
        }
    }// end of actionPerformed

    public void listSelection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM medicine";
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
    private boolean removeMed(String id){
        final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            long id_number = Long.parseLong(id);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...
            Statement stmt = conn.createStatement();
            String sql =  "DELETE FROM medicine WHERE medicineID=?";
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
