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

    private int numParticules = 20;

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
      Label lightning = createLightningLabel(player);
      test.getChildren().add(lightning);
      playLightningTransitions(lightning);
  }

  private Label createLightningLabel(ImageView player) {
      Label lightning = new Label("⚡");
      lightning.setStyle("-fx-font-size: 40px; -fx-text-fill: yellow;");
      double playerCenterX = player.getLayoutX() + player.getFitWidth() / 2;
      double playerCenterY = player.getLayoutY() + player.getFitHeight() / 2;
      lightning.setLayoutX(playerCenterX);
      lightning.setLayoutY(playerCenterY);
      return lightning;
  }

  private void playLightningTransitions(Label lightning) {
      Random rand = new Random();
      TranslateTransition moveTransition = new TranslateTransition();
      moveTransition.setNode(lightning);
      moveTransition.setCycleCount(1);
      moveTransition.setDuration(Duration.millis(800));
      double moveX = rand.nextInt(61) - 30;
      double moveY = rand.nextInt(61) - 30;
      moveTransition.setByX(moveX);
      moveTransition.setByY(moveY);
      FadeTransition fadeTransition = 
      		new FadeTransition(Duration.millis(800), lightning);
      fadeTransition.setFromValue(1.0);
      fadeTransition.setToValue(0.0);
      fadeTransition.setOnFinished(e -> test.getChildren().remove(lightning));
      moveTransition.play();
      fadeTransition.play();
  }

}
