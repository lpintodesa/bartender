package com.luispintodesa.bartender.models;

import java.util.ArrayList;

public class InventoryValidation {

    public static boolean check (String input, ArrayList<ListIngredient> list) {

        for (ListIngredient i : list) {
            if (i.getStrIngredient1().equals(input)) {
                return true;
            }
        }
        return false;
        }

    }