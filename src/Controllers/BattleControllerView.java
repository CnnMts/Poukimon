package Controllers;

import Models.Type;
import Models.TypeTools;
import Competences.BouleElec;
import Competences.ToileEleck;
import Competences.Belier;
import Models.Pokemon;
import Models.Attackable;
import Models.Defendable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
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
    @FXML
    private Text PokemonName;
    @FXML
    private Text PokemonName2;
    @FXML
    private Text HpPokemon;
    @FXML
    private Text HpPokemon2;

    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private boolean playerTurn = true;
    
    

   public void initialize() {
    	
    	TypeTools.initializeTypeRelations();
        
        BouleElec bouleElec = new BouleElec(null);
        ToileEleck toileEleck = new ToileEleck(null);
        Belier belier = new Belier(playerPokemon, enemyPokemon);



        playerPokemon = new Pokemon("Pikachu", new Type[]{Type.ELECTRIQUE}, 150, 30, new Attackable[]{bouleElec, toileEleck}, new Defendable[]{});
        enemyPokemon = new Pokemon("Bulbizarre", new Type[]{Type.PLANTE}, 140, 30, new Attackable[]{}, new Defendable[]{belier});

        
        bouleElec.setTarget(enemyPokemon);
        toileEleck.setTarget(enemyPokemon);
        belier.setTarget(playerPokemon,enemyPokemon);
       
        
        PokemonName.setText(playerPokemon.getName());
        PokemonName2.setText(enemyPokemon.getName());
        
        HpPokemon.setText(getPourcentageHpPlayer());
        HpPokemon2.setText(getPourcentageEnemy());
        
       
        statusText.setText(playerPokemon.getName() + " vs " + enemyPokemon.getName());

        attackButton1.setText(playerPokemon.getAttacks()[0].getClass().getSimpleName());
        attackButton2.setText(playerPokemon.getAttacks()[1].getClass().getSimpleName());
        
        
        pokemon.setImage(new Image(getClass().getResourceAsStream("/pikachu.png")));
        pokemon2.setImage(new Image(getClass().getResourceAsStream("/bulbizarre.png")));

        playerHealthBar.setProgress(1.0);
        enemyHealthBar.setProgress(1.0);
        
        attackButton1.setOnAction(event -> {
            playerPokemon.getAttacks()[0].attack(playerPokemon, enemyPokemon);
            updateHealthBarEnemy();
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        });

        attackButton2.setOnAction(event -> {
            playerPokemon.getAttacks()[1].attack(playerPokemon, enemyPokemon);
            updateHealthBarEnemy();
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        });

        defenseButton1.setOnAction(event -> {
        	playerPokemon.getDefends()[0].defend(playerPokemon);
            updateHealthBarPlayer();
            updateHealthBarEnemy();
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        });

        
    }

   public String getPourcentageHpPlayer() {
        double percentage = (playerPokemon.getHp() / (double) playerPokemon.getMaxHp()) * 100;
        System.out.println(playerPokemon.getHp());
        return String.format("%.2f%%", percentage);
    }
    
   public String getPourcentageEnemy() {
        double percentage = (enemyPokemon.getHp() / (double) enemyPokemon.getMaxHp()) * 100;
        return String.format("%.2f%%", percentage);
    
    }
    
   private void updateHealthBarPlayer() {
        double playerHealthPercentage = (double) playerPokemon.getHp() 
        		/ playerPokemon.getMaxHp();
        animateHealthBar(playerHealthBar, playerHealthPercentage);
        if (playerPokemon.getHp() <= 0) {
            HpPokemon.setText("0%");
        } else {
            HpPokemon.setText(getPourcentageHpPlayer());
        }

    }

   private void updateHealthBarEnemy() {
        double enemyHealthPercentage = (double) enemyPokemon.getHp() / enemyPokemon.getMaxHp();
        animateHealthBar(enemyHealthBar, enemyHealthPercentage);
        if (enemyPokemon.getHp() <= 0) {
            HpPokemon2.setText("0%");
        } else {
            HpPokemon2.setText(getPourcentageEnemy());
        }

        
    }

   private void animateHealthBar(ProgressBar healthBar, double targetProgress){
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(healthBar.progressProperty(), targetProgress, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void round() {
        if (!playerTurn) {
            statusText.setText("Enemy's turn!");

            new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                if (Math.random() < 1) {
                    int damage = 20;
                    playerPokemon.takeDamage(damage);
                    updateHealthBarPlayer();
                    statusText.setText(enemyPokemon.getName() + " attacks! " + playerPokemon.getName() + " has " + playerPokemon.getHp() + " HP left");
                } else {
                    enemyPokemon.heal(10);
                    updateHealthBarEnemy();
                    statusText.setText(enemyPokemon.getName() + " defends and recovers 10 HP!");
                }
                statusText.setText("Player's turn!");
            })).play();
        }

        playerTurn = !playerTurn;

        if (playerPokemon.getHp() <= 1) {
            statusText.setText(playerPokemon.getName() + " is KO! Enemy wins!");
            pokemon.setVisible(false);
        } else if (enemyPokemon.getHp() <= 1) {
            statusText.setText(enemyPokemon.getName() + " is KO! Player wins!");
            pokemon2.setVisible(false);
        } else if (!playerTurn) {
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        }
    }
}
