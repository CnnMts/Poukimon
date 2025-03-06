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
        double speed = attacker.getSpeed();
        double baseDamage = 40;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        System.out.println("Efficacité de l'attaque : " + effectiveness);
        double finalDamage = baseDamage * effectiveness;
        target.takeDamage(finalDamage);

        System.out.println(attacker.getName() + " utilise Tonnerre et inflige " + finalDamage + " dégâts à " + target.getName() + " !");
    }

    @Override
    public String getName() {
        return "Tonnerre";
    }
}
