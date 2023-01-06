import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InfoBoard extends JFrame implements ActionListener {

    private Container container = getContentPane();
    private JButton addButton = new JButton("");
    private JButton removeButton = new JButton("");
    private JButton listButton = new JButton("");
    private JButton searchButton = new JButton("");
    private JButton updateButton = new JButton("");
    private JButton backButton = new JButton("BACK");
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/user.png");
    private long id;

    InfoBoard(long id){
        this.id = id;
        setFrameProperties();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Information");
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

            this.dispose();
        }
        if (e.getSource() == removeButton ) {

            this.dispose();
        }
        if (e.getSource() == listButton ) {

        }
        if (e.getSource() == searchButton ) {

            this.dispose();
        }
        if (e.getSource() == backButton) {

            this.dispose();
        }
        if (e.getSource() == updateButton ) {

            this.dispose();
        }
    }// end of actionperformed
}
