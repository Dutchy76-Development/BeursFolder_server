package nl.thedutchmc.beursfolderserver.socketHandler;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import nl.thedutchmc.beursfolderserver.BeursFolderServer;
import nl.thedutchmc.beursfolderserver.dataHandler.CreateJSFile;

public class ServerSocketHandler extends Thread {

	Socket newClientSocket = null;
	ServerSocket serverSocket = null;

	//Start the server
	public void startServer() {
		try {
			serverSocket = new ServerSocket(BeursFolderServer.SERVER_PORT);
			
			System.out.println("Server started sucessfully on port "  + BeursFolderServer.SERVER_PORT + "!");
			
			new Thread( new Runnable() {
				@Override
				public void run() {
					listener();
				}
 			}).start();
			
 		} catch (IOException e) {
			System.err.println("Failed to start server!");
			e.printStackTrace();
		}
	}
	
	//Start the listener for new clients
	public void listener() {
		try {
			while(true) {
				synchronized(serverSocket) {
					
					//Accept any incoming sockets
					newClientSocket = serverSocket.accept();
					
					System.out.println("Accepted new client: " + newClientSocket.getInetAddress().getHostName().toString());
					
					//Start the ClientConnector Thread
					ClientConnector cc = new ClientConnector(newClientSocket);
					cc.start();
					
					//Start another Listener
					new Thread( new Runnable() {
						@Override
						public void run() {
							listener();
						}
		 			}).start();
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to accept client!");
			e.printStackTrace();
		}
	}
	
	class ClientConnector extends Thread {
		private Socket socket;
		
		public ClientConnector(Socket clientSocket) {
			clientSocket = this.socket;
		}
		
		public void run() {
			DataInputStream dis = null;
			
			socket = newClientSocket;
			
			if(!this.socket.isConnected()) {
				System.out.println("Client is not connected. Not opening a listener!");
				
			} else {
				//Open the DataInputStream for the Client
				try {
					dis = new DataInputStream(this.socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				

				
				//Listen for messages from the Client
				while(true) {
					String messageReceived = null;
					
					try {
						messageReceived = dis.readUTF();
					} catch(Exception e) {
						
						//Check if its an EOFException, because this isn't a bad exception
						if(e instanceof EOFException) {
							continue;
						} else {
							e.printStackTrace();
						}
					}
					
					if(messageReceived != null) {
						
						System.out.println("Message received successfully!");

						//Split the message into its individual components
						//Format: emailLinkSuffix, firstName, surName, companyName, phoneNumber, token, option number, option url
						String[] messageReceivedComponents = messageReceived.split(",");
						
						try {
						String emailLinkSuffix = messageReceivedComponents[0];
						String firstName = messageReceivedComponents[1];
						String surName = messageReceivedComponents[2];
						String companyName = messageReceivedComponents[3];
						String phoneNumber = messageReceivedComponents[4];
						String token = messageReceivedComponents[5];
						int optionNumber = Integer.valueOf(messageReceivedComponents[6]);
						String optionURL = messageReceivedComponents[7];
						
						//Create the JS file responsible for redirecting etc.
						CreateJSFile cjsf = new CreateJSFile();
						cjsf.creatFile(emailLinkSuffix, firstName, surName, companyName, phoneNumber, token, optionNumber, optionURL);
						} catch(Exception e) {
							System.err.println("There was an error reading the Received Array!");
						}
					}
				}
			}
		}
	}
}