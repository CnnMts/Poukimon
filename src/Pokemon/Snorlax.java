package Pokemon;

 
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Snorlax extends Pokemon {

    public Snorlax() {
        super("Ronflex", new Type[]{Type.NORMAL}, 45, 45,
              new Attackable[]{});
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Bulbizarre utilise Croissance !");
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
