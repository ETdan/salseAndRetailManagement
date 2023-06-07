import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home implements ActionListener {
    int user_id;
    JFrame frame=new JFrame("WELCOME");
    JButton customerButton=new JButton("customer");
    JButton adminButton=new JButton("Admin");
    public home(int user_id){

        this.user_id=user_id;

        customerButton.setBounds(80,100,100,30);
        adminButton.setBounds(80,140,100,30);
        customerButton.setFocusable(false);
        adminButton.setFocusable(false);

        adminButton.addActionListener(this);
        customerButton.addActionListener(this);



        frame.add(customerButton);
        frame.add(adminButton);
    frame.setSize(300,300);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() ==customerButton)
        {
            new productlist(user_id);
            frame.dispose();
        }
        else
        {
            System.out.println("Admin");
            new productlist(user_id);
            frame.dispose();
        }

        frame.dispose();

    }
}
