package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Status.Poison;

public class PoudreToxik implements Attackable {
	private Type type = Type.POISON;
	private String name = "Poudre Toxik";
	private double baseDamage = 0;
	private double accuracy = 75;
	private Poison poisonStatus = new Poison();

	@Override
	public void attack(Pokemon attacker, Pokemon target) {
		if (Math.random() * 100 <= accuracy) {
			target.applyStatus(poisonStatus);
		} else {
		}
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	public double getBaseDamage(Pokemon attacker, Pokemon target) {
		return baseDamage;
	}

	public double getAccuracy() {
		return accuracy;
	}

	@Override
	public double getDamage(Pokemon attacker, Pokemon target) {
		return 0;
	}
}
