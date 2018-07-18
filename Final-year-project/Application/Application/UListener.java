package Application;
import java.math.BigInteger;

class UListener extends Entity {
	/**
	 * The following method is created by constcoh and refactored by Grigor Yordanov
	 * The method is using the data which is inputed by the user 
	 *
	 */
	private UListener(String Ip, Integer Port, String Login, BigInteger M, BigInteger E, BigInteger D) {
		super(Ip, Port, Login, M, E, D);
	}

	private UListener() {
		super("", 0, "", BigInteger.ZERO, BigInteger.ZERO);
	}

	/**
	 * A method which is using the singleton class. It control object creation. By
	 * using this structure of the method, it ensures that singleton instances are
	 * creates only when needed.
	 */
	private static UListener ulistener = null;

	public static UListener getInstance() {
		if (ulistener == null) {
			ulistener = new UListener();
		}
		return ulistener;
	}
}