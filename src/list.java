import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Stack;

public class list implements ActionListener {

//    DB db=new DB();
//    Connection conn=db.getConn();
    JFrame frame=new JFrame("WELCOME");
    JLabel textfiled=new JLabel();
    JButton adminButton=new JButton("Admin");
    JTable t=new JTable();
    public list() {


        textfiled.setBounds(80,140,500,500);
        adminButton.setBounds(80,100,100,30);
        textfiled.setFocusable(false);
        adminButton.setFocusable(false);

        adminButton.addActionListener(this);


        t.setBounds(80,180,500,500);


//        JLabel x=new JLabel("some");
//        x.setBounds(80,100,30,30);

//        frame.add(x);
//        frame.add(t);

        frame.add(adminButton);
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            JFrame frame1 = new JFrame("Database Search Result");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setLayout(new BorderLayout());

            DefaultTableModel model = new DefaultTableModel();
//            model.setColumnIdentifiers(columnNames);

            JTable table = new JTable();
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            try {
                String url = "jdbc:mysql://localhost:3306/salesAndRetailManagement";
                String username = "root";
                String password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conn = DriverManager.getConnection(url, username, password);

                Statement s = conn.createStatement();
                String q = "select * from products";

                ResultSet r = s.executeQuery(q);
                ResultSetMetaData rm = r.getMetaData();

//                DefaultTableModel m = new DefaultTableModel();
//                m.setColumnIdentifiers();

                int cols = rm.getColumnCount();
                System.out.println("cpls:" + cols);

                String[] colName = new String[cols];

                for (int i = 0; i < cols; i++) {
                    colName[i] = rm.getColumnName(i + 1);
//                    System.out.println(rm.getColumnName(i+1));
                }
                model.setColumnIdentifiers(colName);
                String id, name, dis, price, amount;
                while (r.next()) {
                    id = r.getString(1);
                    name = r.getString(2);
                    dis = r.getString(3);
                    price = r.getString(4);
                    amount = r.getString(4);
                    model.addRow(new Object[]{id, name, dis, price, amount});
                }
                table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {

                        int row = table.getSelectedRow();
                        int col = table.getSelectedColumn();

                        System.out.println(row+" "+col);
                    }
                });
            } catch (Exception E) {
                E.printStackTrace();
            }
        frame1.add(t);
            frame1.add(scroll);
            frame1.setVisible(true);
            frame1.setSize(400,300);
        }

//            new login();

//        frame.dispose();

    }
}
