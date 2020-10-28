/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginTCP.DaoChuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author thuc
 */
public class TCPClient {
// khai bao socket cho client, luong vao-ra

    Socket mySocket = null;
    DataOutputStream os = null;
    DataInputStream is = null;
// Tao ket noi

    public void connection() {
        try {
            mySocket = new Socket("localhost", 9999);
            os = new DataOutputStream(mySocket.getOutputStream());
            is = new DataInputStream(mySocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void send(String str) { // gui du lieu den server
        if (mySocket != null && os != null) {
            try {
                os.writeBytes(str);
            } catch (UnknownHostException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public String receive() { // nhan du lieu tra ve tu server
        if (mySocket != null && is != null) {
            try {
                String responseStr;
                if ((responseStr = is.readLine()) != null) {
                    return responseStr;
                }
            } catch (UnknownHostException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        return null;
    }
// dong cac ket noi

    public void close() {
        if (mySocket != null && os != null && is != null) {
            try {
                os.close();
                is.close();
                mySocket.close();
            } catch (UnknownHostException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        TCPClient tcpc = new TCPClient();
        tcpc.send("thuc");
        tcpc.receive();
    }
}
