package Items;

import Models.Pokemon;

public class BaieStatus{
	
	private Boolean baie ;
	
	public BaieStatus() {
		this.baie = true;
	}
	
	public void use(Pokemon pokemon) {
		pokemon.setCanAttack(baie);
	}

}