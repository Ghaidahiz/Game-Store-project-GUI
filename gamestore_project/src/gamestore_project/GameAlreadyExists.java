package gamestore_project;

import javax.swing.JOptionPane;

public class GameAlreadyExists extends Exception {
    public GameAlreadyExists(){
        JOptionPane.showMessageDialog(null, "the game you are trying to add already exists in the store", "Oh no :(", 0);
    }

}

