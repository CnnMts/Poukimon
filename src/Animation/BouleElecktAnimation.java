package Animation;

import javafx.util.Duration;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public class BouleElecktAnimation{
	
	public void start(ImageView boule, ImageView ennemi) {
        
        double distanceX = ennemi.getLayoutX() - boule.getLayoutX();
        double distanceY = ennemi.getLayoutY() - boule.getLayoutY();

      
        TranslateTransition translateTransition = 
        		new TranslateTransition(Duration.millis(15), boule);

        translateTransition.setFromX(0);
        translateTransition.setFromY(0);
        translateTransition.setToX(distanceX);
        translateTransition.setToY(distanceY);

        translateTransition.play();
    }
}