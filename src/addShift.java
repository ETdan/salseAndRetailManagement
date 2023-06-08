import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class addShift implements ActionListener {

    int user_id;

    Connection conn;

    JFrame frame=new JFrame("Add Shift");
    JLabel employeIdLable=new JLabel("employe id");
    JLabel employeStartLable=new JLabel("start");
    JLabel employeEndLable=new JLabel("end");

    JTextField employeIdText=new JTextField();
    JTextField employeStartText=new JTextField();
    JTextField employeEndText=new JTextField();

    JLabel confirmLable=new JLabel();

    JButton add=new JButton("ADD");
    JButton go=new JButton("return");
    public addShift(int user_id)
    {
        this.user_id=user_id;
        employeIdLable.setBounds(80,100,100,30);
        employeStartLable.setBounds(80,135,100,30);
        employeEndLable.setBounds(80,170,100,30);

        employeIdText.setBounds(185,100,100,30);
        employeStartText.setBounds(185,135,100,30);
        employeEndText.setBounds(185,170,100,30);
        add.setBounds(200,270,80,20);
        go.setBounds(200,295,80,20);
        confirmLable.setBounds(40,210,300,30);
//        confirmLable.setText("aaaaaaaaaaaaaaaaa");

        add.setFocusable(false);
        go.setFocusable(false);


        add.addActionListener(this);
        go.addActionListener(this);

        frame.add(employeIdLable);
        frame.add(employeStartLable);
        frame.add(employeEndLable);
        frame.add(employeIdText);
        frame.add(employeStartText);
        frame.add(employeEndText);
        frame.add(confirmLable);
        frame.add(add);
        frame.add(go);

        frame.setSize(350, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==go)
        {
            new manager(this.user_id);
            frame.dispose();
        }
        if(e.getSource()==add){
            String pu=  employeIdText.getText();
            String pp= employeStartText.getText();
            String pt= employeEndText.getText();

            if (!(!pu.isEmpty() && !pt.isEmpty() && !pp.isEmpty()))
            {
                confirmLable.setForeground(Color.RED);
                confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                confirmLable.setText("make sure to fill all");
            }
            else
            {
                try{
                    String url = "jdbc:mysql://localhost:3306/salesAndRetailManagement";
                    String username = "root";
                    String password = "";

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = DriverManager.getConnection(url, username, password);

                    Statement s = conn.createStatement();
                    String q = "insert into shift (user_id,start,end) values('"+pu+"','"+pp+"','"+pt+"');";

                    int r = s.executeUpdate(q);

//
                    if(r>=0)
                    {
                        confirmLable.setForeground(Color.GREEN);
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setText("Added to products");
                    }
                    else{
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setForeground(Color.RED);
                        confirmLable.setText("error try again");
                    }
                }catch (Exception v){
                    System.out.println(v);
                }
            }
        }
    }
}
