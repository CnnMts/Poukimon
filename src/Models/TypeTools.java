package Models;

import java.util.HashMap;
import java.util.Map;

public class TypeTools {
    private static final Map<Type, Map<Type, Double>> typeChart = new HashMap<>();

<<<<<<< HEAD
    static {
        initializeTypeRelations();
    }

    public static void addTypeRelation(Type attacker, Type defender, double effectiveness) {
        typeChart.computeIfAbsent(attacker, k -> new HashMap<>())
        .put(defender, effectiveness);
    }

    public static double getEffectiveness(Type attacker, Type... defenders) {
        double multiplier = 1.0;
        for (Type defender : defenders) {
            multiplier *= typeChart.getOrDefault(attacker, new HashMap<>())
            		.getOrDefault(defender, 1.0);
=======
   
    public static void addTypeRelation(String attacker, String defender, double effectiveness) {
        Type attackerType = Type.of(attacker);
        Type defenderType = Type.of(defender);
        
        typeChart.computeIfAbsent(attackerType, k -> new HashMap<>()).put(defenderType, effectiveness);
    }

    
    public static double getEffectiveness(Type attacker, Type[] defenders) {
        double multiplier = 1.0;
        for (Type defender : defenders) {
            double effectiveness = typeChart
            		.getOrDefault(attacker, new HashMap<>())
            		.getOrDefault(defender, 1.0);
            multiplier *= effectiveness;
>>>>>>> developM
        }
        return multiplier;
    }

<<<<<<< HEAD
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
=======
    public static void effectivenessElectrique() {
    	addTypeRelation("Electrique", "Acier", 0.5); 
    	addTypeRelation("Electrique", "Combat", 1.0);
    	addTypeRelation("Electrique", "Dragon", 1.0); 
        addTypeRelation("Electrique", "Eau", 1.0);
        addTypeRelation("Electrique", "Electrique", 0.5);
        addTypeRelation("Electrique", "Fee", 1.0);
        addTypeRelation("Electrique", "Feu", 1.0);
        addTypeRelation("Electrique", "Glace", 1.0); 
        addTypeRelation("Electrique", "Insect", 1.0);
        addTypeRelation("Electrique", "Normal", 1.0); 
        addTypeRelation("Electrique", "Plante", 1.0);
        addTypeRelation("Electrique", "Poison", 1.0);
        addTypeRelation("Electrique", "Psy", 1.0);
        addTypeRelation("Electrique", "Roche", 1.0);
        addTypeRelation("Electrique", "Sol", 2.0);
        addTypeRelation("Electrique", "Spectre", 1.0);
        addTypeRelation("Electrique", "Tenebre", 1.0);
        addTypeRelation("Electrique", "Vol", 0.5);
    }
    
    public static void initializeTypeRelations() {
        
    	effectivenessElectrique();
    	
        
        addTypeRelation("Combat", "Acier", 1.0); 
    	addTypeRelation("Combat", "Combat", 1.0);
    	addTypeRelation("Combat", "Dragon", 1.0); 
        addTypeRelation("Combat", "Eau", 1.0);
        addTypeRelation("Combat", "Electrique", 1.0);
        addTypeRelation("Combat", "Fee", 2.0);
        addTypeRelation("Combat", "Feu", 1.0);
        addTypeRelation("Combat", "Glace", 1.0); 
        addTypeRelation("Combat", "Insect", 0.5);
        addTypeRelation("Combat", "Normal", 1.0); 
        addTypeRelation("Combat", "Plante", 1.0);
        addTypeRelation("Combat", "Poison", 1.0);
        addTypeRelation("Combat", "Psy", 2.0);
        addTypeRelation("Combat", "Roche", 0.5);
        addTypeRelation("Combat", "Sol", 1.0);
        addTypeRelation("Combat", "Spectre", 1.0);
        addTypeRelation("Combat", "Tenebre", 0.5);
        addTypeRelation("Combat", "Vol", 2.0); 

        
        
        
        
        
        
        
        addTypeRelation("Eau", "Feu", 2.0); 
        addTypeRelation("Eau", "Eau", 1.0); 
        addTypeRelation("Eau", "Plante", 0.5); 
        addTypeRelation("Eau", "Electrique", 0.5); 

        
        addTypeRelation("Feu", "Plante", 2.0); 
        addTypeRelation("Feu", "Eau", 0.5); 
        addTypeRelation("Feu", "Feu", 1.0); 
        addTypeRelation("Feu", "Sol", 0.5); 

        
        addTypeRelation("Plante", "Eau", 2.0); 
        addTypeRelation("Plante", "Feu", 0.5); 
        addTypeRelation("Plante", "Vol", 0.5); 
        addTypeRelation("Plante", "Plante", 1.0); 

       
        addTypeRelation("Vol", "Plante", 2.0); 
        addTypeRelation("Vol", "Insecte", 2.0); 
        addTypeRelation("Vol", "Electrique", 0.5); 
        addTypeRelation("Vol", "Vol", 1.0); 

       
        addTypeRelation("Insecte", "Plante", 2.0); 
        addTypeRelation("Insecte", "Psy", 2.0); 
        addTypeRelation("Insecte", "Feu", 0.5); 
        addTypeRelation("Insecte", "Vol", 0.5); // L'Insecte est moins efficace contre le Vol

       
        addTypeRelation("Sol", "Feu", 2.0); // Le Sol est super efficace contre le Feu
        addTypeRelation("Sol", "Electrique", 2.0); // Le Sol est super efficace contre l'Electrique
        addTypeRelation("Sol", "Plante", 1.0); // Le Sol est neutre contre la Plante
        addTypeRelation("Sol", "Vol", 0.0); // Le Sol n'a pas d'effet contre le Vol

        
        addTypeRelation("Psy", "Poison", 2.0); // Le Psy est super efficace contre le Poison
        addTypeRelation("Psy", "Combat", 2.0); // Le Psy est super efficace contre le Combat
        addTypeRelation("Psy", "Psy", 1.0); // Le Psy est neutre contre le Psy
        addTypeRelation("Psy", "Insecte", 0.5); // Le Psy est moins efficace contre l'Insecte

       
        addTypeRelation("Combat", "Normal", 2.0); // Le Combat est super efficace contre le Normal
        addTypeRelation("Combat", "Psy", 2.0); // Le Combat est super efficace contre le Psy
        addTypeRelation("Combat", "Vol", 0.5); // Le Combat est moins efficace contre le Vol
        addTypeRelation("Combat", "Insecte", 1.0); // Le Combat est neutre contre l'Insecte

        // Autres types (similaire à ce modèle, ajouter chaque type à sa propre logique)...
        // Ajoutez ici des relations supplémentaires pour tous les types existants...
    }
}
>>>>>>> developM
