package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Drink;

import java.util.ArrayList;

public class DrinkListDivider {

    public static ArrayList<ArrayList<Drink>> divideInThree(ArrayList<Drink> original) {

        ArrayList<Drink> one = new ArrayList<Drink>();
        ArrayList<Drink> two = new ArrayList<Drink>();
        ArrayList<Drink> three = new ArrayList<Drink>();

        ArrayList<ArrayList<Drink>> lists = new ArrayList<ArrayList<Drink>>();

        int counter = 0;
        for (Drink i : original) {
            if (counter < 3) {
                counter += 1;
            } else {
                counter = 1;
            }
            if (counter == 1) {
                one.add(i);
            } else if (counter == 2) {
                two.add(i);
            } else {
                three.add(i);
            }
        }

        lists.add(one);
        lists.add(two);
        lists.add(three);

        return lists;
    }

    public static ArrayList<ArrayList<Drink>> divideByScore (ArrayList<Drink> undivided){

        ArrayList<Drink> score0 = new ArrayList<Drink>();
        ArrayList<Drink> score1 = new ArrayList<Drink>();
        ArrayList<Drink> score2 = new ArrayList<Drink>();
        ArrayList<ArrayList<Drink>> scoreList = new ArrayList<ArrayList<Drink>>();

        for (Drink drink:undivided){
            if (drink.getScore()==0){
                score0.add(drink);
            } else if (drink.getScore()==1){
                score1.add(drink);
            } else if (drink.getScore()==2){
                score2.add(drink);
            }
        }
        scoreList.add(score0);
        scoreList.add(score1);
        scoreList.add(score2);

        return scoreList;
    }
}
