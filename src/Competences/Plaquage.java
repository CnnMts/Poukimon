package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Paralysie;

public class Plaquage implements Attackable {
	private Type type = Type.NORMAL;
	private Paralysie paralysieStatus = new Paralysie();
	private String name = "Plaquage";

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void attack(Pokemon attacker, Pokemon target) {
		double damage = getDamage(attacker, target);
		target.takeDamage(damage);
		target.applyStatus(paralysieStatus);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDamage(Pokemon attacker, Pokemon target) {
		double speed = attacker.getSpeed();
		double baseDamage = 25 * (speed / 100) + 25;
		double effectiveness = 
				TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
		return baseDamage * effectiveness;
	}
}
