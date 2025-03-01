package Models;

public class Pokemon {
    private String name;
    private int hp;
    private String[] attacks;
    private String[] defends;

    public Pokemon(String name, int hp, String[] attacks, String[] defends) {
        this.name = name;
        this.hp = hp;
        this.attacks = attacks;
        this.defends = defends;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public String[] getAttacks() {
        return attacks;
    }
    public String[] getDefends() {
        return defends;
    }

    public void takeDamage(int damage) {
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
