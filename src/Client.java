/*
 * Decompiled with CFR 0_114.
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private static final int PORT = 10004;

	public static Socket getSocket(String hostname) {
		Socket socket = null;
		try {
			socket = new Socket(InetAddress.getByName(hostname), PORT);
		} catch (IOException v0) {
			socket = null;
		}
		return socket;
	}
}
