package com.luispintodesa.bartender.models;

import java.util.ArrayList;

public class DividePerScore {

    public static ArrayList<ArrayList<DrinkDetails>> divide (ArrayList<DrinkDetails> undivided){

        ArrayList<DrinkDetails> score0 = new ArrayList<DrinkDetails>();
        ArrayList<DrinkDetails> score1 = new ArrayList<DrinkDetails>();
        ArrayList<DrinkDetails> score2 = new ArrayList<DrinkDetails>();
        //ArrayList<DrinkForIngredients> score3plus = new ArrayList<DrinkForIngredients>();
        ArrayList<ArrayList<DrinkDetails>> scoreList = new ArrayList<ArrayList<DrinkDetails>>();

        for (DrinkDetails drink:undivided){
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
