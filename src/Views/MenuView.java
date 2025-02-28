package Views;

import java.io.File;




import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuView extends Application {
    public static Stage primaryStage;
    public static Clip clip;
    
    public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
        	primaryStage = stage;
        	
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
            Parent root = fxmlLoader.load();
            Scene sceneMenu = new Scene(root, 900, 900);
            primaryStage.setMaxHeight(1080);
            primaryStage.setMaxWidth(1920);
            primaryStage.setScene(sceneMenu);
            audioMenu();
            sceneMenu.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            primaryStage.setTitle("Poukimon");
            primaryStage.show();
            
           
    }
    public static void audioMenu()throws UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, IOException {
    	File mediaPath;
    	mediaPath = new File(MenuView.class.getResource("/startMenu.wav").toURI());
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(mediaPath);
    	clip = AudioSystem.getClip();
    	
    	clip.open(audioStream);
    	clip.start();
    }
    
    public static Clip getAudio() {
    	return clip ;
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    
    public static void main(String[] args) {
        launch();
    }
	
}