package Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import Competences.Belier;
import Competences.BouleElec;
import Competences.ToileEleck;
import Competences.Tonnerre;
import Models.Attackable;
import Models.Pokemon;
import Pokemon.Bulbizarre;
import Pokemon.Evoli;
import Pokemon.Pikachu;
import Pokemon.Salameche;
import Views.BattleView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ChoiceController {
  @FXML private HBox Pokemon1;
  @FXML private HBox Pokemon2;
  @FXML private HBox Pokemon3;
  @FXML private HBox Pokemon4;
  @FXML private HBox Pokemon5;
  @FXML private HBox Pokemon6;
  @FXML private ImageView ImgPokemon;
  @FXML private ComboBox<String> listAtk;
  @FXML private ComboBox<String> listAtk2;
  @FXML private ComboBox<String> listPokemon;
  @FXML private Text PokemonNameTitle; 
  @FXML private ProgressBar HpBar;
  @FXML private ProgressBar SpeedBar;
  @FXML private Button Add;
    

    protected List<Pokemon> team = new ArrayList<>();
    private ObservableList<String> pokemonList;
    private String selectedAttack;
    private HBox[] pokemonBoxes;
    private HashMap<Pokemon, List<Attackable>> pokemonAttacks = new HashMap<>();


    @FXML
    private void initialize() {
        pokemonList = FXCollections.observableArrayList(
            "Pikachu", "Bulbizarre", "Evoli", "Salameche"
        );
        listPokemon.setItems(pokemonList);
        pokemonBoxes = new HBox[]{Pokemon1, Pokemon2, Pokemon3, Pokemon4, Pokemon5, Pokemon6};
        List<Pokemon> pokemonData = new ArrayList<>();
        pokemonData.add(new Pikachu());
        Pokemon1.setUserData(pokemonData);
        viewPokemon(pokemonData.get(0));

        selectionListener();
        
        listAtk.setOnAction(event -> {
            selectedAttack = listAtk.getValue();  
        });
    }


    private void selectionListener() {
      listPokemon.setOnAction(event -> {
        if (listPokemon.getValue() != null) {
            String selectedPokemonName = listPokemon.getValue();
            List<Pokemon> currentPokemonData = (List<Pokemon>) 
            		Pokemon1.getUserData();
            Pokemon newPokemon = getPokemonByName(selectedPokemonName);
            if (newPokemon != null) {
             currentPokemonData.add(newPokemon);
             Pokemon1.setUserData(currentPokemonData);
             
                }viewPokemon(newPokemon);
            }});    
    }

    private void viewPokemon(Pokemon pokemon) {
        if (pokemon != null) {
        	getImgPokemon(pokemon);
            PokemonNameTitle.setText(pokemon.getName());
            listPokemon.setPromptText(pokemon.getName());
            String[] attackNamesArray = pokemon.getAttackNames();
            String[] defendNameArray = pokemon.getDefendsNames();
            List<String> attackNamesList = new ArrayList<>(Arrays.
            		asList(attackNamesArray));
            List<String> defendNameList = new ArrayList<>(Arrays.
            		asList(defendNameArray));
            listAtk.getItems().setAll(attackNamesList);
            listAtk2.getItems().setAll(attackNamesList);
            getHpForProgress(pokemon);
            getSpeedPokemon(pokemon);
        }
    }

    private Pokemon getPokemonByName(String name) {
        switch (name) {
            case "Pikachu":
                return new Pikachu();
            case "Bulbizarre":
                return new Bulbizarre();
            case "Evoli":
                return new Evoli();
            case "Salameche":
                return new Salameche();
            default:
                return null;
        }
    }

    @FXML
    public void switchBattleScene() throws IOException {
        String selectedAttackName1 = listAtk.getValue();
        String selectedAttackName2 = listAtk2.getValue();
        
        if (selectedAttackName1 != null && selectedAttackName2 != null) {
            Attackable selectedAttack1 = getAttackByName(selectedAttackName1);
            Attackable selectedAttack2 = getAttackByName(selectedAttackName2);
            
            Pokemon currentPokemon = team.get(team.size() - 1);
            List<Attackable> attacks = pokemonAttacks.getOrDefault(currentPokemon, new ArrayList<>());
            attacks.clear();
            attacks.add(selectedAttack1);
            attacks.add(selectedAttack2);
            pokemonAttacks.put(currentPokemon, attacks);
            System.out.println("🔄 Pokémon ajouté : " + currentPokemon.getName() + " → " + pokemonAttacks.get(currentPokemon));


        }
        BattleView.switchBattleScene("/Battle.fxml", team, pokemonAttacks);
    }


    private Attackable getAttackByName(String attackName) {
        if (attackName == null) return null;

        // Liste des attaques possibles
        HashMap<String, Attackable> attacks = new HashMap<>();
        attacks.put("Boule Eleckt", new BouleElec()); // Corrige l'orthographe
        attacks.put("Toile Eleckt", new ToileEleck());
        attacks.put("Tonnerre", new Tonnerre());

        if (!attacks.containsKey(attackName)) {
            System.out.println("⚠️ Attaque inconnue : " + attackName);
            System.out.println("📜 Attaques disponibles : " + attacks.keySet());
            return null;
        }
        
        return attacks.get(attackName);
    }


    
    private void getImgPokemon(Pokemon pokemon) {
    	 ImgPokemon.setImage(pokemon.getImage());
    	 
    }
    private void getHpForProgress(Pokemon pokemon) {
        if (pokemon != null) {
            int hp = pokemon.getHp(); 
            int maxHp = pokemon.getMaxHp();
            double progressValue = (double) hp / maxHp;
            HpBar.setProgress(progressValue);
        }
    }
    

    @FXML
    public void addPokemonToTeam() {
        String selectedPokemonName = listPokemon.getValue();
        Pokemon newPokemon = getPokemonByName(selectedPokemonName);
        if (newPokemon != null && !team.contains(newPokemon)) {
            team.add(newPokemon);
            pokemonAttacks.put(newPokemon, new ArrayList<>());
            int teamSize = team.size();
            if (teamSize <= 6) { 
                addPokemonToHBox(teamSize - 1, newPokemon); 
            }
        }
    }


    
    private void getSpeedPokemon(Pokemon pokemon) {
    	if(pokemon != null) {
    		SpeedBar.setProgress(pokemon.getSpeed());
    	}
    }
    
    public void addPokemonToHBox(int index, Pokemon pokemon) {
        if (index < 0 || index >= pokemonBoxes.length) {
            return;
        }
        HBox pokemonBox = pokemonBoxes[index];
        ImageView pokemonImageView = new ImageView(pokemon.getImage());
        pokemonImageView.setFitWidth(50);
        pokemonImageView.setFitHeight(50);
        Text pokemonNameText = new Text(pokemon.getName());
        pokemonBox.getChildren().clear(); 
        pokemonBox.getChildren().addAll(pokemonImageView, pokemonNameText);
    }


}
