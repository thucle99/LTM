/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testLoginMVC;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thuc
 */
public class UserDao {
    Connection conn ;
//    private  String jdbcURl;
//    private String jdbcUsername;
//    private String jdbcPassword ;

    public UserDao(String jdbcURL,String jdbcUsername,String jdbcPassowrd) {
       try{
            Class.forName("com.mysql.cj.jdbc.Driver") ;
        conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassowrd) ;
       }catch(ClassNotFoundException e){
           e.printStackTrace();
       }catch(SQLException e){
           e.printStackTrace();
       }
    }
    
    public boolean  Login(User user){
        PreparedStatement pre;
        try{
            pre =conn.prepareStatement("select * from tblUser"  //table
                    + " where username = ? and password = ?");
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                return true ;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false ;
    }
}
