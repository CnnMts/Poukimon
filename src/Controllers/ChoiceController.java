package Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ChoiceController {
    @FXML
    private HBox Pokemon1;

    @FXML
    private ImageView ImgPokemon; 

    @FXML
    private ComboBox<String> listAtk; 
    
    @FXML
    private ComboBox<String> listAtk2;

    @FXML
    private ComboBox<String> listPokemon;

    @FXML
    private Text PokemonNameTitle; 

    private ObservableList<String> pokemonList;

    private String selectedAttack;

    @FXML
    private void initialize() {
        pokemonList = FXCollections.observableArrayList(
            "Pikachu", "Bulbizarre", "Evoli", "Salameche"
        );
        listPokemon.setItems(pokemonList);
        List<Pokemon> pokemonData = new ArrayList<>();
        pokemonData.add(new Pikachu());
        Pokemon1.setUserData(pokemonData);
        afficherPokemon(pokemonData.get(0));
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
                }afficherPokemon(newPokemon);
            }});    
    }

    private void afficherPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            ImgPokemon.setImage(pokemon.getImage());
            PokemonNameTitle.setText(pokemon.getName());
            listPokemon.setPromptText(pokemon.getName());

            
            String[] attackNamesArray = pokemon.getAttackNames();
            String[] defendNameArray = pokemon.getDefendsNames();
            List<String> attackNamesList = new ArrayList<>(Arrays.asList(attackNamesArray));
            List<String> defendNameList = new ArrayList<>(Arrays.asList(defendNameArray));

            
            listAtk.getItems().setAll(attackNamesList);
            listAtk2.getItems().setAll(attackNamesList);

            
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
        String selectedAttackName = listAtk.getValue();
        String selectedAttackName2 = listAtk2.getValue();
        Attackable selectedAttack1 = getAttackByName(selectedAttackName);
        Attackable selectedAttack2 = getAttackByName(selectedAttackName2);

        // Créez un tableau d'attaques
        Attackable[] selectedAttacks = {selectedAttack1, selectedAttack2};

        // Passez le tableau d'attaques à BattleView
        BattleView.switchBattleScene("/Battle.fxml", selectedAttacks);
    }




    private Attackable getAttackByName(String attackName) {
        switch (attackName) {
            case "Boule Eleckt":
                return new BouleElec();
            case "Toile Eleckt":
                return new ToileEleck();
            case "Tonnerre":
                return new Tonnerre();
            default:
                return null;
        }
    }

}
