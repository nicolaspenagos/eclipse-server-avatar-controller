/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * In this class you will find TCP connetion using singleton and observer patterns
 */
public class TCPConnection extends Thread {

	// -------------------------------------
	// Attributtes
	// -------------------------------------
	private static TCPConnection uniqueInstance;
	private BufferedWriter writer;
	private boolean kill;
	private OnMessageListener observer;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	private TCPConnection() {

	}

	// -------------------------------------
	// Singleton
	// -------------------------------------
	public static TCPConnection getInstance() {

		if (uniqueInstance == null) {

			uniqueInstance = new TCPConnection();
			uniqueInstance.setKill(false);
			uniqueInstance.start();

		}

		return uniqueInstance;

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	@Override
	public void run() {

		try {

			System.out.println("Esperando...");
			ServerSocket serverSocket = new ServerSocket(5000);
			Socket socket = serverSocket.accept();
			System.out.println("Conectado");

			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (!kill) {
				
				String msg = reader.readLine();
				observer.onMessage(msg);
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {

		new Thread(
				()->{
					
					try {
						
						writer.write(msg + "\n");
						writer.flush();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
				}
		).start();
		
	}

	// -------------------------------------
	// Getters and setters
	// -------------------------------------
	public boolean isKill() {
		return kill;
	}

	public void setKill(boolean kill) {
		this.kill = kill;
	}

	public OnMessageListener getObserver() {
		return observer;
	}

	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}

}
