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
        	attacker.addAttack(10);
        	target.reduceAttack(1);
    }
    
    @Override
    public double getDamage(Pokemon attacker, Pokemon target) {
    	return 0; 
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
