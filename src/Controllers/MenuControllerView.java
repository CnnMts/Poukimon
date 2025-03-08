package Controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Views.MenuChoicePokemon;
import Views.BattleView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuControllerView{
    
	public void switchSceneChoice() {
	    try {
	        MenuChoicePokemon.switchChoiceScene("/Choice.fxml");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erreur lors du changement de scène : " 
	        + e.getMessage());
	    }
	}


		
}