package Animation;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Random;

public class ParalysieAnimation {

    @FXML
    private StackPane test;

    private int numParticules = 20; // Nombre de particules

    public void initialize(ImageView player, StackPane paneParticles) {
        if (paneParticles != null) {
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

    public void start(ImageView player, StackPane test) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(player);
        translateTransition.setCycleCount(10);
        translateTransition.setAutoReverse(true);
        translateTransition.setDuration(Duration.millis(100));
        translateTransition.setFromX(0);
        translateTransition.setToX(5);

        for (int i = 0; i < numParticules; i++) {
            createLightning(player, test);
        }

        translateTransition.play();
    }

    private void createLightning(ImageView player, StackPane test) {
        Random rand = new Random();
        Label lightning = new Label("⚡");
        lightning.setStyle("-fx-font-size: 40px; -fx-text-fill: yellow;");
        if (test == null) {
            System.out.println("Le conteneur 'paneParticles' n'est pas initialisé.");
            return;
        }

        double playerCenterX = player.getLayoutX() + player.getFitWidth() / 2;
        double playerCenterY = player.getLayoutY() + player.getFitHeight() / 2;
        lightning.setLayoutX(playerCenterX);
        lightning.setLayoutY(playerCenterY);
        test.getChildren().add(lightning);
        TranslateTransition moveTransition = new TranslateTransition();
        moveTransition.setNode(lightning);
        moveTransition.setCycleCount(1);
        moveTransition.setDuration(Duration.millis(800));

      
        double moveX = rand.nextInt(61) - 30;
        double moveY = rand.nextInt(61) - 30;

        moveTransition.setByX(moveX);
        moveTransition.setByY(moveY);

        // Animation de disparition progressive
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), lightning); // Durée d'animation augmentée à 800ms
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        // Suppression de l'éclair après disparition
        fadeTransition.setOnFinished(e -> test.getChildren().remove(lightning));

        // Lancer les animations en même temps
        moveTransition.play();
        fadeTransition.play();
    }

}
