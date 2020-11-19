/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thuc
 */
public class UserDAO {
    String jdbcURL = "jdbc:mysql://localhost:3306/userDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    Connection conn;

    public UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");           
            conn = DriverManager.getConnection(jdbcURL,
                    jdbcUsername, jdbcPassword);
            //connect with mysql
        }catch (ClassNotFoundException e) {
            System.out.println("error" +e);
        }catch(SQLException e){
            System.out.println("error"+e);
        }
    }// connect with mysql
    
    public boolean login(User user){
        boolean isValid =false ;
        PreparedStatement pre ;
        try{
            pre = conn.prepareStatement("select * from tblUser"  //table
                    + " where username = ? and password = ?");
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            ResultSet rs = pre.executeQuery() ;
            if(rs.next()){
                isValid =true;
            }
        }catch(SQLException e){
            System.out.println("error" +e);
        }
        return isValid ;
    }
}
