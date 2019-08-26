package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Ingredient;

import java.util.List;

public class InventoryDuplicateCheck {

    public static boolean check (String input, List<Ingredient> list) {

        for (Ingredient i : list) {
            if (i.getStrIngredient().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}

