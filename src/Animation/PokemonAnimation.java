package Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PokemonAnimation {
	
    public void start(ImageView player) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(player);
        translateTransition.setCycleCount(10);
        translateTransition.setAutoReverse(true);
        translateTransition.setDuration(Duration.millis(100));  
        translateTransition.setFromX(0); 
        translateTransition.setToX(5);   
        
        translateTransition.play();
    }
}
