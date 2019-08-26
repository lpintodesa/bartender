package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.ListIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class InventoryValidation {

    public static boolean check (String input, ArrayList<ListIngredient> list) {

        for (ListIngredient i : list) {
            if (i.getStrIngredient1().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }

        return false;
        }

    }