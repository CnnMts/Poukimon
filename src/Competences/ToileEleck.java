package Competences;

import Models.Attackable;
import Models.Pokemon;

public class ToileEleck implements Attackable {
    private Pokemon enemyPokemon;

    public ToileEleck(Pokemon enemyPokemon) {
        this.enemyPokemon = enemyPokemon;
    }

    public void setTarget(Pokemon target) {
        this.enemyPokemon = target;
    }

    @Override
    public void attack(Pokemon target) {
        double damage = 15;
        enemyPokemon.takeDamage(damage);
        weakness();
    }

    public int weakness() {
        if (Math.random() < 0.3) {
            enemyPokemon.setSpeed(enemyPokemon.getSpeed() - 5);
        }
        return enemyPokemon.getSpeed();
    }
}
