package Competences;
import Animation.BrulureAnimation;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Paralysie;
import Models.StatusEffect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BouleElec implements Attackable {
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

    public void setPaneParticles(StackPane paneParticles) {
        this.paneParticles = paneParticles;
    }

    private void applyParalysis(Pokemon target) {
        if (Math.random() <= 0.3) {
            StatusEffect paralysie = new Paralysie();
            target.addStatusEffect(paralysie);
        }
    }
}
