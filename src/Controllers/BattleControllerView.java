package Controllers;

import Animation.ParalysieAnimation;
import Animation.PokemonAnimation;
import Competences.BouleElec;
import Models.TypeTools;
import Pokemon.Charmander;
import Pokemon.Evee;
import Pokemon.Gengar;
import Pokemon.Pikachu;
import Models.Pokemon;
import Models.Round;
import Models.Attackable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BattleControllerView {
    @FXML private Button attackButton1, attackButton2, attackButton3, attackButton4;
    @FXML private ImageView pokemon, pokemon2;
    @FXML private ProgressBar playerHealthBar, enemyHealthBar;
    @FXML private Text PokemonName, PokemonName2, statusText, HpPokemon, HpPokemon2;
    @FXML private StackPane PokemonInTeam1, PokemonInTeam2, PokemonInTeam3,
    	PokemonInTeam4, PokemonInTeam5, PokemonInTeam6, test;
    @FXML private Label roundLabel;
    @FXML private VBox roundsContainer;
    @FXML private ScrollPane scrollPane;

    private List<Pokemon> playerTeam = new ArrayList<>(), enemyTeam = new ArrayList<>();
    private HashMap<Pokemon, List<Attackable>> pokemonAttacksMap = new HashMap<>();
    private Pokemon playerPokemon, enemyPokemon;
    private boolean playerTurn = true;
    private Attackable selectedAttack1, selectedAttack2, selectedAttack3, selectedAttack4;
    private PokemonAnimation pokemonAnimation = new PokemonAnimation();
    private ParalysieAnimation pokemonParalysie = new ParalysieAnimation();
    private List<Round> roundHistory = new ArrayList<>();
    private Round currentRound;
    private int currentRoundNumber = 1;

    public void setPlayerTeam(List<Pokemon> team) {
        this.playerTeam = new ArrayList<>(team);
        if (!playerTeam.isEmpty()) {
            playerPokemon = playerTeam.get(0);
            PokemonName.setText(playerPokemon.getName());
            pokemon.setImage(playerPokemon.getImageReverse());
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            showTeamOnBattle();
        }
    }

    public void setEnemyTeam() {
    	addPokemonToEnemyTeam(new Pikachu());
        addPokemonToEnemyTeam(new Evee());
        addPokemonToEnemyTeam(new Gengar());
        addPokemonToEnemyTeam(new Charmander());
    }

    public void addPokemonToEnemyTeam(Pokemon pokemon) {
        if (enemyTeam.size() < 6) {
            enemyTeam.add(pokemon);
            if (enemyTeam.size() == 1) {
                enemyPokemon = enemyTeam.get(0);
                PokemonName2.setText(enemyPokemon.getName());
                pokemon2.setImage(enemyPokemon.getImage());
                updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
            }
        }
    }

    public void setSelectedAttacks(List<Attackable> selectedAttacks) {
        selectedAttack1 = selectedAttacks.size() > 0 ? selectedAttacks.get(0) : null;
        selectedAttack2 = selectedAttacks.size() > 1 ? selectedAttacks.get(1) : null;
        selectedAttack3 = selectedAttacks.size() > 2 ? selectedAttacks.get(2) : null;
        selectedAttack4 = selectedAttacks.size() > 3 ? selectedAttacks.get(3) : null;

        attackButton1.setText(selectedAttack1 != null ? selectedAttack1.getName() : "None");
        attackButton2.setText(selectedAttack2 != null ? selectedAttack2.getName() : "None");
        attackButton3.setText(selectedAttack3 != null ? selectedAttack3.getName() : "None");
        attackButton4.setText(selectedAttack4 != null ? selectedAttack4.getName() : "None");
    }


    
    public void setPokemonAttacks(HashMap<Pokemon, List<Attackable>> pokemonAttacks) {
        this.pokemonAttacksMap = pokemonAttacks;
        for (Pokemon p : pokemonAttacksMap.keySet()) {
        }

        if (playerPokemon != null && pokemonAttacks.containsKey(playerPokemon)) {
            setSelectedAttacks(pokemonAttacks.get(playerPokemon));
        }
    }



    public void initialize() {
        TypeTools.initializeTypeRelations();
        setEnemyTeam();
        configureAttackButtons();
        currentRound = new Round();
  
    }
    
    private void showTeamOnBattle() {
        HashMap<Integer, StackPane> teamPositions = new HashMap<>();
        teamPositions.put(0, PokemonInTeam1);
        teamPositions.put(1, PokemonInTeam2);
        teamPositions.put(2, PokemonInTeam3);
        teamPositions.put(3, PokemonInTeam4);
        teamPositions.put(4, PokemonInTeam5);
        teamPositions.put(5, PokemonInTeam6);

        for (int i = 0; i < playerTeam.size(); i++) {
            Pokemon currentPokemon = playerTeam.get(i);
            ImageView pokemonImageView = new ImageView(currentPokemon.getImage());
            pokemonImageView.setFitWidth(50);
            pokemonImageView.setFitHeight(50);

            StackPane currentVBox = teamPositions.get(i);
            if (currentVBox != null) {
                currentVBox.getChildren().clear();
                currentVBox.getChildren().add(pokemonImageView);
                pokemonImageView.setOnMouseClicked(event -> {
                    if (playerTurn) {
                        switchPokemon(currentPokemon);
                    }
                });
            }
        }
        determineFirstTurn();
    }


    private void switchPokemon(Pokemon newPokemon) {
        if (newPokemon != playerPokemon) {
            playerPokemon = newPokemon;
            PokemonName.setText(playerPokemon.getName());
            pokemon.setImage(playerPokemon.getImageReverse());
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            setSelectedAttacks(pokemonAttacksMap.getOrDefault(playerPokemon, new ArrayList<>()));
            nextTurn();
        }
    }

    
    private void determineFirstTurn() {
        if (playerPokemon.getSpeed() >= enemyPokemon.getSpeed()) {
            playerTurn = true;
        } else {
            playerTurn = false;
            nextTurn();
        }
    }


    private void configureAttackButtons() {
        attackButton1.setOnAction(event -> { if (playerTurn) executeAttack(selectedAttack1); });
        attackButton2.setOnAction(event -> { if (playerTurn) executeAttack(selectedAttack2); });
        attackButton3.setOnAction(event -> { if (playerTurn) executeAttack(selectedAttack3); });
        attackButton4.setOnAction(event -> { if (playerTurn) executeAttack(selectedAttack4); });
    }
    
    private void animationParalysie(Attackable attack) {
            if (attack instanceof BouleElec) {
            	pokemonParalysie.start(pokemon2,test);
            }
    }

    private void executeAttack(Attackable attack) {
        if (attack != null && playerTurn) {
            double damage = attack.getDamage(playerPokemon, enemyPokemon);
            attack.attack(playerPokemon, enemyPokemon);
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
            pokemonAnimation.start(pokemon2);
            animationParalysie(attack);
            playerTurn = false;
            displayAttackInfo(playerPokemon, attack, enemyPokemon, damage);
            addToRoundHistory(playerPokemon, attack, enemyPokemon, damage);
            nextTurn();
        }
    }

    private void displayAttackInfo(Pokemon attacker, Attackable attack, Pokemon defender, double damage) {
        Label attackInfo = new Label(attacker.getName() + " utilise " + attack.getName() + " et inflige " + String.format("%.2f", damage) + " dégâts à " + defender.getName() + ".");
        roundsContainer.getChildren().add(attackInfo);
    }

    private void updateHealthBar(ProgressBar healthBar, Pokemon pokemon, Text hpText) {
        double progress = pokemon.getMaxHp() > 0 ? (double) pokemon.getHp() / pokemon.getMaxHp() : 0;
        new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(healthBar.progressProperty(), progress, Interpolator.EASE_BOTH))).play();
        hpText.setText(String.format("%.2f%%", progress * 100));
    }

    private void nextTurn() {
        if (!playerTurn) {
            if (enemyPokemon.getHp() > 0) {
                startEnemyTurnTimer();
            } else {
                pokemon2.setVisible(false);
                if (isEnemyTeamDefeated()) {
                    handlePlayerVictory();
                } else {
                    switchEnemyPokemon();
                    startEnemyTurnTimer(); 
                    playerTurn = true;
                }
            }
        }
    }

    private boolean isEnemyTeamDefeated() {
        for (Pokemon pokemon : enemyTeam) {
            if (pokemon.getHp() > 0) {
                return false; 
            }
        }
        return true;
    }

    private void handlePlayerVictory() {
    	 Label victory = new Label("Player a gagné");
    	roundsContainer.getChildren().add(victory);

    }

    private void startEnemyTurnTimer() {
        new Timeline(new KeyFrame(Duration.millis(500), event -> enemyAction())).play();
    }
    private Attackable chooseBestAttack() {
        Attackable bestAttack = null;
        double maxDamage = 0;
        for (Attackable attack : enemyPokemon.getAttacks()) {
            double potentialDamage = attack.getDamage(enemyPokemon, playerPokemon);
            if (potentialDamage > maxDamage) {
                maxDamage = potentialDamage;
                bestAttack = attack;
            }
        }

        return bestAttack != null ? bestAttack : enemyPokemon.getAttacks()[0];
    }


    private void enemyAction() {
        if (enemyPokemon.getHp() <= 0) {
            switchEnemyPokemon();
            if (enemyPokemon != null) {
                playerTurn = false;
                startEnemyTurnTimer();
            }
            return;
        }
        Attackable bestAttack = chooseBestAttack();
        if (bestAttack != null) {
            double damage = bestAttack.getDamage(enemyPokemon, playerPokemon);
            bestAttack.attack(enemyPokemon, playerPokemon);
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            pokemonAnimation.start(pokemon);
            animationParalysie(bestAttack);
            displayAttackInfo(enemyPokemon, bestAttack, playerPokemon, damage);
            addToRoundHistory(enemyPokemon, bestAttack, playerPokemon, damage);
        }
        playerTurn = true;
    }

    private void switchEnemyPokemon() {
        enemyPokemon = getNextAvailableEnemyPokemon();
        if (enemyPokemon != null) {
            pokemon2.setImage(enemyPokemon.getImage());
            pokemon2.setVisible(true);
            PokemonName2.setText(enemyPokemon.getName());
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
        }
    }

    private Pokemon getNextAvailableEnemyPokemon() {
        for (Pokemon pokemon : enemyTeam) {
            if (pokemon.getHp() > 0) {
                return pokemon;
            }
        }
        return null;
    }
    
    private void addToRoundHistory(Pokemon attacker, Attackable attack, Pokemon defender, double damage) {
        String roundInfo = attacker.getName() + " utilise " + attack.getName() +
                           " et inflige " + String.format("%.2f", damage) + " dégâts à " + defender.getName() + ".";

        if (currentRound == null) {
            currentRound = new Round();
        }
        currentRound.addAction(roundInfo);
        if (currentRound.getActions().size() == 2) {
            roundHistory.add(currentRound);
            currentRound = new Round();
            currentRoundNumber++;
        }
        displayRoundHistory();
    }

    private void displayRoundHistory() {
        roundsContainer.getChildren().clear();
        for (int i = 0; i < roundHistory.size(); i++) {
            Label roundLabel = new Label("Round " + (i + 1));
            roundLabel.setStyle("-fx-font-weight: bold;"); 
            roundsContainer.getChildren().add(roundLabel);

            for (String action : roundHistory.get(i).getActions()) {
                Label actionLabel = new Label(action);
                roundsContainer.getChildren().add(actionLabel);
            }
        }
    }


}
