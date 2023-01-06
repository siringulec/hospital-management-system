import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

public class SearchByPatient extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel idLabel = new JLabel("Patient ID");
    private JLabel dateLabel = new JLabel("Date (yyyy-mm-dd)");
    private JTextField idTextField = new JTextField();
    private JTextField dateTextField = new JTextField();
    private JButton searchButton = new JButton("Search");
    private JButton backButton = new JButton("Back");
    private String sql;
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user-small.png");

    SearchByPatient(String sql) {
        this.sql = sql;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }//constructer

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Search");
        setVisible(true);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 35, 100, 30);
        idTextField.setBounds(50, 65, 205, 30);
        dateLabel.setBounds(50, 95, 200, 30);
        dateTextField.setBounds(50, 125, 200, 30);
        searchButton.setBounds(50, 175, 200, 30);
        backButton.setBounds(290, 175, 100, 30);
        icon.setBounds(290, 25, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(dateLabel);
        container.add(idLabel);
        container.add(dateTextField);
        container.add(idTextField);
        container.add(searchButton);
        container.add(backButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
        if (e.getSource() == searchButton) {
            try{
                String date = dateTextField.getText();
                String id = idTextField.getText();
                Long id_number = Long.parseLong(id);
                new List().list(id_number, sql, date);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Please make sure to enter everything correctly.", "", JOptionPane.ERROR_MESSAGE);
            }
        }// end
    }// end of actionPerformed
}// end of class
