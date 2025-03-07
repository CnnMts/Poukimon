package Pokemon;

import Competences.BouleElec;
import Competences.ToileEleck;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Salameche extends Pokemon {

    public Salameche() {
        super("Salameche", new Type[]{Type.FEU}, 35, 90,
              new Attackable[]{new BouleElec(), new ToileEleck()});
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Salamèche utilise Éclair Fou !");
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
        return new Image(getClass().getResourceAsStream("/charmander_face.png"));
    }
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/charmander_back.png"));
    }
}
