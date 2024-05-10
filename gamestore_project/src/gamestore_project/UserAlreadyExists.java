package gamestore_project;

import javax.swing.JOptionPane;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(){
        JOptionPane.showMessageDialog(null, "the user you are trying to add already has an account in the store", "Oh no :(", 0);
    }

}

