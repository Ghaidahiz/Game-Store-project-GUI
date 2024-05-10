package gamestore_project;

//logout
//save
//buy an added game 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class GEAgamestore {
	String user = "";
	String adminName = "";
	private JFrame frame;
	protected Object frame2;

	/**
	 * Launch the application.
	 */
	Scanner sc = new Scanner(System.in);

	static Admin Almaha = new Admin("Almaha", "123"); // setting up
	static Admin Eman = new Admin("Eman", "321"); // the admins
	static Admin Ghaida = new Admin("Ghaida", "213"); // of the store

	static GameStore GEA = new GameStore("GEA", Almaha, Eman, Ghaida, 200, 100); // creating the store

	public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {

		File file = new File("gamestore_project/src/gamestore_project/Users.data");
		FileInputStream f1 = new FileInputStream(file);
		ObjectInputStream userIn = new ObjectInputStream(f1);

		File file2 = new File("gamestore_project/src/gamestore_project/Games.data");
		FileInputStream f3 = new FileInputStream(file2);
		ObjectInputStream gameIn = new ObjectInputStream(f3);

		try {
			while (true) {
				GEA.addUser(((User) userIn.readObject()));
			}
		} catch (EOFException e) {
		}
		try {
			while (true) {
				GEA.addGame(((Game) gameIn.readObject()));
			}
		} catch (EOFException e) {
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GEAgamestore window = new GEAgamestore();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GEAgamestore() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(new FlatMoonlightIJTheme());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		frame = new JFrame();
		frame.setBounds(250, 160, 840, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 470, 0, 470, 0 };
		gridBagLayout.rowHeights = new int[] { 77, 77, 77, 0, 0, 0, 77, 0, 77, 77, 77, 77, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		frame.getContentPane().add(label, gbc_label);

		JLabel label_1_1 = new JLabel("");
		GridBagConstraints gbc_label_1_1 = new GridBagConstraints();
		gbc_label_1_1.fill = GridBagConstraints.BOTH;
		gbc_label_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1_1.gridx = 0;
		gbc_label_1_1.gridy = 1;
		frame.getContentPane().add(label_1_1, gbc_label_1_1);
		BufferedImage image;
		try {
			image = ImageIO.read(new File("gamestore_project/src/gamestore_project/img/crown.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(image), SwingConstants.CENTER);
			// FlowLayout FlowLayout = new FlowLayout();
			// JPanel imgPanel = new JPanel(FlowLayout);
			GridBagConstraints gbc_label_2_1 = new GridBagConstraints();
			gbc_label_2_1.fill = GridBagConstraints.BOTH;
			gbc_label_2_1.insets = new Insets(0, 0, 5, 5);
			gbc_label_2_1.gridx = 1;
			gbc_label_2_1.gridy = 1;
			frame.getContentPane().add(imageLabel, gbc_label_2_1);
			// imgPanel.add(imageLabel,SwingConstants.CENTER);
			// frame.add(imgPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// JLabel label_2_1 = new JLabel("");

		// Icon GEAIcon = new
		// ImageIcon("gamestore_project/src/gamestore_project/img/crown.png");
		JLabel Gealabel = new JLabel("GEA GAMESTORE");
		Gealabel.setHorizontalAlignment(SwingConstants.CENTER);
		Gealabel.setFont(new Font("Lato", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		frame.getContentPane().add(Gealabel, gbc_lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("Welcome to GEA gamestore");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel label_3_1 = new JLabel("");
		GridBagConstraints gbc_label_3_1 = new GridBagConstraints();
		gbc_label_3_1.fill = GridBagConstraints.BOTH;
		gbc_label_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_3_1.gridx = 0;
		gbc_label_3_1.gridy = 6;
		frame.getContentPane().add(label_3_1, gbc_label_3_1);

		JLabel lblNewLabel_2 = new JLabel("do you want to login as...");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 6;
		frame.getContentPane().add(label_4, gbc_label_4);

		JLabel label_5 = new JLabel("");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 8;
		frame.getContentPane().add(label_5, gbc_label_5);

		JButton btnNewButton_1 = new JButton("USER");

		btnNewButton_1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JFrame frame2 = new JFrame();
				frame2.setBounds(450, 300, 450, 300);
				frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[] { 150, 150, 150, 0 };
				gridBagLayout.rowHeights = new int[] { 90, 90, 90, 0 };
				gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
				frame2.getContentPane().setLayout(gridBagLayout);

				JLabel label = new JLabel("");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.fill = GridBagConstraints.BOTH;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				frame2.getContentPane().add(label, gbc_label);

				JLabel lblNewLabel = new JLabel("Hello, please enter your username");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
				gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 0;
				frame2.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

				JLabel label_1 = new JLabel("");
				GridBagConstraints gbc_label_1 = new GridBagConstraints();
				gbc_label_1.fill = GridBagConstraints.BOTH;
				gbc_label_1.insets = new Insets(0, 0, 5, 0);
				gbc_label_1.gridx = 2;
				gbc_label_1.gridy = 0;
				frame2.getContentPane().add(label_1, gbc_label_1);

				JTextField textfield = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(0, 0, 5, 5);
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 1;
				frame2.getContentPane().add(textfield, gbc_textField);
				textfield.setColumns(10);
				JLabel label_2 = new JLabel("");
				GridBagConstraints gbc_label_2 = new GridBagConstraints();
				gbc_label_2.fill = GridBagConstraints.BOTH;
				gbc_label_2.insets = new Insets(0, 0, 0, 5);
				gbc_label_2.gridx = 1;
				gbc_label_2.gridy = 2;
				frame2.getContentPane().add(label_2, gbc_label_2);

				JButton btnNewButton = new JButton("login");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = textfield.getText();
						user = username;
						if (GEA.findUser(username) != null) {
							JOptionPane.showMessageDialog(frame2, "welcome", "login message", 1);
							System.out.println("success");
							frame2.dispose();
							switchToMainPanel();
						} else {
							Icon f8mh = new ImageIcon("gamestore_project/src/gamestore_project/img/walrus.png");
							int result = JOptionPane.showOptionDialog(frame2,
									"this account doesn't exist, do you want to create a new account", "oops",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, f8mh, null, null);
							if (result == JOptionPane.NO_OPTION) {
								JOptionPane.getRootFrame().dispose();
								frame2.setVisible(false);
							}

							else if (result == JOptionPane.YES_OPTION) {
								String amount = JOptionPane.showInputDialog(null, "enter your wallet amount",
										"register", JOptionPane.PLAIN_MESSAGE);
								double wlt = 0;
								try {
									wlt = Double.parseDouble(amount);
									if (wlt < 0) {
										JOptionPane.showMessageDialog(frame2, "please enter a positive number", "error",
												JOptionPane.ERROR_MESSAGE);
									} else {
										User u1 = new User(username, wlt);
										GEA.addUser(u1);
										JOptionPane.getRootFrame().dispose();
										frame2.setVisible(false);
										switchToMainPanel();
									}
								} catch (NumberFormatException e2) {
									JOptionPane.showMessageDialog(frame2, "please enter a number", "error",
											JOptionPane.ERROR_MESSAGE);
								}
							}

						}
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 2;
				frame2.getContentPane().add(btnNewButton, gbc_btnNewButton);

				JLabel label_3 = new JLabel("");
				GridBagConstraints gbc_label_3 = new GridBagConstraints();
				gbc_label_3.fill = GridBagConstraints.BOTH;
				gbc_label_3.gridx = 2;
				gbc_label_3.gridy = 2;
				frame2.getContentPane().add(label_3, gbc_label_3);
				frame2.setVisible(true);

			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 8;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel label_6 = new JLabel("");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.BOTH;
		gbc_label_6.insets = new Insets(0, 0, 5, 0);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 8;
		frame.getContentPane().add(label_6, gbc_label_6);

		JLabel label_7 = new JLabel("");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.BOTH;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 9;
		frame.getContentPane().add(label_7, gbc_label_7);

		JButton btnNewButton = new JButton("ADMIN");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 9;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame3 = new JFrame();
				frame3.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/ghaida/Downloads/crown.png"));
				frame3.getContentPane().setBackground(UIManager.getColor("window"));
				SpringLayout springLayout = new SpringLayout();
				frame3.getContentPane().setLayout(springLayout);

				JLabel msg = new JLabel("Hello admin, please enter your information");
				msg.setForeground(UIManager.getColor("textText"));
				frame3.getContentPane().add(msg);

				JLabel adminLabel = new JLabel("username:");
				springLayout.putConstraint(SpringLayout.EAST, adminLabel, -344, SpringLayout.EAST,
						frame3.getContentPane());
				adminLabel.setForeground(UIManager.getColor("textText"));
				frame3.getContentPane().add(adminLabel);
				JTextField userfield = new JTextField();

				adminLabel.setLabelFor(userfield);

				springLayout.putConstraint(SpringLayout.SOUTH, msg, -37, SpringLayout.NORTH, userfield);
				springLayout.putConstraint(SpringLayout.SOUTH, userfield, -170, SpringLayout.SOUTH,
						frame3.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, userfield, 6, SpringLayout.EAST, adminLabel);
				frame3.getContentPane().add(userfield);
				userfield.setColumns(10);

				JLabel adminlabel2 = new JLabel("password:");
				springLayout.putConstraint(SpringLayout.NORTH, adminlabel2, 163, SpringLayout.NORTH,
						frame3.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, adminLabel, -15, SpringLayout.NORTH, adminlabel2);
				springLayout.putConstraint(SpringLayout.EAST, adminlabel2, 0, SpringLayout.EAST, adminLabel);
				adminlabel2.setForeground(UIManager.getColor("textText"));
				frame3.getContentPane().add(adminlabel2);

				JPasswordField passwordField = new JPasswordField();
				springLayout.putConstraint(SpringLayout.NORTH, passwordField, 5, SpringLayout.SOUTH, userfield);
				springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, adminlabel2);
				passwordField.setColumns(10);
				frame3.getContentPane().add(passwordField);
				adminlabel2.setLabelFor(passwordField);

				JButton loginButton = new JButton("log in");
				springLayout.putConstraint(SpringLayout.NORTH, loginButton, 25, SpringLayout.SOUTH, passwordField);
				springLayout.putConstraint(SpringLayout.WEST, loginButton, 233, SpringLayout.WEST,
						frame3.getContentPane());
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						adminName = userfield.getText();
						String password = String.valueOf(passwordField.getPassword());
						if (GEA.adminExists(adminName, password)) {
							System.out.println("success");
							frame3.setVisible(false);
							switchToAdminPanel1();// new method switchtoadminpanel FIX!
						}
					}
				});
				frame3.getContentPane().add(loginButton);

				JLabel blank = new JLabel("");
				springLayout.putConstraint(SpringLayout.EAST, blank, -433, SpringLayout.EAST, frame3.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, msg, 25, SpringLayout.EAST, blank);
				springLayout.putConstraint(SpringLayout.NORTH, blank, 48, SpringLayout.NORTH, frame3.getContentPane());
				blank.setIcon(new ImageIcon("/Users/ghaida/Downloads/disco-ball.png"));
				frame3.getContentPane().add(blank);
				frame3.setBackground(new Color(45, 45, 45));
				frame3.setBounds(450, 300, 546, 351);
				frame3.setVisible(true);

			}

		});

		JLabel label_8 = new JLabel("");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.BOTH;
		gbc_label_8.insets = new Insets(0, 0, 5, 0);
		gbc_label_8.gridx = 2;
		gbc_label_8.gridy = 9;
		frame.getContentPane().add(label_8, gbc_label_8);

		JLabel label_9 = new JLabel("");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.BOTH;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 10;
		frame.getContentPane().add(label_9, gbc_label_9);

		JLabel label_10 = new JLabel("");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.fill = GridBagConstraints.BOTH;
		gbc_label_10.insets = new Insets(0, 0, 0, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 11;
		frame.getContentPane().add(label_10, gbc_label_10);
	}

	public void switchToMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the mainPanel

		// Add welcome message label in the north
		JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout with CENTER
																					// alignment
		Icon geaIconSmall = new ImageIcon("gamestore_project/src/gamestore_project/img/crown (2).png");
		JLabel welcomeLabel = new JLabel(" Welcome back! Take a look around GEA store", geaIconSmall,
				SwingConstants.CENTER);
		welcomePanel.add(welcomeLabel);
		mainPanel.add(welcomePanel, BorderLayout.NORTH);
		Icon profileIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/dancing (1).png");
		JButton profileButton = new JButton("My Profile", profileIcon);
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLibrary();
			}

		});

		mainPanel.add(profileButton, BorderLayout.EAST);

		// Create a panel for the game grid
		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap
		for (int i = 0; i < GEA.getNoGames(); i++) {
			final int index = i; // Capture the value of i in a final variable

			// Create a panel for each game
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

			// Add label for the game name at the top
			Icon price = new ImageIcon("gamestore_project/src/gamestore_project/img/coin (2).png");
			String priceAfterDiscount2 = "" + GEA.getGameList()[index].priceAfterDiscount();
			JLabel gameNameLabel = new JLabel(
					GEA.getGameList()[index].getName() + " "
							+ priceAfterDiscount2.substring(0, (priceAfterDiscount2.indexOf('.') + 2)) + " SAR",
					price, SwingConstants.CENTER);
			gameNameLabel.setHorizontalTextPosition(JLabel.LEFT);
			gamePanel.add(gameNameLabel, BorderLayout.NORTH);

			String fullname = GEA.getGameList()[index].getName() + " ";
			int space = fullname.indexOf(" ");
			String cutname = fullname.substring(0, space + 1);
			Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
			File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
			if(gamefile.exists()){
			JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
			gamePanel.add(imageLabel, BorderLayout.CENTER);}
			else {
				Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1.png");
				JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
				gamePanel.add(imageLabel1, BorderLayout.CENTER);}


			// Create a panel for buy and gift buttons
			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap

			// Add "Buy" button
			Icon buyicon = new ImageIcon("gamestore_project/src/gamestore_project/img/payment.png");
			Icon gifticon = new ImageIcon("gamestore_project/src/gamestore_project/img/gift.png");

			JButton buyButton = new JButton("", buyicon);

			buyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					(GEA.findUser(user)).buyGame(GEA.getGameList()[index]);
				}

			});

			buttonPanel.add(buyButton);

			// Add "Gift" button
			JButton giftButton = new JButton("", gifticon);
			giftButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Fname = JOptionPane.showInputDialog("Enter your friend's name");
					if (GEA.findUser(Fname) == null) { // if the friend wasn't found, it'll print a message
						JOptionPane.showMessageDialog(mainPanel, "SORRY.. COULDN'T FIND USER \"" + Fname + "\"" + "",
								"error", JOptionPane.ERROR_MESSAGE);
						// System.out.println("SORRY.. COULDN'T FIND USER \"" + Fname + "\"");
					} else {// if the friend was found (in users), the games of the store will be displayed,
							// then the user will be asked to enter a game to send
						GEA.findUser(user).sendGift(GEA.findUser(Fname), GEA.getGameList()[index]); // the game will be
																									// bought and sent
																									// to the friend
																									// library if it
																									// exists in the
																									// store (see
																									// methods)

					}
				}
			});
			buttonPanel.add(giftButton);

			// Add the button panel to the bottom of the game panel
			gamePanel.add(buttonPanel, BorderLayout.SOUTH);

			// Add the game panel to the game grid panel
			gameGridPanel.add(gamePanel);
		}

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(gameGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Revalidate and repaint the frame
		frame.revalidate();
		frame.repaint();
	}

	public void switchToLibrary() {
		JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the mainPanel

		// Add welcome message label in the north
		Icon profileIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/boy.png");
		Icon walletIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
		String sWallet = GEA.findUser(user).getWallet() + "";
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout with CENTER alignment
		JLabel GELABEL11 = new JLabel("User: " + user + "  ", profileIcon, SwingConstants.CENTER);
		JLabel GELABEL22 = new JLabel("Wallet: " + sWallet.substring(0, (sWallet.indexOf('.') + 2)), walletIcon,
				SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                     ",
				SwingConstants.CENTER);
		Icon searchIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/zoom.png");
		JButton searchInLib = new JButton("", searchIcon);
		searchInLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String in = JOptionPane.showInputDialog("please enter the name of the game you are looking for");
				try {
					if (in == null || GEA.findUser(user).findGame(in) == null) {
						throw new GameNotFoundException(); // user defined exception
					}
				} catch (GameNotFoundException E) {
				}
				switchToSearchView(GEA.findUser(user).findGame(in));
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL22);
		titlePanel.add(GELABEL33);
		titlePanel.add(searchInLib);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon homeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/home.png");
		JButton homeButton = new JButton("Go Home", homeIcon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToMainPanel();
			}

		});

		mainPanel.add(homeButton, BorderLayout.EAST);

		// Create a panel for the game grid
		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap

		for (int i = 0; i < GEA.findUser(user).getNumberOfGames(); i++) {
			final int index = i; // Capture the value of i in a final variable

			// Create a panel for each game
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

			// Add label for the game name at the top
			JLabel gameNameLabel = new JLabel(GEA.findUser(user).getGameLibrary()[index].getName(),
					SwingConstants.CENTER);
			gamePanel.add(gameNameLabel, BorderLayout.NORTH);

			String fullname = GEA.findUser(user).getGameLibrary()[index].getName() + " ";
                int space = fullname.indexOf(" ");
                String cutname = fullname.substring(0, space + 1);
                Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
                File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
                if(gamefile.exists()){
                JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
                gamePanel.add(imageLabel, BorderLayout.CENTER);}
                else {
                    Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1.png");
                    JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
                    gamePanel.add(imageLabel1, BorderLayout.CENTER);}


			// Create a panel for buy and gift buttons
			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap

			// Add "Buy" button
			Icon delIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");
			JButton delButton = new JButton("", delIcon);

			delButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					(GEA.findUser(user)).removeGameFromLibrary((GEA.findUser(user).getGameLibrary()[index].getName()));
					switchToLibrary();
				}

			});

			buttonPanel.add(delButton);

			// Add the button panel to the bottom of the game panel
			gamePanel.add(buttonPanel, BorderLayout.SOUTH);

			// Add the game panel to the game grid panel
			gameGridPanel.add(gamePanel);
		}

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(gameGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Revalidate and repaint the frame
		frame.revalidate();
		frame.repaint();
	}

	public void switchToSearchView(Game g) {
		JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the mainPanel

		// Add welcome message label in the north
		Icon profileIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/boy.png");
		Icon walletIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
		String sWallet = GEA.findUser(user).getWallet() + "";
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout with CENTER alignment
		JLabel GELABEL11 = new JLabel("User: " + user + "  ", profileIcon, SwingConstants.CENTER);
		JLabel GELABEL22 = new JLabel("Wallet: " + sWallet.substring(0, (sWallet.indexOf('.') + 2)), walletIcon,
				SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                             ",
				SwingConstants.CENTER);
		Icon searchIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/zoom.png");
		JButton searchInLib = new JButton("", searchIcon);
		searchInLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String in = JOptionPane.showInputDialog("please enter the name of the game you are looking for");
				try {
					if (in == null || GEA.findUser(user).findGame(in) == null) {
						throw new GameNotFoundException(); // user defined exception
					}
				} catch (GameNotFoundException E) {
				}
				switchToSearchView(GEA.findUser(user).findGame(in));
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL22);
		titlePanel.add(GELABEL33);
		titlePanel.add(searchInLib);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon homeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/home.png");
		JButton homeButton = new JButton("Go Home", homeIcon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToMainPanel();
			}

		});

		mainPanel.add(homeButton, BorderLayout.EAST);

		// Create a panel for the game grid
		JPanel gameGridPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap

		// Create a panel for each game
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());

		// Add label for the game name at the top
		JLabel gameNameLabel = new JLabel(g.getName(), SwingConstants.CENTER);
		gamePanel.add(gameNameLabel, BorderLayout.NORTH);

		String fullname = g.getName() + " ";
		int space = fullname.indexOf(" ");
		String cutname = fullname.substring(0, space + 1);
		Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
		File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
		if(gamefile.exists()){
		JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
		gamePanel.add(imageLabel, BorderLayout.CENTER);}
		else {
			Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1.png");
			JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
			gamePanel.add(imageLabel1, BorderLayout.CENTER);}

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap

		Icon delIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");
		JButton delButton = new JButton("", delIcon);

		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(GEA.findUser(user)).removeGameFromLibrary((g.getName()));
				switchToLibrary();
			}

		});

		buttonPanel.add(delButton);

		// Add the button panel to the bottom of the game panel
		gamePanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add the game panel to the game grid panel
		gameGridPanel.add(gamePanel);

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(gameGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Revalidate and repaint the frame
		frame.revalidate();
		frame.repaint();
	}

	public void switchToAdminPanel1() {
		JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the mainPanel

		// Add welcome message label in the north
		Icon adminIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/mask.png");
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout with CENTER alignment
		JLabel GELABEL11 = new JLabel("Admin: " + adminName + "  ", adminIcon, SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                                   ",
				SwingConstants.CENTER);
		Icon addIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/add.png");

		JButton addGame = new JButton("", addIcon);
		addGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inGame = JOptionPane
						.showInputDialog("please enter the name of the game you want to add to the store");
						
				try {
					if (GEA.searchForGame(inGame) != null ) {
						throw new GameAlreadyExists(); // user defined exception
					}
					else if (inGame == null || inGame.isEmpty()){
					JOptionPane.showMessageDialog(null, "please enter the game's name", "Oh no :(", 0);
					}
					else {
						JTextField pubField = new JTextField(5);
						JTextField priceField = new JTextField(5);
						

						JPanel myPanel = new JPanel();
						myPanel.add(Box.createHorizontalStrut(8)); // a spacer
						myPanel.add(new JLabel("Publisher:"));
						myPanel.add(pubField);
						myPanel.add(Box.createHorizontalStrut(8)); // a spacer
						myPanel.add(new JLabel("Price:"));
						myPanel.add(priceField);
						myPanel.add(Box.createHorizontalStrut(8)); // a spacer

						String[] genres = { "", "Horror", "RPG", "Story-Rich", "Detective", "Survival" };

						JComboBox<String> comboBox = new JComboBox<>(genres);

						comboBox.setBounds(196, 209, 153, 27);
						JLabel lblGenre = new JLabel("Genre:");
						lblGenre.setBounds(138, 213, 47, 16);

						myPanel.add(lblGenre);
						myPanel.add(comboBox);

						int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Game Information",
								JOptionPane.OK_CANCEL_OPTION);
						Game newGame;

						if (result == JOptionPane.OK_OPTION) {
							String genre = (String) comboBox.getSelectedItem();
							try {
								double price = Double.parseDouble(priceField.getText());
								if (price < 0) {
									JOptionPane.showMessageDialog(null, "please enter a positive number", "error", JOptionPane.ERROR_MESSAGE);
								}	
								else{
									if (!genre.isEmpty()) {
									switch (genre) {
									case "Horror":
										newGame = new Horror(inGame, pubField.getText(), price);
										GEA.addGame(newGame);
										JOptionPane.showMessageDialog(null, "Done", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										switchToAdminPanel1();
										break;
									case "RPG":
										newGame = new RPG(inGame, pubField.getText(), price);
										GEA.addGame(newGame);
										JOptionPane.showMessageDialog(null, "Done", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										switchToAdminPanel1();
										break;
									case "Story-Rich":
										newGame = new StoryRich(inGame, pubField.getText(), price);
										GEA.addGame(newGame);
										JOptionPane.showMessageDialog(null, "Done", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										switchToAdminPanel1();
										break;
									case "Detective":
										newGame = new Detective(inGame, pubField.getText(), price);
										GEA.addGame(newGame);
										JOptionPane.showMessageDialog(null, "Done", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										switchToAdminPanel1();
										break;
									case "Survival":
										newGame = new Survival(inGame, pubField.getText(), price);
										GEA.addGame(newGame);
										JOptionPane.showMessageDialog(null, "Done", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										switchToAdminPanel1();
										break;
									}
								} else {
									JOptionPane.showMessageDialog(null, "Please choose a genre", "Oops",
											JOptionPane.ERROR_MESSAGE);
								}}
							} catch (NumberFormatException e3) {
								JOptionPane.showMessageDialog(null, "Invalid price, please add a positive number",
										"Error", JOptionPane.ERROR_MESSAGE);
							} catch (NullPointerException e4) {
								JOptionPane.showMessageDialog(null, "invalid", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						


					}
				} catch (GameAlreadyExists E1) {
				}
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL33);
		titlePanel.add(addGame);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon userIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/users.png");
		JButton userButton = new JButton("Users", userIcon);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToAdminPanel2();
			}

		});

		mainPanel.add(userButton, BorderLayout.EAST);

		// Create a panel for the game grid
		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap
		for (int i = 0; i < GEA.getNoGames(); i++) {
			final int index = i; // Capture the value of i in a final variable

			// Create a panel for each game
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

			// Add label for the game name at the top
			Icon price = new ImageIcon("gamestore_project/src/gamestore_project/img/coin (2).png");
			String priceAfterDiscount2 = "" + GEA.getGameList()[index].priceAfterDiscount();
			JLabel gameNameLabel = new JLabel(
					GEA.getGameList()[index].getName() + " "
							+ priceAfterDiscount2.substring(0, (priceAfterDiscount2.indexOf('.') + 2)) + " SAR",
					price, SwingConstants.CENTER);
			gameNameLabel.setHorizontalTextPosition(JLabel.LEFT);
			gamePanel.add(gameNameLabel, BorderLayout.NORTH);


			String fullname = GEA.getGameList()[index].getName() + " ";
				int space = fullname.indexOf(" ");
				String cutname = fullname.substring(0, space + 1);
				Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
				File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".jpg");
				if(gamefile.exists()){
				JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
				gamePanel.add(imageLabel, BorderLayout.CENTER);}
				else {
					Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1.png");
					JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
					gamePanel.add(imageLabel1, BorderLayout.CENTER);}
	
				
					JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap

			Icon removeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");

			JButton removeButton = new JButton("", removeIcon);

			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GEA.removeGame(GEA.getGameList()[index].getName());
					switchToAdminPanel1();
				}

			});

			buttonPanel.add(removeButton);

			
			// Add the button panel to the bottom of the game panel
			gamePanel.add(buttonPanel, BorderLayout.SOUTH);

			// Add the game panel to the game grid panel
			gameGridPanel.add(gamePanel);
		}

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(gameGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Revalidate and repaint the frame
		frame.revalidate();
		frame.repaint();
	}

	public void switchToAdminPanel2() {
		JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the mainPanel

		// Add welcome message label in the north
		Icon adminIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/mask.png");
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout with CENTER alignment
		JLabel GELABEL11 = new JLabel("Admin: " + adminName + "  ", adminIcon, SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                                   ",
				SwingConstants.CENTER);
		Icon addIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/add.png");

		JButton addUser = new JButton("", addIcon);
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inUser = JOptionPane
						.showInputDialog("please enter the name of the user you want to add to the store");
						
				try {
					if (GEA.findUser(inUser) != null ) {
						throw new UserAlreadyExists(); // user defined exception
					}
					else if (inUser == null || inUser.isEmpty()){
					JOptionPane.showMessageDialog(null, "please enter the user's name", "Oh no :(", 0);
					}
					else {
						String walletamt =JOptionPane.showInputDialog("Enter the user's wallet amount");
						double walletamtt = Double.parseDouble(walletamt);
						if (walletamtt < 0) {
							JOptionPane.showMessageDialog(null, "please enter a positive number", "error", JOptionPane.ERROR_MESSAGE);
						}	
						else{
							User u = new User(inUser, walletamtt);
							GEA.addUser(u);
							switchToAdminPanel2();
							JOptionPane.showMessageDialog(null, "Done", "Success",
							JOptionPane.INFORMATION_MESSAGE);
						}

					}
				} catch (UserAlreadyExists E1) {
				}
				catch(NumberFormatException E2){
					JOptionPane.showMessageDialog(null, "please enter a number", "error",
					JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL33);
		titlePanel.add(addUser);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon gamesIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/games.png");
		JButton homeButton = new JButton("Games", gamesIcon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToAdminPanel1();
			}

		});

		mainPanel.add(homeButton, BorderLayout.EAST);

		JPanel userGridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap
		for (int i = 0; i < GEA.getNoUsers(); i++) {
			final int index = i; 

			JPanel userPanel = new JPanel();
			userPanel.setLayout(new BorderLayout());

			Icon walletIconn = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
			String wallet = "" + GEA.getUserList()[index].getWallet();
			JLabel usernameLabel = new JLabel(
					GEA.getUserList()[index].getUsername() + " "
							+ wallet.substring(0, (wallet.indexOf('.') + 2)) + " SAR",
					walletIconn, SwingConstants.CENTER);
					usernameLabel.setHorizontalTextPosition(JLabel.LEFT);
			userPanel.add(usernameLabel, BorderLayout.NORTH);


				Icon userpic = new ImageIcon("gamestore_project/src/gamestore_project/img/bigboy.png");
				JLabel imageLabel = new JLabel(userpic, SwingConstants.CENTER);
				userPanel.add(imageLabel, BorderLayout.CENTER);
				
				
				JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap

			Icon removeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");

			JButton removeButton = new JButton("", removeIcon);

			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GEA.removeUser(GEA.getUserList()[index].getUsername());
					switchToAdminPanel2();
				}

			});

			buttonPanel.add(removeButton);

			
			// Add the button panel to the bottom of the game panel
			userPanel.add(buttonPanel, BorderLayout.SOUTH);

			// Add the game panel to the game grid panel
			userGridPanel.add(userPanel);
		}

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(userGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Revalidate and repaint the frame
		frame.revalidate();
		frame.repaint();
	}


}
