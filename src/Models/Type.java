 package Models;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private static final Map<String, Type> TYPES = new HashMap<>();
    public static final Type ACIER = Type.of("Acier");
    public static final Type COMBAT = Type.of("Combat");
    public static final Type DRAGON = Type.of("Dragon");
    public static final Type EAU = Type.of("Eau");
    public static final Type ELECTRIQUE = Type.of("Electrique");
    public static final Type FEE = Type.of("Fee");
    public static final Type FEU = Type.of("Feu");
    public static final Type GLACE = Type.of("Glace");
    public static final Type INSECT = Type.of("Insect");
    public static final Type NORMAL = Type.of("Normal");
    public static final Type PLANTE = Type.of("Plante");
    public static final Type POISON = Type.of("Poison");
    public static final Type PSY = Type.of("Psy");
    public static final Type ROCHE = Type.of("Roche");
    public static final Type SOL = Type.of("Sol");
    public static final Type SPECTRE = Type.of("Spectre");
    public static final Type TENEBRE = Type.of("Tenebre");
    public static final Type VOL = Type.of("Vol");
    private final String name;

    private Type(String name) {
        this.name = name;
    }

    public static Type of(String name) {
        return TYPES.computeIfAbsent(name, Type::new);
    }

    public static void register(String name) {
        TYPES.putIfAbsent(name, new Type(name));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}