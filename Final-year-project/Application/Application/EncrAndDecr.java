package Application;

/**
 * @version 1.0
 */
import java.math.BigInteger;
import java.util.Random;

class GlobalRandom {
	public static Random random = new Random(System.currentTimeMillis());
}

class EncrAndDecr {
	private static final int LENGTH1 = 200;
	private static final int LENGHT2 = 250;
	protected int length1;
	protected int length2;

	protected RSAScheme RSA;

	public EncrAndDecr(int length1, int length2, BigInteger M, BigInteger D, BigInteger E) {

		RSA = new RSAScheme(length1, length2, M, D, E);
	}

	public EncrAndDecr(BigInteger M, BigInteger D, BigInteger E) {
		length2 = LENGHT2;
		length1 = LENGTH1;
		RSA = new RSAScheme(length1, length2, M, D, E);
	}

	/**
	 * The method is created by constcoh and refactored by Grigor Yordanov Method
	 * which is encrypting the data by using the methods from class RSAScheme
	 * 
	 * @return Return the encrypted message.
	 */
	public byte[] encrypt(String data) {
		byte[] bytes = data.getBytes();
		int len;
		if (bytes.length % length1 > 0) {
			byte[] buf = bytes;
			len = bytes.length + length1 - bytes.length % length1;
			bytes = new byte[len];

			for (int i = 0; i < buf.length; ++i)
				bytes[i] = buf[i];
			bytes[buf.length] = 0;

			for (int i = buf.length + 1; i < bytes.length; ++i)
				bytes[i] = (byte) GlobalRandom.random.nextInt();

		}
		byte[] bytes2 = new byte[bytes.length / length1 * length2];
		RSA.encryption(bytes, bytes2, bytes.length);
		return bytes2;
	}

	/**
	 * The method is created by constcoh and refactored by Grigor Yordanov
	 * 
	 * @param data
	 * @return
	 */
	public String decrypt(byte[] data) {
		int ciphertext;
		if (data.length == 0) {
			return new String();
		}
		ciphertext = data.length / length2;
		byte[] bytes3 = new byte[ciphertext * length1];
		RSA.decryption(data, bytes3, ciphertext * length2);

		int zI = -1;
		for (int i = 0; i < bytes3.length; ++i)
			if (bytes3[i] == 0) {
			} else if (zI > 1) {
				byte[] buf = new byte[zI];
				for (int j = 0; j < zI; ++j)
					buf[j] = bytes3[j];
				bytes3 = buf;
			} else {
				zI = i;
			}

		return new String(bytes3);
	}
}