import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class receit implements ActionListener {

    int user_id;
    Connection conn;
    JFrame frame=new JFrame("Receipt");
    JLabel dateLabel=new JLabel();
    JLabel amountLabel=new JLabel();
    JLabel priceLabel=new JLabel();
    JLabel priceLabela=new JLabel();
    JLabel taxLabel=new JLabel();
    JButton productButton=new JButton("Back to products");

    JTextField dateText=new JTextField();
    JTextField amountTaxt=new JTextField();
    JTextField priceText=new JTextField();
    JTextField priceTexta=new JTextField();
    JTextField taxText=new JTextField();

    JButton comfirm=new JButton("confirm");
    public receit(int user_id) {
        this.user_id=user_id;

        dateLabel.setBounds(80, 100, 100, 30);
        amountLabel.setBounds(80, 140, 100, 30);
        priceLabel.setBounds(80, 175, 100, 30);
        priceLabela.setBounds(80, 205, 100, 30);
        taxLabel.setBounds(80, 235, 100, 30);
        productButton.setBounds(180, 355, 150, 30);

        comfirm.setBounds(180, 390, 150, 30);


        dateText.setBounds(190, 100, 150, 30);
        amountTaxt.setBounds(190, 140, 100, 30);
        priceText.setBounds(190, 175, 100, 30);
        priceTexta.setBounds(190, 205, 100, 30);
        taxText.setBounds(190, 235, 100, 30);

        productButton.setFocusable(false);

        dateLabel.setText("Date:");
        amountLabel.setText("total products");
        priceLabel.setText("price before tax");
        priceLabela.setText("price after tax");
        taxLabel.setText("Tax");


        int counter = 0;
        int price = 0;
        try {
            String url = "jdbc:mysql://localhost:3306/salesAndRetailManagement";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            Statement s = conn.createStatement();
            System.out.println("user iddd:" + user_id);
            String q = "select * from receipt where user_id=" + this.user_id + ";";

            ResultSet r = s.executeQuery(q);

            int id;
            int userid;
            int product_id;
            price = 0;
            counter = 0;
            while (r.next()) {
                userid = r.getInt(2);
                product_id = r.getInt(3);
                Statement rs = conn.createStatement();
                String rq = "select price from products where id=" + product_id + ";";

                ResultSet rr = rs.executeQuery(rq);
                if (rr.next())
                    price=price + rr.getInt("price");
                counter++;
                System.out.println("i");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        dateText.setText(String.valueOf(dateFormat.format(date)));
        taxText.setText("15%");
        amountTaxt.setText(String.valueOf(counter));
        priceText.setText(String.valueOf(price));
        priceTexta.setText(String.valueOf(price+(price * 0.15)));

        productButton.addActionListener(this);
        comfirm.addActionListener(this);


        frame.add(taxText);
        frame.add(priceTexta);
        frame.add(priceText);
        frame.add(amountTaxt);
        frame.add(dateText);

        frame.add(dateLabel);
        frame.add(amountLabel);
        frame.add(priceLabel);
        frame.add(productButton);
        frame.add(taxLabel);
        frame.add(priceLabela);
        frame.add(comfirm);

        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==productButton)
        {
            new productlist(this.user_id);
            frame.dispose();
        }if(e.getSource()==comfirm)
        {
            try{

            String sql = "delete from receipt where user_id= "+this.user_id+";";

            Statement stmt = conn.createStatement();

            int rowsAffected = stmt.executeUpdate(sql);
            }
            catch (Exception E)
            {
                System.out.println(E);
            }
//            new productlist(this.user_id);
//            frame.dispose();
        }

    }
};