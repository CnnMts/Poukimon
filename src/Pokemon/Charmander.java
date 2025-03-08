package Pokemon;

import Competences.Belier;
import Competences.BouleElec;
import Competences.Dé20;
import Competences.Flemme;
import Competences.LanceFlamme;
import Competences.ToileEleck;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import javafx.scene.image.Image;

public class Charmander extends Pokemon {

    public Charmander() {
        super("Charmander", new Type[]{Type.FEU}, 39,65,
              new Attackable[]{new LanceFlamme(), new Flemme(), 
              		new Dé20(), new Belier()});
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
