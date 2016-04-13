package network;

import java.io.*;
import java.net.*;

import views.ServeurFrame;

public class Serveur implements Runnable {
	private ServeurFrame serveurFrame;
	private static final int PORT = 6666;
	private Thread thread;

	public Serveur(ServeurFrame serveurFrame) {
		this.serveurFrame = serveurFrame;
		this.thread = new Thread(this);
		serveurFrame.setThread(this.thread);
		this.thread.start();
	}

	@Override
	public void run() {
		ServerSocket socketServeur;
		try {
			socketServeur = new ServerSocket(6666, 5);
		} catch (IOException v0) {
			this.serveurFrame.setSocket(null);
			this.thread.stop();
			return;
		}
		Socket socket = null;
		try {
			socket = socketServeur.accept();
		} catch (IOException v1) {
			socket = null;
		}
		this.serveurFrame.setSocket(socket);
	}
}
