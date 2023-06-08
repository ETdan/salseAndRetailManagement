import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class manager implements ActionListener {

    int user_id;
    JFrame frame = new JFrame("Manager Page");
    JButton addproduct = new JButton("Add product");
    JButton viewreport = new JButton("View report");
    JButton addemployee = new JButton("Add employee");
    JButton addshift = new JButton("Add shift");
    JButton viewshift = new JButton("View shift");

    public manager(int user_id) {

        this.user_id = user_id;

        addproduct.setBounds(20, 80, 150, 30);
        viewreport.setBounds(175, 80, 150, 30);
        addemployee.setBounds(330, 80, 150, 30);
        addshift.setBounds(20, 120, 150, 30);
        viewshift.setBounds(175, 120, 150, 30);

        addproduct.setFocusable(false);
        viewreport.setFocusable(false);
        addemployee.setFocusable(false);
        addshift.setFocusable(false);
        viewshift.setFocusable(false);

        viewreport.addActionListener(this);
        addproduct.addActionListener(this);
        addemployee.addActionListener(this);
        addshift.addActionListener(this);
        viewshift.addActionListener(this);


        frame.add(addproduct);
        frame.add(viewreport);
        frame.add(addemployee);
        frame.add(addshift);
        frame.add(viewshift);
        frame.setSize(550, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addproduct) {
            new addproduct(user_id);
            frame.dispose();
        } else if (e.getSource() == viewreport) {
            new viewReceipt(user_id);
            frame.dispose();
        } else if (e.getSource() == addemployee) {
            new addEmp(user_id);
            frame.dispose();
        } else if (e.getSource() == addshift) {
            new addShift(user_id);
            frame.dispose();
        } else if (e.getSource() == viewshift) {
            new viewShift(user_id);
            frame.dispose();
        }
        frame.dispose();

    }
}
