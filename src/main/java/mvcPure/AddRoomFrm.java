package mvcPure;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddRoomFrm extends JFrame implements ActionListener {

    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtType;
    private JTextField txtDisplayPrice;
    private JTextField txtDescription;
    private JButton btnSubmit;
    private JButton btnReset;

    public AddRoomFrm() {
        super("Room management pure-MVC");
        txtID = new JTextField(15);
        txtName = new JTextField(15);
        txtType = new JTextField(15);
        txtDisplayPrice = new JTextField(15);
        txtDescription = new JTextField(15);
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(6, 2));
        content.add(new JLabel("ID:"));
        content.add(txtID);
        content.add(new JLabel("Name:"));
        content.add(txtName);
        content.add(new JLabel("Type:"));
        content.add(txtType);
        content.add(new JLabel("Display price:"));
        content.add(txtDisplayPrice);
        content.add(new JLabel("Description:"));
        content.add(txtDescription);
        content.add(btnReset);
        content.add(btnSubmit);
        btnSubmit.addActionListener(this);
        btnReset.addActionListener(this);
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnSubmit)) {
            btnSubmit_actionperformed();
        } else if (btn.equals(btnReset)) {
            btnReset_actionperformed();
        }
    }

    public void btnSubmit_actionperformed() {
        Room room = new Room();
        room.setId(txtID.getText());
        room.setName(txtName.getText());
        room.setType(txtType.getText());
        room.setDisplayPrice(Float.parseFloat(txtDisplayPrice.getText()));
        room.setDescription(txtDescription.getText());
    }
    RoomDAO rd = new RoomDAO();

    rd.addRoom (room);

    JOptionPane.showMessageDialog(this, "Add room successfullly!");
public void btnReset_actionperformed() {
        txtID.setText("");
        txtName.setText("");
        txtType.setText("");
        txtDisplayPrice.setText("");
        txtDescription.setText("");
    }
}
