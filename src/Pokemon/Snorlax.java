package Pokemon;

 
import Competences.Belier;
import Competences.BouleElec;
import Competences.Flemme;
import Competences.Plaquage;
import Competences.SpaceCake;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Snorlax extends Pokemon {

    public Snorlax() {
        super("Ronflex", new Type[]{Type.NORMAL}, 160, 30,
              new Attackable[]{new SpaceCake(), new Plaquage(), new BouleElec(), new Flemme()});
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
        return new Image(getClass().getResourceAsStream("/snorlax_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/snorlax_back.png"));
    }
}
