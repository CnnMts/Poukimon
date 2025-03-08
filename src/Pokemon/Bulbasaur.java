package Pokemon;

 
import Competences.MegaFouet;
import Competences.Plaquage;
import Competences.PoudreToxik;
import Competences.TranchHerbe;
import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Status.Paralysie;
import javafx.scene.image.Image;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbizarre", new Type[]{Type.PLANTE, Type.POISON}, 45, 45,
              new Attackable[]{new MegaFouet(), new TranchHerbe(),
              		new PoudreToxik(), new Plaquage()});
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
        return new Image(getClass().getResourceAsStream("/bulbasaur_face.png"));
    }
    
    public Image getImageReverse() {
        return new Image(getClass().getResourceAsStream("/bulbasaur_back.png"));
    }

}
