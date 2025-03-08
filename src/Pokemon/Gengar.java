package Pokemon;

 
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Gengar extends Pokemon {

    public Gengar() {
        super("Gengar", new Type[]{Type.SPECTRE, Type.POISON}, 45, 45,
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
        return new Image(getClass().getResourceAsStream("/gengar_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/gengar_back.png"));
    }
}
