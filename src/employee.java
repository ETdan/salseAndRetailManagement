import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class employee implements ActionListener {
    Connection conn ;
    PreparedStatement pst;
    int user_id;

    JPanel panel = new JPanel();

    JFrame frame = new JFrame("employe menu");
    JButton viewshiftButton = new JButton("View Shift");
    JButton buyButton = new JButton("Buy");

    public employee(int user_id) {
        this.user_id = user_id;
//        System.out.println(this.user_id + "abbbabababab");

            viewshiftButton.setBounds(80, 100, 100, 30);
            buyButton.setBounds(80, 140, 100, 30);


            viewshiftButton.setFocusable(false);
            buyButton.setFocusable(false);

            buyButton.addActionListener(this);
            viewshiftButton.addActionListener(this);


            frame.add(viewshiftButton);
            frame.add(buyButton);

            frame.setSize(420, 420);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewshiftButton) {
           new shift(this.user_id);
           frame.dispose();

        } if (e.getSource() == buyButton) {

           new productlist(this.user_id);
           frame.dispose();

        }

    }
}
