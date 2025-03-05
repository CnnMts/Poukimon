package Models;

public abstract class Pokemon {
    private String name;
    protected int hp;
    protected int speed;
    private int maxhp;
    private Type[] types;
    private Attackable[] attacks;
    private Defendable[] defends;

    public Pokemon(String name, Type[] types, int hp, int speed, Attackable[] attacks, Defendable[] defends) {
        this.name = name;
        this.types = types;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Type[] getTypes() {
        return types;
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
        this.hp = Math.min(maxhp, this.hp + healing);
    }

    @Override
    public String toString() {
        return name + " (" + hp + " HP, Types: " + java.util.Arrays.toString(types) + ")";
    }

    public Type[] getDefensiveTypes() {
        return types;
    }

   
    public abstract void useSpecialAbility();
}
