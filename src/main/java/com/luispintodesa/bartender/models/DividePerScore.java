package com.luispintodesa.bartender.models;

import java.util.ArrayList;

public class DividePerScore {

    public static ArrayList<ArrayList<DrinkForIngredients>> divide (ArrayList<DrinkForIngredients> undivided){

        ArrayList<DrinkForIngredients> score0 = new ArrayList<DrinkForIngredients>();
        ArrayList<DrinkForIngredients> score1 = new ArrayList<DrinkForIngredients>();
        ArrayList<DrinkForIngredients> score2 = new ArrayList<DrinkForIngredients>();
        //ArrayList<DrinkForIngredients> score3plus = new ArrayList<DrinkForIngredients>();
        ArrayList<ArrayList<DrinkForIngredients>> scoreList = new ArrayList<ArrayList<DrinkForIngredients>>();

        for (DrinkForIngredients drink:undivided){
            if (drink.getScore()==0){
                score0.add(drink);
            } else if (drink.getScore()==1){
                score1.add(drink);
            } else if (drink.getScore()==2){
                score2.add(drink);
            //} else {
               // score3plus.add(drink);
            }
        }
        scoreList.add(score0);
        scoreList.add(score1);
        scoreList.add(score2);
        //scoreList.add(score3plus);

        return scoreList;
    }
}
