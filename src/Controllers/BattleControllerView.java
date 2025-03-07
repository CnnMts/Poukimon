package Controllers;

import Animation.PokemonAnimation;
import Models.TypeTools;
import Pokemon.Evoli;
import Pokemon.Pikachu;
import Models.Pokemon;
import Models.Attackable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BattleControllerView {
    @FXML private Button attackButton1, attackButton2, defenseButton1, defenseButton2;
    @FXML private ImageView pokemon, pokemon2;
    @FXML private ProgressBar playerHealthBar, enemyHealthBar;
    @FXML private Text PokemonName, PokemonName2, statusText, HpPokemon, HpPokemon2;
    @FXML private StackPane PokemonInTeam1, PokemonInTeam2, PokemonInTeam3, PokemonInTeam4, PokemonInTeam5, PokemonInTeam6;
    @FXML private Label roundLabel;

    private List<Pokemon> playerTeam = new ArrayList<>();
    private List<Pokemon> enemyTeam = new ArrayList<>();
    private HashMap<Pokemon, List<Attackable>> pokemonAttacksMap = new HashMap<>();
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private boolean playerTurn = true;
    private Attackable selectedAttack1;
    private Attackable selectedAttack2;
    private PokemonAnimation pokemonAnimation = new PokemonAnimation();
    private int currentRound = 1;
    private Random random = new Random();

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

    public void setEnemyTeam(List<Pokemon> team) {
        this.enemyTeam = new ArrayList<>(team);
        if (!enemyTeam.isEmpty()) {
            enemyPokemon = enemyTeam.get(0);
            PokemonName2.setText(enemyPokemon.getName());
            pokemon2.setImage(enemyPokemon.getImage());
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
        }
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

    public void setPokemonAttacks(HashMap<Pokemon, List<Attackable>> pokemonAttacks) {
        this.pokemonAttacksMap = pokemonAttacks;

        System.out.println("Attaques enregistrées :");
        for (Pokemon p : pokemonAttacksMap.keySet()) {
            System.out.println(p.getName() + " → " + pokemonAttacksMap.get(p));
        }

        if (playerPokemon != null && pokemonAttacks.containsKey(playerPokemon)) {
            setSelectedAttacks(pokemonAttacks.get(playerPokemon));
        }
    }

    public void initialize() {
        TypeTools.initializeTypeRelations();
        configureAttackButtons();
        addPokemonToEnemyTeam(new Pikachu());
        addPokemonToEnemyTeam(new Evoli());
    }

    private void configureAttackButtons() {
        attackButton1.setOnAction(event -> {
            if (playerTurn) {
                executeAttack(selectedAttack1);
            }
        });
        attackButton2.setOnAction(event -> {
            if (playerTurn) {
                executeAttack(selectedAttack2);
            }
        });
    }

    private void executeAttack(Attackable attack) {
        if (attack != null && playerTurn) {
            attack.attack(playerPokemon, enemyPokemon);
            updateHealthBar(playerHealthBar,playerPokemon, HpPokemon);
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
            pokemonAnimation.start(pokemon2);
            playerTurn = false;
            nextTurn();
        }
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

    public void setSelectedAttacks(List<Attackable> selectedAttacks) {
        selectedAttack1 = selectedAttacks.size() > 0 ? selectedAttacks.get(0) : null;
        selectedAttack2 = selectedAttacks.size() > 1 ? selectedAttacks.get(1) : null;
        attackButton1.setText(selectedAttack1 != null ? selectedAttack1.getName() : "None");
        attackButton2.setText(selectedAttack2 != null ? selectedAttack2.getName() : "None");
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

    private void updateHealthBar(ProgressBar healthBar, Pokemon pokemon, Text hpText) {
        double progress = pokemon.getMaxHp() > 0 ? (double) pokemon.getHp() / pokemon.getMaxHp() : 0;
        new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(healthBar.progressProperty(), progress, Interpolator.EASE_BOTH))).play();
        hpText.setText(String.format("%.2f%%", progress * 100));
    }

    private void determineFirstTurn() {
        if (playerPokemon.getSpeed() >= enemyPokemon.getSpeed()) {
            playerTurn = true;
        } else {
            playerTurn = false;
            nextTurn();
        }
    }

    private void nextTurn() {
        if (playerTurn) {
            statusText.setText("C'est votre tour.");
        } else {
            statusText.setText("C'est au tour de l'adversaire.");
            if (enemyPokemon.getHp() > 0) {
                startEnemyTurnTimer();
            } else {
                if (enemyPokemon.getHp() <= 0) {
                    pokemon2.setVisible(false);
                    switchEnemyPokemon();
                }
            }
            if (playerPokemon.getHp() <= 0) {
                pokemon.setVisible(false);
            }
            currentRound++;
            roundLabel.setText("Round " + currentRound);
        }
    }

    private void startEnemyTurnTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> enemyAction()));
        timeline.play();
    }

    private void enemyAction() {
        if (random.nextBoolean()) {
            if (enemyPokemon.getAttacks().length > 0) {
                enemyPokemon.getAttacks()[0].attack(enemyPokemon, playerPokemon);
                updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
                pokemonAnimation.start(pokemon);
            }
        } else {
            switchEnemyPokemon();
        }
        playerTurn = true;
        statusText.setText("C'est votre tour.");
    }

    private void switchEnemyPokemon() {
        int currentIndex = enemyTeam.indexOf(enemyPokemon);
        if (currentIndex < enemyTeam.size() - 1) {
            enemyPokemon = enemyTeam.get(currentIndex + 1);
            PokemonName2.setText(enemyPokemon.getName());
            pokemon2.setImage(enemyPokemon.getImage());
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
            pokemon2.setVisible(true);
        } else {
            pokemon2.setVisible(false);
        }
    }
}
