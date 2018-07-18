package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

class UConnection {
	private Entity user;
	private Entity data;
	private static final int READ_TIME = 1000;
	private static final int SEND_TIME = 1000;
	private AtomicBoolean Start;
	private AtomicBoolean Stop;
	private Thread thread;
	private MsgSend msgSend;
	private AtomicBoolean sent;
	private MsgHistory msgHistory;

	class MsgHistory {
		private Vector<Messages> msgHistory;

		public MsgHistory() {
			msgHistory = new Vector<Messages>();
		}

		public void add(Messages msg) {
			msgHistory.add(msg);
		}

		/**
		 * In this method it is used Iterator which is used for iterating a collection
		 * of objects.
		 * 
		 * @return Return the vector.
		 */
		public Vector<Messages> getHistory() {
			Vector<Messages> vector = new Vector<Messages>();
			Iterator<Messages> item = msgHistory.iterator();

			while (item.hasNext()) {
				vector.add(item.next());
			}
			return vector;
		}
	}

	public Vector<Messages> getHistory() {
		return msgHistory.getHistory();
	}

	public Boolean Start() {
		return Start.get();
	}

	/**
	 * The class is created by "constcoh" and refactored by Grigor Yordanov The
	 * class is creating the user connection
	 */
	class ConnectionTwo implements Runnable {
		private Entity user;
		private Entity data;
		private Socket CSocket;

		public ConnectionTwo(Entity data, Entity user) {
			this.data = data;
			this.user = user;
			CSocket = null;

		}

		public ConnectionTwo(Entity data, Entity user, Socket socket) {
			this.data = data;
			this.user = user;
			CSocket = socket;
		}

		public void run() throws RuntimeException {
			try {
				PrintWriter print = null;
				BufferedReader read = null;

				if (CSocket == null)
					try {
						CSocket = new Socket(user.getIp(), user.getPort());
						read = new BufferedReader(new InputStreamReader(CSocket.getInputStream()));
						print = new PrintWriter(CSocket.getOutputStream(), true);
						print.println(data.getLogin());
						read.readLine();
					} catch (Exception ERROR) {
						Stop.set(true);
						Start.set(false);
						return;
					}

				CSocket.setSoTimeout(READ_TIME);
				read = new BufferedReader(new InputStreamReader(CSocket.getInputStream()));
				print = new PrintWriter(CSocket.getOutputStream(), true);

				while (!Stop.get()) {
					String dataInput;
					try {
						if ((dataInput = read.readLine()) != null) {
							dataInput = data.decryption(dataInput);
							msgHistory.add(new Messages(dataInput, false));

							if (dataInput.compareTo("STOP") == 0) {

								Stop.set(true);
								Start.set(false);

								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										UI.get().updConn();
									}
								});
							}

							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									UI.get().UpdateMsgLst();
								}
							});
						}

					} catch (SocketTimeoutException ERROR) {
					}

					if (!sent.get()) {
						String msg = msgSend.getMsg();
						print.println(user.encryption(msg));
						msgHistory.add(new Messages(msg, true));

						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								UI.get().UpdateMsgLst();
							}
						});

						sent.set(true);
					}
				}

				print.close();
				read.close();
				CSocket.close();
			} catch (SocketException ERROR) {

			} catch (Exception ERROR) {

			} finally {
				Stop.set(true);
				Start.set(false);
			}
		}
	}

	private void StartIsTrue() throws InterruptedException {
		do {
			try {
				Thread.sleep(SEND_TIME);
			} catch (InterruptedException ERROR) {
			}
		} while (!sent.get());
	}

	public void Sending(String message) throws InterruptedException, Exception {
		if (!Start.get())
			throw new Exception("ERROR");
		try {
			StartIsTrue();
			msgSend.setMsg(message);
			sent.set(false);
		} catch (InterruptedException ERROR) {
		}
	}

	public void SendingOne(String message) throws InterruptedException, Exception {
		if (!Start.get())
			throw new Exception("ERROR");
		try {
			StartIsTrue();
			msgSend.setMsg(message);
			sent.set(false);
			StartIsTrue();
		} catch (InterruptedException ERROR) {

		}
	}

	public void StartOne() throws Exception {
		if (Start.get()) {
			throw new Exception("ERROR");
		}

		thread = new Thread(new ConnectionTwo(data, user));
		Stop = new AtomicBoolean(false);

		if (thread == null) {
			throw new Exception("ERROR");
		}

		thread.start();
		Start.set(true);
	}

	public void StartTwo(Socket socket) throws Exception {
		if (Start.get()) {
			throw new Exception("ERROR");
		}

		Stop = new AtomicBoolean(false);
		thread = new Thread(new ConnectionTwo(data, user, socket));

		if (thread == null) {
			throw new Exception("ERROR");
		}

		thread.start();
		Start.set(true);
	}

	public UConnection(Entity data, Entity user) {
		Start = new AtomicBoolean(false);
		this.data = data;
		this.user = user;
		msgHistory = new MsgHistory();

		sent = new AtomicBoolean(true);
		msgSend = new MsgSend();
	}

	public void stop() {
		if (!Start.get()) {
			return;
		}

		try {
			this.SendingOne("STOP");
			Stop.set(true);
			thread.join();
		} catch (Exception error) {
		}
		Start.set(false);
	}
}