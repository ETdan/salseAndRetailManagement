import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;


public class buyproduct implements ActionListener {

    int user_id;
    Vector<Integer> x=new Vector<>();

    Connection conn;
    DB db=new DB();

    JFrame frame = new JFrame("buy page");
    JPanel p=new JPanel();
    JPanel n=new JPanel();
    JButton buyButton=new JButton("Buy");
    JButton backTocart=new JButton("cart");
    JButton removeButton=new JButton("remove");
    JLabel product_idLabel=new JLabel("id");
    JLabel amountLabel=new JLabel("Amount");
    JTextField amountText=new JTextField();
    JTextField product_idText=new JTextField();
    JLabel confirmLable=new JLabel();

    public buyproduct(int user_id){

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
//            String q = "select * from cart";

            System.out.println("user iddd:" +user_id);
            String q = "select * from cart where user_id="+user_id+";";

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
            String id, userID, productID;
            int quntity;
            while (r.next()) {
                id = r.getString(1);
                userID = r.getString(2);
                productID = r.getString(3);
                quntity = r.getInt(4);
                this.x.add(quntity);
                model.addRow(new Object[]{id, userID, productID, quntity});
            }
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());

                    Object value=table.getValueAt(row,col);

                    product_idText.setText(String.valueOf(value));

                    System.out.println(value+" "+col);
                }
            });
            table.getColumnModel().getColumn(0).setMaxWidth(20);
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
        removeButton.setBounds(50,150,90,40);
        backTocart.setBounds(50,195,90,40);
        confirmLable.setBounds(150,150,490,40);
        buyButton.setBounds(100,350,190,30);

        removeButton.setFocusable(false);
        buyButton.setFocusable(false);
        backTocart.setFocusable(false);

        removeButton.addActionListener(this);
        buyButton.addActionListener(this);
        backTocart.addActionListener(this);

        n.add(product_idLabel);
        n.add(product_idText);
//        n.add(amountLabel);
//        n.add(amountText);
        n.add(removeButton);
        n.add(buyButton);
        n.add(confirmLable);
        n.add(backTocart);
        p.add(new JScrollPane(table));
        n.setLayout(null);
//      splitpanel

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p, n);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(500);

        Dimension minimumSize = new Dimension(450, 50);
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
        if(e.getSource()==removeButton){
            p=product_idText.getText();
//            a=amountText.getText();

            if (p.isEmpty() /*|| a.isEmpty()*/)
            {
                confirmLable.setForeground(Color.RED);
                confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                confirmLable.setText("make sure to fill both");
            }
            else
            {
                try{
                    x.remove(p);
                    Statement s = conn.createStatement();
                    String q = "delete from cart where id="+p+";";

                    int affectedRow = s.executeUpdate(q);
//                    int id=0;
//                    if (affectedRow>0)
//                        id = r.getInt("product_id");
//
                    if( affectedRow>0)
                    {
                        System.out.println("amount:"+affectedRow);
                        confirmLable.setForeground(Color.GREEN);
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setText("product removed");
                    }
                    else{
                        confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
                        confirmLable.setForeground(Color.RED);
                        confirmLable.setText("Data does not exist");
                    }
                }catch (Exception v){
                    System.out.println(v);
                }

//                confirmLable.setForeground(Color.GREEN);
//                confirmLable.setFont(new Font("Arial", Font.ITALIC,26));
//                confirmLable.setText("Added to cart");
            }

            System.out.println(p);
        }
        else if (e.getSource()==backTocart) {

            new productlist(this.user_id);
            frame.dispose();
        }
        else if (e.getSource()==buyButton) {
            try {
             Statement s=conn.createStatement();
             Statement prs=conn.createStatement();
             Statement pl=conn.createStatement();
             Statement rc=conn.createStatement();
             String d;
             int pa=0;
             int pra=0;
                String q = "select * from cart where user_id="+user_id+";";
                String rq ;

                ResultSet r = s.executeQuery(q);
                int amount;
                int product_id;
                int uid;
                int i=0;
                while (r.next()) {
                    amount = r.getInt(4);
                    uid = r.getInt(2);
                    product_id = r.getInt(3);
                    System.out.println("amount:"+amount);

                     rq = "insert into receipt (user_id,product_id) values("+uid+","+product_id+")";

                    int rr= rc.executeUpdate(rq);

                    String pid="select amount from products where id="+product_id+";";
                    ResultSet pr=prs.executeQuery(pid);
                    if (pr.next())
                        pa= pr.getInt("amount");
                    pa=amount-pa;
                    String pq="UPDATE products SET amount =" +pa+" WHERE id="+product_id+";";
                    int rpq = pl.executeUpdate(pq);
                    if (rpq<0)
                        confirmLable.setText("product" +product_id+"haven't been add at "+rpq);
                    else
                        confirmLable.setText("product " +product_id+" add at "+rpq);
                    System.out.println(pa);
                }
                System.out.println("to receit:"+user_id);

                String sql = "TRUNCATE TABLE cart";
                Statement stmt = conn.createStatement();

                int rowsAffected = stmt.executeUpdate(sql);

                new receit(user_id);
                frame.dispose();
            }catch (Exception exception)
            {
                System.out.println(exception);
            }


            System.out.println("boght");
        }
    }
}
