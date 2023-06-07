import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login implements ActionListener {
        JFrame j=new JFrame("Login page");
        JLabel userLabel=new JLabel("user name");
        JLabel passwordLabel=new JLabel("password");
        JTextField userText=new JTextField();
        JPasswordField passwordText=new JPasswordField();
        JButton loginButton=new JButton("login");
        JButton resetButton=new JButton("reset");
        JButton signupButton=new JButton("sign up");
        JLabel messageLabel=new JLabel();
    public login(int user_id){

        userLabel.setBounds(50,50,100,25);
        passwordLabel.setBounds(50,75,100,25);
        userText.setBounds(125,50,200,20);
        passwordText.setBounds(125,80,200,20);
        loginButton.setBounds(123,105,100,25);
        resetButton.setBounds(280,105,100,25);
        signupButton.setBounds(180,205,100,25);
        messageLabel.setBounds(150,250,300,50);

        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        loginButton.setFocusable(false);
        resetButton.setFocusable(false);
        signupButton.setFocusable(false);

        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        signupButton.addActionListener(this);

        j.add(userLabel);
        j.add(passwordLabel);
        j.add(userText);
        j.add(passwordText);
        j.add(loginButton);
        j.add(resetButton);
        j.add(signupButton);
        j.add(messageLabel);

        j.setSize(420,420);
        j.setLayout(null);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == signupButton) {
            new signup();
            j.dispose();
        }
        if (e.getSource() == resetButton) {
            userText.setText("");
            passwordText.setText("");
        }

        if (e.getSource() == loginButton) {

            String userID = userText.getText();
            String password = String.valueOf(passwordText.getPassword());
            DB db=new DB();
            int result= db.cusomerExist(userID,password);
            if (result>=0)
            {
                new home(result);
                j.dispose();
            }
            else
            {
                userText.setText("");
                passwordText.setText("");
                messageLabel.setText("Login failed");
                messageLabel.setForeground(Color.RED);
                System.out.println(userID+" "+password);
//                j.dispose();
            }

            /*if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage(userID);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }

            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("username not found");
            }*/
        }
    }
}
