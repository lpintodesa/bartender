package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Drink;

import java.util.ArrayList;

public class DivideDrinksByScore {

    public static ArrayList<ArrayList<Drink>> divide (ArrayList<Drink> undivided){

        ArrayList<Drink> score0 = new ArrayList<Drink>();
        ArrayList<Drink> score1 = new ArrayList<Drink>();
        ArrayList<Drink> score2 = new ArrayList<Drink>();
        //ArrayList<DrinkForIngredients> score3plus = new ArrayList<DrinkForIngredients>();
        ArrayList<ArrayList<Drink>> scoreList = new ArrayList<ArrayList<Drink>>();

        for (Drink drink:undivided){
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
