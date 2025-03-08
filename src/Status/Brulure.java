package Status;

import Models.Pokemon;
import Models.StatusEffect;

public class Brulure implements StatusEffect {
	private String name = "Brûlure";

	@Override
	public void applyEffect(Pokemon target) {
		double damage = target.getMaxHp() * 0.0625;
		target.takeDamage(damage);
		target.reduceAttack(0.5);
		System.out
				.println(target.getName() + 
						" subit des dégâts de brûlure et perd " + damage 
						+ " PV. Son attaque est réduite.");
	}

	@Override
	public String getName() {
		return name;
	}

	public double getBrulureDamage(Pokemon target) {
		return target.getMaxHp() * 0.0625;
	}
}
