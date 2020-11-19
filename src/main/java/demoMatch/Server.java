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
import java.util.*;

public class Server {
	 
	// the socket used by the server
	private ServerSocket serverSocket;
	
	ArrayList<TcpThread> tcpArray = new ArrayList<TcpThread>();
	
	private String cardOrder;
	
	// server constructor
	Server(int port){
	        
		cardOrder = createCardList();
	    /* create socket server and wait for connection requests */
	    try{
	        serverSocket = new ServerSocket(port);
	        System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());
	        int counter = 0;
	        while(true){
        		counter++;
                Socket socket = serverSocket.accept();  // accept connection
                System.out.println("New client asked for a connection");
                TcpThread t = new TcpThread(socket, counter);    // make a thread of it
                tcpArray.add(t);
                System.out.println("Starting a thread for a new Client");
                t.start();
            }
        }
        catch (IOException e){
        	System.out.println("Exception on new ServerSocket: " + e);
        }
    }               
	 
	//you must "run" server to have the server run as a console application
	public static void main(String[] arg){
        // start server on port 1500
        new Server(1500);
    }
	       
    /** One instance of this thread will run for each client */
    class TcpThread extends Thread{
        // the socket where to listen/talk
        Socket socket;
        int counter = 0;
        BufferedReader Sinput;
        PrintWriter Soutput;
        String playerName;
       
        TcpThread(Socket socket, int number) {
            this.socket = socket;
            this.counter = number;
        }
        	       
        public String process(String string){
        	if(string.contains("name")){
        		playerName = string.replace("name-", "");
        		return playerName;
        	}
        	if(string.contains("cardflip"))
        	{
        		
        		sendToEveryPlayer(string);
        		
        	}
        	else if(string.contains("CardOrder")){
        		return cardOrder;
        	}       	
        	return "";
        }
        
        public void run(){
        	/* Creating both Data Stream */
            System.out.println("Thread trying to create Object Input/Output Streams");
            try{
                // create output first
                Soutput = new PrintWriter(socket.getOutputStream());
                Soutput.flush();
                Sinput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	}
            catch (IOException e){
                System.out.println("Exception creating new Input/output Streams: " + e);
                return;
            }
            
            while(true){
	            System.out.println("Thread waiting for a String from the Client");
	            // read a String (which is an object)
	            try{
	                String str = Sinput.readLine();	                
	                Soutput.println(process(str));
	                Soutput.flush();
	            }
	            catch (IOException e){
	                System.out.println("Exception reading/writing streams: " + e);
	                return;                               
	            }
	           
            }
        }

		public void send(String string) {
			 Soutput.println(string);
             Soutput.flush();
			
		}
    }
    public String createCardList(){
    	StringBuilder tempString = new StringBuilder();
		ArrayList<String>cardList = new ArrayList<String>();
		for(int i=1; i<15; i++){
			cardList.add(Integer.toString(i));
			cardList.add(Integer.toString(i));
			Collections.shuffle(cardList);
		}
		cardList.add("w");
		Collections.shuffle(cardList);
		cardList.add("w");
		Collections.shuffle(cardList);
		int cardNum = 0;
		int rowCounter = 0;
		
		for(cardNum=0; cardNum<cardList.size(); cardNum++){
			tempString.append("-");
			tempString.append(cardList.get(cardNum));
		}
		
		/*for(cardNum=0; cardNum<cardList.size(); cardNum++){
			String symbol = cardList.get(cardNum);
			if(symbol.length() == 1){
				symbol = " " + symbol;
			}
			String cardInfo = "[" + " " + symbol + "]";
			if(rowCounter == 5){
				cardInfo = cardInfo.concat("\n");
				rowCounter = 0;
			}					
			else{
				rowCounter++;
			}			
			System.out.print(cardInfo);			
		}*/
		return tempString.toString();
	}

	public void sendToEveryPlayer(String string) {
		for (TcpThread client : tcpArray)
		{
			client.send(string);
			
			
		}
		
	}
}


