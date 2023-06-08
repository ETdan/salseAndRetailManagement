import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class viewShift implements ActionListener{
    int user_id;
    Connection conn;

    JFrame frame=new JFrame("SHIFTS");

    JButton emp=new JButton("back to manager page");
    JPanel panel=new JPanel();

    public viewShift(int user_id) {

        this.user_id=user_id;
        System.out.println(this.user_id+" ?????????");

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
            System.out.println("user iddd:" +user_id);
            String q = "select * from shift;";

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
            String id, start, end, userid;
            while (r.next()) {
                userid = r.getString(1);
                start = r.getString(2);
                end = r.getString(3);
                id = r.getString(4);
                model.addRow(new Object[]{userid, start, end, id});
            }
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();

//                    product_idText.setText(String.valueOf(row+1));

                    System.out.println(row+" "+col);
                }
            });
            table.getColumnModel().getColumn(0).setMaxWidth(300);
            table.getColumnModel().getColumn(1).setMaxWidth(300);
            table.getColumnModel().getColumn(2).setMaxWidth(300);
            table.getColumnModel().getColumn(3).setMaxWidth(20);
        } catch (Exception E) {
            E.printStackTrace();
        }


        emp.setFocusable(false);
        emp.addActionListener( this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(emp);

        // Create a frame to hold the table and the button
        JScrollPane scrollPane = new JScrollPane(table);
//        JFrame frame = new JFrame("Table with Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the table and the button panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

//        panel.add(table);
//        frame.getContentPane().add(scrollPane);

//        frame.add(panel);
//        frame.add(emp);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==emp)
        {
            new manager(this.user_id);
            frame.dispose();
        }
    }
}
