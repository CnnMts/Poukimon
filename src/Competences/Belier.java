package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class Belier implements Attackable {
    private Type type = Type.NORMAL;
    protected int damage = 20;
    
    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        target.takeDamage(damage);
        attacker.takeDamage(damage - 8);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return "Belier";
    }
    @Override
    public double getDamage(Pokemon attacker, Pokemon target) {
        double baseDamage = 20;
        double effectiveness = 
        		TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness;
    }


}
