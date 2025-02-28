package Views;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BattleView {

    public static void switchBattleScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleView.class.getResource(fxmlFile));
        Parent newRoot = fxmlLoader.load();
        Scene newScene = new Scene(newRoot, 100, 100);
        MenuView.getPrimaryStage().setScene(newScene);
        
       // newScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }
    
    public static void stopSoundMenu() throws UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, IOException {
    //	MenuView.audioMenu().clip.stop();
    }

   
}