import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin implements ActionListener {
    int user_id;
    JFrame frame=new JFrame("WELCOME");
    JButton managerButton=new JButton("Manager");
    JButton employeeButton=new JButton("Employee");
    public admin(int user_id){

        this.user_id=user_id;

        managerButton.setBounds(80,100,100,30);
        employeeButton.setBounds(80,140,100,30);
        managerButton.setFocusable(false);
        employeeButton.setFocusable(false);

        employeeButton.addActionListener(this);
        managerButton.addActionListener(this);



        frame.add(managerButton);
        frame.add(employeeButton);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() ==managerButton)
        {
            new emplogin(0);
            frame.dispose();
        }
        else
        {
            System.out.println("Employee");
            new emplogin(0);
            frame.dispose();
        }

        frame.dispose();

    }
}
