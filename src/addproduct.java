import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class addproduct implements ActionListener {

    int user_id;

    Connection conn;

    JFrame frame=new JFrame("add product");
    JLabel productName=new JLabel("Name");
    JLabel productPrice=new JLabel("Price");
    JLabel productAmount=new JLabel("Quantity");

    JTextField productNameText=new JTextField();
    JTextField productPriceText=new JTextField();
    JTextField productAmountText=new JTextField();

    JLabel confirmLable=new JLabel();

    JButton add=new JButton("ADD");
    JButton go=new JButton("return");
    public addproduct(int user_id)
    {
        this.user_id=user_id;
        productName.setBounds(80,100,100,30);
        productPrice.setBounds(80,135,100,30);
        productAmount.setBounds(80,170,100,30);

        productNameText.setBounds(185,100,100,30);
        productPriceText.setBounds(185,135,100,30);
        productAmountText.setBounds(185,170,100,30);
        add.setBounds(200,270,80,20);
        go.setBounds(200,295,80,20);
        confirmLable.setBounds(40,210,300,30);
//        confirmLable.setText("aaaaaaaaaaaaaaaaa");

        add.setFocusable(false);
        go.setFocusable(false);


        add.addActionListener(this);
        go.addActionListener(this);

        frame.add(productName);
        frame.add(productPrice);
        frame.add(productAmount);
        frame.add(productNameText);
        frame.add(productPriceText);
        frame.add(productAmountText);
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
            String pn=productNameText.getText();
            int pp=0;
            int pa=0;
            try {
                pp= Integer.parseInt(productPriceText.getText());
                pa= Integer.parseInt(productAmountText.getText());
            }
            catch (Exception E)
            {
                System.out.println(E);
            }

            if (!(!pn.isEmpty() && pa!=0 && pp!=0))
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

//                    Statement s = conn.createStatement();
//                    System.out.println("user iddd:" +user_id);
//                    String q = "select * from shift where user_id="+this.user_id+";";

//                    ResultSet r = s.executeQuery(q);

                    Statement s = conn.createStatement();
                    String q = "insert into products (name,price,amount) values('"+pn+"',"+pp+","+pa+");";

                    int r = s.executeUpdate(q);

//
                    if(r>=0)
                    {
//                        System.out.println("amount:"+id);
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
