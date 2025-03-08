package Competences;

import Models.Attackable;
import Models.Pokemon;
import Models.Type;
import Models.TypeTools;
import Status.*;

public class SpaceCake implements Attackable {
    private Type type = Type.FEE;
    private String name = "SpaceCake";
    private double baseDamage = 20; 
    private double accuracy = 85;
    private int pp = 10;
    private Brulure brulureStatus = new Brulure();
    private Poison poisonStatus = new Poison();

    @Override
    public void attack(Pokemon attacker, Pokemon target) {
        switch((int)(Math.random()*3+1 )) {
        case 1:
        	target.applyStatus(poisonStatus);
            System.out.println(target.getName() + " a été empoissonée");
            break;
        case 2:
        	target.applyStatus(brulureStatus);
        	System.out.println(target.getName() + " a été brulé");
            break;
        case 3:
        	attacker.applyStatus(poisonStatus);
        	System.out.println(attacker.getName() + " a été empoissonée");
            break;
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

    public double getBaseDamage() {
        return baseDamage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getPP() {
        return pp;
    }
 }