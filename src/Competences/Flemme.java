package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;

public class Flemme implements Attackable {
    private Type type = Type.SOL;
    private String name = "Flemme";

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
       
    }

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double getDamage(Pokemon attacker, Pokemon target ) {
		return 0 ;
	}
}
