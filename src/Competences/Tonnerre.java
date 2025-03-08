package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class Tonnerre implements Attackable {
    private Type type = Type.ELECTRIQUE;

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double damage = getDamage(attacker, target);
        target.takeDamage(damage);
    }

    public double getDamage(Pokemon attacker, Pokemon target) {
        double baseDamage = 40;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness;
    }

    @Override
    public String getName() {
        return "Tonnerre";
    }
}
