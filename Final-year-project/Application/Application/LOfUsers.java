package Application;

/**Class which is initializing the list of users when user is added or removed
 * from the system.
 * @author Grigor Yordanov
 * @version 1.0 
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class LOfUsers {
	protected LinkedList<UContact> UContact;
	private static LOfUsers lOfUsers = null;

	private LOfUsers() {
		UContact = new LinkedList<UContact>();

	}

	/**
	 * Method which is adding the new users to the list
	 * 
	 * @param argument
	 *            - A constant
	 */
	public void ADD(UContact argument) {
		UContact.add(argument);
	}

	public ListIterator<UContact> getIterator() {
		return UContact.listIterator();
	}

	public UContact getIndex(Integer index) {
		if (index < 0) {
			return null;
		} else if (index > UContact.size()) {
			return null;
		} else {
			return UContact.get(index.intValue());
		}
	}

	/**
	 * 
	 * Method which is used when a new user is been added
	 * 
	 * @return Return the new added user
	 */
	public UContact Login(String argument) {
		Integer integer = -1;
		{
			Iterator<UContact> Itr = UContact.iterator();
			int x = 0;

			do {
				if (Itr.next().getUser().getLogin().compareTo(argument) == 0) {
					integer = x;
					++x;
				}
			} while (Itr.hasNext());
		}

		if (integer < 0) {
			return null;
		} else if (integer > UContact.size()) {
			return null;
		} else {
			return UContact.get(integer.intValue());
		}
	}

	public String[] getList() {
		String[] string = new String[UContact.size()];
		Iterator<UContact> iter = UContact.iterator();
		int i = 0;
		while (iter.hasNext()) {
			string[i++] = iter.next().getUser().getLogin();
		}
		return string;
	}

	/**
	 * Method which is used for removing the user as a argument
	 * 
	 * @param argument
	 *            - A constant
	 */
	public void RemoveArgument(String argument) {
		Integer integer = -1;
		{
			Iterator<UContact> Itr = UContact.iterator();
			int x = 0;
			do {
				if (Itr.next().getUser().getLogin().compareTo(argument) == 0) {
					integer = x;
					++x;
				}
			} while (Itr.hasNext());
		}

		if (integer >= 0) {
			UContact.remove(integer);
		}
	}

	/**
	 * Method for removing the user from the list.
	 * 
	 * @param index
	 */
	public void RemoveIndex(Integer index) {
		if (index >= 0) {
			UContact.remove(index.intValue());
		}
	}

	public void clear() {
		UContact.clear();
	}

	/**
	 * A method which is using the singleton class. It control object creation.
	 * 
	 */

	public static LOfUsers getInstance() {
		if (lOfUsers == null) {
			lOfUsers = new LOfUsers();
		}
		return lOfUsers;
	}
}