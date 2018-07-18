package Application;
/**
 * 
 * @author Grigor Yordanov
 * @version 1.0
 *
 */
class MsgSend {
	private String msg;

	public MsgSend() {
		msg = new String();
	}

	public synchronized String getMsg() {
		return msg;
	}

	public synchronized void setMsg(String arg) {
		msg = arg;
	}
}