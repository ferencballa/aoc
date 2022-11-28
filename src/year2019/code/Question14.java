package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question14 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 14);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input, 3687786);//starting point: 1.000.000.000.000 divided by answer of part1, then by binary search narrow each digit down to exact answer
    }

    private static void part1(String[] input) {
        HashMap<String, ProductionFormula> formulaHashMap = new HashMap<>();
        for (String line : input) {
            String[] formulaParts = line.split(" => ");
            String[] ingredientParts = formulaParts[0].split(", ");
            ArrayList<Cost> costs = new ArrayList<>();
            for (String ingredientPart : ingredientParts) {
                String[] costParts = ingredientPart.split(" ");
                Cost cost = new Cost();
                cost.amount = Integer.parseInt(costParts[0]);
                cost.name = costParts[1];
                costs.add(cost);
            }
            String[] resultParts = formulaParts[1].split(" ");
            ProductionFormula productionFormula = new ProductionFormula();
            productionFormula.ingredients = costs;
            productionFormula.yield = Integer.parseInt(resultParts[0]);
            formulaHashMap.put(resultParts[1], productionFormula);
        }
        long amountOfOre = 0;
        ArrayList<Cost> requirements = new ArrayList<>();
        HashMap<String, Long> spareIngredients = new HashMap<>();
        for (String chemical : formulaHashMap.keySet()) {
            spareIngredients.put(chemical, 0L);
        }
        spareIngredients.put("ORE", 0L);
        Cost fuel = new Cost();
        fuel.amount = 1;
        fuel.name = "FUEL";
        requirements.add(fuel);
        while (!requirements.isEmpty()) {
            Cost currentIngredient = requirements.remove(0);
            ProductionFormula productionFormulaForCurrentIngredient = formulaHashMap.get(currentIngredient.name);
            long amountNeeded = currentIngredient.amount;
            long amountSpare = spareIngredients.get(currentIngredient.name);
            if (amountSpare >= amountNeeded) {
                spareIngredients.put(currentIngredient.name, amountSpare - amountNeeded);
            } else {
                amountNeeded -= amountSpare;
                long numberOfReactionsNeeded = (long) Math.ceil((double) amountNeeded / (double) productionFormulaForCurrentIngredient.yield);
                spareIngredients.put(currentIngredient.name, numberOfReactionsNeeded * productionFormulaForCurrentIngredient.yield - amountNeeded);
                for (Cost cost : productionFormulaForCurrentIngredient.ingredients) {
                    long ingredientAmountNeeded = cost.amount * numberOfReactionsNeeded;
                    if (cost.name.equals("ORE")) {
                        amountOfOre += ingredientAmountNeeded;
                    } else {
                        Cost newCost = new Cost();
                        newCost.name = cost.name;
                        newCost.amount = ingredientAmountNeeded;
                        requirements.add(newCost);
                    }
                }
            }
        }
        System.out.println(amountOfOre);
    }

    private static void part2(String[] input, int fuelGuess) {
        HashMap<String, ProductionFormula> formulaHashMap = new HashMap<>();
        for (String line : input) {
            String[] formulaParts = line.split(" => ");
            String[] ingredientParts = formulaParts[0].split(", ");
            ArrayList<Cost> costs = new ArrayList<>();
            for (String ingredientPart : ingredientParts) {
                String[] costParts = ingredientPart.split(" ");
                Cost cost = new Cost();
                cost.amount = Integer.parseInt(costParts[0]);
                cost.name = costParts[1];
                costs.add(cost);
            }
            String[] resultParts = formulaParts[1].split(" ");
            ProductionFormula productionFormula = new ProductionFormula();
            productionFormula.ingredients = costs;
            productionFormula.yield = Integer.parseInt(resultParts[0]);
            formulaHashMap.put(resultParts[1], productionFormula);
        }
        long amountOfOre = 0;
        ArrayList<Cost> requirements = new ArrayList<>();
        HashMap<String, Long> spareIngredients = new HashMap<>();
        for (String chemical : formulaHashMap.keySet()) {
            spareIngredients.put(chemical, 0L);
        }
        spareIngredients.put("ORE", 0L);
        Cost fuel = new Cost();
        fuel.amount = fuelGuess;
        fuel.name = "FUEL";
        requirements.add(fuel);
        while (!requirements.isEmpty()) {
            Cost currentIngredient = requirements.remove(0);
            ProductionFormula productionFormulaForCurrentIngredient = formulaHashMap.get(currentIngredient.name);
            long amountNeeded = currentIngredient.amount;
            long amountSpare = spareIngredients.get(currentIngredient.name);
            if (amountSpare >= amountNeeded) {
                spareIngredients.put(currentIngredient.name, amountSpare - amountNeeded);
            } else {
                amountNeeded -= amountSpare;
                long numberOfReactionsNeeded = (long) Math.ceil((double) amountNeeded / (double) productionFormulaForCurrentIngredient.yield);
                spareIngredients.put(currentIngredient.name, numberOfReactionsNeeded * productionFormulaForCurrentIngredient.yield - amountNeeded);
                for (Cost cost : productionFormulaForCurrentIngredient.ingredients) {
                    long ingredientAmountNeeded = cost.amount * numberOfReactionsNeeded;
                    if (cost.name.equals("ORE")) {
                        amountOfOre += ingredientAmountNeeded;
                    } else {
                        Cost newCost = new Cost();
                        newCost.name = cost.name;
                        newCost.amount = ingredientAmountNeeded;
                        requirements.add(newCost);
                    }
                }
            }
        }
        System.out.println(amountOfOre);
    }
}

class Cost {
    long amount;
    String name;
}

class ProductionFormula {
    ArrayList<Cost> ingredients;
    int yield;
}
