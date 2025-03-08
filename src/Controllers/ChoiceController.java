package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import Items.BaieStatus;
import Items.Potion;
import Models.Attackable;
import Models.Pokemon;
import Pokemon.Bulbasaur;
import Pokemon.Charmander;
import Pokemon.Evee;
import Pokemon.Gengar;
import Pokemon.Mewtow;
import Pokemon.Onix;
import Pokemon.Pikachu;
import Pokemon.Snorlax;
import Pokemon.Squirtle;
import Pokemon.Suicune;
import Views.BattleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Competences.Belier;
import Competences.BouleElec;
import Competences.Dé20;
import Competences.ExtensionTerritoire;
import Competences.Flemme;
import Competences.HydroCanon;
import Competences.LanceFlamme;
import Competences.MegaFouet;
import Competences.MitraPoing;
import Competences.Plaquage;
import Competences.PoudreToxik;
import Competences.SpaceCake;
import Competences.ToileEleck;
import Competences.Tonnerre;
import Competences.TranchHerbe;

public class ChoiceController {
	@FXML
	private HBox Pokemon1, Pokemon2, Pokemon3, Pokemon4, Pokemon5, Pokemon6;
	@FXML
	private ImageView ImgPokemon;
	@FXML
	private ComboBox<String> listAtk, listAtk2, listAtk3, listAtk4, 
	listPokemon, listItems;
	@FXML
	private Text PokemonNameTitle;
	@FXML
	private ProgressBar HpBar, SpeedBar;
	@FXML
	private Button Add;

	protected List<Pokemon> team = new ArrayList<>();
	private ObservableList<String> pokemonList;
	private String selectedAttack;
	private HBox[] pokemonBoxes;
	private HashMap<Pokemon, List<Attackable>> pokemonAttacks = new HashMap<>();
	private HashMap<String, Pokemon> pokemonMap = new HashMap<>();
	private ArrayList<Object> listItemsArray = new ArrayList<>();

	@FXML
	private void initialize() {
		initializePokemonMap();
		pokemonList = FXCollections.observableArrayList("Pikachu", "Bulbizarre", 
				"Evoli", "Salameche", "Mewtwo", "Ronflex",
				"Ectoplasma", "Onix", "Carapuce", "Suicune");
		listPokemon.setItems(pokemonList);
		pokemonBoxes = new HBox[] { Pokemon1, Pokemon2, Pokemon3, Pokemon4, 
				Pokemon5, Pokemon6 };
		List<Pokemon> pokemonData = new ArrayList<>();
		pokemonData.add(new Pikachu());
		Pokemon1.setUserData(pokemonData);
		viewPokemon(pokemonData.get(0));

		selectionListener();

		listAtk.setOnAction(event -> {
			selectedAttack = listAtk.getValue();
		});

		initializeItems();
	}

	private void initializePokemonMap() {
		pokemonMap.put("Pikachu", new Pikachu());
		pokemonMap.put("Bulbizarre", new Bulbasaur());
		pokemonMap.put("Evoli", new Evee());
		pokemonMap.put("Salameche", new Charmander());
		pokemonMap.put("Mewtwo", new Mewtow());
		pokemonMap.put("Ronflex", new Snorlax());
		pokemonMap.put("Ectoplasma", new Gengar());
		pokemonMap.put("Onix", new Onix());
		pokemonMap.put("Carapuce", new Squirtle());
		pokemonMap.put("Suicune", new Suicune());
	}

	private void selectionListener() {
		listPokemon.setOnAction(event -> {
			if (listPokemon.getValue() != null) {
				String selectedPokemonName = listPokemon.getValue();
				List<Pokemon> currentPokemonData = 
						(List<Pokemon>) Pokemon1.getUserData();
				Pokemon newPokemon = getPokemonByName(selectedPokemonName);
				if (newPokemon != null) {
					currentPokemonData.add(newPokemon);
					Pokemon1.setUserData(currentPokemonData);
				}
				viewPokemon(newPokemon);
			}
		});
	}

	private void viewPokemon(Pokemon pokemon) {
		if (pokemon != null) {
			getImgPokemon(pokemon);
			PokemonNameTitle.setText(pokemon.getName());
			listPokemon.setPromptText(pokemon.getName());
			String[] attackNamesArray = pokemon.getAttackNames();
			List<String> attackNamesList = 
					new ArrayList<>(Arrays.asList(attackNamesArray));
			listAtk.getItems().setAll(attackNamesList);
			listAtk2.getItems().setAll(attackNamesList);
			listAtk3.getItems().setAll(attackNamesList);
			listAtk4.getItems().setAll(attackNamesList);
			getHpForProgress(pokemon);
			getSpeedPokemon(pokemon);
		}
	}

	private Pokemon getPokemonByName(String name) {
		return pokemonMap.get(name);
	}

	@FXML
	public void switchBattleScene() throws IOException {
		String selectedAttackName1 = listAtk.getValue();
		String selectedAttackName2 = listAtk2.getValue();
		String selectedAttackName3 = listAtk3.getValue();
		String selectedAttackName4 = listAtk4.getValue();
		if (selectedAttackName1 != null && selectedAttackName2 != null) {
			Attackable selectedAttack1 = getAttackByName(selectedAttackName1);
			Attackable selectedAttack2 = getAttackByName(selectedAttackName2);
			Attackable selectedAttack3 = getAttackByName(selectedAttackName3);
			Attackable selectedAttack4 = getAttackByName(selectedAttackName4);
			Pokemon currentPokemon = team.get(team.size() - 1);
			List<Attackable> attacks = 
					pokemonAttacks.getOrDefault(currentPokemon, new ArrayList<>());
			attacks.clear();
			attacks.add(selectedAttack1);
			attacks.add(selectedAttack2);
			attacks.add(selectedAttackName3 != null ? selectedAttack3 : null);
			attacks.add(selectedAttackName4 != null ? selectedAttack4 : null);
			pokemonAttacks.put(currentPokemon, attacks);
		}

		BaieStatus baie = getBaieByName("Baie");
		Potion potion = getPotionByName("Potion");

		BattleView.switchBattleScene("/Battle.fxml",
				team, pokemonAttacks, baie, potion);
	}

	private Attackable getAttackByName(String attackName) {
		if (attackName == null)
			return null;

		HashMap<String, Attackable> attacks = new HashMap<>();
		attacks.put("Boule Eleckt", new BouleElec());
		attacks.put("Toile Eleckt", new ToileEleck());
		attacks.put("Tonnerre", new Tonnerre());
		attacks.put("Mégafouet", new MegaFouet());
		attacks.put("Tranch’Herbe", new TranchHerbe());
		attacks.put("Poudre Toxik", new PoudreToxik());
		attacks.put("Dé20", new Dé20());
		attacks.put("Extension du Territoire", new ExtensionTerritoire());
		attacks.put("Flemme", new Flemme());
		attacks.put("Hydro Canon", new HydroCanon());
		attacks.put("LanceFlamme", new LanceFlamme());
		attacks.put("Mitra Poing", new MitraPoing());
		attacks.put("Plaquage", new Plaquage());
		attacks.put("SpaceCake", new SpaceCake());
		attacks.put("Belier", new Belier());
		return attacks.get(attackName);
	}

	private BaieStatus getBaieByName(String name) {
		if ("Baie".equalsIgnoreCase(name)) {
			return new BaieStatus();
		}
		return null;
	}

	private Potion getPotionByName(String name) {
		if ("Potion".equalsIgnoreCase(name)) {
			return new Potion();
		}
		return null;
	}

	private void getImgPokemon(Pokemon pokemon) {
		ImgPokemon.setImage(pokemon.getImage());
	}

	private void getHpForProgress(Pokemon pokemon) {
		if (pokemon != null) {
			int hp = pokemon.getHp();
			double progressValue = (double) hp / 200.0;
			HpBar.setProgress(progressValue);
		}
	}

	@FXML
	public void addPokemonToTeam() {
		String selectedPokemonName = listPokemon.getValue();
		Pokemon newPokemon = getPokemonByName(selectedPokemonName);
		if (newPokemon != null && !team.contains(newPokemon)) {
			String selectedAttackName1 = listAtk.getValue();
			String selectedAttackName2 = listAtk2.getValue();
			String selectedAttackName3 = listAtk3.getValue();
			String selectedAttackName4 = listAtk4.getValue();

			List<Attackable> attacks = new ArrayList<>();
			if (selectedAttackName1 != null)
				attacks.add(getAttackByName(selectedAttackName1));
			if (selectedAttackName2 != null)
				attacks.add(getAttackByName(selectedAttackName2));
			if (selectedAttackName3 != null)
				attacks.add(getAttackByName(selectedAttackName3));
			if (selectedAttackName4 != null)
				attacks.add(getAttackByName(selectedAttackName4));

			pokemonAttacks.put(newPokemon, attacks);

			team.add(newPokemon);
			int teamSize = team.size();
			if (teamSize <= 6) {
				addPokemonToHBox(teamSize - 1, newPokemon);
			}
		}
	}

	private void getSpeedPokemon(Pokemon pokemon) {
		if (pokemon != null) {
			double progressValue = pokemon.getSpeed() / 200.0;
			SpeedBar.setProgress(progressValue);
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

	private void initializeItems() {
		ObservableList<String> itemList = 
				FXCollections.observableArrayList("Baie", "Potion");
		listItems.setItems(itemList);
		listItemsArray.add(getBaieByName("Baie"));
		listItemsArray.add(getPotionByName("Potion"));
		listItems.setOnAction(event -> {
			String selectedItem = listItems.getValue();
			if (selectedItem != null) {
				useItem(selectedItem);
			}
		});
	}

	private void useItem(String itemName) {
		Pokemon currentPokemon = team.isEmpty() ? null : team.get(team.size() - 1);
		if (currentPokemon != null) {
			if ("Baie".equalsIgnoreCase(itemName)) {
				BaieStatus baie = (BaieStatus) listItemsArray.stream().filter(item ->
				item instanceof BaieStatus).findFirst()
						.orElse(null);
				if (baie != null) {
					baie.use(currentPokemon);
				}
			} else if ("Potion".equalsIgnoreCase(itemName)) {
				Potion potion = (Potion) listItemsArray.stream().filter(item -> 
				item instanceof Potion).findFirst()
						.orElse(null);
				if (potion != null) {
					potion.usePotion(currentPokemon);
				}
			}
		}
	}
}
