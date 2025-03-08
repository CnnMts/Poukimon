package Pokemon;

 
import Competences.Belier;
import Competences.Dé20;
import Competences.Plaquage;
import Competences.SpaceCake;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Onix extends Pokemon {

    public Onix () {
        super("Onix ", new Type[]{Type.ROCHE, Type.SOL}, 35, 70,
              new Attackable[]{new Dé20(),new Belier(),
              		new Plaquage(), new SpaceCake()});
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
        return new Image(getClass().getResourceAsStream("/onix_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/onix_back.png"));
    }
}
