package gamestore_project;

import javax.swing.JOptionPane;

public class UserAlreadyExistsException extends Exception {
    public void UserAlreadyExistException(){
        JOptionPane.showMessageDialog(null, "the user you are trying to add already has an account in the store", "Oh no :(", 0);
    }

}

