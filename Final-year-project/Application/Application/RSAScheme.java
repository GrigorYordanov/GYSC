package Application;

/**
 * The class is implemented by "constcoh" and refactored by Grigor Yordanov
 * 
 */
import java.math.BigInteger;
import java.util.*;

class RSAScheme {
	private int lengthOne;
	private int lengthTwo;
	private BigInteger M;
	private BigInteger D;
	private BigInteger E;

	public int LengthOne() {
		return lengthOne;
	}

	public int LengthTwo() {
		return lengthTwo;
	}

	public RSAScheme(int lOne, int lTwo, BigInteger M, BigInteger D, BigInteger E) {
		lengthOne = lOne;
		lengthTwo = lTwo;

		this.M = new BigInteger(M.toByteArray());
		this.D = null;
		this.E = null;

		if (E != null) {
			this.E = new BigInteger(E.toByteArray());
		}

		if (D != null) {
			this.D = new BigInteger(D.toByteArray());
		}
	}

	/**
	 * This method is converting the big integer into an array of bytes.
	 * 
	 * @param bInt
	 *            - Big Integer
	 * @param bArr
	 *            - byte array
	 * @param x
	 *            - integer variable
	 * @param y
	 *            - integer variable
	 */

	private void BIntBArray(BigInteger bInt, byte[] bArr, int x, int y) {

		byte[] Arr = bInt.toByteArray();

		int array;
		array = x + y - 1;

		for (int i = Arr.length - 1; i >= 0; --i) {
			bArr[array--] = Arr[i];

			if (array == x - 1) {
				return;
			}
		}

		do {
			bArr[array--] = 0;
		} while (array >= x);
	}

	/**
	 * This method convert the byte array into Big Integer.
	 * 
	 * @param bArr
	 *            - byte array
	 * @param x
	 *            - integer value
	 * @param y
	 *            - integer value
	 * @return Return the converted integer from the byte array.
	 */
	private BigInteger BArrBInt(byte[] bArr, int x, int y) {
		byte[] Arr = new byte[y + 1];

		Arr[0] = 0;
		for (int i = 1; i <= y; ++i) {

			Arr[i] = bArr[i + x - 1];
		}
		return new BigInteger(Arr);
	}

	/**
	 * The method is used when the encryption and decryption operations are being
	 * run
	 * 
	 * @param bArr
	 *            - Byte Array
	 * @param lengthOne
	 *            - Integer length
	 * @param BArr
	 *            - Byte Array
	 * @param lengthTwo
	 *            - Integer length
	 * @param x
	 *            - Integer value
	 * @param Bint
	 *            - BigInteger value
	 */
	private void Converter(byte[] bArr, int lengthOne, byte[] BArr, int lengthTwo, int x, BigInteger Bint) {

		for (int i = 0; i < x; ++i) {

			BigInteger BInt = BArrBInt(bArr, lengthTwo, i * lengthOne);
			BInt = BInt.modPow(Bint, M);
			BIntBArray(BInt, BArr, i * lengthOne, lengthTwo);
		}
	}

	/**
	 * This method encrypt the data with the one of the generated secret key.
	 */
	public void encryption(byte[] bArr, byte[] BArr, int x) {
		Converter(bArr, lengthOne, BArr, lengthTwo, x / lengthOne, E);
	}

	/**
	 * This method decrypt the data with the another generated secret key.
	 */

	public void decryption(byte[] bArr, byte[] BArr, int y) {
		Converter(bArr, lengthTwo, BArr, lengthOne, y / lengthTwo, D);
	}

	/**
	 * Method getPrime generates prime numbers.
	 * 
	 * @return - Return the generated prime number.
	 */
	public BigInteger getPrime(int x) {

		return BigInteger.probablePrime(x, new Random());
	}

	/**
	 * Method which run as Great Common Divisor
	 */

	public static BigInteger[] GCD(BigInteger A, BigInteger B) {
		BigInteger[] bIntArray = new BigInteger[3];

		int Qlength = 0;

		Stack<BigInteger> Q = new Stack<BigInteger>();
		BigInteger[] rem = B.divideAndRemainder(A);
		BigInteger div = A;
		BigInteger divisor = rem[1];

		BigInteger Gcd = BigInteger.valueOf(0).add(A);

		while (rem[1].compareTo(BigInteger.valueOf(0)) != 0) {
			Gcd = rem[1];
			Q.push(rem[0]);
			++Qlength;
			rem = div.divideAndRemainder(divisor);
			div = divisor;
			divisor = rem[1];
		}

		bIntArray[0] = Gcd;
		/**
		 * The two if statements are checking if the length of Q is 0 or 1.
		 */
		switch (Qlength) {
		case 1:
			Qlength = 1;

			bIntArray[1] = rem[0].subtract(BigInteger.ONE).negate();
			bIntArray[2] = BigInteger.ONE;
			return bIntArray;

		case 2:
			Qlength = 1;

			bIntArray[1] = Q.peek().negate();
			bIntArray[2] = BigInteger.ONE;
			return bIntArray;
		}
		
		{
			BigInteger phai = null;
			BigInteger one = BigInteger.ONE;
			BigInteger two = Q.push(one);
			BigInteger key = one.add(two);
			while (phai.gcd(key).compareTo(one) != 0) {
				key = key.add(two);
			}

			bIntArray[1] = key;
			if ((Qlength - 1) % 2 == 1) {
				bIntArray[1] = bIntArray[1].negate();
			}
		}
		
		{
			BigInteger phai = null;
			BigInteger one = BigInteger.ONE;
			BigInteger two = Q.push(one);
			BigInteger key2 = one.add(two);
			while (phai.gcd(key2).compareTo(one) != 0) {
				key2 = key2.add(two);
			}
			bIntArray[2] = key2;
			if ((Qlength - 1) % 2 == 1) {
				bIntArray[2] = bIntArray[2].negate();
			}
		}
		return bIntArray;
	}

	/**
	 * The method get M by multiplying p1 and p2
	 * 
	 * @return Return M
	 */
	public static BigInteger[] GetM(int lengthOne, int lengthTotal, Random random) {

		BigInteger[] arr = new BigInteger[2];
		arr[0] = BigInteger.probablePrime(lengthOne, random);
		byte[] byteArr = arr[0].toByteArray();

		int lengthTwo;
		lengthTwo = lengthTotal - lengthOne;

		BigInteger bI = new BigInteger("2");
		BigInteger bIOne = bI.pow(8 * lengthTotal - 1);

		do {
			arr[1] = BigInteger.probablePrime(lengthTwo, random);
			if (arr[0].multiply(arr[1]).compareTo(bIOne) > 0) {
				arr[0] = new BigInteger(byteArr);
				return arr;
			}
		} while (true);
	}

	/**
	 * The method compute N and phi which are used in the methods GetED() and
	 * GetD().
	 */
	private static BigInteger RandomBInt(BigInteger n) {
		Random random = new Random();
		BigInteger result = new BigInteger(n.bitLength(), random);

		do {
			result = new BigInteger(n.bitLength(), random);
		} while (result.compareTo(n) >= 0);
		return result;
	}

	/**
	 * The method compute a suitable E, where the great common division of E and phi
	 * is one.
	 * 
	 */
	public static BigInteger[] GetED(int ELength, BigInteger[] M, Random random) {
		BigInteger E;
		Long l = new Long(2);
		BigInteger bI = BigInteger.valueOf(l).pow(ELength * 8).subtract(BigInteger.ONE);
		E = RandomBInt(bI);
		BigInteger PhiM = M[0].subtract(BigInteger.ONE).multiply(M[1].subtract(BigInteger.ONE));
		BigInteger[] GCD = GCD(E, PhiM);

		while (GCD[0].compareTo(BigInteger.ONE) != 0) {
			E = RandomBInt(bI);
			GCD = GCD(E, PhiM);
		}

		BigInteger D = GCD[1];
		while (D.compareTo(BigInteger.ZERO) < 0) {
			D = D.add(PhiM);
		}

		do {
			D = D.subtract(PhiM);
		} while (D.compareTo(PhiM) > 0);

		BigInteger[] result = new BigInteger[2];
		result[0] = E;
		result[1] = D;
		return result;
	}

	/**
	 * The method is computing D. Based on E and N, D is computed.
	 * 
	 * @return It return D and it return "-1" if E is impossible.
	 *
	 */

	public static BigInteger GetD(BigInteger E, BigInteger[] M, Random random) {
		BigInteger phiM = M[0].subtract(BigInteger.ONE).multiply(M[1].subtract(BigInteger.ONE));

		BigInteger[] gcd = GCD(E, phiM);

		if (gcd[0].compareTo(BigInteger.ONE) != 0) {
			return BigInteger.valueOf(-1);
		}

		BigInteger D = gcd[1];

		while (D.compareTo(BigInteger.ZERO) < 0) {
			D = D.add(phiM);
		}
		do {
			D = D.subtract(phiM);
		} while (D.compareTo(phiM) > 0);

		return D;
	}
}