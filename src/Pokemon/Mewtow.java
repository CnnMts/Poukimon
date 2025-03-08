package Pokemon;

 
import Competences.Belier;
import Competences.ExtensionTerritoire;
import Competences.MitraPoing;
import Competences.Plaquage;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Mewtow extends Pokemon {

    public Mewtow() {
        super("Mewtwo", new Type[]{Type.PSY}, 106, 130,
              new Attackable[]{new ExtensionTerritoire(), new Belier(),new MitraPoing(), new Plaquage()});
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
        return new Image(getClass().getResourceAsStream("/mewtwo_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/mewtwo_back.png"));
    }
}
