package Items;

import Models.Pokemon;

public class Potion {
	
	private Integer potion ;
	
	public Potion() {
		this.potion = 50;
	}
	
	public void usePotion(Pokemon pokemon) {
		pokemon.addHp(potion);
	}

}