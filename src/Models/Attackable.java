package Models;

public interface Attackable {
    Type getType();
    void attack(Pokemon attacker, Pokemon target);
    String getName();
    double getDamage(Pokemon attacker, Pokemon target);
}


