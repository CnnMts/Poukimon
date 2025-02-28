package Controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Views.BattleView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuControllerView{
    
	public void switchSceneBattle() throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
		BattleView.switchBattleScene("/Battle.fxml");
		callStopSound();
	}
	
	public void callStopSound() throws UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, IOException {
		BattleView.stopSoundMenu();
	}
	
}