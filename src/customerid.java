import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerid implements ActionListener {
    int user_id;
    JFrame frame=new JFrame("Customer add");
    JTextField custemorIDText=new JTextField();
    JButton addCustomer=new JButton("Add customer");
    public customerid()
    {

        custemorIDText.setBounds(30,100,200,30);
        addCustomer.setBounds(30,140,200,30);

        addCustomer.setFocusable(false);
        addCustomer.addActionListener(this);



        frame.add(custemorIDText);
        frame.add(addCustomer);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() ==addCustomer)
        {
            int s= Integer.parseInt(custemorIDText.getText());
            new productlist(s);
            frame.dispose();
        }

    }
}
