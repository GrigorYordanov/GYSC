package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.net.*;
import java.util.Enumeration;

public class StartApplication {

	/**
	 * The method is running the user interface and printing the runtime of the program in nanoseconds.
	 * 
	 */
	public static void main(String[] arguments) {
		final long startTime = System.nanoTime();
         UI.get();
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
	}

	/**
	 * The mainOne method is running the network interface and while there are more
	 * elements - nextElement()
	 */
	public static void mainOne(String[] argument) {
		try {

			Enumeration enumeration = NetworkInterface.getNetworkInterfaces();

			do {
				NetworkInterface netInt = (NetworkInterface) enumeration.nextElement();

				Enumeration Enumeration = netInt.getInetAddresses();

			} while (enumeration.hasMoreElements());
		}
		/**
		 * Catch method to indicate that there is an error creating or accessing a
		 * Socket.
		 */
		catch (SocketException Error) {

		}
	}
}