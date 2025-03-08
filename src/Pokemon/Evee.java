package Pokemon;

 
import Competences.BouleElec;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Evee extends Pokemon {

    public Evee() {
        super("Evoli", new Type[]{Type.NORMAL}, 45, 45,
              new Attackable[]{new BouleElec()});
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Evoli utilise Croissance !");
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
        return new Image(getClass().getResourceAsStream("/eevee_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/eevee_back.png"));
    }
}
