package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;

public class ToileEleck implements Attackable {
	private Type type = Type.ELECTRIQUE;
    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double baseDamage = 15;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        double finalDamage = baseDamage * effectiveness;

        target.takeDamage(finalDamage);
        applyWeakness(target);

        System.out.println(attacker.getName() + " utilise Toile Éleck et inflige " + finalDamage + " dégâts à " + target.getName() + " !");
    }

    private void applyWeakness(Pokemon target) {
        if (Math.random() < 0.3) {
            target.setSpeed(target.getSpeed() - 5);
            System.out.println(target.getName() + "'s speed has been reduced!");
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
}
