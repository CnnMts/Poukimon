package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class ToileEleck implements Attackable {
	private Type type = Type.ELECTRIQUE;
	private double baseDamage = 15;

	@Override
	public void attack(Pokemon attacker, Pokemon target) {
		double effectiveness = 
				TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
		double finalDamage = baseDamage * effectiveness;
		target.takeDamage(finalDamage);
		applyWeakness(target);
	}

	private void applyWeakness(Pokemon target) {
		if (Math.random() < 0.3) {
			target.setSpeed(target.getSpeed() - 5);
		}
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getName() {
		return "Toile Eleckt";
	}

	@Override
	public double getDamage(Pokemon attacker, Pokemon target) {
		double effectiveness =
				TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
		double finalDamage = baseDamage * effectiveness;
		return (int) finalDamage;
	}
}
