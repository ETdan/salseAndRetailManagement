import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.*;

public class productlist implements ActionListener {

            int user_id;

            Connection conn;
            DB db=new DB();

            JFrame frame = new JFrame("ProductsList");
            JPanel p=new JPanel();
            JPanel n=new JPanel();
            JButton buyButton=new JButton("Buy");
            JButton addButton=new JButton("ADD");
            JLabel product_idLabel=new JLabel("Product id");
            JLabel amountLabel=new JLabel("Amount");
            JTextField amountText=new JTextField();
            JTextField product_idText=new JTextField();
            JLabel confirmLable=new JLabel();

    public productlist(int user_id){

            this.user_id=user_id;

            DefaultTableModel model = new DefaultTableModel();
//            model.setColumnIdentifiers(columnNames);

            JTable table = new JTable();
            table.setModel(model);
//            table.setAutoResizeMode(20);

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

                conn = DriverManager.getConnection(url, username, password);

                Statement s = conn.createStatement();
                String q = "select * from products";

                ResultSet r = s.executeQuery(q);
                ResultSetMetaData rm = r.getMetaData();


                int cols = rm.getColumnCount();
                System.out.println("cpls:" + cols);

                String[] colName = new String[cols];

                for (int i = 0; i < cols; i++) {
                    colName[i] = rm.getColumnName(i + 1);
                    System.out.println(rm.getColumnName(i+1));
                }
                model.setColumnIdentifiers(colName);
                String id, name, dis, price, amount;
                while (r.next()) {
                    id = r.getString(1);
                    name = r.getString(2);
                    price = r.getString(3);
                    amount = r.getString(4);
                    model.addRow(new Object[]{id, name, price, amount});
                }
                table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {

                        int row = table.getSelectedRow();
                        int col = table.getSelectedColumn();

                        product_idText.setText(String.valueOf(row+1));

                        System.out.println(row+" "+col);
                    }
                });
                table.getColumnModel().getColumn(0).setMaxWidth(10);
                table.getColumnModel().getColumn(1).setMaxWidth(300);
                table.getColumnModel().getColumn(2).setMaxWidth(300);
                table.getColumnModel().getColumn(3).setMaxWidth(100);
            } catch (Exception E) {
                E.printStackTrace();
            }

            product_idLabel.setBounds(30,40,90,40);
            product_idText.setBounds(130,40,90,40);
            amountLabel.setBounds(30,90,90,40);
            amountText.setBounds(130,90,90,40);
            addButton.setBounds(50,150,90,40);
            confirmLable.setBounds(150,150,290,40);
            buyButton.setBounds(100,350,190,30);

            addButton.setFocusable(false);
            buyButton.setFocusable(false);

            addButton.addActionListener(this);
            buyButton.addActionListener(this);

            n.add(product_idLabel);
            n.add(product_idText);
            n.add(amountLabel);
            n.add(amountText);
            n.add(addButton);
            n.add(buyButton);
            n.add(confirmLable);
            p.add(new JScrollPane(table));
            n.setLayout(null);
//      splitpanel

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p, n);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(500);

        Dimension minimumSize = new Dimension(100, 50);
        p.setMinimumSize(minimumSize);
        n.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(400, 200));

//

            frame.getContentPane().add(splitPane);
            frame.setVisible(true);
            frame.setSize(1000,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String p,a;
        if(e.getSource()==addButton){
            p=product_idText.getText();
            a=amountText.getText();

            if (p.isEmpty() || a.isEmpty())
            {
                confirmLable.setForeground(Color.RED);
                confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                confirmLable.setText("make sure to fill both");
            }
            else
            {
                try{

                    Statement s = conn.createStatement();
                    String q = "select amount from products where id="+p+";";

                    ResultSet r = s.executeQuery(q);
                    int id=0;
                    if (r.next())
                        id = r.getInt("amount");
//
                    if(Integer.parseInt(a)<=id)
                    {
                        System.out.println("amount:"+id);
                        confirmLable.setForeground(Color.GREEN);
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setText("Added to cart");

                        try {
                            Statement stmt = conn.createStatement();
                            /*
                            *
                            *
                            *
                            * 
                            *
                            *
                            * add validation to amount
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            * */
                            String sql = "INSERT INTO cart (user_id, product_id,quantity) VALUES ("+user_id+","+id+","+a+")";
                            int rowsInserted = stmt.executeUpdate(sql);
                            System.out.println(rowsInserted);
                        }
                        catch (Exception einsert){
                            System.out.println(einsert);
                        }
                    }
                    else{
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setForeground(Color.RED);
                        confirmLable.setText("Amout to large");
                    }
                }catch (Exception v){
                    System.out.println(v);
                }

//                confirmLable.setForeground(Color.GREEN);
//                confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
//                confirmLable.setText("Added to cart");
            }

            System.out.println(p+" "+a);
        }
        else if (e.getSource()==buyButton) {

        }
    }
}
