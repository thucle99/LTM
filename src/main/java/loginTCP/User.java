/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginTCP;

import java.io.Serializable;

/**
 *
 * @author thuc
 */
public class User implements Serializable {

    private String userName;
    private String password;

    public User() {
        // muốn tạo đối tượng ra,không có thông tin gì
    }

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
        // sử dụng luôn User với đầy đủ các thuộc tính
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
