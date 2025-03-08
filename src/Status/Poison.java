package Status;

import Models.Pokemon;
import Models.StatusEffect;

public class Poison implements StatusEffect {
	private String name = "Poison";

	@Override
	public void applyEffect(Pokemon target) {
		double damage = target.getMaxHp() * 0.125;
		target.takeDamage(damage);
		System.out.println(target.getName() 
				+ " subit des dégâts de poison et perd " + damage + " PV.");
	}

	@Override
	public String getName() {
		return name;
	}

	public double getPoisonDamage(Pokemon target) {
		return target.getMaxHp() * 0.125;
	}
}
