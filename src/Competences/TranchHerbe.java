package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class TranchHerbe implements Attackable {
    private Type type = Type.PLANTE;
    private String name = "Tranch’Herbe";
    private double baseDamage = 30;
    private double accuracy = 95;
    private int pp = 25;
    private double criticalHitRate = 0.2;

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        if (Math.random() * 100 <= accuracy) {
            double damage = getDamage(attacker, target); // Prend en compte le coup critique
            target.takeDamage(damage);
            System.out.println(attacker.getName() + " utilise " + name + " et inflige " + damage + " dégâts à " + target.getName() + " !");
        } else {
            System.out.println(attacker.getName() + " a raté son attaque " + name + " !");
        }
    }

    public double getDamage(Pokemon attacker, Pokemon target) {
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        double damage = baseDamage * effectiveness;

        
        return damage;
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

}
