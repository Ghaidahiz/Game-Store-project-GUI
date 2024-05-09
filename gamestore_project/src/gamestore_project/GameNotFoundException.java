package gamestore_project;

import javax.swing.JOptionPane;

public class GameNotFoundException extends Exception{
    public GameNotFoundException(){
        JOptionPane.showMessageDialog(null, "the game was not found, make sure to enter the game's full name", "Oh no :(", 0);
    }

}