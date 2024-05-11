package gamestore_project;

import javax.swing.JOptionPane;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        JOptionPane.showMessageDialog(null, "the user was not found, make sure to enter the user's full name", "Oh no :(", 0);
    }

}