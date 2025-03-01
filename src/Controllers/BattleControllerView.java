package Controllers;

import Models.Pokemon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class BattleControllerView {

    @FXML
    private Button attackButton1;

    @FXML
    private Button attackButton2;

    @FXML
    private Button defenseButton1;

    @FXML
    private Button defenseButton2;

    @FXML
    private Text statusText;
    

    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;

    public void initialize() {
   	
        playerPokemon = new Pokemon("Pikachu", 100, new String[]{"Foudre", "Boule Eleck"}, new String[]{"Hurlement", "Pleure"});
        enemyPokemon = new Pokemon("Bulbizarre", 100, new String[]{"Lianes", "Vampigraine"}, new String[]{"bouclier", "Vitesse"});

        statusText.setText(playerPokemon + " vs " + enemyPokemon);
        
        attackButton1.setText(playerPokemon.getAttacks()[0]); 
        attackButton2.setText(playerPokemon.getAttacks()[1]); 
        defenseButton1.setText(playerPokemon.getDefends()[0]); 
        defenseButton2.setText(playerPokemon.getDefends()[1]);

        attackButton1.setOnAction(event -> performAttack(playerPokemon.getAttacks()[0]));
        attackButton2.setOnAction(event -> performAttack(playerPokemon.getAttacks()[1]));
        defenseButton1.setOnAction(event -> performDefense());
        defenseButton2.setOnAction(event -> performDefense());
    }

    private void performAttack(String attack) {
        statusText.setText(playerPokemon.getName() + " attaque avec " + attack + " !");
        int damage = 20;
        enemyPokemon.takeDamage(damage);
        if (enemyPokemon.getHp() <= 0) {
            statusText.setText(enemyPokemon.getName() + " est KO!");
        } else {
            statusText.setText(enemyPokemon + " a encore " + enemyPokemon.getHp() + " HP");
        }
    }

    
    private void performDefense() {
        statusText.setText(playerPokemon.getName() + " se défend !");
        playerPokemon.heal(10);
        statusText.setText(playerPokemon + " a récupéré 10 HP !");
    }
}
