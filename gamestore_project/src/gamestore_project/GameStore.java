package gamestore_project;


import java.util.*;

import javax.swing.JOptionPane;

public class GameStore {
   // GameStore is the fundamental class that deals with Games, Users, and Admins,
   // and handles the store's operations
   Scanner sc = new Scanner(System.in);

   private String name; 
   private User[] userList; 
   private Game[] gameList; 
   private Admin[] adminList = new Admin[3];
   private int noUsers;
   private int noGames;

   public GameStore(String name, Admin a1, Admin a2, Admin a3, int Usize, int Gsize) { //parametrized constructor that initialises the attributes
      this.name = name;
      adminList[0] = a1;
      adminList[1] = a2;
      adminList[2] = a3;
      noUsers = 0;
      noGames = 0;
      userList = new User[Usize];
      gameList = new Game[Gsize];
   }

   public boolean userExists(String user) {  //checks if there is a user with the received username 
      for (int i = 0; i < noUsers; i++) {

         if (userList[i].getUsername().equalsIgnoreCase(user)) { // to check the uniqueness of username
            System.out.println("THE USERNAME ALREADY EXISTS");
            return true;
         }
      }
      return false;
   }

   public boolean addUser(User u) {      //this method adds a user to the userlist if and only if:
      if (!userExists(u.getUsername())) {// 1- the username doesn't already exist in the gamestore
         if (noUsers < userList.length) {// 2- the userlist is not full
            userList[noUsers++] = u;
            return true;
         } else
            System.out.println("CAN NOT ADD A NEW USER - THE STORE HAS EXCEEDED THE LIMIT - :( ");
         return false;
      } else {
         System.out.println("THE USERNAME ALREADY EXISTS");
         return false;
      }
   }

   public void removeUser(String username) {//this method removes a user from the userlist if and only if:
      boolean exist = false;                 //the user with the given name exists in the userlist
      int i;                                 
      for (i = 0; i < noUsers; i++)
         if (userList[i].getUsername().equalsIgnoreCase(username)) {
            exist = true;
            break;
         }
      if (exist) {
         userList[i] = userList[noUsers - 1]; //remove the user by replacing with the last user in userlist
         userList[noUsers - 1] = null; 
         noUsers--;
         System.out.println("THE USER \"" + username + "\" WAS REMOVED SUCCESSFULLY");
      } else
         System.out.println("FAILED TO REMOVE THE USER, CAN NOT FIND A USER WITH THIS NAME: \"" + username + "\"");
   }

   public User findUser(String username) {//this method returns a user if it was found in the userlist
      for (int i = 0; i < noUsers; i++)
         if (userList[i].getUsername().equalsIgnoreCase(username))
            return userList[i];

      return null;

   }

   public boolean adminExists(String name, String password) {//this method verifies admin's username and password, and allows the user to go back to the main menu 
   boolean adminExist = false; 
   int i; 
         for (i = 0; i < adminList.length; i++)
            if (adminList[i].getUsername().equalsIgnoreCase(name)) { //if the entered username exists in adminlist, then the admin exists
               adminExist = true;
               break;
            }
         if (!adminExist) { //if not, it will print a message
            System.out.println(" INCORRECT USERNAME, PLEASE TRY AGAIN");
            JOptionPane.showMessageDialog(null, "INCORRECT USERNAME, PLEASE TRY AGAIN", "oh no!", 1);
            return false;

         } else { //this will check the password after verifying that the admin exists

            if (adminList[i].getPassword().equals(password)){
               JOptionPane.showMessageDialog(null, "WELCOME BACK ADMIN " + name.toUpperCase(), "welcome!", 1);
               return true; //this will stop the verification loop
            }
            else {
               System.out.println("INCORRECT PASSWORD");
               JOptionPane.showMessageDialog(null, "INCORRECT PASSWORD, PLEASE TRY AGAIN", "oh no!", 1);
                 return false;
            }
         }

}
   public boolean gameExists(String name) { //check if game exists in the store
      for (int i = 0; i < noGames; i++) {
         if (gameList[i].getName().equalsIgnoreCase(name))
            return true;
      }
      return false;

   }

   public boolean addGame(Game g){ 
                                     //adds a game and casts it to its genre if 
                                    //the gamelist wasn't full
      if (noGames < gameList.length) {
         if (g instanceof Detective) {
            gameList[noGames++] = new Detective(g); 
         }

         else if (g instanceof RPG) {
            gameList[noGames++] = new RPG(g);
         }

         else if (g instanceof StoryRich) {
            gameList[noGames++] = new StoryRich(g);
         }

         else if (g instanceof Horror) {
            gameList[noGames++] = new Horror(g);
         }

         else if (g instanceof Survival) {
            gameList[noGames++] = new Survival(g);
         } 
         else {
            return false; // not one of the genres "impossible, but we have to cover all cases"
         }
         return true;
      } else {
         System.out.println("CAN NOT ADD A NEW GAME, THE STORE IS FULL :( ");
         return false;
      }

   }

   public void removeGame(String name) { //this method removes a game from the store
      boolean exist = false;
      int i;
      for (i = 0; i < noGames; i++) //check if game exists
         if (gameList[i].getName().equalsIgnoreCase(name)) {
            exist = true;
            break;
         }
      if (exist) { //if it exists, remove it 
         gameList[i] = gameList[noGames - 1];
         gameList[noGames - 1] = null;
         noGames--;
         System.out.println("THE GAME \"" + name + "\" WAS REMOVED SUCCESSFULLY");
         JOptionPane.showMessageDialog(null, "THE GAME \"" + name + "\" WAS REMOVED SUCCESSFULLY", "Success!", JOptionPane.INFORMATION_MESSAGE);

      } else{
         System.out.println("\nFAILED TO REMOVE THE GAME, CAN NOT FIND A GAME WITH THIS NAME: \"" + name + "\"");
         JOptionPane.showMessageDialog(null, "FAILED TO REMOVE THE GAME, CAN NOT FIND A GAME WITH THIS NAME:" + name  , "Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   public Game searchForGame(String name) { //this method returns the game that has the recieved name

      for (int i = 0; i < noGames; i++)
         if (gameList[i].getName().equalsIgnoreCase(name))
            return gameList[i];

      System.out.println("\nCAN NOT FIND A GAME WITH THIS NAME: \"" + name + "\" :(");
      return null;

   }
   //getters
   public User[] getUserList(){
      return userList;
   }

   public Game[] getGameList() {
      return gameList;
   }

   public int getNoGames() {
      return noGames;
   }

   public int getNoUsers() {
      return noUsers;
   }
   //these methods return a gamelist of the specified genre
   public Game[] getRPG() {
      int RPGcount = 0, index = 0;
      for (int i = 0; i < noGames; i++) {
         if (gameList[i] instanceof RPG)
            RPGcount++;
      }
      if (RPGcount == 0) {
         System.out.println("THERE IS NO GAMES OF THIS GENRE IN THIS GAMESTORE :(");
      }
      Game[] RPGgame = new Game[RPGcount];
      for (int i = 0; i < noGames; i++)
         if (gameList[i] instanceof RPG)
            RPGgame[index++] = gameList[i];
      return RPGgame;
   }

   public Game[] getStoryRich() {
      int SRcount = 0, index = 0;
      for (int i = 0; i < noGames; i++) {
         if (gameList[i] instanceof StoryRich)
            SRcount++;
      }
      if (SRcount == 0) {
         System.out.println("THERE IS NO GAMES OF THIS GENRE IN THIS GAMESTORE :(");
      }
      Game[] SRgame = new Game[SRcount];
      for (int i = 0; i < noGames; i++)
         if (gameList[i] instanceof StoryRich)
            SRgame[index++] = gameList[i];
      return SRgame;
   }

   public Game[] getHorror() {
      int HRRcount = 0, index = 0;
      for (int i = 0; i < noGames; i++) {
         if (gameList[i] instanceof Horror)
            HRRcount++;
      }
      if (HRRcount == 0) {
         System.out.println("THERE IS NO GAMES OF THIS GENRE IN THIS GAMESTORE :(");
      }
      Game[] HRRgame = new Game[HRRcount];
      for (int i = 0; i < noGames; i++)
         if (gameList[i] instanceof Horror)
            HRRgame[index++] = gameList[i];
      return HRRgame;
   }

   public Game[] getDetective() {
      int DETcount = 0, index = 0;
      for (int i = 0; i < noGames; i++) {
         if (gameList[i] instanceof Detective)
            DETcount++;
      }
      if (DETcount == 0) {
         System.out.println("THERE IS NO GAMES OF THIS GENRE IN THIS GAMESTORE :(");
      }
      Game[] DETgame = new Game[DETcount];
      for (int i = 0; i < noGames; i++)
         if (gameList[i] instanceof Detective)
            DETgame[index++] = gameList[i];
      return DETgame;
   }

   public Game[] getSurvival() {
      int SURcount = 0, index = 0;
      for (int i = 0; i < noGames; i++) {
         if (gameList[i] instanceof Survival)
            SURcount++;
      }
      if (SURcount == 0) {
         System.out.println("THERE IS NO GAMES OF THIS GENRE IN THIS GAMESTORE :(");
      }
      Game[] SURgame = new Game[SURcount];
      for (int i = 0; i < noGames; i++)
         if (gameList[i] instanceof Survival)
            SURgame[index++] = gameList[i];
      return SURgame;
   }

   public Admin[] getAdminList() {
      return adminList;
   }

   public void displayAllGames() { //this method displays all games in gamestore
      System.out.println("\nTHESE ARE ALL THE GAMES IN " + name + " STORE:\n-------------- \n");
      for (int i = 0; i < noGames; i++)
         System.out.println(gameList[i]);

   }

   public void displayGames() { //this method allows the user to display all games or games of a specific genre
      System.out.println("CHOOSE WHAT DO YOU WANT TO DISPLAY?");
      int selection = 0;
      do {
         try {
            System.out.println(
                  " ~~~~~~~~~~~~~~~~~~~~~~ \n 1-ALL GAMES. \n 2-RPG GAMES. \n 3-DETECTIVE GAMES. \n 4-HORROR GAMES. \n 5-STORY RICH GAMES. \n 6-SURVIVAL GAMES. ");
            selection = sc.nextInt();
            sc.nextLine();

            if (selection < 1 || selection > 6) {
               System.out.println("INVALID INPUT , PLEASE ENTER A NUMBER BETWEEN 1 AND 6 ");
            }
         } catch (InputMismatchException e) {
            System.out.println("PLEASE ENTER A DIGIT");
            sc.next();
            selection = -1; // Set selection to -1 to continue loop
         }
      } while (selection < 1 || selection > 6);

      switch (selection) {
      case 1:
         displayAllGames();
         break;
      case 2:
         System.out.println("\nTHESE ARE ALL THE GAMES OF TYPE RPG IN " + name + " STORE:\n-------------- \n");
         Game[] RPGGame = getRPG();
         for (int i = 0; i < RPGGame.length; i++)
            System.out.println(RPGGame[i]);
         break;

      case 3:
         System.out.println("\nTHESE ARE ALL THE GAMES OF TYPE DETECTIVE IN " + name + " STORE:\n-------------- \n");
         Game[] DetectiveGame = getDetective();
         for (int i = 0; i < DetectiveGame.length; i++)
            System.out.println(DetectiveGame[i]);
         break;

      case 4:
         System.out.println("\nTHESE ARE ALL THE GAMES OF TYPE HORROR IN " + name + " STORE:\n-------------- \n");
         Game[] HorrorGame = getHorror();
         for (int i = 0; i < HorrorGame.length; i++)
            System.out.println(HorrorGame[i]);
         break;

      case 5:
         System.out.println("\nTHESE ARE ALL THE GAMES OF TYPE STORY-RICH IN " + name + " STORE:\n-------------- \n");
         Game[] SRGame = getStoryRich();
         for (int i = 0; i < SRGame.length; i++)
            System.out.println(SRGame[i]);
         break;

      case 6:
         System.out.println("\nTHESE ARE ALL THE GAMES OF TYPE SURVIVAL IN " + name + " STORE:\n-------------- \n");
         Game[] SurvivalGame = getSurvival();
         for (int i = 0; i < SurvivalGame.length; i++)
            System.out.println(SurvivalGame[i]);
         break;
      }
   }

   public void displayAllUsers() { //this method displays all users of gamestore
      for (int i = 0; i < noUsers; i++) {
         System.out.println("\n " + userList[i].toString());

      }

   }
}
