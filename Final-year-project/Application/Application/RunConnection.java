package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class RunConncetion {
	/**
	 * Method which is used for setting the user connection
	 * 
	 * @param BufReader
	 *            - Reads text from a character-input stream.
	 * @throws IOException
	 *             - Throws exception
	 */
	private void Users(BufferedReader BufReader) throws IOException {
		try {
			UListener.getInstance().setIp(BufReader.readLine());
			UListener.getInstance().setPort(Integer.parseInt(BufReader.readLine()));
			UListener.getInstance().setLogin(BufReader.readLine());

		} catch (IOException ERROR) {
		}
	}

	/**
	 * This method establish the connection.
	 * 
	 * @param BufReader
	 *            - Reads lines of text.
	 * @throws Exception
	 *             - Throws exception
	 * @throws IOException
	 *             - Throws exception
	 * @throws InterruptedException
	 *             - Throws exception
	 */
	private void Connect(BufferedReader BufReader) throws Exception, IOException, InterruptedException {
		String IpNumber;
		String LoginName;
		int portNumber;
		String cm;

		IpNumber = BufReader.readLine();
		portNumber = Integer.parseInt(BufReader.readLine());

		try {
			LoginName = BufReader.readLine();
			Entity user = new Entity(IpNumber, portNumber, LoginName, BigInteger.ZERO, BigInteger.ZERO,
					BigInteger.ZERO);
			UConnection UConnection = new UConnection(UListener.getInstance(), user);
			UConnection.StartOne();

			do {
				cm = BufReader.readLine();
				if (!UConnection.Start()) {
					return;
				} else if (cm.compareTo("STOP") == 0) {
					UConnection.stop();
					break;
				}

				UConnection.Sending(cm);

			} while (true);

		} catch (IOException ERROR) {

		} catch (InterruptedException ERROR) {

		} catch (Exception ERROR) {

		}
	}

	/**
	 * This method start the connection with the server
	 * 
	 * @throws Exception
	 */
	public void Start() throws Exception {
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));

		do {

			String cm = bufReader.readLine();
			if (cm.compareTo("SET USER") == 0) {
				Users(bufReader);
			} else if (cm.compareTo("Start the server") == 0) {
				SrvListnr.getInstance().start(UListener.getInstance());

			} else if (cm.compareTo("Stop the server") == 0) {
				SrvListnr.getInstance().stop();

			} else if (cm.compareTo("Connect to server") == 0) {
				Connect(bufReader);
			}
		} while (true);
	}
}