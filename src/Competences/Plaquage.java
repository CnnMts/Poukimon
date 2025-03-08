package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Paralysie;

public class Plaquage implements Attackable {
    private Type type = Type.NORMAL ;
    private Paralysie paralysieStatus = new Paralysie();
    private String name = "Plaquage";

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed();
        double baseDamage = 25 * (speed / 100) + 25;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        System.out.println(effectiveness);
        target.applyStatus(paralysieStatus);
        double finalDamage = baseDamage * effectiveness;
        target.takeDamage(finalDamage);

        System.out.println(attacker.getName() + " utilise " + name 
        		+ " et inflige " + finalDamage + " dégâts à " 
        		+ target.getName() + " !");
    }

	@Override
	public String getName() {
		return name;
	}
}

