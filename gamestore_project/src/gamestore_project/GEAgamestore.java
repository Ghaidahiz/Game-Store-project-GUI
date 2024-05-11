/*

GEAgamestore program implements a GameStore called GEA that 
has some existing users "clients" saved in a file, and has the ability to add new ones. 
it has admins who manage the store e.g.(add, remove..etc), 
and it contains a lot of games of different genres that 
the user can interact with e.g.(buy, remove, send as a gift..etc).

Authors:
 Almaha Alaiban - 444200477 
 Eman Qaed - 444200073 
 Ghaida Alzaidan - 444201103 
*/
package gamestore_project;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
		// reading the users and games from a file and adding them to the store
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

		try { // a theme using flatLaf and flatLaf intellij themes libraries
			UIManager.setLookAndFeel(new FlatMoonlightIJTheme());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		frame = new JFrame(); // this is the main frame where most of the program happens
		frame.setBounds(250, 160, 940, 650);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() { // changed the close action to save then close (instead of the
														// usual close or hide)
			public void windowClosing(WindowEvent e) {
				save();
				System.exit(0);
			}

			public void save() { // rewrites the user and game files to update the store when the method is
									// called
				try {
					File file = new File("gamestore_project/src/gamestore_project/Users.data");
					File file2 = new File("gamestore_project/src/gamestore_project/Games.data");
					FileOutputStream f0 = new FileOutputStream(file);
					ObjectOutputStream userOut = new ObjectOutputStream(f0);
					User[] user = GEA.getUserList();
					for (int i = 0; i < GEA.getNoUsers(); i++)
						userOut.writeObject(user[i]);

					FileOutputStream f2 = new FileOutputStream(file2);
					ObjectOutputStream gameOut = new ObjectOutputStream(f2);
					Game[] game = GEA.getGameList();
					for (int i = 0; i < GEA.getNoGames(); i++)
						gameOut.writeObject(game[i]);

					gameOut.close();
					f2.close();
					userOut.close();
					f0.close();
				} catch (IOException e) {
				}
			}
		});

		switchToLoginPage(); // after initializing the frame, we start the login page
	}

	public void switchToMainPanel() { // switches to store's homepage, where all the games are displayed and the user
										// can interact with them
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Icon geaIconSmall = new ImageIcon("gamestore_project/src/gamestore_project/img/crown (2).png");
		JLabel welcomeLabel = new JLabel(" Welcome back! Take a look around GEA store", geaIconSmall,
				SwingConstants.CENTER);
		welcomePanel.add(welcomeLabel);
		mainPanel.add(welcomePanel, BorderLayout.NORTH);
		Icon profileIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/dancing (1).png");
		JButton profileButton = new JButton("My Profile", profileIcon);
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLibrary(); // this button switches the panel to the user's library
			}

		});
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLoginPage(); // this button switches the panel to the login page
			}

		});
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(profileButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);

		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		for (int i = 0; i < GEA.getNoGames(); i++) { // a loop to display all games with their pictures and prices, and
														// buttons to buy or gift them
			final int index = i;

			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

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
			Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			if (gamefile.exists()) { // if the game picture was found, it will be displayed here, otherwise, the
										// default picture (the crown) will appear
				JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
				gamePanel.add(imageLabel, BorderLayout.CENTER);
			} else {
				Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1 .png");
				JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
				gamePanel.add(imageLabel1, BorderLayout.CENTER);
			}

			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

			Icon buyicon = new ImageIcon("gamestore_project/src/gamestore_project/img/payment.png");
			Icon gifticon = new ImageIcon("gamestore_project/src/gamestore_project/img/gift.png");

			// buy button that calls the buy method when clicked
			JButton buyButton = new JButton("", buyicon);

			buyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					(GEA.findUser(user)).buyGame(GEA.getGameList()[index]);
				}

			});

			buttonPanel.add(buyButton);
			// gift button that calls the gift method when clicked

			JButton giftButton = new JButton("", gifticon);
			giftButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Fname = JOptionPane.showInputDialog("Enter your friend's name");
					if (GEA.findUser(Fname) == null) { // if the friend wasn't found, it'll show a pop up
						JOptionPane.showMessageDialog(mainPanel, "SORRY.. COULDN'T FIND USER \"" + Fname + "\"" + "",
								"error", JOptionPane.ERROR_MESSAGE);
					} else {// if the friend was found (in users)
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

			// Add the button panel that contains buy and gift to the bottom of the game
			// panel
			gamePanel.add(buttonPanel, BorderLayout.SOUTH);

			// Add the game and its buttons to the game grid panel
			gameGridPanel.add(gamePanel);
		}

		// Create JScrollPane for the game grid panel
		JScrollPane scrollPane = new JScrollPane(gameGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the game grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^

	}

	public void switchToLibrary() { // switches to user's library, where all the games they have are displayed and
									// they can interact with them
		JPanel mainPanel = new JPanel(new BorderLayout());
		Icon profileIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/boy.png");
		Icon walletIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
		String sWallet = GEA.findUser(user).getWallet() + "";
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel GELABEL11 = new JLabel("User: " + user + "  ", profileIcon, SwingConstants.CENTER);
		JLabel GELABEL22 = new JLabel("Wallet: " + sWallet.substring(0, (sWallet.indexOf('.') + 2)), walletIcon,
				SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                     ",
				SwingConstants.CENTER);
		Icon searchIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/zoom.png");
		JButton searchInLib = new JButton("", searchIcon);
		searchInLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // this button allows the users to search for a certain game in
															// their library
				String in = JOptionPane.showInputDialog("please enter the name of the game you are looking for");
				try {
					if (in == null || GEA.findUser(user).findGame(in) == null) { // if the user didn't enter anything or
																					// the game was not found
						throw new GameNotFoundException(); // user defined exception
					}
				} catch (GameNotFoundException E) {
				}
				switchToSearchView(GEA.findUser(user).findGame(in)); // this displays the game that the user searched
																		// for
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
			public void actionPerformed(ActionEvent e) { // this button makes the user go to the home page
				switchToMainPanel();
			}

		});
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// this button makes the user logout
				switchToLoginPage();
			}

		});
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(homeButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);

		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10));

		for (int i = 0; i < GEA.findUser(user).getNumberOfGames(); i++) {// a loop to display all games in the user's
																			// library, and a button to remove
			final int index = i;

			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

			JLabel gameNameLabel = new JLabel(GEA.findUser(user).getGameLibrary()[index].getName(),
					SwingConstants.CENTER);
			gamePanel.add(gameNameLabel, BorderLayout.NORTH);

			String fullname = GEA.findUser(user).getGameLibrary()[index].getName() + " ";
			int space = fullname.indexOf(" ");
			String cutname = fullname.substring(0, space + 1);
			Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			if (gamefile.exists()) { // if the game picture was found, it will be displayed here, otherwise, the
										// default picture (the crown) will appear
				JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
				gamePanel.add(imageLabel, BorderLayout.CENTER);
			} else {
				Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1 .png");
				JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
				gamePanel.add(imageLabel1, BorderLayout.CENTER);
			}

			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
			Icon delIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");
			JButton removeButton = new JButton("", delIcon);
			JLabel blannk = new JLabel("");
			JLabel blannkk = new JLabel("");

			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // delete button to delete a game in library
					GEA.findUser(user).removeGameFromLibrary(GEA.findUser(user).getGameLibrary()[index].getName());
					switchToLibrary();
				}

			});
			FlowLayout flow = new FlowLayout();
			JPanel square = new JPanel(flow);
			square.add(blannk);
			square.add(removeButton);
			square.add(blannkk);
			// adds a square panel to the button panel to make the button square
			buttonPanel.add(square);
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
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^

	}

	public void switchToSearchView(Game g) { // shows the game the user searched for in their library
		JPanel mainPanel = new JPanel(new BorderLayout());
	 
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
		searchInLib.addActionListener(
		   new ActionListener() {
			  public void actionPerformed(ActionEvent e) { // if the user decided to search again for a game
				 String in = JOptionPane.showInputDialog("please enter the name of the game you are looking for");
				 try {
					if (in == null || GEA.findUser(user).findGame(in) == null) { // if the game was not found or user
																				// didn't enter anything
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
		homeButton.addActionListener(
		   new ActionListener() {
			  public void actionPerformed(ActionEvent e) { // goes back to the home page
				 switchToMainPanel();
			  }
		   
		   });
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(
		   new ActionListener() {
			  public void actionPerformed(ActionEvent e) { // logs out
				 switchToLoginPage();
			  }
		   
		   });
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(homeButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);
	 
		JPanel gameGridPanel = new JPanel(new GridLayout(3, 3, 10, 10)); 
	 
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
	 
		JLabel gameNameLabel = new JLabel(g.getName(), SwingConstants.CENTER);
		gamePanel.add(gameNameLabel, BorderLayout.NORTH);
	 
		String fullname = g.getName() + " ";
		int space = fullname.indexOf(" ");
		String cutname = fullname.substring(0, space + 1);
		Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
		File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
		if (gamefile.exists()) {// if the game picture was found, it will be displayed here, otherwise, the
								// default picture (the crown) will appear
		   JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
		   gamePanel.add(imageLabel, BorderLayout.CENTER);
		} else {
		   Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1 .png");
		   JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
		   gamePanel.add(imageLabel1, BorderLayout.CENTER);
		}
	 
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		Icon delIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");
		JButton removeButton = new JButton("", delIcon);
		JLabel blannk = new JLabel("");
		JLabel blannkk = new JLabel("");

		removeButton.addActionListener(
		   new ActionListener() {
			  public void actionPerformed(ActionEvent e) {// delete button to delete a game in library
				 GEA.findUser(user).removeGameFromLibrary(g.getName());
				 switchToLibrary();
			  }
		   
		   });
	 
		FlowLayout flow = new FlowLayout();
		JPanel square = new JPanel(flow);
		square.add(blannk);
		square.add(removeButton);
		square.add(blannkk);
		 // adds a square panel to the button panel to make the button square
		buttonPanel.add(square);
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
		frame.revalidate();
		frame.repaint();
		 // these methods to make sure that the panel switches and updates correctly ^^
	 }
  
	  public void switchToSearchView(User u) { // shows the user the admin searched for
		JPanel mainPanel = new JPanel(new BorderLayout());

		Icon adminIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/mask.png");
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
		JLabel GELABEL11 = new JLabel("Admin: " + adminName + "  ", adminIcon, SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                                                    ",
				SwingConstants.CENTER);		Icon searchIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/zoom.png");
		JButton searchInStore = new JButton("", searchIcon);
		searchInStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // this button allows the admin to search for a user
				// their library
				String in = JOptionPane.showInputDialog("please enter the name of the user you are looking for");
				try {
					if (in == null || GEA.findUser(in) == null) { // if the admin didn't enter anything or
						// the user was not found
						throw new UserNotFoundException(); // user defined exception
					}
				} catch (UserNotFoundException E) {
				}
				switchToSearchView(GEA.findUser(in)); // this displays the user that the admin searched for
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL33);
		titlePanel.add(searchInStore);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon gamesIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/games.png");
		JButton gameButton = new JButton("Games", gamesIcon);
		gameButton.addActionListener(new ActionListener() {// to switch to the games pages
			public void actionPerformed(ActionEvent e) {
				switchToAdminPanel1();
			}

		});
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(new ActionListener() {// log out for the admin
			public void actionPerformed(ActionEvent e) {
				switchToLoginPage();
			}

		});
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(gameButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);

		JPanel userGridPanel = new JPanel(new GridLayout(3, 3, 10, 10));

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());


		Icon walletIconn = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
		String wallet = "" + u.getWallet();
		JLabel usernameLabel = new JLabel(u.getUsername() + " "
				+ wallet.substring(0, (wallet.indexOf('.') + 2)) + " SAR", walletIconn, SwingConstants.CENTER);
		usernameLabel.setHorizontalTextPosition(JLabel.LEFT);
		userPanel.add(usernameLabel, BorderLayout.NORTH);

		Icon userpic = new ImageIcon("gamestore_project/src/gamestore_project/img/bigboy.png");
		JLabel imageLabel = new JLabel(userpic, SwingConstants.CENTER);
		userPanel.add(imageLabel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

		Icon removeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");

		JButton removeButton = new JButton("", removeIcon);
		JLabel blannk = new JLabel("");
		JLabel blannkk = new JLabel("");

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // this button allows the admin to remove a user's account
															// from the store
				GEA.removeUser(u.getUsername());
				switchToAdminPanel2();
			}

		});


		FlowLayout flow = new FlowLayout();
		JPanel square = new JPanel(flow);
		square.add(blannk);
		square.add(removeButton);
		square.add(blannkk);
		// adds a square panel to the button panel to make the button square
		buttonPanel.add(square);
		// Add the button panel to the bottom of the user panel
		userPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add the user to the user grid panel
		userGridPanel.add(userPanel);

		// Create JScrollPane for the user grid panel
		JScrollPane scrollPane = new JScrollPane(userGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the user grid panel with scroll pane to the center of the main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Set up frame's content pane
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^
	}

	public void switchToAdminPanel1() { // switches to the admin home page where they can manage the store's games
		JPanel mainPanel = new JPanel(new BorderLayout());
		Icon adminIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/mask.png");
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
		JLabel GELABEL11 = new JLabel("Admin: " + adminName + "  ", adminIcon, SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                                                    ",
				SwingConstants.CENTER);
		Icon addIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/add.png");

		JButton addGame = new JButton("", addIcon);
		addGame.addActionListener(new ActionListener() { // this button adds a game to the store
			public void actionPerformed(ActionEvent e) {
				String inGame = JOptionPane
						.showInputDialog("please enter the name of the game you want to add to the store");

				try { // checks if the game already exists in the store and validates the price input
					if (GEA.searchForGame(inGame) != null) {
						throw new GameAlreadyExistsException(); // user defined exception
					} else if (inGame == null || inGame.isEmpty()) {
						JOptionPane.showMessageDialog(null, "please enter the game's name", "Oh no :(", 0);
					} else {
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

						JComboBox<String> comboBox = new JComboBox<>(genres); // this is a drop down list containing the
																				// genres in order to initialize the new
																				// game

						comboBox.setBounds(196, 209, 153, 27);
						JLabel lblGenre = new JLabel("Genre:");
						lblGenre.setBounds(138, 213, 47, 16);

						myPanel.add(lblGenre);
						myPanel.add(comboBox);

						int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Game Information",
								JOptionPane.OK_CANCEL_OPTION);
						Game newGame;

						if (result == JOptionPane.OK_OPTION) {
							String genre = (String) comboBox.getSelectedItem();// this line stores the user's genre
																				// choice in a string
							try { // this block validates the price and adds the game according to its genre to
									// the store
								double price = Double.parseDouble(priceField.getText());
								if (price < 0) {
									JOptionPane.showMessageDialog(null, "please enter a positive number", "error",
											JOptionPane.ERROR_MESSAGE);
								} else {
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
												JOptionPane.ERROR_MESSAGE); // if admin didn't choose a genre
									}
								}
							} catch (NumberFormatException e3) {
								JOptionPane.showMessageDialog(null, "Invalid price, please add a positive number",
										"Error", JOptionPane.ERROR_MESSAGE);
							} catch (NullPointerException e4) {
								JOptionPane.showMessageDialog(null, "invalid input", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				} catch (GameAlreadyExistsException E1) {
				}
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL33);
		titlePanel.add(addGame);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon userIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/users.png");
		JButton usersButton = new JButton("Users", userIcon);
		usersButton.addActionListener(new ActionListener() { // this button switches to the page that contains all users
			public void actionPerformed(ActionEvent e) {
				switchToAdminPanel2();
			}

		});
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // log out for admin
				switchToLoginPage();
			}

		});
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(usersButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);

		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		for (int i = 0; i < GEA.getNoGames(); i++) {
			final int index = i;

			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

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
			Icon gamepic = new ImageIcon("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			File gamefile = new File("gamestore_project/src/gamestore_project/img/" + cutname + ".png");
			if (gamefile.exists()) {// if the game picture was found, it will be displayed here, otherwise, the
									// default picture (the crown) will appear
				JLabel imageLabel = new JLabel(gamepic, SwingConstants.CENTER);
				gamePanel.add(imageLabel, BorderLayout.CENTER);
			} else {
				Icon defaultpic = new ImageIcon("gamestore_project/src/gamestore_project/img/Untitled-1 .png");
				JLabel imageLabel1 = new JLabel(defaultpic, SwingConstants.CENTER);
				gamePanel.add(imageLabel1, BorderLayout.CENTER);
			}

			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

			Icon removeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");
			JLabel blannk = new JLabel("");
			JLabel blannkk = new JLabel("");

			JButton removeButton = new JButton("", removeIcon);

			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // this button allows the admin to remove a game from the
																// store
					GEA.removeGame(GEA.getGameList()[index].getName());
					switchToAdminPanel1();
				}

			});
			FlowLayout flow = new FlowLayout();
			JPanel square = new JPanel(flow);
			square.add(blannk);
			square.add(removeButton);
			square.add(blannkk);
			// adds a square panel to the button panel to make the button square

			buttonPanel.add(square);

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
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^
	}

	public void switchToAdminPanel2() {// switches to the admin users page where they can manage the store's users
										// account
		JPanel mainPanel = new JPanel(new BorderLayout());
		Icon adminIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/mask.png");
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
		JLabel GELABEL11 = new JLabel("Admin: " + adminName + "  ", adminIcon, SwingConstants.CENTER);
		JLabel GELABEL33 = new JLabel(
				"                                                                                                                                                                   ",
				SwingConstants.CENTER);

		Icon searchIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/zoom.png");
		JButton searchInStore = new JButton("", searchIcon);
		searchInStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // this button allows the users to search for a certain game in
															// their library
				String in = JOptionPane.showInputDialog("please enter the name of the user you are looking for");
				try {
					if (in == null || GEA.findUser(in) == null) { // if the admin didn't enter anything or
																	// the user was not found
						throw new UserNotFoundException(); // user defined exception
					}
				} catch (UserNotFoundException E) {
				}
				switchToSearchView(GEA.findUser(in)); // this displays the user that the user searched
														// for
			}
		});

		Icon addIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/add.png");

		JButton addUser = new JButton("", addIcon);
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // this button adds a new user to the store
				String inUser = JOptionPane
						.showInputDialog("please enter the name of the user you want to add to the store");

				try { // checks if the user already exists in the store and validates the wallet input
					if (GEA.findUser(inUser) != null) {
						throw new UserAlreadyExistsException(); // user defined exception
					} else if (inUser == null || inUser.isEmpty()) {// if the admin didn't enter anything
						JOptionPane.showMessageDialog(null, "please enter the user's name", "Oh no :(", 0);
					} else { // validates the wallet
						String walletamt = JOptionPane.showInputDialog("Enter the user's wallet amount");
						double walletamtt = Double.parseDouble(walletamt);
						if (walletamtt < 0) {
							JOptionPane.showMessageDialog(null, "please enter a positive number", "error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							User u = new User(inUser, walletamtt);
							GEA.addUser(u);
							switchToAdminPanel2();
							JOptionPane.showMessageDialog(null, "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
						}

					}
				} catch (UserAlreadyExistsException E1) {
				} catch (NumberFormatException E2) {
					JOptionPane.showMessageDialog(null, "please enter a number", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		titlePanel.add(GELABEL11);
		titlePanel.add(GELABEL33);
		titlePanel.add(addUser);
		titlePanel.add(searchInStore);

		mainPanel.add(titlePanel, BorderLayout.NORTH);

		Icon gamesIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/games.png");
		JButton gameButton = new JButton("Games", gamesIcon);
		gameButton.addActionListener(new ActionListener() {// to switch to the games pages
			public void actionPerformed(ActionEvent e) {
				switchToAdminPanel1();
			}

		});
		Icon logoutIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/angel.png");
		JButton logoutButton = new JButton("LOGOUT", logoutIcon);
		logoutButton.addActionListener(new ActionListener() {// log out for the admin
			public void actionPerformed(ActionEvent e) {
				switchToLoginPage();
			}

		});
		GridLayout grd = new GridLayout(2, 0, 2, 2);
		JPanel btns = new JPanel(grd);
		btns.add(gameButton);
		btns.add(logoutButton);
		mainPanel.add(btns, BorderLayout.EAST);

		JPanel userGridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		for (int i = 0; i < GEA.getNoUsers(); i++) { // displays the users of the store and their information
			final int index = i;

			JPanel userPanel = new JPanel();
			userPanel.setLayout(new BorderLayout());

			Icon walletIconn = new ImageIcon("gamestore_project/src/gamestore_project/img/money-bag (1).png");
			String wallet = "" + GEA.getUserList()[index].getWallet();
			JLabel usernameLabel = new JLabel(GEA.getUserList()[index].getUsername() + " "
					+ wallet.substring(0, (wallet.indexOf('.') + 2)) + " SAR", walletIconn, SwingConstants.CENTER);
			usernameLabel.setHorizontalTextPosition(JLabel.LEFT);
			userPanel.add(usernameLabel, BorderLayout.NORTH);

			Icon userpic = new ImageIcon("gamestore_project/src/gamestore_project/img/bigboy.png");
			JLabel imageLabel = new JLabel(userpic, SwingConstants.CENTER);
			userPanel.add(imageLabel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

			Icon removeIcon = new ImageIcon("gamestore_project/src/gamestore_project/img/cancel.png");

			JButton removeButton = new JButton("", removeIcon);
			JLabel blannk = new JLabel("");
			JLabel blannkk = new JLabel("");

			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // this button allows the admin to remove a user's account
																// from the store
					GEA.removeUser(GEA.getUserList()[index].getUsername());
					switchToAdminPanel2();
				}

			});
			FlowLayout flow = new FlowLayout();
			JPanel square = new JPanel(flow);
			square.add(blannk);
			square.add(removeButton);
			square.add(blannkk);
			// adds a square panel to the button panel to make the button square
			buttonPanel.add(square);
			userPanel.add(buttonPanel, BorderLayout.SOUTH);

			userGridPanel.add(userPanel);
		}

		JScrollPane scrollPane = new JScrollPane(userGridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		mainPanel.add(scrollPane, BorderLayout.CENTER);

		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^
	}

	public void switchToLoginPage() { // this method switches to the login page, and it is called in the beginning and
										// when the user presses logout
										// it contains a panel that contains labels and 2 buttons user and admin
		SpringLayout springLayout = new SpringLayout();
		JPanel panel = new JPanel(springLayout);
		frame.add(panel);
		JLabel label_4 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label_4, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_4, 0, SpringLayout.WEST, frame.getContentPane());
		panel.add(label_4);

		JLabel label_2_1 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label_2_1, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_2_1, 0, SpringLayout.WEST, frame.getContentPane());
		panel.add(label_2_1);

		JLabel label_1_1 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label_1_1, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1_1, 0, SpringLayout.WEST, frame.getContentPane());
		panel.add(label_1_1);

		JLabel crownlbl = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, crownlbl, 88, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, crownlbl, 406, SpringLayout.WEST, frame.getContentPane());
		crownlbl.setIcon(new ImageIcon("gamestore_project/src/gamestore_project/img/crown.png"));
		panel.add(crownlbl);

		JLabel GEEAA = new JLabel("GEA GAMESTORE");
		springLayout.putConstraint(SpringLayout.NORTH, GEEAA, 236, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, GEEAA, 337, SpringLayout.WEST, frame.getContentPane());
		GEEAA.setHorizontalAlignment(SwingConstants.CENTER);
		GEEAA.setFont(new Font("Lato", Font.PLAIN, 32));
		panel.add(GEEAA);

		JLabel welcomemsg = new JLabel("Welcome to GEA gamestore");
		springLayout.putConstraint(SpringLayout.NORTH, welcomemsg, 280, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, welcomemsg, 337, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, welcomemsg, 602, SpringLayout.WEST, frame.getContentPane());
		welcomemsg.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(welcomemsg);

		JLabel ask = new JLabel("do you want to login as...");
		springLayout.putConstraint(SpringLayout.NORTH, ask, 301, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ask, 337, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ask, 602, SpringLayout.WEST, frame.getContentPane());
		ask.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ask);
		JButton userButton = new JButton("USER");

		userButton.addActionListener(new ActionListener() { // when the user button is clicked a pop up appears to let
															// the user enter the username, then checks if the
															// user already exists, and shows a message accordingly. the
															// user then will enter the shop or create a new account.
			public void actionPerformed(ActionEvent e) { // open the frame for user login
				JFrame frame2 = new JFrame();
				frame2.setResizable(false);
				frame2.getContentPane().setBackground(UIManager.getColor("window"));
				SpringLayout springLayout = new SpringLayout();
				frame2.getContentPane().setLayout(springLayout);

				JLabel msg = new JLabel("Hello user, please enter your information");
				springLayout.putConstraint(SpringLayout.NORTH, msg, 74, SpringLayout.NORTH, frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, msg, 90, SpringLayout.NORTH, frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, msg, -102, SpringLayout.EAST, frame2.getContentPane());
				msg.setForeground(UIManager.getColor("textText"));
				frame2.getContentPane().add(msg);

				JLabel adminLabel = new JLabel("username:");
				springLayout.putConstraint(SpringLayout.NORTH, adminLabel, -130, SpringLayout.SOUTH,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, adminLabel, 94, SpringLayout.WEST,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, adminLabel, -114, SpringLayout.SOUTH,
						frame2.getContentPane());
				adminLabel.setForeground(UIManager.getColor("textText"));
				frame2.getContentPane().add(adminLabel);
				JTextField userfield = new JTextField();

				adminLabel.setLabelFor(userfield);

				springLayout.putConstraint(SpringLayout.EAST, adminLabel, -12, SpringLayout.WEST, userfield);
				springLayout.putConstraint(SpringLayout.EAST, userfield, -172, SpringLayout.EAST,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, userfield, 171, SpringLayout.WEST,
						frame2.getContentPane());
				frame2.getContentPane().add(userfield);
				userfield.setColumns(10);

				JButton loginButton = new JButton("login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = userfield.getText();
						user = username;
						if (GEA.findUser(username) != null) {
							JOptionPane.showMessageDialog(frame2, "welcome", "login message", 1);
							System.out.println("success");
							frame2.dispose();
							switchToMainPanel(); // here the panel will switch to the shop if the login was successful
						} else {
							Icon f8mh = new ImageIcon("gamestore_project/src/gamestore_project/img/walrus.png");
							int result = JOptionPane.showOptionDialog(frame2,
									"this account doesn't exist, do you want to create a new account", "oops",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, f8mh, null, null); // asks
																												// the
																												// user
																												// to
																												// create
																												// a new
																												// account
																												// if
																												// the
																												// name
																												// wasn't
																												// found
							if (result == JOptionPane.NO_OPTION) {
								JOptionPane.getRootFrame().dispose();
								frame2.setVisible(false);
							}

							else if (result == JOptionPane.YES_OPTION) { // if the user chose to create a new account
								String amount = JOptionPane.showInputDialog(null, "enter your wallet amount",
										"register", JOptionPane.PLAIN_MESSAGE);
								double wlt = 0;
								try { // validation for wallet amount
									wlt = Double.parseDouble(amount);
									if (wlt < 0) {
										JOptionPane.showMessageDialog(frame2, "please enter a positive number", "error",
												JOptionPane.ERROR_MESSAGE);
									} else { // adds the new user to the store
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

				springLayout.putConstraint(SpringLayout.NORTH, userfield, -41, SpringLayout.NORTH, loginButton);
				springLayout.putConstraint(SpringLayout.SOUTH, userfield, -15, SpringLayout.NORTH, loginButton);
				springLayout.putConstraint(SpringLayout.NORTH, loginButton, -94, SpringLayout.SOUTH,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, loginButton, -197, SpringLayout.EAST,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, loginButton, -65, SpringLayout.SOUTH,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, loginButton, 196, SpringLayout.WEST,
						frame2.getContentPane());
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}

				});
				frame2.getContentPane().add(loginButton);

				JLabel blank = new JLabel("");
				springLayout.putConstraint(SpringLayout.WEST, blank, 25, SpringLayout.WEST, frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, blank, -384, SpringLayout.EAST, frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, msg, 12, SpringLayout.EAST, blank);
				springLayout.putConstraint(SpringLayout.NORTH, blank, -205, SpringLayout.SOUTH,
						frame2.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, blank, -141, SpringLayout.SOUTH,
						frame2.getContentPane());
				blank.setIcon(new ImageIcon("gamestore_project/src/gamestore_project/img/disco-ball.png"));
				frame2.getContentPane().add(blank);
				frame2.setBackground(new Color(45, 45, 45));
				frame2.setBounds(450, 300, 473, 281);
				frame2.setVisible(true);

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, userButton, 366, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, userButton, 337, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, userButton, 418, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, userButton, 602, SpringLayout.WEST, frame.getContentPane());

		panel.add(userButton);

		JButton adminButton = new JButton("ADMIN");// when the admin button is clicked a pop up appears to let the admin
													// enter the username and password, then checks if the
													// information was correct, and shows a message accordingly. the
													// admin then will enter the admin panel or the pop up will close.
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// opens the frame for admin login
				JFrame frame3 = new JFrame();
				frame3.setResizable(false);
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
				loginButton.addActionListener(new ActionListener() {// checks if the admin exists and checks the
																	// username and the password
					public void actionPerformed(ActionEvent e) {
						adminName = userfield.getText();
						String password = String.valueOf(passwordField.getPassword());
						if (GEA.adminExists(adminName, password)) {
							System.out.println("success");
							frame3.setVisible(false);
							switchToAdminPanel1(); // switch to admin panel (games panel)
						}
					}
				});
				frame3.getContentPane().add(loginButton);

				JLabel blank = new JLabel("");
				springLayout.putConstraint(SpringLayout.EAST, blank, -433, SpringLayout.EAST, frame3.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, msg, 25, SpringLayout.EAST, blank);
				springLayout.putConstraint(SpringLayout.NORTH, blank, 48, SpringLayout.NORTH, frame3.getContentPane());
				blank.setIcon(new ImageIcon("gamestore_project/src/gamestore_project/img/disco-ball.png"));
				frame3.getContentPane().add(blank);
				frame3.setBackground(new Color(45, 45, 45));
				frame3.setBounds(450, 300, 546, 351);
				frame3.setVisible(true);

			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, adminButton, 430, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, adminButton, 337, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, adminButton, 482, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, adminButton, 602, SpringLayout.WEST, frame.getContentPane());
		panel.add(adminButton);

		JLabel label_9 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label_9, 724, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_9, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_9, 791, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_9, 325, SpringLayout.WEST, frame.getContentPane());
		panel.add(label_9);

		JLabel label_10 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label_10, 796, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_10, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_10, 873, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_10, 325, SpringLayout.WEST, frame.getContentPane());
		panel.add(label_10);

		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		// these methods to make sure that the panel switches and updates correctly ^^

	}

}