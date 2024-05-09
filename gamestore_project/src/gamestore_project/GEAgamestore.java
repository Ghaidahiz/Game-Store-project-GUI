package gamestore_project;
//img
//library
//profile
//admin
//ipad notes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JFrame frame;
	protected Object frame2;

	/**
	 * Launch the application.
	 */
	Scanner sc = new Scanner(System.in);
            
	static Admin Almaha = new Admin("Almaha", "123");      // setting up  
	static Admin Eman = new Admin("Eman", "321");         // the admins
	static  Admin Ghaida = new Admin("Ghaida", "213");     // of the store

        static GameStore GEA = new GameStore("GEA", Almaha, Eman, Ghaida, 200, 100); //creating the store
    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {

        
        File file = new File("gamestore_project/src/gamestore_project/Users.data");
        FileInputStream f1 = new FileInputStream(file);
        ObjectInputStream userIn = new ObjectInputStream(f1);
     
        File file2 = new File("gamestore_project/src/gamestore_project/Games.data");
       FileInputStream f3 = new FileInputStream(file2);
       ObjectInputStream gameIn = new ObjectInputStream(f3);

         try{
            while (true) {
                GEA.addUser(((User)userIn.readObject()));
            }
        }
        catch(EOFException e ){} 
        try{
            while (true) {
                GEA.addGame(((Game)gameIn.readObject()));
            }
        }
        catch(EOFException e ){} 
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
		frame.setBounds(250, 160, 840, 550);
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

		JLabel label_2_1 = new JLabel("");
		GridBagConstraints gbc_label_2_1 = new GridBagConstraints();
		gbc_label_2_1.fill = GridBagConstraints.BOTH;
		gbc_label_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_2_1.gridx = 0;
		gbc_label_2_1.gridy = 2;
		frame.getContentPane().add(label_2_1, gbc_label_2_1);

		JLabel lblNewLabel_3 = new JLabel("GEA GAMESTORE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Lato", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

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
						if (GEA.findUser(username) != null)  {
							JOptionPane.showMessageDialog(frame2, "welcome", "login message", 1);
							System.out.println("success");
							frame2.dispose();
							switchToMainPanel();
						} else {
							JOptionPane.showMessageDialog(frame2, "incorrect username", "Title", 1);

							System.out.println("naaay");}

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
		JLabel welcomeLabel = new JLabel("Welcome back! Take a look around GEA store", SwingConstants.CENTER);
		welcomeLabel.setForeground(UIManager.getColor("textText"));
		mainPanel.add(welcomeLabel, BorderLayout.NORTH);
		//JLabel wallet = new JLabel("Wallet amount: "+ GEA.findUser(user).getWallet(), SwingConstants.CENTER);
		//mainPanel.add(wallet, BorderLayout.SOUTH);

	
		// Create a panel for the game grid
		JPanel gameGridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, variable rows, and 10 pixels gap
		for (int i = 0; i < GEA.getNoGames(); i++) {
			final int index = i; // Capture the value of i in a final variable
	
			// Create a panel for each game
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
	
			// Add label for the game name at the top
			JLabel gameNameLabel = new JLabel(GEA.getGameList()[index].getName(), SwingConstants.CENTER);
			gamePanel.add(gameNameLabel, BorderLayout.NORTH);
	
			try {
				// Load and add the image icon to the center
				String fullname = GEA.getGameList()[index].getName()+" ";
				int space = fullname.indexOf(" ");
				String cutname = fullname.substring(0,space+1);
				BufferedImage image = ImageIO.read(new File("gamestore_project\\src\\gamestore_project\\img\\"+cutname+".jpg"));
				JLabel imageLabel = new JLabel(new ImageIcon(image), SwingConstants.CENTER);
				gamePanel.add(imageLabel, BorderLayout.CENTER);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			// Create a panel for buy and gift buttons
			JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 row, 2 columns, and 5 pixels gap
	
			// Add "Buy" button
			JButton buyButton = new JButton("Buy");
			buyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					(GEA.findUser(user)).buyGame(GEA.getGameList()[index]);
				}
				
			});

			buttonPanel.add(buyButton);

	
			// Add "Gift" button
			JButton giftButton = new JButton("Gift");
			giftButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Fname =JOptionPane.showInputDialog("Enter your friend's name");
					if (GEA.findUser(Fname) == null) { //if the friend wasn't found, it'll print a message
					JOptionPane.showMessageDialog(mainPanel, "SORRY.. COULDN'T FIND USER \"" + Fname + "\""+"", "error", 1);
					//System.out.println("SORRY.. COULDN'T FIND USER \"" + Fname + "\"");
				} else {// if the friend was found (in users), the games of the store will be displayed, then the user will be asked to enter a game to send
					GEA.findUser(user).sendGift(GEA.findUser(Fname),GEA.getGameList()[index] ); //the game will be bought and sent to the friend library if it exists in the store (see methods)
				
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
		JScrollPane scrollPane = new JScrollPane(gameGridPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
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
