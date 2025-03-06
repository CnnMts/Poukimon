package Competences;

import Models.Defendable;
import Models.Pokemon;
import Models.Type;

public class Belier implements Defendable {
    private Pokemon enemyPokemon;
    private Pokemon playerPokemon;
    private Type type = Type.NORMAL;

    
    public Belier() {
        this.enemyPokemon = null;
        this.playerPokemon = null;
    }

    public void setTarget(Pokemon target, Pokemon attacker) {
        this.enemyPokemon = target;
        this.playerPokemon = attacker;
    }

    @Override
    public void defend(Pokemon target) {
        if (enemyPokemon == null || playerPokemon == null) {
            throw new IllegalStateException("Targets are not set.");
        }
        double damage = 20;
        enemyPokemon.takeDamage(damage);
        playerPokemon.takeDamage(damage - 8);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return "Belier";
    }
}
