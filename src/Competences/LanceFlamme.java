package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.Brulure;

public class LanceFlamme implements Attackable {
    private Type type = Type.FEU;
    private double accuracy = 30;
    private String name = "LanceFlamme";
    private Brulure brulureStatus = new Brulure();
    
    @Override
    public Type getType() {
        return type;
    }


    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        double damage = getDamage(attacker, target);
        target.takeDamage(damage); 
        if (Math.random() * 100 <= accuracy) { 
            target.applyStatusEffects();
        }
    }


    @Override
    public String getName() {
        return name;
    }

 
    @Override
    public double getDamage(Pokemon attacker, Pokemon target) {
        double speed = attacker.getSpeed(); 
        double baseDamage = 25 * (speed / 100) + 25; 
        double effectiveness = TypeTools.getEffectiveness(this.type, target.getDefensiveTypes());
        return baseDamage * effectiveness; 
    }
}
