package Controllers;

import Models.Pokemon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ImageView pokemon; 
    @FXML
    private ImageView pokemon2;
    
    @FXML
    private ProgressBar playerHealthBar;
    @FXML
    private ProgressBar enemyHealthBar;
    
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
        
        pokemon.setImage(new Image(getClass().getResourceAsStream("/pikachu.png")));
        pokemon2.setImage(new Image(getClass().getResourceAsStream("/bulbizarre.png")));

        
        playerHealthBar.setProgress(1.0); 
        enemyHealthBar.setProgress(1.0); 
        
        attackButton1.setOnAction(event -> performAttack(playerPokemon.getAttacks()[0]));
        attackButton2.setOnAction(event -> performAttack(playerPokemon.getAttacks()[1]));
        defenseButton1.setOnAction(event -> performDefense());
        defenseButton2.setOnAction(event -> performDefense());
    }

    private void performAttack(String attack) {
        statusText.setText(playerPokemon.getName() + " attaque avec " + attack + " !");
        int damage = 20;
        enemyPokemon.takeDamage(damage);
        
        updateHealthBar();

        if (enemyPokemon.getHp() <= 0) {
            statusText.setText(enemyPokemon.getName() + " est KO!");
            updateHealthBar();
        } else {
            statusText.setText(enemyPokemon + " a encore " + enemyPokemon.getHp() + " HP");
            updateHealthBar();
        }
    }

    private void updateHealthBar() {
    	 double enemyHealthPercentage = (double) enemyPokemon.getHp() / enemyPokemon.getMaxHp();
         enemyHealthBar.setProgress(enemyHealthPercentage);
    }
    private void performDefense() {
        statusText.setText(playerPokemon.getName() + " se défend !");
        playerPokemon.heal(10);
        statusText.setText(playerPokemon + " a récupéré 10 HP !");
    }
}
