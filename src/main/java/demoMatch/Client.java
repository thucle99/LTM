/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoMatch;

/**
 *
 * @author thuc
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    BufferedReader Sinput;                // to read the socket
    PrintWriter Soutput;        // to write on the socket
    Socket socket;
    private int port = 1500;
    private Memory tempApp;

    // Constructor connection receiving a socket number
    public Client(Memory tempApp) {
        this.tempApp = tempApp;
        // we use "localhost" as host name, the server is on the same machine
        // but you can put the "real" server name or IP address
        try {
            socket = new Socket("localhost", port);
        } catch (Exception e) {
            System.out.println("Error connectiong to server:" + e);
            return;
        }
        System.out.println("Connection accepted "
                + socket.getInetAddress() + ":"
                + socket.getPort());

        /* Creating both Data Stream */
        try {
            Sinput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Soutput = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Exception creating new Input/output Streams: " + e);
            return;
        }
    }

    public void listenToServer() {
        Thread listening = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String readingData = Sinput.readLine();
                        processServerData(readingData);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        listening.start();
    }

    public void sendData(String sendServerData, String code) {
        Soutput.println(code + "-" + sendServerData);
    }

    public void processServerData(String string) {
        if (string.contains("cardflip")) {

            String cardToFlipLocation = string.replace("cardflip-", "");

            tempApp.flipCard(Integer.parseInt(cardToFlipLocation));

        }
    }

    public String sendAndGetData(String data) {
        Soutput.println(data);
        try {
            return Sinput.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Card> getCardsFromServer() {
        ArrayList<Card> cardLi = new ArrayList<Card>();
        String data = sendAndGetData("CardOrder");
        String[] cardSymbols = data.split("-");
        for (int i = 1; i < 31; i++) {
            cardLi.add(new Card(cardSymbols[i], tempApp, i - 1));
        }
        return cardLi;
    }
}
