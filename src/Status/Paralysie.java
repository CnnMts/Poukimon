package Status;

import Models.Pokemon;
import Models.StatusEffect;

public class Paralysie implements StatusEffect {
    private String name = "Paralysie";

    @Override
    public void applyEffect(Pokemon target) {
        if (Math.random() <= 0.25) { 
            System.out.println(target.getName() + " est paralysé et ne peut pas attaquer ce tour !");
            target.setCanAttack(false);
        } else {
            target.setCanAttack(true);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
