package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class MegaFouet implements Attackable {
    private Type type = Type.PLANTE;
    private String name = "Mégafouet";
    private double baseDamage = 20;
    private double accuracy = 85;
    private int pp = 10;

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        if (Math.random() * 100 <= accuracy) {
            double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
            double finalDamage = baseDamage * effectiveness;

            target.takeDamage(finalDamage);
        } else {
        }
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getPP() {
        return pp;
    }

    @Override
    public double getDamage(Pokemon attacker, Pokemon target) {
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        double finalDamage = baseDamage * effectiveness;
        return (int) finalDamage;
    }
}
