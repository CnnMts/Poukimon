package Competences;

import Models.Attackable;
import Models.Pokemon;

public class BouleElec implements Attackable {
    private Pokemon enemyPokemon;

    public BouleElec(Pokemon enemyPokemon) {
        this.enemyPokemon = enemyPokemon;
    }

    public void setTarget(Pokemon target) {
        this.enemyPokemon = target;
    }

    @Override
    public void attack(Pokemon attacker) {
    	System.out.println(enemyPokemon);
        double speed = attacker.getSpeed();
        double damage = 25 * (speed / 100) + 25;
        enemyPokemon.takeDamage(damage);
        System.out.println(damage);
    }
}
