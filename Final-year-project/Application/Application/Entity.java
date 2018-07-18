package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;

/** This class initialize a user or entity as an object.
 *
 */
class Entity {
	private EncrAndDecr encr;
	private String Ip;
	private String Login;
	private Integer Port;

	public String getIp() {
		return Ip;
	}

	public String getLogin() {
		return Login;
	}

	public Integer getPort() {
		return Port;
	}

	public void setIp(String argument) {
		this.Ip = argument;
	}

	public void setLogin(String argument) {
		this.Login = argument;
	}

	public void setPort(Integer argument) {
		this.Port = argument;
	}

	public Entity(String Ip, Integer Port, String Login, BigInteger M, BigInteger E, BigInteger D) {
		this.Ip = Ip;
		this.Port = Port;
		this.Login = Login;

		encr = new EncrAndDecr(M, D, E);
	}

	public Entity(String Ip, Integer Port, String Login, BigInteger M, BigInteger E) {
		this.Ip = Ip;
		this.Port = Port;
		this.Login = Login;
		encr = new EncrAndDecr(M, BigInteger.ONE, E);
	}

	public Entity() {
		Ip = null;
		Port = null;
		Login = null;
		encr = null;
	}

	public String encryption(String argument) {
		if (encr == null) {
			return argument;
		} else {
			return DatatypeConverter.printBase64Binary(encr.encrypt(argument));
		}
	}

	public String decryption(String argument) {
		if (encr == null) {
			return argument;
		} else {
			return encr.decrypt(DatatypeConverter.parseBase64Binary(argument));
		}
	}

	public EncrAndDecr getEncr() {
		return encr;
	}

	public void setEncryption(EncrAndDecr uEncr) {
		encr = uEncr;
	}
}
