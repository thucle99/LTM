/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginTCP;

/**
 *
 * @author thuc
 */
public class ServerView {
    public ServerView() {
        new ServerControl();
        // gọi hàm constructor của ServerControl ,không có các thuộc tính khác
//        showMessage("TCP server is running...");
        System.out.println("TCP server is running...");
    }

//    public void showMessage(String msg) {
//        System.out.println(msg);
//    }
}
