package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Brulure;

public class HydroCanon implements Attackable {
    private Type type = Type.EAU;
    private String name = "Hydro Canon";
  

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double damage = getDamage(attacker, target);
        target.takeDamage(damage);
    }

    public double getDamage(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed();
        double baseDamage = 30 * (speed / 100) + 15;
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness;
    }

	@Override
	public String getName() {
		return name ;
		}
}