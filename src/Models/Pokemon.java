package Models;

import javafx.scene.image.Image;
import javafx.scene.text.Text;

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
        this.attacks = (attacks != null) ? attacks : new Attackable[0];
        this.defends = (defends != null) ? defends : new Defendable[0];
    }

    public String getName() {
        return name;
    }
    public Text getNameText() {
        return new Text(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return name != null && name.equals(pokemon.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
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

    public String[] getAttackNames() {
        String[] names = new String[attacks.length];
        for (int i = 0; i < attacks.length; i++) {
            names[i] = attacks[i].getName();
        }
        return names;
    }
    
    public String[] getDefendsNames() {
        String[] names = new String[defends.length];
        for (int i = 0; i < defends.length; i++) {
            names[i] = defends[i].getName();
        }
        return names;
    }

    public Defendable[] getDefends() {
        return defends;
    }

    public void takeDamage(double damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void heal(int healing) {
        this.hp = Math.min(maxhp, this.hp + healing);
    }

    public Type[] getDefensiveTypes() {
        return types;
    }

    public abstract void useSpecialAbility();

    public abstract Image getImage();

    @Override
    public String toString() {
        return name + " (" + hp + " HP, Types: " + java.util.Arrays.toString(types) + ")";
    }
}
