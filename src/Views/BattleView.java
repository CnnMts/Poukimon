package Views;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Controllers.BattleControllerView;
import Models.Attackable;

public class BattleView {
	public static void switchBattleScene(String fxmlFile, Attackable[] selectedAttacks) throws IOException {
	    FXMLLoader fxmlLoader = new FXMLLoader(BattleView.class.getResource(fxmlFile));
	    Parent newRoot = fxmlLoader.load();
	    BattleControllerView controller = fxmlLoader.getController();

	    // Passez le tableau d'attaques au contrôleur
	    controller.setSelectedAttacks(selectedAttacks);

	    Scene newScene = new Scene(newRoot, 1500, 800);
	    MenuView.getPrimaryStage().setMinHeight(800);
	    MenuView.getPrimaryStage().setMinWidth(1500);
	    MenuView.getPrimaryStage().setScene(newScene);

	    newScene.getStylesheets().add(BattleView.class.getResource("/battleStyles.css").toExternalForm());
	}

}

