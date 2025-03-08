package Animation;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class BrulureAnimation {

    @FXML
    private StackPane stackPane123;

    private int numParticules = 60;

    public void initialize(ImageView player, StackPane stackPane123) {
        if (stackPane123 != null) {
            System.out.println("StackPane correctement initialisé");
        } else {
            System.out.println("StackPane n'a pas été initialisé.");
        }

        if (player != null) {
            System.out.println("ImageView correctement initialisé");
        } else {
            System.out.println("ImageView n'a pas été initialisé.");
        }
    }

    public void start(ImageView player, StackPane stackPane123) {
        // Lancer l'effet de crépitement au sol
        createCrepitementEffect(player, stackPane123);
    }

    private void createCrepitementEffect(ImageView player, StackPane stackPane123) {
        Random rand = new Random();

        // Générer des particules de crépitement
        for (int i = 0; i < numParticules; i++) {
            // Créer une petite particule de couleur aléatoire
            Circle particle = new Circle(3 + rand.nextInt(3), Color.color(rand.nextDouble() * 0.5 + 0.5, 0, 0));  // Couleur rouge/orange
            // Toujours proche du bas du joueur

            // Ajouter la particule au StackPane
            stackPane123.getChildren().add(particle);

            // Animation de déplacement de la particule
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(particle);
            translateTransition.setCycleCount(1);
            translateTransition.setDuration(Duration.millis(5000 + rand.nextInt(500))); // Durée variable pour l'effet naturel

            // Déplacer les particules légèrement en haut et à droite/gauche
            translateTransition.setByX(rand.nextInt(20) - 10); // Déplacement horizontal aléatoire
            translateTransition.setByY(rand.nextInt(20) - 10); // Déplacement vertical aléatoire

            // Animation de disparition progressive de la particule (fade out)
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500 + rand.nextInt(500)), particle);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);

            // Lorsque l'animation de crépitement est terminée, supprimer la particule
            fadeTransition.setOnFinished(e -> stackPane123.getChildren().remove(particle));

            // Lancer les animations de déplacement et de disparition
            translateTransition.play();
            fadeTransition.play();
        }
    }
}
