package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;

public class Belier implements Attackable {
    private Type type = Type.NORMAL;
    
    @Override
	public void attack(Pokemon attacker, Pokemon target) {
        double damage = 20;
       target.takeDamage(damage);
       System.out.println("cc");
       System.out.println(target);
       attacker.takeDamage(damage - 8);
       System.out.println(attacker);
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

