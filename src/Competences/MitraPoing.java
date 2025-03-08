package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class MitraPoing implements Attackable {
    private Type type = Type.NORMAL;
    private String name = "Mitra Poing";

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed();
        int hitNumber = (int)(Math.random()*4 +2);
        double baseDamage = (4 * (speed / 100) + 10) * hitNumber;
        double effectiveness = TypeTools.getEffectiveness(this.type, 
        		target.getDefensiveTypes());
        System.out.println(effectiveness);
        double finalDamage = baseDamage * effectiveness;
        target.takeDamage(finalDamage);
        
        System.out.println(attacker.getName() + " utilise " + name 
        		+ " frappes"+ hitNumber + " fois et inflige " 
        		+ finalDamage + " dégâts à " + target.getName() + " !");
    }

	@Override
	public String getName() {
		return name;
	}
}
