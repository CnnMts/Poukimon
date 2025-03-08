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

		createCrepitementEffect(player, stackPane123);
	}

	private void createCrepitementEffect(ImageView player, StackPane stackPane123) {
		Random rand = new Random();
		for (int i = 0; i < numParticules; i++) {
			Circle particle = createParticle(rand);
			stackPane123.getChildren().add(particle);
			playTransitions(particle, rand, stackPane123);
		}
	}

	private Circle createParticle(Random rand) {
		return new Circle(3 + rand.nextInt(3), 
				Color.color(rand.nextDouble() * 0.5 + 0.5, 0, 0));
	}

	private void playTransitions(Circle particle, Random rand, 
			StackPane stackPane123) {
		TranslateTransition translateTransition = new TranslateTransition();
		translateTransition.setNode(particle);
		translateTransition.setCycleCount(1);
		translateTransition.setDuration(Duration.millis(5000 + rand.nextInt(500)));
		translateTransition.setByX(rand.nextInt(20) - 10);
		translateTransition.setByY(rand.nextInt(20) - 10);

		FadeTransition fadeTransition = 
				new FadeTransition(Duration.millis(500 + rand.nextInt(500)), particle);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setOnFinished(e -> stackPane123.getChildren()
				.remove(particle));

		translateTransition.play();
		fadeTransition.play();
	}
}
