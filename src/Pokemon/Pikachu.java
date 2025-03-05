package Pokemon;

import Competences.BouleElec;
import Competences.ToileEleck;
import Models.Attackable;
import Models.Defendable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", new Type[]{Type.ELECTRIQUE}, 35, 90,
              new Attackable[]{new BouleElec(), new ToileEleck()},
              new Defendable[]{});
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Pikachu utilise Éclair Fou !");
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
        return new Image(getClass().getResourceAsStream("/pikachu.png"));
    }
}
