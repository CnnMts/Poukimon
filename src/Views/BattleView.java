package Views;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Controllers.BattleControllerView;
import Models.Attackable;
import Models.Pokemon;

public class BattleView {
	public static void switchBattleScene(String fxmlFile, Attackable[] selectedAttacks, List<Pokemon> team) throws IOException {
	    FXMLLoader fxmlLoader = new FXMLLoader(BattleView.class.getResource(fxmlFile));
	    Parent newRoot = fxmlLoader.load();
	    BattleControllerView controller = fxmlLoader.getController();
	    controller.setTeam(team);
	    
	    controller.setSelectedAttacks(selectedAttacks);

	    Scene newScene = new Scene(newRoot, 1500, 800);
	    MenuView.getPrimaryStage().setMinHeight(800);
	    MenuView.getPrimaryStage().setMinWidth(1500);
	    MenuView.getPrimaryStage().setScene(newScene);

	    newScene.getStylesheets().add(BattleView.class.getResource("/battleStyles.css").toExternalForm());
	}


}

