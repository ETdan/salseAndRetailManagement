import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class emplogin implements ActionListener {
    int user_id;
    Connection conn;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    JFrame j=new JFrame("Login page");
    JLabel userLabel=new JLabel("user name");
    JLabel passwordLabel=new JLabel("password");
    JTextField userText=new JTextField();
    JPasswordField passwordText=new JPasswordField();
    JButton loginButton=new JButton("login");
    JButton resetButton=new JButton("reset");

    JLabel messageLabel=new JLabel();
    public emplogin(int user_id){

        userLabel.setBounds(50,50,100,25);
        passwordLabel.setBounds(50,75,100,25);
        userText.setBounds(125,50,200,20);
        passwordText.setBounds(125,80,200,20);
        loginButton.setBounds(123,105,100,25);
        resetButton.setBounds(225,105,100,25);
        messageLabel.setBounds(150,250,300,50);

        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        loginButton.setFocusable(false);
        resetButton.setFocusable(false);

        loginButton.addActionListener(this);
        resetButton.addActionListener(this);

        j.add(userLabel);
        j.add(passwordLabel);
        j.add(userText);
        j.add(passwordText);
        j.add(loginButton);
        j.add(resetButton);
        j.add(messageLabel);

        j.setSize(420,420);
        j.setLayout(null);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userText.setText("");
            passwordText.setText("");
        }

        if (e.getSource() == loginButton) {

            String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());

            int result= 0;
            int user_id;

            try {
                String url = "jdbc:mysql://localhost:3306/salesAndRetailManagement";
                String u = "root";
                String p = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(url, u, p);

                Statement statement=conn.createStatement();
                String sql="select *from admin where username='"+username+"' and password='"+password+"';";
                ResultSet resultSet=statement.executeQuery(sql);

                if (resultSet.next())
                {
                    if(resultSet.getString(4).charAt(0)=='e')
                        new employee(resultSet.getInt(1));
                    else
                        new manager(resultSet.getInt(1));
                    j.dispose();
                }
                else
                {
                    userText.setText("");
                    passwordText.setText("");
                    messageLabel.setText("Login failed");
                    messageLabel.setForeground(Color.RED);
                    System.out.println(username+" "+password);
                }
            }catch (Exception E)
            {
                System.out.println(E);
            }


        }

        }
        }

