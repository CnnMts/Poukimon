package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class BouleElec implements Attackable {
    private Type type = Type.ELECTRIQUE;
    private Pokemon enemyPokemon;

    public BouleElec(Pokemon enemyPokemon) {
        this.enemyPokemon = enemyPokemon;
    }

    public void setTarget(Pokemon target) {
        this.enemyPokemon = target;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {

        double speed = attacker.getSpeed();
        double baseDamage = 25 * (speed / 100) + 25;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        System.out.println(effectiveness);
        
            double finalDamage = baseDamage * effectiveness;
            target.takeDamage(finalDamage);
        
    }
}
