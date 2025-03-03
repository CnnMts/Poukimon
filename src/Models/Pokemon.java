package Models;

import Competences.BouleElec;
import Competences.ToileEleck;

public class Pokemon {
    private String name;
    protected int hp;
    protected int speed;
    private int maxhp;
    private Attackable[] attacks;
    private Defendable[] defends;

    public Pokemon(String name, int hp,int speed, Attackable[] attacks, Defendable[] defends) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.maxhp = hp;
        this.attacks = attacks;
        this.defends = defends;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxhp;
    }
    public int getHp() {
        return hp;
    }
    public int getSpeed() {
        return speed;
    }
    public int setSpeed(int speed) {
        this.speed = speed;
        return speed;
    }
    

    public Attackable[] getAttacks() {
        return attacks;
    }
    public Defendable[] getDefends() {
        return defends;
    }

    public void takeDamage(double damage) {
        this.hp -= damage;
    }

    public void heal(int healing) {
        this.hp += healing;
    }

    @Override
    public String toString() {
        return name + " (" + hp + " HP)";
    }    
    
}
