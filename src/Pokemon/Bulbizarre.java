package Pokemon;

 
import Models.Attackable;
import Models.Defendable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Bulbizarre extends Pokemon {

    public Bulbizarre() {
        super("Bulbizarre", new Type[]{Type.PLANTE, Type.POISON}, 45, 45,
              new Attackable[]{}, 
              new Defendable[]{});
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
        return new Image(getClass().getResourceAsStream("/bulbizarre.png"));
    }
}
