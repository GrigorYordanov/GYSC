package Application;

/**A class which is initializing the messages as objects.
 * @author Grigor
 * @version 1.0
 */
import java.util.Date;

class Messages {
	private Date Date;
	private String Data;
	private boolean mine;

	public Messages(String data, boolean mine) {
		this.Date = new Date();
		this.Data = data;
		this.mine = mine;
	}

	public boolean Mine() {
		return mine;
	}

	public String getData() {
		return Data;
	}

	public Date getDate() {
		return Date;
	}
}