/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author thuc
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // tao socket kết nối đển Server
        Socket socket = new Socket("localhost", 9999);

        // tạo đối tượng DataInputStream để đọc dữ liệu
        DataInputStream dIn = new DataInputStream(socket.getInputStream());
        // Tạo đối tượng DataOutputStream để truyền dữ liệu đi
        DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
        while (true) {
            System.out.println("Enter message : ");
            // nhập tin nhắn và gửi cho server 
            String mesage = sc.nextLine();
            // hàm WriteUTF() của OutputStream cho phép nó gửi Data dạng String đến InputStream của Client
            dOut.writeUTF(mesage);

            System.out.println("You say : " + mesage);
            // đọc tin nhắn từ client , dùng ReadUTF để đọc String
            String client_message = dIn.readUTF();
            System.out.println("Server say : " + client_message);
        }
    }
}
