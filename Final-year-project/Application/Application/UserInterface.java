package Application;

/**
 * @author Grigor Yordanov
 * @version 1.0
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

class SplashScreen {
	JFrame Jframe;
	JLabel picture = new JLabel(new ImageIcon("image/GYSC.jpg"));
	JProgressBar JPBar = new JProgressBar();

	/**
	 * Creating constructor of class SplashScreen
	 */
	SplashScreen() {
		CreateSplashScreen();
		AddPicture();
		AddJPBar();
		RunPBar();
	}

	/**
	 * Method for creating the splash screen
	 */
	public void CreateSplashScreen() {
		Jframe = new JFrame();
		Jframe.getContentPane().setLayout(null);
		Jframe.setSize(586, 640);
		Jframe.setUndecorated(true);
		Jframe.setLocationRelativeTo(null);
		Jframe.setVisible(true);

	}

	/**
	 * Method for adding the picture to the splash screen
	 */
	public void AddPicture() {
		picture.setSize(586, 575);
		Jframe.add(picture);
	}

	/**
	 * Method for adding the progress bar
	 */
	public void AddJPBar() {
		JPBar.setBounds(00, 575, 586, 65);
		JPBar.setBorderPainted(true);
		JPBar.setStringPainted(true);
		JPBar.setBackground(Color.WHITE);
		JPBar.setForeground(Color.RED);
		JPBar.setValue(0);
		Jframe.add(JPBar);
	}

	/**
	 * Method for running the progress bar
	 */
	public void RunPBar() {
		int x = 0;

		while (x <= 100) {
			try {
				Thread.sleep(50);
				JPBar.setValue(x);
				x++;
				if (x == 100) {
					Jframe.dispose();
				}
			} catch (Exception ERROR) {

			}
		}
	}
}

class AddContact {

	private static final int BORDER = 15;
	private static final int MiddleBORDER = 5;
	private JDialog JDialog;
	private JTextField Login;
	private JTextField M;
	private JTextField E;
	private JTextField Ip;
	private JTextField Port;

	/**
	 * Method which maps elements to values.
	 */
	private Map<String, String> MapResult;

	public static Map<String, String> Query(Frame FRAME) throws Exception {
		final AddContact queryFrame = new AddContact(FRAME);

		return queryFrame.MapResult;
	}

	/**
	 * Method which is used for creating a window for adding new users by adding the
	 * requested details for connection.
	 * 
	 * @param owner
	 */
	public AddContact(Frame FRAME) throws Exception {

		JDialog = new JDialog(FRAME, "New Contact", true);
		/**
		 * Initializing the panel for adding new users
		 */
		JPanel Jpanel;
		{
			JPanel JPanelOne = new JPanel();
			BoxLayout boxL = new BoxLayout(JPanelOne, BoxLayout.LINE_AXIS);
			JPanelOne.setLayout(boxL);

			JPanelOne.add(Box.createVerticalStrut(BORDER));
			Jpanel = new JPanel();
			{
				BoxLayout boxLay = new BoxLayout(Jpanel, BoxLayout.Y_AXIS);
				Jpanel.setLayout(boxLay);

			}

			JDialog.setLayout(new FlowLayout());
			JPanelOne.add(Jpanel);
			JPanelOne.add(Box.createHorizontalStrut(BORDER));
			JDialog.add(JPanelOne);
		}

		Jpanel.add(Box.createVerticalStrut(BORDER));
		{

			JTextField text;
			Box box;
			/**
			 * Login details
			 */
			JLabel Jlabel = new JLabel("Login:");

			Login = text = new JTextField("", 15);

			box = Box.createHorizontalBox();
			box.add(Jlabel);
			box.add(Box.createHorizontalStrut(MiddleBORDER));
			box.add(text);
			Jpanel.add(box);
			Jpanel.add(Box.createVerticalStrut(BORDER));

			/**
			 * Ip address details
			 */
			Jlabel = new JLabel("Ip:");
			Ip = text = new JTextField("", 15);
			box = Box.createHorizontalBox();
			box.add(Jlabel);
			box.add(Box.createHorizontalStrut(MiddleBORDER));
			box.add(text);
			Jpanel.add(box);
			Jpanel.add(Box.createVerticalStrut(BORDER));

			/**
			 * Port number details
			 */
			Jlabel = new JLabel("Port:");
			Port = text = new JTextField("", 15);
			box = Box.createHorizontalBox();
			box.add(Jlabel);
			box.add(Box.createHorizontalStrut(MiddleBORDER));
			box.add(text);
			Jpanel.add(box);

		}

		/**
		 * Creating and setting listeners for the buttons "Done" and ""Cancel" in the
		 * panel for adding new contacts.
		 */
		Jpanel.add(Box.createVerticalStrut(BORDER));
		{
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			JButton JButton;

			JButton = new JButton("Done");

			JButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MapResult = new HashMap<String, String>();
					MapResult.put("login", Login.getText());
					MapResult.put("M", M.getText());
					MapResult.put("E", E.getText());
					MapResult.put("ip", Ip.getText());
					MapResult.put("port", Port.getText());
					JDialog.dispose();
				}
			});
			box.add(JButton);
			box.add(Box.createHorizontalStrut(BORDER));

			JButton = new JButton("Cancel");

			JButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						MapResult = new HashMap<String, String>();

						JDialog.dispose();
					} catch (Exception E) {

					}
				}
			});
			box.add(JButton);
			Jpanel.add(box);
		}
		Jpanel.add(Box.createVerticalStrut(BORDER));
		{
			JTextField text;
			Box box;
			M = text = new JTextField(
					"685736394058674943094857578930975645398133198298113795322055388046957411865388383684826599331989921538391578601261291782060429810000904121670209210618991442805378573315330642902868268518281829821088107828255901899669812392602881563355885322555356435347307075034083484667631848695799702679812829446350404849",
					10);

			box = Box.createHorizontalBox();
			box.add(Box.createHorizontalStrut(MiddleBORDER));
			box.add(text);
			box.setVisible(false);
			Jpanel.add(box);
			Jpanel.add(Box.createVerticalStrut(BORDER));

			E = text = new JTextField("11", 15);

			box = Box.createHorizontalBox();
			box.add(Box.createHorizontalStrut(MiddleBORDER));
			box.add(text);
			box.setVisible(false);
			Jpanel.add(box);
			Jpanel.add(Box.createVerticalStrut(BORDER));
		}

		Jpanel.add(Box.createVerticalStrut(BORDER));
		JDialog.pack();
		JDialog.setVisible(true);
	}
}

class UI extends JFrame {
	private static final int BORDER = 20;
	private static final int HeightOfLIST = 80;
	private static final int WeightOfLIST = 240;
	private static final int HeightOfEDIT = 340;
	private static final int WeightOfEDIT = 400;
	private static final int HeightOfMESSAGE = 80;
	private static final int BORDERTop = 20;
	private static final int MiddleBORDER = 5;

	private static final int HeightOfADD = 2;
	private static final int HeightOFEDIT = 20;
	private static final int WeightOFEDIT = 165;

	protected JTextPane JTextPane;
	protected String[] StrDataArr;
	protected JList<String> ListOfCont;

	private static UI mainFrame = null;

	private JPanel ContPanel;
	private JPanel ConnectionPanel;
	private JPanel ServerPanel;
	private JTextArea MsgTextArea;

	private JTextField Login;
	private JTextField M;
	private JTextField E;
	private JTextField D;
	private JTextField Ip;
	private JTextField Port;

	private JButton ConnectButton;
	private JButton RemoveButton;
	private JButton StartButton;
	private JButton StopButton;

	private guiMethods GUI;
	private UContact UContact;

	private UI() {
		super("GYSC");
		GUI = new guiMethods();
		UContact = null;
		new SplashScreen();

		JPanel Jpanel;
		{
			JPanel JPanelOne = new JPanel();
			BoxLayout boxL = new BoxLayout(JPanelOne, BoxLayout.LINE_AXIS);
			JPanelOne.setLayout(boxL);

			JPanelOne.add(Box.createVerticalStrut(BORDER));
			Jpanel = new JPanel();
			{
				BoxLayout boxLay = new BoxLayout(Jpanel, BoxLayout.Y_AXIS);
				Jpanel.setLayout(boxLay);

			}

			setLayout(new FlowLayout());
			JPanelOne.add(Jpanel);
			JPanelOne.add(Box.createHorizontalStrut(BORDER));
			add(JPanelOne);
		}

		/**
		 * Method for initializing the panel for contacts list
		 */
		{
			ContPanel = new JPanel();
			ContPanel.setLocation(BORDER, 40);
			ContPanel.setBackground(new Color(77, 176, 230));

			Box box = Box.createHorizontalBox();
			JLabel Label = new JLabel("Contact List");
			box.add(Label);

			JPanel JPanelOne;
			{
				JPanel JPanelTwo = new JPanel();
				JPanelTwo.setBackground(new Color(77, 176, 230));

				BoxLayout BLayout = new BoxLayout(JPanelTwo, BoxLayout.LINE_AXIS);
				JPanelTwo.setLayout(BLayout);

				JPanelTwo.add(Box.createHorizontalStrut(BORDER));
				JPanelOne = new JPanel();
				JPanelOne.setBackground(new Color(77, 176, 230));

				{
					BoxLayout BLayoutOne = new BoxLayout(JPanelOne, BoxLayout.Y_AXIS);
					JPanelOne.setLayout(BLayoutOne);

				}
				ContPanel.setLayout(new FlowLayout());
				JPanelTwo.add(JPanelOne);
				JPanelTwo.add(Box.createHorizontalStrut(BORDER));
				ContPanel.add(JPanelTwo);
			}

			JPanelOne.add(Box.createVerticalStrut(BORDER));

			{

				JPanelOne.add(box);
			}

			JPanelOne.add(Box.createVerticalStrut(BORDER));
			/**
			 * Method for creating and presenting the added users The method is created by
			 * "constcoh" and refactored by Grigor Yordanov
			 */

			{
				StrDataArr = new String[] { "250", "250", "250" };
				JList<String> Jlist = new JList<String>(StrDataArr);
				ListOfCont = Jlist;
				ListOfCont.addListSelectionListener(GUI.new userSelection(this));
				Jlist.setAlignmentX(Component.CENTER_ALIGNMENT);
				Jlist.setPreferredSize(new Dimension(WeightOfLIST, HeightOfLIST));

				Jlist.setMinimumSize(Jlist.getPreferredSize());
				JPanel pnl = new JPanel();
				pnl.setLayout(new BorderLayout(0, 0));
				JScrollPane scrollPane = new JScrollPane(Jlist, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				pnl.add(scrollPane);
				JPanelOne.add(pnl);
			}

			JPanelOne.add(Box.createVerticalStrut(BORDER));

			{
				Box ButtonsBox = Box.createHorizontalBox();
				/**
				 * Initializing the buttons in the contact list panel
				 */
				JButton button = new JButton("Add");
				ButtonsBox.add(button);
				ButtonsBox.add(Box.createHorizontalStrut(BORDER));
				button.addActionListener(GUI.new addUBtn(this));

				RemoveButton = button = new JButton("Remove");
				ButtonsBox.add(button);
				ButtonsBox.add(Box.createHorizontalStrut(BORDER));
				button.addActionListener(GUI.new removeUBtn(this));

				ConnectButton = button = new JButton("Connect");
				ButtonsBox.add(button);

				JPanelOne.add(ButtonsBox);
				button.addActionListener(GUI.new userConnectBtn(this));
			}
			JPanelOne.add(Box.createVerticalStrut(BORDER));

		}

		/**
		 * Creating and initializing the server panel.
		 */
		{
			ServerPanel = new JPanel();

			ServerPanel.setBackground(new Color(0, 102, 0));
			ServerPanel.setLocation(20, 40 + ContPanel.getHeight() + BORDERTop);

			JPanel JPanel;
			{
				JPanel JPanelOne = new JPanel();
				JPanelOne.setBackground(new Color(0, 102, 0));
				BoxLayout BLayout = new BoxLayout(JPanelOne, BoxLayout.X_AXIS);
				JPanelOne.setLayout(BLayout);

				JPanelOne.add(Box.createHorizontalStrut(BORDER));
				JPanel = new JPanel();
				JPanel.setBackground(new Color(0, 102, 0));

				{
					BoxLayout BLayoutOne = new BoxLayout(JPanel, BoxLayout.Y_AXIS);
					JPanel.setLayout(BLayoutOne);

				}

				ServerPanel.setLayout(new FlowLayout());
				JPanelOne.add(JPanel);
				JPanelOne.add(Box.createHorizontalStrut(BORDER));
				ServerPanel.add(JPanelOne);
			}

			JPanel.add(Box.createVerticalStrut(BORDER));

			{
				Box box = Box.createHorizontalBox();
				JLabel label = new JLabel("Server");
				label.setForeground(Color.WHITE);
				box.add(label);
				JPanel.add(box);
			}

			JPanel.add(Box.createVerticalStrut(BORDER));

			{
				Box box;
				JTextField TextField;

				JLabel Label = new JLabel("Login:");

				/**
				 * Server Login details
				 */
				Login = TextField = new JTextField("", 20);
				TextField.setFont(new Font("X", Font.PLAIN, 15));
				TextField.setPreferredSize(new Dimension(WeightOFEDIT, HeightOFEDIT + HeightOfADD));
				TextField.setMaximumSize(TextField.getPreferredSize());
				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalGlue());
				box.add(Label);
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));
				/**
				 * Server Ip address
				 */
				Label = new JLabel("Ip:", SwingConstants.RIGHT);

				Ip = TextField = new JTextField("", 20);
				TextField.setFont(new Font("X", Font.PLAIN, 15));
				TextField.setPreferredSize(new Dimension(WeightOFEDIT, HeightOFEDIT + HeightOfADD));
				TextField.setMaximumSize(TextField.getPreferredSize());
				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalGlue());
				box.add(Label);
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));
				/**
				 * Server port number
				 */
				Label = new JLabel("Port:", SwingConstants.RIGHT);

				Port = TextField = new JTextField("", 20);
				TextField.setFont(new Font("X", Font.PLAIN, 15));
				TextField.setPreferredSize(new Dimension(WeightOFEDIT, HeightOFEDIT + HeightOfADD));
				TextField.setMaximumSize(TextField.getPreferredSize());
				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalGlue());
				box.add(Label);
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));

			}

			/**
			 * Initializing the server buttons
			 */
			{
				Box box = Box.createHorizontalBox();
				JButton Button;
				StartButton = Button = new JButton("Start");
				box.add(Button);
				box.add(Box.createHorizontalGlue());
				Button.addActionListener(GUI.new startServerBtn(this));

				StopButton = Button = new JButton("Stop");
				box.add(Button);
				JPanel.add(box);
				Button.addActionListener(GUI.new stopServerBtn(this));

			}
			JPanel.add(Box.createVerticalStrut(BORDER));

			JPanel.add(Box.createVerticalStrut(BORDER));

			/**
			 * RSA constants
			 */
			{
				Box box;
				JTextField TextField;

				M = TextField = new JTextField(
						"685736394058674943094857578930975645398133198298113795322055388046957411865388383684826599331989921538391578601261291782060429810000904121670209210618991442805378573315330642902868268518281829821088107828255901899669812392602881563355885322555356435347307075034083484667631848695799702679812829446350404849",
						20);
				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				box.setVisible(false);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));

				E = TextField = new JTextField("11", 20);
				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				box.setVisible(false);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));

				D = TextField = new JTextField(
						"46579686774749585789384757589348475757368399731281125577166640217940643978745534266025252526103671821851301884409677649756519217576060126152013648738834115569557723830211673224899100047246087323077845838488231251345580647327928968077861595525702625052750974624691780284383267722427455369306490531580110939",
						20);

				box = Box.createHorizontalBox();
				box.add(Box.createHorizontalStrut(MiddleBORDER));
				box.add(TextField);
				box.setVisible(false);
				JPanel.add(box);
				JPanel.add(Box.createVerticalStrut(BORDER));
			}
		}

		/**
		 * Creating and initializing the communication panel
		 */
		{
			ConnectionPanel = new JPanel();
			ConnectionPanel.setBackground(new Color(102, 55, 0));
			ConnectionPanel.setLocation(40 + ConnectionPanel.getWidth(), BORDER + BORDERTop);

			JPanel JPanel;
			{
				JPanel JPanelOne = new JPanel();
				JPanelOne.setBackground(new Color(102, 55, 0));

				BoxLayout boxLayout = new BoxLayout(JPanelOne, BoxLayout.LINE_AXIS);
				JPanelOne.setLayout(boxLayout);

				JPanelOne.add(Box.createHorizontalStrut(BORDER));
				JPanel = new JPanel();
				JPanel.setBackground(new Color(102, 55, 0));

				{
					BoxLayout pnlVertLayout = new BoxLayout(JPanel, BoxLayout.Y_AXIS);
					JPanel.setLayout(pnlVertLayout);
					Font font = new Font("X", Font.PLAIN, 15);
					JPanel.setFont(font);
				}
				ConnectionPanel.setLayout(new FlowLayout());
				JPanelOne.add(JPanel);
				JPanelOne.add(Box.createHorizontalStrut(BORDER));
				ConnectionPanel.add(JPanelOne);
			}

			JPanel.add(Box.createVerticalStrut(BORDER));
			{
				Box box = Box.createHorizontalBox();
				box.add(Box.createHorizontalGlue());
				JLabel label = new JLabel("Connection");
				label.setForeground(Color.WHITE);
				box.add(label);
				box.add(Box.createHorizontalGlue());
				JPanel.add(box);
			}
			/**
			 * Method for presenting the message in the chat window when is being sent.
			 */
			JPanel.add(Box.createVerticalStrut(BORDER));
			{
				JTextPane Text = new JTextPane();
				JTextPane = Text;
				Text.setContentType("text/html");
				Text.setBackground(new Color(200, 200, 200));
				Text.setLocation(0, 0);
				Text.setPreferredSize(new Dimension(WeightOfEDIT, HeightOfEDIT));

				JScrollPane scrollPane = new JScrollPane(Text);
				scrollPane.setPreferredSize(new Dimension(WeightOfEDIT, HeightOfEDIT));

				DefaultCaret DfltCaret = (DefaultCaret) Text.getCaret();
				DfltCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				Text.setText("<font color='red'>");
				JPanel.add(scrollPane);
			}

			/**
			 * Creating and initializing the message panel.
			 */
			JPanel.add(Box.createVerticalStrut(BORDER));
			{
				{
					Box BoxMsg = Box.createHorizontalBox();
					JLabel label = new JLabel("Message");
					label.setForeground(Color.WHITE);
					BoxMsg.add(label);
					JPanel.add(BoxMsg);
				}

				JTextArea TextArea;
				MsgTextArea = TextArea = new JTextArea("", 7, 50);
				TextArea.setFont(new Font("X", Font.PLAIN, 20));
				TextArea.setPreferredSize(new Dimension(WeightOfEDIT, HeightOfMESSAGE));
				JPanel.add(TextArea);
				JPanel.add(Box.createVerticalStrut(BORDER));
				{
					Box boxBtns = Box.createHorizontalBox();
					JButton button;
					button = new JButton("Send");
					boxBtns.add(button);
					boxBtns.add(Box.createHorizontalGlue());
					button.addActionListener(GUI.new BtnSendClient(this));

					button = new JButton("Close chat");
					boxBtns.add(button);
					button.addActionListener(GUI.new closeBtn(this));
					JPanel.add(boxBtns);
				}
			}
			JPanel.add(Box.createVerticalStrut(BORDER));
		}

		{
			Box B1 = Box.createHorizontalBox();

			Box B2 = Box.createVerticalBox();

			B2.add(ContPanel);
			B2.add(Box.createVerticalStrut(BORDER));
			B2.add(ServerPanel);

			B1.add(B2);
			B1.add(Box.createHorizontalStrut(BORDER));
			B1.add(ConnectionPanel);

			Jpanel.add(Box.createVerticalStrut(BORDER));
			Jpanel.add(B1);
			Jpanel.add(Box.createVerticalStrut(BORDER));

		}
		{
			this.pack();
			this.setVisible(true);

		}

		CListUpdate();
		setListContactsButtonsAndPanelConnection(true);
		ValidateFeatures(ConnectionPanel, false);
		ServerBtns(true);
	}

	/**
	 * The method validates the features in the communication panel The method is
	 * creted by "constcoh" and refactored by Grigor Yordanov
	 * 
	 */
	private void ValidateFeatures(Container window, boolean validate) {
		Component[] features = window.getComponents();
		for (Component feature : features) {
			feature.setEnabled(validate);
			if (feature instanceof Container) {
				ValidateFeatures((Container) feature, validate);
			}
		}
	}

	/**
	 * The method is updating the list of contacts
	 */
	private void CListUpdate() {
		int ftr = ListOfCont.getSelectedIndex();
		String[] m = LOfUsers.getInstance().getList();

		do {
			ftr = m.length - 1;
		} while (m.length <= ftr);
		ListOfCont.setListData(m);
		ListOfCont.setSelectedIndex(ftr);

	}

	/**
	 * The method allows the server data to be edited
	 */
	private void ServerDataEditing(Boolean bol) {
		Login.setEnabled(bol);
		Ip.setEnabled(bol);
		Port.setEnabled(bol);
	}

	/**
	 * Method which is stopping the server when button "Stop" is been pressed
	 */
	private void ServerBtns(Boolean stop) {
		StopButton.setEnabled(!stop);
		StartButton.setEnabled(stop);
		ServerDataEditing(stop);
	}

	private void setListContactsButtonsAndPanelConnection(Boolean open) {
		RemoveButton.setEnabled(!open);
		ConnectButton.setEnabled(!open);
		ValidateFeatures(ConnectionPanel, open);
		UpdateMsgLst();
	}

	/**
	 * Method which update the chat list when new message has been sent.
	 */
	public void UpdateMsgLst() {
		String Msg = new String();

		if (UContact != null) {
			Entity User = UListener.getInstance();
			Vector<Messages> vector = UContact.getHistory();
			Iterator<Messages> item = vector.iterator();
			while (item.hasNext()) {
				Messages msg = item.next();
				String x = new String();
				if (msg.Mine()) {
					x += "<font color=\"e60000\">" + User.getLogin() + ":";
				} else
					x += "<font color=\"0000aa\">" + User.getLogin() + ":";
				x += msg.getDate() + ">><br>" + msg.getData() + "<br>";
				Msg = Msg.concat(x);

			}
		}

		JTextPane.setText(Msg);
	}

	/**
	 * Update connection
	 */
	public void updConn() {
		if (UContact == null) {
			setListContactsButtonsAndPanelConnection(false);
		} else {
			setListContactsButtonsAndPanelConnection(UContact.isOpen());
		}
		UpdateMsgLst();

	}

	/**
	 * Singleton
	 */
	public static UI get() {
		if (mainFrame == null) {
			mainFrame = new UI();
		}
		return mainFrame;
	}

	class guiMethods {
		/**
		 * The method is used when the button "Add" is being pressed
		 *
		 */
		public class addUBtn implements ActionListener {
			private UI user;

			public addUBtn(UI frame) {
				user = frame;
			}

			public void actionPerformed(ActionEvent event) {
				try {
					Map<String, String> map = AddContact.Query(user);
					UContact userContact = new UContact(new Entity(map.get("ip"), Integer.parseInt(map.get("port")),
							map.get("login"), new BigInteger(map.get("M")), new BigInteger(map.get("E"))));
					LOfUsers.getInstance().ADD(userContact);
					user.CListUpdate();
				} catch (Exception ERROR) {
					System.out.println("No new contact has been added!");
				}
			}
		}

		/**
		 * The method is used when the button "Remove" is being pressed
		 *
		 */

		public class removeUBtn implements ActionListener {
			private UI user;

			public removeUBtn(UI main) {
				user = main;
			}

			public void actionPerformed(ActionEvent event) {
				Integer ind = user.ListOfCont.getSelectedIndex();
				LOfUsers.getInstance().RemoveIndex(ind);
				user.CListUpdate();
			}
		}

		/**
		 * 
		 * Method which is used when a new user is been selected for communication.
		 *
		 */
		public class userSelection implements ListSelectionListener {
			private UI user;

			public userSelection(UI main) {
				user = main;
			}

			public void valueChanged(ListSelectionEvent event) {
				int ind;
				ind = user.ListOfCont.getSelectedIndex();
				if (ind >= 0) {
					UContact pUser = user.UContact;

					user.UContact = LOfUsers.getInstance().getIndex(ind);

					Boolean open = user.UContact.isOpen();
					user.setListContactsButtonsAndPanelConnection(open);
					if (pUser != user.UContact)
						user.UpdateMsgLst();
				} else {
					user.UContact = null;
					Boolean open = true;
					user.setListContactsButtonsAndPanelConnection(open);
					user.ValidateFeatures(user.ConnectionPanel, false);
				}
			}

		}

		/**
		 * 
		 * Method which is used for establishing a connection when user is been selected
		 * for chat
		 *
		 */
		public class userConnectBtn implements ActionListener {
			public userConnectBtn(UI main) {
			}

			public void actionPerformed(ActionEvent event) {
				if (UContact == null) {
					return;
				}
				{
					Entity data = UListener.getInstance();
					data.setLogin(Login.getText());
					data.setIp(Ip.getText());
					data.setPort(Integer.parseInt(Port.getText()));
				}
				try {
					if (UContact.isOpen()) {

					} else
						UContact.Open();
				} catch (Exception ERROR) {

				}
				setListContactsButtonsAndPanelConnection(UContact.isOpen());
				UpdateMsgLst();

			}
		}

		/**
		 * 
		 * Method which is used for closing the connection between users when the button
		 * "Close chat" is being pressed.
		 *
		 */
		public class closeBtn implements ActionListener {
			public closeBtn(UI main) {

			}

			public void actionPerformed(ActionEvent event) {
				if (UContact == null) {
					return;
				}
				try {
					if (UContact.isOpen()) {
						UContact.Close();
					}

				} catch (Exception ERROR) {

				}
				setListContactsButtonsAndPanelConnection(UContact.isOpen());
				UpdateMsgLst();

			}
		}

		/**
		 * 
		 * Method is starting the server when the button "Start" is being pressed.
		 *
		 */
		public class startServerBtn implements ActionListener {
			public startServerBtn(UI main) {
			}

			public void actionPerformed(ActionEvent event) {
				Entity usr = UListener.getInstance();
				usr.setLogin(Login.getText());
				usr.setIp(Ip.getText());
				usr.setEncryption(new EncrAndDecr(new BigInteger(M.getText()), new BigInteger(D.getText()),
						new BigInteger(E.getText())));
				usr.setPort(Integer.parseInt(Port.getText()));
				try {
					SrvListnr.getInstance().start(usr);
				} catch (Exception ERROR) {

				}
				ServerBtns(!SrvListnr.getInstance().Start());
			}
		}

		/**
		 * 
		 * Method which is used for stopping the server when the button "Stop" is being
		 * pressed.
		 *
		 */
		public class stopServerBtn implements ActionListener {
			public stopServerBtn(UI main) {

			}

			public void actionPerformed(ActionEvent event) {
				try {
					SrvListnr.getInstance().stop();
				} catch (Exception ERROR) {

				}
				ServerBtns(!SrvListnr.getInstance().Start());

			}
		}

		/**
		 * 
		 * Method which is used for sending the typed data when the button "Send" is
		 * being pressed.
		 *
		 */
		public class BtnSendClient implements ActionListener {

			public BtnSendClient(UI main) {

			}

			public void actionPerformed(ActionEvent event) {
				if (UContact == null) {
					return;
				}
				try {
					if (UContact.isOpen()) {
						UContact.Send(MsgTextArea.getText().replaceAll("\n", "<br>"));
						MsgTextArea.setText(new String());
					}
				} catch (Exception ERROR) {

				}
				setListContactsButtonsAndPanelConnection(UContact.isOpen());

			}
		}
	}
}

public class UserInterface {

}