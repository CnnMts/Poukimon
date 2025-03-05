package Animation;

import javafx.util.Duration;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public class BouleElecktAnimation{
	
	public void start(ImageView boule, ImageView ennemi) {
        // Calculer la distance entre la boule et l'ennemi
        double distanceX = ennemi.getLayoutX() - boule.getLayoutX();
        double distanceY = ennemi.getLayoutY() - boule.getLayoutY();

        // Créer une TranslateTransition pour déplacer la boule
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(15), boule);

        // Définir les positions de départ et d'arrivée
        translateTransition.setFromX(0);
        translateTransition.setFromY(0);
        translateTransition.setToX(distanceX);
        translateTransition.setToY(distanceY);

        // Jouer l'animation
        translateTransition.play();
    }
}