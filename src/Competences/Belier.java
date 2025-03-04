package Competences;

import Models.Defendable;
import Models.Pokemon;

public class Belier implements Defendable {
    private Pokemon enemyPokemon;
    private Pokemon playerPokemon;
    public Belier(Pokemon enemyPokemon, Pokemon playerPokemon) {
        this.enemyPokemon = enemyPokemon;
        this.playerPokemon = playerPokemon;
        }


	public void setTarget(Pokemon target , Pokemon attacker) {
        this.enemyPokemon = target;
        this.playerPokemon = attacker ;
    }
	@Override
	public void defend(Pokemon target) {
		double damage = 20;
		enemyPokemon.takeDamage(damage);
       playerPokemon.takeDamage(damage -8);
        
	}


}