package Views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Controllers.BattleControllerView;
import Items.BaieStatus;
import Items.Potion;
import Models.Attackable;
import Models.Pokemon;

public class BattleView {
	public static void switchBattleScene(String fxmlFile, List<Pokemon> playerTeam,
			HashMap<Pokemon, List<Attackable>> pokemonAttacks, 
			BaieStatus baie, Potion potion) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(BattleView.class.getResource(fxmlFile));
		Parent newRoot = fxmlLoader.load();
		BattleControllerView controller = fxmlLoader.getController();
		controller.setPlayerTeam(playerTeam);
		controller.setPokemonAttacks(pokemonAttacks);
		controller.setBaie(baie);
		controller.setPotion(potion);
		Scene newScene = new Scene(newRoot, 1500, 800);
		MenuView.getPrimaryStage().setScene(newScene);
		newScene.getStylesheets().add(BattleView.class
				.getResource("/battleStyles.css").toExternalForm());
	}

}
