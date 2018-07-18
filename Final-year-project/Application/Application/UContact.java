package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.net.Socket;
import java.util.Vector;

/**
 * This class is establishing the connection between users.
 *
 */
class UContact {
	private Entity User;
	private UConnection UConnection;

	public UContact(Entity user) {
		this.User = user;
		UConnection = new UConnection(UListener.getInstance(), user);
	}

	public Entity getUser() {
		return User;
	}

	public void Open(Socket socket) throws Exception {
		try {
			UConnection.StartTwo(socket);
		} catch (Exception ERROR) {

		}
	}

	public void Open() throws Exception {
		try {
			UConnection.StartOne();
		} catch (Exception ERROR) {

		}
	}

	public void Close() throws Exception {
		try {
			UConnection.stop();
		} catch (Exception ERROR) {

		}
	}

	public void Send(String argument) throws Exception {
		try {
			UConnection.Sending(argument);
		} catch (Exception ERROR) {

		}
	}

	public Vector<Messages> getHistory() {
		return UConnection.getHistory();
	}

	public boolean isOpen() {
		return UConnection.Start();
	}
}