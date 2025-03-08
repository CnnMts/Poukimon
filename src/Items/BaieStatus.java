package Items;

import Models.Pokemon;

public class BaieStatus {

    private Integer baie;

    public BaieStatus() {
        this.baie = 20;
    }

    public void use(Pokemon pokemon) {
        pokemon.addHp(baie);
    }

    public String getName() {
        return "Baie";
    }
}
