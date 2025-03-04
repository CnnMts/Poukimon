package Views;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuChoicePokemon {

    public static void switchChoiceScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuChoicePokemon.class.getResource(fxmlFile));
        Parent newRoot = fxmlLoader.load();
        Scene newScene = new Scene(newRoot, 1500, 800);
        MenuView.getPrimaryStage().setScene(newScene);
        
        newScene.getStylesheets().add(MenuChoicePokemon.class.getResource("/choiceStyles.css").toExternalForm());
    }
    
    public static void stopSoundMenu() throws UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, IOException {
    	Clip audio =  MenuView.getAudio();
    	audio.stop();
    }

   
}