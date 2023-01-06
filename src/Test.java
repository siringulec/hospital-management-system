import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Test extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JButton addButton = new JButton("ADD TEST");
    private JButton removeButton = new JButton("REMOVE TEST");
    private JButton listButton = new JButton("LIST TESTS");
    private JButton searchButton = new JButton("SEARCH TEST");
    private JButton updateButton = new JButton("UPDATE TEST");
    private JButton backButton = new JButton("BACK");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");

    Test() {
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }// end of costructor

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("TESTS");
        setVisible(true);
        setSize(400, 555);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }
    public void setLocationAndSize() {
        addButton.setBounds(50, 225, 300, 30);
        removeButton.setBounds(50, 275, 300, 30);
        listButton.setBounds(50, 325 , 300 , 30);
        searchButton.setBounds(50, 375 , 300 , 30);
        updateButton.setBounds(50, 425 , 300 , 30);
        backButton.setBounds(50, 475, 300, 30);
        icon.setBounds(136, 50, image.getIconWidth(), image.getIconHeight());
    }

    public void addComponentsToContainer() {
        container.add(addButton);
        container.add(removeButton);
        container.add(listButton);
        container.add(searchButton);
        container.add(backButton);
        container.add(updateButton);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        listButton.addActionListener(this);
        searchButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton ) {
            new InsertTest();
            this.dispose();
        }
        if (e.getSource() == removeButton ) {
            String sql = "DELETE FROM test WHERE patientID=? AND date=?";
            new RemoveByPatient(sql);
            this.dispose();
        }
        if (e.getSource() == listButton ) {
            String sql = "SELECT * FROM test";
            new List().list(sql);
        }
        if (e.getSource() == searchButton ) {
            String sql = "SELECT * FROM test WHERE patientID=?  AND date=?";
            new SearchByPatient(sql);
            this.dispose();
        }
        if (e.getSource() == backButton) {
            new AdminDashboard();
            this.dispose();
        }
        if (e.getSource() == updateButton ) {
            //new UpdateTest();
            this.dispose();
        }
    }// end of actionperformed
}// end of class
