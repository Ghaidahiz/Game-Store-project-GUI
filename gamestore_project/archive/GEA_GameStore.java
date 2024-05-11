/*

GEA_GameStore program implements a GameStore called GEA that 
has some existing users "clients", and has the ability to add new ones. 
it has admins who manage the store e.g.(add, remove, and search for any user or game..etc), 
and it contains a lot of games of different genres that 
the user can interact with e.g.(buy, remove, send as a gift..etc).

Authors:
 Almaha Alaiban - 444200477 
 Eman Qaed - 444200073 
 Ghaida Alzaidan - 444201103 
*/

package gamestore_project;
import java.io.*;
import java.util.*;
public class GEA_GameStore {

    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {

        Scanner sc = new Scanner(System.in);
            
        Admin Almaha = new Admin("Almaha", "123");      // setting up  
        Admin Eman = new Admin("Eman", "321");         // the admins
        Admin Ghaida = new Admin("Ghaida", "213");     // of the store

        GameStore GEA = new GameStore("GEA", Almaha, Eman, Ghaida, 200, 100); //creating the store
        File file = new File("gamestore_project/src/gamestore_project/Users.data");
        FileInputStream f1 = new FileInputStream(file);
        ObjectInputStream userIn = new ObjectInputStream(f1);
     
        File file2 = new File("/gamestore_project/src/gamestore_project/Games.data");
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
      /* User[] users = new User[4];
       users[0] = new User("Afnan", 500);            // setting up
       users[1] = new User("sarah", 250.50);         // some users
       users[2] = new User("michael", 20); 
       users[3] = new User("ABDULLAH", 300); 
        
        GEA.addUser(users[0]);
        GEA.addUser(users[1]);   // adding them 
        GEA.addUser(users[2]);    // to the store's userList
        GEA.addUser(users[3]);
        for(int i =0 ; i<4;i++)
        userOut.writeObject(users[i]); userOut.close(); f0.close();
        
        /*
        Setting up some games and adding them to the store's gameList

                                |
                                V
                             
        Game [] games = new Game[10];
        games[0] = new StoryRich("Sekiro", "cube game", 229);  
        games[1] = new StoryRich("Detroit become human", "Quantic Dream", 185);
        games[2]= new RPG("Ori and the blind forest", "XBOX", 85);
        games[3] = new RPG("Persona 5", "SEGA", 250);
        games[4] = new Survival("Minecraft", "Mojang", 100);
        games[5]= new Survival("Don't Starve", "Klei", 21.95);
        games[6] = new Detective("Unsolved case", "eleven puzzels", 13.95);
        games[7] = new Detective("Ace attorney", "CAPCOM", 138);
        games[8] = new Horror("Silent Hill", "Konami", 90.33);
        games[9] = new Horror("Lethal company", "zeekerss", 22.49);

        GEA.addGame( games[0]);
        GEA.addGame( games[1]);
        GEA.addGame( games[2]);
        GEA.addGame( games[3]);
        GEA.addGame( games[4]);
        GEA.addGame( games[5]);
        GEA.addGame( games[6]);
        GEA.addGame( games[7]);
        GEA.addGame( games[8]);
        GEA.addGame( games[9]);
        for(int i =0; i<10; i++)
           gameOut.writeObject(games[i]);   gameOut.close(); f2.close();*/

        System.out.println("\nGEA GAME STORE\n~*~*~*~*~*~*~*~"); //Start of the actual program
        int check = 0; 

        do { 
            //this block takes the user's choice and validates it using a do while loop 
            //combined with a try-catch for input mismatch in case the user entered anything other than a number that is in range
            //it is used multiple times throughout the code for validation, hence we will refer to it as "input validation" 

            System.out.println("\n DO YOU WANT TO LOG IN AS A(N) .. \n\n 1- USER \n 2- ADMIN \n 3- CLOSE THE APP \n"); //the first menu in which the user is asked to enter a digit between 1-3 
            try {                                                                                                        //to choose to login as a user or as an admin, or to exit
                check = sc.nextInt();   // takes the user's input then consumes the remaining
                sc.nextLine();          // line to avoid problems in the rest of the code
                if (check < 1 || check > 3) 
                    throw new ChoiceOutOfBoundException();
            } 
            catch(ChoiceOutOfBoundException e){
               System.out.println("\nINVALID INPUT, PLEASE ENTER A NUMBER BETWEEN 1-3 \n"); //checks if the number was in range and prints a message if it was not
            }
            catch (InputMismatchException e) {
                System.out.println("\n INVALID INPUT, PLEASE TRY AGAIN.. \n PLEASE ENTER A DIGIT "); //prints a message to direct the user
                sc.next(); // consumes anything left from previous attempts
                check = 0; // sets the check to 0 so we re-enter the while loop, the loop continues until the user enters 3 in check

            }
            User u1;
            if (check == 1) { //if the user chose to login as a user

                System.out.println("\n PLEASE ENTER YOUR NAME :\n");
                String name = sc.nextLine();
                if (GEA.findUser(name) == null) { //findUser returns null if the user wasn't found, so we check if the user exists or not in this if statement
                    System.out.println("\nWELCOME TO OUR STORE " + name + ", THIS IS YOUR FIRST TIME IN OUR APP :D");
                    double wallet;
                    do { // please refer to "input validation" in the first block, this block works in a similar way
                        try {
                            System.out.println("PLEASE ENTER YOUR WALLET AMOUNT :");
                            wallet = sc.nextDouble();
                            sc.nextLine();
                            if (wallet < 0) {
                                System.out.println("\nINVALID INPUT, PLEASE ENTER A POSITIVE AMOUNT \n"); //the wallet only accepts positive numbers
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nINVALID INPUT, PLEASE ENTER A NUMBER \n");
                            sc.next();
                            wallet = -1; // Sets wallet to -1 to continue loop
                        }
                    } while (wallet < 0);

                    u1 = new User(name, wallet); //creating and adding a new user based on the user's input
                 
                    GEA.addUser(u1);

                } else if (GEA.findUser(name) != null) {//if the user has been found, the program identifies them. such as the predefined users in the beginning of this class 
                    u1 = GEA.findUser(name);
                    System.out.println("\n WELCOME BACK !");
                }
                int choice;
                do { // this loop continues as long as the input != 0, if the user entered 0 "logout" it will stop the loop
                
                    do  { // please refer to "input validation" in the first block, this block works in a similar way

                        try {
                            System.out.println(" \nPLEASE SELECT NUMBER OF SERVICE THAT YOU WANT : ");
                            System.out.println(
                                    " --------------------- \n 1- BUY A GAME \n 2- SEND A GIFT TO A FRIEND \n 3- REMOVE A GAME FROM YOUR LIBRARY \n 4- FIND A GAME IN YOUR LIBRARY  \n 5- SHOW ALL GAMES IN LIBRARY \n 6- SHOW GAMES OF GEA STORE \n 7- DISPLAY YOUR PERSONAL INFORMATION \n 0- LOGOUT ");
                            choice = sc.nextInt();
                            sc.nextLine();

                            if (choice < 0 || choice > 7) {
                                System.out.println("INVALID INPUT, PLEASE ENTER A NUMBER BETWEEN 0 AND 7 ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("INVALID INPUT, PLEASE ENTER A DIGIT");
                            sc.next();
                            choice = -1; // Set choice to -1 to continue loop
                        }
                    } while (choice < 0 || choice > 7); //it continues the loop as long as the choice is not in range

                    switch (choice) {
                    case 1: //when the user enters 1, this case will run to allow the user to buy a game / add it to the library

                        GEA.displayAllGames(); //calls the method that displays all games in this store, so the user chooses what to buy
                        System.out.println("ENTER THE NAME OF THE GAME YOU WANT TO PURCHASE ..");
                        String Gname = sc.nextLine();
                        (GEA.findUser(name)).buyGame(GEA.searchForGame(Gname)); //this line takes the user's name and uses it to buy the game, + it checks if the game already exists
                        break;                                                  //in library or if the wallet is not enough for the purchase and other conditions inside the methods

                    case 2: //when the user enters 2, this case will run to allow the user to send a gift (a game) to a friend's library
                        System.out.println(" ENTER THE NAME OF YOUR FRIEND ACCOUNT ");

                        String Fname = sc.nextLine();
                        if (GEA.findUser(Fname) == null) { //if the friend wasn't found, it'll print a message
                            System.out.println("SORRY.. COULDN'T FIND USER \"" + Fname + "\"");
                        } else {// if the friend was found (in users), the games of the store will be displayed, then the user will be asked to enter a game to send
                            GEA.displayAllGames();
                            System.out.println("THE NAME OF THE GAME YOU WANT TO GIFT \n");
                            Gname = sc.nextLine();
                            (GEA.findUser(name)).sendGift(GEA.findUser(Fname), GEA.searchForGame(Gname)); //the game will be bought and sent to the friend library if it exists in the store (see methods)
                        }
                        break;

                    case 3:  //when the user enters 3, this case will run to allow the user to remove a game from their library
                        System.out.println("ENTER THE NAME OF THE GAME YOU WANT TO REMOVE FROM YOUR LIBRARY..");
                        Gname = sc.nextLine();
                        (GEA.findUser(name)).removeGameFromLibrary(Gname);
                        break;

                    case 4:  //when the user enters 4, this case will run to allow the user to search for a game in their library and display it
                        System.out.println("ENTER THE NAME OF THE GAME YOU WANT TO FIND IN YOUR LIBRARY ..");
                        Gname = sc.nextLine();
                        GEA.findUser(name).findGame(Gname);
                        break;

                    case 5:  //when the user enters 5, this case will run to allow the user to display all the games in their library
                        (GEA.findUser(name)).displayMyLibrary();
                        break;

                    case 6:  //when the user enters 6, this case will run to allow the user to display all the games in the store
                        GEA.displayGames();
                        break;

                    case 7: //when the user enters 7, this case will run to display the current user's information
                        System.out.println(GEA.findUser(name));
                        break;

                    }

                } while (choice != 0); //when the user enters 0, the loop will stop and the user will log out

            } else if (check == 2) { //if the user chose to login as an admin
                if (GEA.adminExists()) { //verifies admin's username and password

                    int choice = 0;
                    do {
                        System.out.println(" PLEASE SELECT THE SERVICE THAT YOU WANT ");
                        do {  // please refer to "input validation" in the first block, this block works in a similar way

                            try {
                                System.out.println(
                                        " -------------------------- \n \n 1- ADD A NEW GAME TO THE STORE  \n 2- REMOVE A GAME FROM THE STORE  \n 3- SEARCH FOR A GAME IN THE STORE \n 4- DISPLAY GAMES IN THE STORE \n 5- ADD A NEW USER \n 6- REMOVE A USER \n 7- DISPLAY ALL USERS  \n 8- SEARCH FOR A CERTAIN USER \n 9- LOGOUT");
                                choice = sc.nextInt();
                                sc.nextLine();

                                if (choice < 1 || choice > 9) {
                                    System.out.println("INVALID INPUT , PLEASE ENTER A NUMBER BETWEEN 1 AND 9 ");
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("INVALID INPUT, PLEASE ENTER A DIGIT");
                                sc.next();
                                choice = -1; // Set choice to -1 to continue loop
                            }
                        } while (choice < 1 || choice > 9);

                        switch (choice) { //when the admin enters a valid choice, this block runs

                        case 1: //when the admin enters 1, this case will run to allow the admin to add a new game to the store
                            int GGenre = 0;
                            System.out.println("ENTER THE GAME'S NAME");    
                            String Gname = sc.nextLine();
                            if (GEA.gameExists(Gname) == true) { //checks if the game already exists
                                System.out.println("\n THE GAME ALREADY EXISTS");
                                break;
                            }
                            System.out.println("ENTER THE GAME'S PUBLISHER");
                            String Gpub = sc.nextLine();
                            double Gprice;
                            do { //please refer to "input validation" in the first block, this block works in a similar way
                                try {
                                    System.out.println("PLEASE ENTER THE GAME'S PRICE :");
                                    Gprice = sc.nextDouble();
                                    sc.nextLine();
                                    if (Gprice < 0) {
                                        System.out.println("\nINVALID INPUT, PLEASE ENTER A POSITIVE AMOUNT \n");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("\nINVALID INPUT, PLEASE ENTER A NUMBER \n");
                                    sc.next();
                                    Gprice = -1; // Set Gprice to -1 to continue loop
                                }
                            } while (Gprice < 0);

                            do { //please refer to "input validation" in the first block, this block works in a similar way
                                try {
                                    System.out.println("ENTER THE GAME'S GENRE");
                                    System.out.println(
                                            " 1- DETECTIVE  \n 2- HORROR  \n 3- RPG  \n 4- STORY-RICH  \n 5- SURVIVAL ");
                                    GGenre = sc.nextInt();
                                    sc.nextLine();

                                    if (GGenre < 1 || GGenre > 5) {
                                        System.out.println("INVALID INPUT, PLEASE ENTER A NUMBER BETWEEN 0 AND 5 ");
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println("INVALID INPUT, PLEASE ENTER A DIGIT");
                                    sc.next();
                                    GGenre = -1; // Set GGenre to -1 to continue loop
                                }
                            } while (GGenre < 1 || GGenre > 5);

                            switch (GGenre) { //this switch assigns the game to its genre
                            case 1:
                                Game game = new Detective(Gname, Gpub, Gprice);
                                if (GEA.addGame(game) == true) {
                                    System.out.println("THE GAME WAS ADDED SUCCESSFULLY :)");
                                }
                                break;
                            case 2:
                                game = new Horror(Gname, Gpub, Gprice);
                                if (GEA.addGame(game) == true) {
                                    System.out.println("THE GAME WAS ADDED SUCCESSFULLY :)");
                                }
                                break;
                            case 3:
                                game = new RPG(Gname, Gpub, Gprice);
                                if (GEA.addGame(game) == true) {
                                    System.out.println("THE GAME WAS ADDED SUCCESSFULLY :)");
                                }
                                break;
                            case 4:
                                game = new StoryRich(Gname, Gpub, Gprice);
                                if (GEA.addGame(game) == true) {
                                    System.out.println("THE GAME WAS ADDED SUCCESSFULLY :)");
                                }
                                break;
                            case 5:
                                game = new Survival(Gname, Gpub, Gprice);
                                if (GEA.addGame(game) == true) {
                                    System.out.println("THE GAME WAS ADDED SUCCESSFULLY :)");
                                }
                                break;
                            }
                            break; //break case 1

                        case 2: //when the admin enters 2, this case will run to allow the admin to remove a game from the store
                            System.out.println("ENTER THE GAME'S NAME");
                            Gname = sc.nextLine();
                            GEA.removeGame(Gname);
                            break;
                        case 3: //when the admin enters 3, this case will search in the store and displays the game entered
                            System.out.println("ENTER THE GAME'S NAME \n ");
                            Gname = sc.nextLine();
                            if ((GEA.searchForGame(Gname) != null)) {
                                System.out.println(GEA.searchForGame(Gname));
                            }
                            break;
                        case 4: //when the admin enteres 4, this case displays all games or games of a specific genre 
                            GEA.displayGames();
                            break;
                        case 5: //when the admin enters 5, this case adds a user based on admin's input
                            System.out.println("ENTER A USERNAME FOR THE USER YOU WANT TO ADD");
                            String Uname = sc.nextLine();
                            if (GEA.userExists(Uname)) {
                                break;
                            }
                            double Uwallet;
                            do { //please refer to "input validation" in the first block, this block works in a similar way
                                try {
                                    System.out.println("PLEASE ENTER THE USER'S WALLET AMOUNT :");
                                    Uwallet = sc.nextDouble();
                                    sc.nextLine();
                                    if (Uwallet < 0) {
                                        System.out.println("\nINVALID INPUT, PLEASE ENTER A POSITIVE AMOUNT \n");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("\nINVALID INPUT, PLEASE ENTER A NUMBER \n");
                                    sc.next();
                                    Uwallet = -1; // Set Uwallet to -1 to continue loop
                                }
                            } while (Uwallet < 0);
                            User u2 = new User(Uname, Uwallet);
                            if (GEA.addUser(u2) == true) {
                                System.out.println("THE USER WAS ADDED SUCCESSFULLY :)");
                            }
                            break;
                        case 6: //when the admin enters 6, this case removes a user based on admin's input
                            System.out.println("ENTER A USERNAME FOR THE USER YOU WANT TO REMOVE");
                            String Rname = sc.nextLine();
                            GEA.removeUser(Rname);
                            break;
                        case 7: //when the admin enters 7, this case displays all users of gamestore
                            GEA.displayAllUsers();
                            break;
                        case 8: { //when the admin enters 8, this case displays info of a certain user in gamestore
                            System.out.println("ENTER A USERNAME FOR THE USER YOU'RE LOOKING FOR");
                            String username = sc.nextLine();
                            if (GEA.userExists(username))
                                System.out.println(GEA.findUser(username));
                            else
                                System.out.println("SORRY.. COULDN'T FIND USER \"" + username + "\"");
                            break;
                        }}
                    } while (choice != 9);//to logout as an admin
                }
            } 


        } while (check != 3); //closes the program
        
            FileOutputStream f0 = new FileOutputStream(file);
            ObjectOutputStream userOut = new ObjectOutputStream(f0);
            User[] user = GEA.getUserList();
            for(int i=0 ; i <GEA.getNoUsers(); i++)
               userOut.writeObject(user[i]);
     
            FileOutputStream f2 = new FileOutputStream(file2);
            ObjectOutputStream gameOut = new ObjectOutputStream(f2); 
            Game[] game =GEA.getGameList();
            for(int i=0 ; i <GEA.getNoGames(); i++)
            gameOut.writeObject(game[i]);
            gameOut.close(); f2.close(); userOut.close(); f0.close();
    }
}
