package gamestore_project;

import javax.swing.JOptionPane;

public class GameAlreadyExistsException extends Exception {
    public GameAlreadyExistsException(){
        JOptionPane.showMessageDialog(null, "the game you are trying to add already exists in the store", "Oh no :(", 0);
    }

}

