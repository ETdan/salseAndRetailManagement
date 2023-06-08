import java.sql.DriverManager;
import java.sql.*;

public class DB {

    Connection conn;
    public DB(){
         String url="jdbc:mysql://localhost:3306/salesAndRetailManagement";
         String username="root";
         String password="";

         try{
             Class.forName("com.mysql.cj.jdbc.Driver");

              conn= DriverManager.getConnection(url,username,password);

             Statement statement=conn.createStatement();

             ResultSet resultSet=statement.executeQuery("select * from customers");

             while (resultSet.next())
             {
                 System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
             }
//             conn.close();
         }catch (Exception e)
         {
             System.out.println(e);
         }
    }
    public int cusomerExist(String username, String password){
        int user_id;
        try {
            Statement statement=conn.createStatement();
            String sql="select *from customers where username='"+username+"' and password='"+password+"';";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next())
            {
                 user_id=resultSet.getInt(1);
//                 +" "+resultSet.getString(2)+" "+resultSet.getString(3)+ "/n\n";
                System.out.println("user_id send:"+user_id);
                return Integer.parseInt(username);
            }
            else
            {
                System.out.println(-1);
                return -1;
            }
//
//            t.addRow(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
//                System.out.println(user_id);
//            conn.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return -1;
//        return false;
    }

    public Connection getConn() {
        return this.conn;
    }
}
