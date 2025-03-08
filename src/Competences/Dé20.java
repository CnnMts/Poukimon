package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Brulure;

public class Dé20 implements Attackable {
    private Type type = Type.SOL;
    private double accuracy = 30;
    private String name = "Dé20";

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
    	int random20 = (int)(Math.random() * 19) + 1;
    	if(random20 == 20) {
    		target.takeDamage(400);
    	}
    }

	@Override
	public String getName() {
		return name ;
	}
}
