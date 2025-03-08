package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class MitraPoing implements Attackable {
    private Type type = Type.NORMAL;
    private String name = "Mitra Poing";

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double damage = getDamage(attacker, target);
        target.takeDamage(damage);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getDamage(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed();
        int hitNumber = (int)(Math.random() * 4 + 2);
        double baseDamage = (4 * (speed / 100) + 10) * hitNumber;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness;
    }
}
