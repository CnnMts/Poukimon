package Pokemon;

 
import Competences.Belier;
import Competences.ExtensionTerritoire;
import Competences.Flemme;
import Competences.HydroCanon;
import Competences.SpaceCake;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Squirtle extends Pokemon {

    public Squirtle () {
        super("Squirtle ", new Type[]{Type.EAU}, 44, 43,
              new Attackable[]{new HydroCanon(), new Flemme(), 
              		new ExtensionTerritoire(), new SpaceCake(), new Belier()});
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
        return new Image(getClass().getResourceAsStream("/squirtle_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/squirtle_back.png"));
    }
}
