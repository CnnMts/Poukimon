package Models;

import java.util.HashMap;
import java.util.Map;

public class TypeTools {
    private static final Map<Type, Map<Type, Double>> typeChart = 
    		new HashMap<>();

    static {
        initializeTypeRelations();
    }

    public static void addTypeRelation(Type attacker, Type defender, 
    		double effectiveness) {
        typeChart.computeIfAbsent(attacker, k -> new HashMap<>())
        .put(defender, effectiveness);
    }

    public static double getEffectiveness(Type attacker, Type... defenders) {
      double totalEffectiveness = 1.0;
      for (Type defender : defenders) {
        Map<Type, Double> relations = typeChart.
        		getOrDefault(attacker, new HashMap<>());
        double typeEffectiveness = relations.getOrDefault(defender, 1.0);
        totalEffectiveness *= typeEffectiveness;
        }
        return totalEffectiveness;
    }



    public static void initializeTypeRelations() {
      addRelations(Type.ACIER, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0}, 
            {Type.EAU, 0.5}, {Type.ELECTRIQUE, 0.5}, {Type.FEE, 2.0}, 
            {Type.FEU, 0.5}, {Type.GLACE, 2.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 2.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });

        addRelations(Type.COMBAT, new Object[][] {
            {Type.ACIER, 2.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0}, 
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 0.5}, 
            {Type.FEU, 1.0}, {Type.GLACE, 2.0}, {Type.INSECT, 0.5},
            {Type.NORMAL, 2.0}, {Type.PLANTE, 1.0}, {Type.POISON, 0.5},
            {Type.PSY, 0.5}, {Type.ROCHE, 2.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 0.0}, {Type.TENEBRE, 2.0}, {Type.VOL, 0.5}
        });

        addRelations(Type.DRAGON, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 1.0}, {Type.DRAGON, 2.0}, 
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 0.0}, 
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });

        addRelations(Type.EAU, new Object[][] {
            {Type.ACIER, 1.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 0.5}, 
            {Type.EAU, 0.5}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0}, 
            {Type.FEU, 2.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 0.5}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 2.0}, {Type.SOL, 2.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });

        addRelations(Type.ELECTRIQUE, new Object[][] {
            {Type.ACIER, 1.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 0.5}, 
            {Type.EAU, 2.0}, {Type.ELECTRIQUE, 0.5}, {Type.FEE, 1.0}, 
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 0.5}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 1.0}, {Type.SOL, 0.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 2.0}
        });

        addRelations(Type.FEE, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 2.0}, {Type.DRAGON, 2.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 0.5}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 0.5},
            {Type.PSY, 1.0}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 2.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.FEU, new Object[][] {
            {Type.ACIER, 2.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 0.5},
            {Type.EAU, 0.5}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 0.5}, {Type.GLACE, 2.0}, {Type.INSECT, 2.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 2.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 0.5}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 2.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.GLACE, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 1.0}, {Type.DRAGON, 2.0},
            {Type.EAU, 0.5}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 0.5}, {Type.GLACE, 0.5}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 2.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 1.0}, {Type.SOL, 2.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 2.0}
        });
        addRelations(Type.INSECT, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 0.5}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 0.5},
            {Type.FEU, 0.5}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 2.0}, {Type.POISON, 0.5},
            {Type.PSY, 2.0}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 0.5}, {Type.TENEBRE, 2.0}, {Type.VOL, 0.5}
        });
        addRelations(Type.NORMAL, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 0.5}, {Type.SOL, 1.0},
            {Type.SPECTRE, 0.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.PLANTE, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 1.0}, {Type.DRAGON, 0.5},
            {Type.EAU, 2.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 0.5}, {Type.GLACE, 1.0}, {Type.INSECT, 0.5},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 0.5}, {Type.POISON, 0.5},
            {Type.PSY, 1.0}, {Type.ROCHE, 2.0}, {Type.SOL, 2.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 0.5}
        });
        addRelations(Type.POISON, new Object[][] {
            {Type.ACIER, 0.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 2.0},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 2.0}, {Type.POISON, 0.5},
            {Type.PSY, 1.0}, {Type.ROCHE, 0.5}, {Type.SOL, 0.5},
            {Type.SPECTRE, 0.5}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.PSY, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 2.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 2.0},
            {Type.PSY, 0.5}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 0.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.ROCHE, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 0.5}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 2.0}, {Type.GLACE, 2.0}, {Type.INSECT, 2.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 1.0}, {Type.SOL, 0.5},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 2.0}
        });
        addRelations(Type.SOL, new Object[][] {
            {Type.ACIER, 2.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 2.0}, {Type.FEE, 1.0},
            {Type.FEU, 2.0}, {Type.GLACE, 1.0}, {Type.INSECT, 0.5},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 0.5}, {Type.POISON, 2.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 2.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 0.0}
        });
        addRelations(Type.SPECTRE, new Object[][] {
            {Type.ACIER, 1.0}, {Type.COMBAT, 1.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 1.0},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 0.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 2.0}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 2.0}, {Type.TENEBRE, 0.5}, {Type.VOL, 1.0}
        });
        addRelations(Type.TENEBRE, new Object[][] {
            {Type.ACIER, 1.0}, {Type.COMBAT, 0.5}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 1.0}, {Type.FEE, 0.5},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 1.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 1.0}, {Type.POISON, 1.0},
            {Type.PSY, 2.0}, {Type.ROCHE, 1.0}, {Type.SOL, 1.0},
            {Type.SPECTRE, 2.0}, {Type.TENEBRE, 1.0}, {Type.VOL, 1.0}
        });
        addRelations(Type.VOL, new Object[][] {
            {Type.ACIER, 0.5}, {Type.COMBAT, 2.0}, {Type.DRAGON, 1.0},
            {Type.EAU, 1.0}, {Type.ELECTRIQUE, 0.5}, {Type.FEE, 1.0},
            {Type.FEU, 1.0}, {Type.GLACE, 1.0}, {Type.INSECT, 2.0},
            {Type.NORMAL, 1.0}, {Type.PLANTE, 2.0}, {Type.POISON, 1.0},
            {Type.PSY, 1.0}, {Type.ROCHE, 0.5}, {Type.SOL, 1.0},
            {Type.SPECTRE, 1.0}, {Type.TENEBRE, 0.5}, {Type.VOL, 1.0}
        });
        
    }

    private static void addRelations(Type attacker, Object[][] relations) {
        for (Object[] relation : relations) {
            addTypeRelation(attacker, (Type) relation[0], 
            		(double) relation[1]);
        }
    }
}