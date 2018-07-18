package Application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

class SrvListnr {
	private AtomicBoolean start = new AtomicBoolean(false);
	private AtomicBoolean stop = new AtomicBoolean(); // used for stoping threadListener
	private Thread thread;
	private static final int SERVER_TIMEOUT = 1000;
	private static SrvListnr list = null;

	/**
	 * A method which is using the singleton class. It control object creation. By
	 * using this structure of the method, it ensures that singleton instances are
	 * creates only when needed.
	 */

	public static SrvListnr getInstance() {
		if (list == null)
			list = new SrvListnr();

		return list;
	}

	public Boolean Start() {
		return start.get();
	}

	class Server implements Runnable {
		private Entity user;

		public Server(Entity user) {
			this.user = user;
		}

		/**
		 * The method is created by "constcoh" and refactored by Grigor Yordanov
		 */
		public void run() {
			try {
				ServerSocket SSocket = new ServerSocket(user.getPort());
				SSocket.setSoTimeout(SERVER_TIMEOUT);
				while (!stop.get()) {
					try {
						Socket socket = SSocket.accept();
						{

							BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
							print.println(user.getLogin());
							String cLogin = buffer.readLine();
							UContact uContact = LOfUsers.getInstance().Login(cLogin);

							while (uContact != null) {
								uContact.Open(socket);
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										UI.get().updConn();
									}
								});
							}
						}

					} catch (SocketTimeoutException ERROR) {

					} catch (CancellationException ERROR) {

					}
				}
				SSocket.close();
			} catch (Exception ERROR) {

			}
		}
	}

	/**
	 * Start the server
	 */
	public void start(Entity user) throws Exception {
		thread = new Thread(new Server(user));

		while (start.get()) {
			return;
		}

		if (thread == null) {
			throw new Exception("ERROR: ");
		}
		thread.start();

		start.set(true);

	}

	/**
	 * Stop the server
	 */
	public void stop() {
		if (start.get() == false) {
			return;
		} else {
			stop.set(true);
		}

		try {
			thread.join();
		} catch (InterruptedException ERROR) {

		}
		start.set(false);
	}
}