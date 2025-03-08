package Competences;
import Animation.BrulureAnimation;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
<<<<<<< Updated upstream
import Status.Paralysie;
import Models.StatusEffect;
=======
import javafx.fxml.FXML;
>>>>>>> Stashed changes
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BouleElec implements Attackable {
<<<<<<< Updated upstream
    private Type type = Type.ELECTRIQUE;
    private ImageView player;
    private StackPane paneParticles;

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double damage = getDamage(attacker, target);
        target.takeDamage(damage);

        // Appliquer l'effet de paralysie
        applyParalysis(target);
    }

    public double getDamage(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed();
        double baseDamage = 25 * (speed / 100) + 25;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness;
    }

    @Override
    public String getName() {
        return "Boule Eleckt";
    }

    public void setPlayer(ImageView player) {
        this.player = player;
    }
=======
	private Type type = Type.ELECTRIQUE;
	private BrulureAnimation pokemonAnimation = new BrulureAnimation();
	@FXML private StackPane stackPane123;
	 @FXML private ImageView pokemon, pokemon2;
	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void attack(Pokemon attacker, Pokemon target, StackPane stackPane123) {
		double speed = attacker.getSpeed();
		double baseDamage = 25 * (speed / 100) + 25;
		double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
		System.out.println(effectiveness);
		double finalDamage = baseDamage * effectiveness;
		target.takeDamage(finalDamage);
		pokemonAnimation.start(pokemon2, stackPane123);
		
		System.out.println(
				attacker.getName() + " utilise Boule Élec et inflige " + finalDamage + " dégâts à " + target.getName() + " !");
	}
>>>>>>> Stashed changes

    public void setPaneParticles(StackPane paneParticles) {
        this.paneParticles = paneParticles;
    }

    private void applyParalysis(Pokemon target) {
        if (Math.random() <= 0.3) { // 30% de chance d'appliquer la paralysie
            StatusEffect paralysie = new Paralysie();
            target.addStatusEffect(paralysie);
            System.out.println(target.getName() + " a été paralysé par Boule Eleckt !");
        }
    }
}
