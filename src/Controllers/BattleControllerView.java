package Controllers;

import Models.TypeTools;
import Pokemon.Evoli;
import Models.Pokemon;
import Models.Attackable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattleControllerView {
    @FXML private Button attackButton1, attackButton2, defenseButton1, defenseButton2;
    @FXML private ImageView pokemon, pokemon2;
    @FXML private ProgressBar playerHealthBar, enemyHealthBar;
    @FXML private Text PokemonName, PokemonName2, statusText, HpPokemon, HpPokemon2;
    @FXML private VBox PokemonInTeam1, PokemonInTeam2, PokemonInTeam3, PokemonInTeam4, PokemonInTeam5, PokemonInTeam6;

    private List<Pokemon> teams = new ArrayList<>();
    private HashMap<Pokemon, List<Attackable>> pokemonAttacksMap = new HashMap<>();
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private boolean playerTurn = true;
    private Attackable selectedAttack1;
    private Attackable selectedAttack2;

    public void setTeam(List<Pokemon> team) {
        this.teams = new ArrayList<>(team);
        if (!teams.isEmpty()) {
            playerPokemon = teams.get(0);
            PokemonName.setText(playerPokemon.getName());
            pokemon.setImage(playerPokemon.getImage());
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            showTeamOnBattle();
        }
    }

    public void setPokemonAttacks(HashMap<Pokemon, List<Attackable>> pokemonAttacks) {
        this.pokemonAttacksMap = pokemonAttacks;

        // Vérifie chaque Pokémon et ses attaques
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
        configureDefendsButtons();
        enemyPokemon = new Evoli();
        PokemonName2.setText(enemyPokemon.getName());
        pokemon2.setImage(enemyPokemon.getImage());
        updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
    }

    private void configureAttackButtons() {
        attackButton1.setOnAction(event -> executeAttack(selectedAttack1));
        attackButton2.setOnAction(event -> executeAttack(selectedAttack2));
    }

    private void configureDefendsButtons() {
        defenseButton1.setOnAction(event -> executeDefense(0));
        defenseButton2.setOnAction(event -> executeDefense(1));
    }

    private void executeAttack(Attackable attack) {
        if (attack != null) {
            attack.attack(playerPokemon, enemyPokemon);
            updateHealthBar(enemyHealthBar, enemyPokemon, HpPokemon2);
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        }
    }

    private void executeDefense(int index) {
        if (playerPokemon.getDefends().length > index) {
            playerPokemon.getDefends()[index].defend(playerPokemon);
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            new Timeline(new KeyFrame(Duration.seconds(1), e -> round())).play();
        }
    }

    private void showTeamOnBattle() {
        HashMap<Integer, VBox> teamPositions = new HashMap<>();
        teamPositions.put(0, PokemonInTeam1);
        teamPositions.put(1, PokemonInTeam2);
        teamPositions.put(2, PokemonInTeam3);
        teamPositions.put(3, PokemonInTeam4);
        teamPositions.put(4, PokemonInTeam5);
        teamPositions.put(5, PokemonInTeam6);

        for (int i = 0; i < teams.size(); i++) {
            Pokemon currentPokemon = teams.get(i);
            ImageView pokemonImageView = new ImageView(currentPokemon.getImage());
            pokemonImageView.setFitWidth(50);
            pokemonImageView.setFitHeight(50);
            VBox currentVBox = teamPositions.get(i);
            if (currentVBox != null) {
                currentVBox.getChildren().clear();
                currentVBox.getChildren().add(pokemonImageView);
                pokemonImageView.setOnMouseClicked(event -> switchPokemon(currentPokemon));
            }
        }
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
            pokemon.setImage(playerPokemon.getImage());
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
            setSelectedAttacks(pokemonAttacksMap.getOrDefault(playerPokemon, new ArrayList<>()));
        }
    }

    private void updateHealthBar(ProgressBar healthBar, Pokemon pokemon, Text hpText) {
        double progress = pokemon.getMaxHp() > 0 ? (double) pokemon.getHp() / pokemon.getMaxHp() : 0;
        new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(healthBar.progressProperty(), progress, Interpolator.EASE_BOTH))).play();
        hpText.setText(String.format("%.2f%%", progress * 100));
    }

    private void round() {
        playerTurn = !playerTurn;
        if (playerTurn) return;
        
        if (Math.random() < 1 && enemyPokemon.getAttacks().length > 0) {
            enemyPokemon.getAttacks()[0].attack(enemyPokemon, playerPokemon);
            updateHealthBar(playerHealthBar, playerPokemon, HpPokemon);
        }
        new Timeline(new KeyFrame(Duration.seconds(1), e -> playerTurn = true)).play();
        if (enemyPokemon.getHp() <= 0) {
            pokemon2.setVisible(false);
        }
    }
}
