package Models;

import java.util.HashMap;
import java.util.Map;

public class TypeTools {
    private static final Map<Type, Map<Type, Double>> typeChart = new HashMap<>();

   
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
        }
        return multiplier;
    }

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