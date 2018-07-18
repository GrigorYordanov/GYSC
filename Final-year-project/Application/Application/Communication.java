package Application;

/**This class start the communication.
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.math.BigInteger;

public class Communication {

     /**
     * Method for running the connection.
     * 
     * @param argument
     *            - String constant
     */
     public static void Run(String[] argument) {
		try {
			new RunConncetion().Start();
		} catch (Exception ERROR) {

		}
	}

	/**
	 * 
	 * The method is invoking the server listener.
	 */
	public static void Run2(String[] argument) {
		Entity User = new Entity("", 0, "", BigInteger.ZERO, BigInteger.ONE);

		try {
			SrvListnr.getInstance().start(User);
			Thread.sleep(15000);
		} catch (Exception ERROR) {

		}
		SrvListnr.getInstance().stop();

	}
}
