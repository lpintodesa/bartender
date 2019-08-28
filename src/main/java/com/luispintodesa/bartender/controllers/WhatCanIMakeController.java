package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.jsontopojos.DrinkInListJSONtoPOJOs;
import com.luispintodesa.bartender.models.jsontopojos.IngredientsListJSONToPOJOs;
import com.luispintodesa.bartender.models.jsontopojos.MultiIngredientJSONtoPOJOs;
import com.luispintodesa.bartender.models.manipulation.DrinkListDivider;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscore;
import com.luispintodesa.bartender.models.DrinkDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("")
public class WhatCanIMakeController extends AbstractController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "whatcanimake")
    public String inventoryForm(Model model) {
        model.addAttribute(new WhatCanIMakeForm());
        model.addAttribute("ingredients", IngredientsListJSONToPOJOs.convert());
        model.addAttribute("title", "What Can I Make?");
        return "whatcanimake";
    }

    @RequestMapping(value = "whatcanimake", method= RequestMethod.POST)
    public String inventory(Model model, @ModelAttribute WhatCanIMakeForm form){

        String cocktailName = SpaceToUnderscore.convert(form.getCocktailName());


        if (DrinkInListJSONtoPOJOs.convert(cocktailName)==null){
            model.addAttribute("title", "No Results");
            return "noresults";
        }

        ArrayList<DrinkDetails> drinks = (ArrayList<DrinkDetails>) DrinkInListJSONtoPOJOs.convert(cocktailName);

        ArrayList<ArrayList<DrinkDetails>> lists = DrinkListDivider.divide(drinks);
        ArrayList<DrinkDetails> one = lists.get(0);
        ArrayList<DrinkDetails> two = lists.get(1);
        ArrayList<DrinkDetails> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @RequestMapping(value = "whatcanimake/surpriseme", method=RequestMethod.POST)
    public String surpriseMe (Model model, HttpServletRequest request){

        User theUser = getUserFromSession(request.getSession());

        ArrayList<DrinkDetails> drinks = IngredientRecipesURLs.idsToDrinks(IngredientRecipesURLs.addDrinkIDsToList(IngredientRecipesURLs.ingredient_search(theUser)));

        IngredientRecipesURLs.setMatchCounter(drinks, theUser);

        ArrayList<ArrayList<DrinkDetails>> scoreList = DividePerScore.divide(drinks);

        ArrayList<ArrayList<DrinkDetails>> score0 = DrinkListDivider.divide(scoreList.get(0));
        ArrayList<ArrayList<DrinkDetails>> score1 = DrinkListDivider.divide(scoreList.get(1));
        ArrayList<ArrayList<DrinkDetails>> score2 = DrinkListDivider.divide(scoreList.get(2));

        ArrayList<DrinkDetails> score0one = score0.get(0);
        ArrayList<DrinkDetails> score0two = score0.get(1);
        ArrayList<DrinkDetails> score0three = score0.get(2);

        ArrayList<DrinkDetails> score1one = score1.get(0);
        ArrayList<DrinkDetails> score1two = score1.get(1);
        ArrayList<DrinkDetails> score1three = score1.get(2);

        ArrayList<DrinkDetails> score2one = score2.get(0);
        ArrayList<DrinkDetails> score2two = score2.get(1);
        ArrayList<DrinkDetails> score2three = score2.get(2);

        model.addAttribute("score0one", score0one);
        model.addAttribute("score0two", score0two);
        model.addAttribute("score0three", score0three);

        model.addAttribute("score1one", score1one);
        model.addAttribute("score1two", score1two);
        model.addAttribute("score1three", score1three);

        model.addAttribute("score2one", score2one);
        model.addAttribute("score2two", score2two);
        model.addAttribute("score2three", score2three);

        model.addAttribute("title", "Search Results");

        return "surpriseme";
    }

    @RequestMapping(value = "byingredient", method=RequestMethod.POST)
    public String byIngredient (Model model, @ModelAttribute @RequestParam(required=false) String[] strIngredients){

        if (strIngredients==null){
            return "redirect:/whatcanimake";
        }

        String search = "";

        for (String i:strIngredients) {
            if (!i.equals(strIngredients[strIngredients.length-1])) {
                search += SpaceToUnderscore.convert(i) + ",";
            }
            else {
                search += SpaceToUnderscore.convert(i);
            }
        }

        if (MultiIngredientJSONtoPOJOs.convert(search)==""){
            model.addAttribute("title", "No Results");
            return "noresults";
        }

        ArrayList<DrinkDetails> drinks = (ArrayList<DrinkDetails>) MultiIngredientJSONtoPOJOs.convert(search);
        ArrayList<ArrayList<DrinkDetails>> lists = DrinkListDivider.divide(drinks);
        ArrayList<DrinkDetails> one = lists.get(0);
        ArrayList<DrinkDetails> two = lists.get(1);
        ArrayList<DrinkDetails> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("title", "Search Results");
        return "results";
    }
}
