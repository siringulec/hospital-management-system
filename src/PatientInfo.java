import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class PatientInfo extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JButton infoButton = new JButton("Patient Info");
    private JButton backButton = new JButton("Back");
    private JLabel idLabel = new JLabel("Patient ID");
    private JTextField idTextField = new JTextField();
    private JLabel icon = new JLabel();
    private ImageIcon image = new ImageIcon("images/icon.png");
    private long doc_id;

    PatientInfo(long doc_id) {
        this.doc_id = doc_id;
        setFrameProperties();
        setLocationAndSize();
        listSelection(doc_id);
        addComponentsToContainer();
        addActionEvent();
    }

    public void setFrameProperties() {
        container.setLayout(null);
        setTitle("Add Info");
        setVisible(true);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        icon.setIcon(image);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(50, 50, 100, 30);
        idTextField.setBounds(50, 75, 200, 30);
        infoButton.setBounds(50, 100, 200, 30);
        backButton.setBounds(100, 400, 200, 30);
        icon.setBounds(300, 30, image.getIconWidth(), image.getIconHeight());
    }//positons and sizes  on the frame

    public void addComponentsToContainer() {
        container.add(infoButton);
        container.add(backButton);
        container.add(idLabel);
        container.add(idTextField);
        container.add(icon);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        infoButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (e.getSource() == backButton) {
            new DoctorDashboard(doc_id);
            this.dispose();
        }
        if(e.getSource() == infoButton){
            String id = idTextField.getText();
            if(!id.isEmpty()){
                ArrayList<String> idList = checkValid(doc_id);
                long patient_id = Long.parseLong(id);
                for (int i = 0; i < idList.size(); i++) {
                    if (idList.get(i).equals(id)){
                        isValid = true;
                    } else
                        isValid = false;
                }// end of for
                if (isValid){
                    new InfoBoard(patient_id, doc_id);
                    this.dispose();
                } else
                    JOptionPane.showMessageDialog(this, "Please enter a valid id.", null, JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(this, "Please enter an id for a patient.", null, JOptionPane.ERROR_MESSAGE);
        }// end of infoButton
    }// end of actionPerformed

    public void listSelection(long doc_id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT appointments.patientID, patient.name FROM appointments"
                +" INNER JOIN patient ON appointments.patientID = patient.identification_number"
                +" WHERE appointments.doctorID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, doc_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List buildTable = new List();
            JTable table = new JTable(buildTable.buildTableModel(resultSet));
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane sp = new JScrollPane(table);
            container.add(sp);
            sp.setBounds(50, 175, getWidth() -150, getHeight() - 300);
            stmt.close();
            conn.close();
        }// end of try
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }// end of info
    public ArrayList<String> checkValid (long doc_id){
        try{
            ArrayList<String> idList = new ArrayList<String>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT patientID FROM appointments WHERE doctorID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, doc_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idList.add(resultSet.getString(1));
                //System.out.println(resultSet.getString(1));
            }
            return idList;
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error", null, JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }// end of check valid
} // end of class
