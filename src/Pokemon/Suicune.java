package Pokemon;

 
import Competences.Belier;
import Competences.HydroCanon;
import Competences.Plaquage;
import Competences.TranchHerbe;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Suicune extends Pokemon {

    public Suicune () {
        super("Suicune ", new Type[]{Type.EAU}, 100, 85,
              new Attackable[]{new HydroCanon(), new Belier(), new TranchHerbe(), new Plaquage()});
    }

    @Override
    public void useSpecialAbility() {

    }

    public void useAttack(int attackIndex, Pokemon target) {
        if (attackIndex >= 0 && attackIndex < getAttacks().length) {
            Attackable attack = getAttacks()[attackIndex];
            attack.attack(this, target);
        } else {
            System.out.println("Index d'attaque invalide.");
        }
    }
    public Image getImage() {
        return new Image(getClass().getResourceAsStream("/suicune_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/suicune_back.png"));
    }
}
