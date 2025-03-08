package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class ExtensionTerritoire implements Attackable {
    private Type type = Type.PSY;
    private String name = "Extension du Territoire";

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        	attacker.addAtttack(10);
        	target.reduceAttack(1);

            System.out.println(attacker.getName() + " utilise " + name + 
            		" et gagne 10 points d'attaque et enlève 1 point a " + 
            		target.getName());
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
