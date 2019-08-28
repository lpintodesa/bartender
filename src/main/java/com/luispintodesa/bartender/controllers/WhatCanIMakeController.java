package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.deserializers.SearchDrinkByNameDeserializer;
import com.luispintodesa.bartender.models.deserializers.ListAllIngredientsDeserializer;
import com.luispintodesa.bartender.models.deserializers.SearchDrinkByMultipleIngredientsDeserializer;
import com.luispintodesa.bartender.models.manipulation.DivideDrinksByScore;
import com.luispintodesa.bartender.models.manipulation.DrinkListDivider;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscore;
import com.luispintodesa.bartender.models.Drink;
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
    public String myBarForm(Model model) {
        model.addAttribute(new WhatCanIMakeForm());
        model.addAttribute("ingredients", ListAllIngredientsDeserializer.convert());
        model.addAttribute("title", "What Can I Make?");
        return "whatcanimake";
    }

    @RequestMapping(value = "whatcanimake", method= RequestMethod.POST)
    public String myBar(Model model, @ModelAttribute WhatCanIMakeForm form){

        String cocktailName = SpaceToUnderscore.convert(form.getCocktailName());


        if (SearchDrinkByNameDeserializer.convert(cocktailName)==null){
            model.addAttribute("title", "No Results");
            return "noresults";
        }

        ArrayList<Drink> drinks = (ArrayList<Drink>) SearchDrinkByNameDeserializer.convert(cocktailName);

        ArrayList<ArrayList<Drink>> lists = DrinkListDivider.divide(drinks);
        ArrayList<Drink> one = lists.get(0);
        ArrayList<Drink> two = lists.get(1);
        ArrayList<Drink> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @RequestMapping(value = "whatcanimake/surpriseme", method=RequestMethod.POST)
    public String surpriseMe (Model model, HttpServletRequest request){

        User theUser = getUserFromSession(request.getSession());

        ArrayList<Drink> drinks = IngredientToDrinks.idsToDrinks(IngredientToDrinks.addDrinkIDsToList(IngredientToDrinks.ingredient_search(theUser)));

        IngredientToDrinks.setMatchCounter(drinks, theUser);

        ArrayList<ArrayList<Drink>> scoreList = DivideDrinksByScore.divide(drinks);

        ArrayList<ArrayList<Drink>> score0 = DrinkListDivider.divide(scoreList.get(0));
        ArrayList<ArrayList<Drink>> score1 = DrinkListDivider.divide(scoreList.get(1));
        ArrayList<ArrayList<Drink>> score2 = DrinkListDivider.divide(scoreList.get(2));

        ArrayList<Drink> score0one = score0.get(0);
        ArrayList<Drink> score0two = score0.get(1);
        ArrayList<Drink> score0three = score0.get(2);

        ArrayList<Drink> score1one = score1.get(0);
        ArrayList<Drink> score1two = score1.get(1);
        ArrayList<Drink> score1three = score1.get(2);

        ArrayList<Drink> score2one = score2.get(0);
        ArrayList<Drink> score2two = score2.get(1);
        ArrayList<Drink> score2three = score2.get(2);

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

        if (SearchDrinkByMultipleIngredientsDeserializer.convert(search)==""){
            model.addAttribute("title", "No Results");
            return "noresults";
        }

        ArrayList<Drink> drinks = (ArrayList<Drink>) SearchDrinkByMultipleIngredientsDeserializer.convert(search);
        ArrayList<ArrayList<Drink>> lists = DrinkListDivider.divide(drinks);
        ArrayList<Drink> one = lists.get(0);
        ArrayList<Drink> two = lists.get(1);
        ArrayList<Drink> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("title", "Search Results");
        return "results";
    }
}
