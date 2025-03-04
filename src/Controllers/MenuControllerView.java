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
    
	public void switchSceneChoice() throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
		MenuChoicePokemon.switchChoiceScene("/Choice.fxml");
		callStopSound();
	}
	
	public void callStopSound() throws UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, IOException {
		MenuChoicePokemon.stopSoundMenu();
	}
	
	public void switchBattleScene() throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
		BattleView.switchBattleScene("/Battle.fxml");
		callStopSound();
	}
		
}