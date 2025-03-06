package Controllers;

import Models.Type;
import Models.TypeTools;
import Pokemon.Bulbizarre;
import Pokemon.Evoli;
import Pokemon.Pikachu;
import Views.BattleView;
import Competences.BouleElec;
import Competences.ToileEleck;
import Competences.Belier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Animation.PokemonAnimation;
import Models.Pokemon;
import Models.Attackable;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class BattleControllerView {
  @FXML private Button attackButton1, attackButton2, defenseButton1 ,
  					   defenseButton2;
  @FXML private ImageView pokemon, pokemon2;
  @FXML private ProgressBar playerHealthBar, enemyHealthBar;
  @FXML private Text PokemonName,PokemonName2, statusText,HpPokemon,
  					 HpPokemon2;
  @FXML StackPane PokemonInTeam1, PokemonInTeam2, PokemonInTeam3,
  				  PokemonInTeam4, PokemonInTeam5, PokemonInTeam6 ;

    private List<Pokemon> teams = new ArrayList<>();
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private boolean playerTurn = true;

   
    private Attackable selectedAttack;

    PokemonAnimation itPlayers = new PokemonAnimation();

 
    private Attackable selectedAttack1;
    private Attackable selectedAttack2;

    
    public void setSelectedAttacks(Attackable[] selectedAttacks) {
        if (selectedAttacks.length > 0) {
            this.selectedAttack1 = selectedAttacks[0];
            attackButton1.setText(selectedAttacks[0] != null ? 
            		selectedAttacks[0].getName() : "None");
        }
        if (selectedAttacks.length > 1) {
            this.selectedAttack2 = selectedAttacks[1];
            attackButton2.setText(selectedAttacks[1] != null ? 
            		selectedAttacks[1].getName() : "None");
        }
    }


    public void setTeam(List<Pokemon> team) {
        this.teams = team;
        if (!team.isEmpty()) {
            playerPokemon = team.get(0);
            PokemonName.setText(playerPokemon.getName());
            pokemon.setImage(playerPokemon.getImage());
            System.out.println("Team set: " + teams);  
            showTeamOnBattle(); 
        }
    }


    public void initialize() {
        TypeTools.initializeTypeRelations();
        playerPokemon = new Pikachu();
        enemyPokemon = new Evoli();
        PokemonName.setText(playerPokemon.getName());
        PokemonName2.setText(enemyPokemon.getName());

        HpPokemon.setText(getPourcentageHpPlayer());
        HpPokemon2.setText(getPourcentageEnemy());

        statusText.setText(playerPokemon.getName() + " vs " + enemyPokemon.getName());

        pokemon.setImage(((Pikachu) playerPokemon).getImage());
        pokemon2.setImage(((Evoli) enemyPokemon).getImage());

        playerHealthBar.setProgress(1.0);
        enemyHealthBar.setProgress(1.0);
        setTeam(teams);
        showTeamOnBattle();
        configureAttackButtons();
        configureDefendsButtons();
        showTeamOnBattle();
    }

    
    private void configureAttackButtons() {
        attackButton1.setOnAction(event -> {
            if (selectedAttack1 != null) {
                selectedAttack1.attack(playerPokemon, enemyPokemon); 
                updateHealthBarEnemy();
                itPlayers.start(pokemon2);
                new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
            }
        });

        attackButton2.setOnAction(event -> {
            if (selectedAttack2 != null) {
                selectedAttack2.attack(playerPokemon, enemyPokemon);
                updateHealthBarEnemy();
                new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
            }
        });
    }


    private void configureDefendsButtons() {
        defenseButton1.setOnAction(event -> {
            if (playerPokemon.getDefends().length > 0) {
                playerPokemon.getDefends()[0].defend(playerPokemon);
                updateHealthBarPlayer();
            }
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        });

        defenseButton2.setOnAction(event -> {
            if (playerPokemon.getDefends().length > 1) {
                playerPokemon.getDefends()[0].defend(playerPokemon);
                updateHealthBarPlayer();
            }
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        });
    }

    public String getPourcentageHpPlayer() {
        double percentage = (playerPokemon.getHp() / (double) playerPokemon.getMaxHp()) * 100;
        return String.format("%.2f%%", percentage);
    }

    public String getPourcentageEnemy() {
        double percentage = (enemyPokemon.getHp() / (double) enemyPokemon.getMaxHp()) * 100;
        return String.format("%.2f%%", percentage);
    }

    private void updateHealthBarPlayer() {
        double playerHealthPercentage = (double) playerPokemon.getHp() / playerPokemon.getMaxHp();
        animateHealthBar(playerHealthBar, playerHealthPercentage);
        HpPokemon.setText(playerPokemon.getHp() > 0 ? getPourcentageHpPlayer() : "0%");
    }

    private void updateHealthBarEnemy() {
        double enemyHealthPercentage = (double) enemyPokemon.getHp() / enemyPokemon.getMaxHp();
        animateHealthBar(enemyHealthBar, enemyHealthPercentage);
        HpPokemon2.setText(enemyPokemon.getHp() > 0 ? getPourcentageEnemy() : "0%");
    }

    private void animateHealthBar(ProgressBar healthBar, double targetProgress) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(healthBar.progressProperty(), targetProgress, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
    
    private void showTeamOnBattle() {
        if (teams.isEmpty()) {
            return;
        }
        HashMap<Integer, StackPane> teamPositions = new HashMap<>();
        teamPositions.put(0, PokemonInTeam1);
        teamPositions.put(1, PokemonInTeam2);
        teamPositions.put(2, PokemonInTeam3);
        teamPositions.put(3, PokemonInTeam4);
        teamPositions.put(4, PokemonInTeam5);
        teamPositions.put(5, PokemonInTeam6);
        for (int i = 0; i < teams.size(); i++) {
            Pokemon currentPokemon = teams.get(i);
            Image pokemonImage = currentPokemon.getImage();
            ImageView pokemonImageView = new ImageView(pokemonImage);
            pokemonImageView.setFitWidth(50);
            pokemonImageView.setFitHeight(50);
            pokemonImageView.setPreserveRatio(true);

            StackPane currentPane = teamPositions.get(i);
            if (currentPane != null) {
                currentPane.getChildren().clear();
                currentPane.getChildren().add(pokemonImageView);
            }

            ProgressBar pokemonName = new ProgressBar(currentPokemon.getHp());
            if (currentPane != null) {
                currentPane.getChildren().add(pokemonName);
            }
        }
    }




    private void round() {
        if (!playerTurn) {
            statusText.setText("Enemy's turn!");

            new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                if (Math.random() < 0.7) { // 70% chance d'attaquer
                    if (enemyPokemon.getAttacks().length > 0) {
                        enemyPokemon.getAttacks()[0].attack(enemyPokemon, playerPokemon);
                        updateHealthBarPlayer();
                        itPlayers.start(pokemon);
                        statusText.setText(enemyPokemon.getName() + " attacks! " + playerPokemon.getName() + " has " + playerPokemon.getHp() + " HP left");
                    }
                } else {
                    enemyPokemon.heal(10);
                    updateHealthBarEnemy();
                    statusText.setText(enemyPokemon.getName() + " defends and recovers 10 HP!");
                }
                statusText.setText("Player's turn!");
            })).play();
        }

        playerTurn = !playerTurn;

        if (playerPokemon.getHp() <= 0) {
            statusText.setText(playerPokemon.getName() + " is KO! Enemy wins!");
            pokemon.setVisible(false);
        } else if (enemyPokemon.getHp() <= 0) {
            statusText.setText(enemyPokemon.getName() + " is KO! Player wins!");
            pokemon2.setVisible(false);
        } else if (!playerTurn) {
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        }
    }
}
