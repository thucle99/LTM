/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginTCP.DaoChuoi;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thuc
 */
public class TCPServer {
// Khai bao server socket, luong vao-ra, va doi tuong socket

    ServerSocket myServer = null;
    String input;
    DataInputStream is;
    PrintStream os;
    Socket clientSocket = null;
// Mo mot server socket

    public TCPServer() {
        openServer();
        while (true) {
            listening();
        }
    }
    
    public void openServer() {
        try {
            try {
                myServer = new ServerSocket(8888);  
                // taọ 1 server socket cổng 888
            } catch (IOException e) {
                System.out.println(e);
            }
            clientSocket = myServer.accept();  // chờ yêu cầu từ phía client 
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
// Chap nhan ket noi va xu li du lieu

    public void listening() {
        try {
            
// Xu li du lieu nhan duoc va tra ve
            
// doc du lieu vao
                input = is.readLine();
                System.out.println(input);
// xu li du lieu
                ReverseString str = new ReverseString(input);
                str.reverse();
// tra ve du lieu
//                System.out.println(str.get_string());
                os.println(str.get_string());
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        TCPServer tcps = new TCPServer();
    }
}
