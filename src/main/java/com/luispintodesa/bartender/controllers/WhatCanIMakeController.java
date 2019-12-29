package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Deserializer;
import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.IngredientToDrinks;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.manipulation.DrinkListDivider;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.*;

@Controller
@RequestMapping("")
public class WhatCanIMakeController extends AbstractController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "whatcanimake")
    public String myBarForm(Model model) {
        model.addAttribute(new WhatCanIMakeForm());
        model.addAttribute("ingredients", Deserializer.listAllIngredients());
        model.addAttribute(TITLE, "What Can I Make?");
        return "whatcanimake";
    }

    @PostMapping(value = "whatcanimake")
    public String myBar(Model model, @ModelAttribute WhatCanIMakeForm form){

        String cocktailName = SpaceToUnderscoreConverter.convert(form.getCocktailName());


        if (Deserializer.searchDrinkByName(cocktailName)==null){
            model.addAttribute(TITLE, NO_RESULTS);
            return NO_RESULTS_TEMPLATE;
        }

        List<Drink> drinks = (ArrayList<Drink>) Deserializer.searchDrinkByName(cocktailName);

        List<List<Drink>> lists = DrinkListDivider.divideInThree(drinks);
        List<Drink> one = lists.get(0);
        List<Drink> two = lists.get(1);
        List<Drink> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute(TITLE, SEARCH_RESULTS);
        return "results";
    }

    @PostMapping(value = "whatcanimake/surpriseme")
    public String surpriseMe (Model model, HttpServletRequest request){

        User theUser = getUserFromSession(request.getSession());

        List<Drink> drinks = IngredientToDrinks.idsToDrinks(IngredientToDrinks.addDrinkIDsToList(IngredientToDrinks.ingredientSearch(theUser)));

        IngredientToDrinks.setMatchCounter(drinks, theUser);

        List<List<Drink>> scoreList = DrinkListDivider.divideByScore(drinks);

        List<List<Drink>> score0 = DrinkListDivider.divideInThree(scoreList.get(0));
        List<List<Drink>> score1 = DrinkListDivider.divideInThree(scoreList.get(1));
        List<List<Drink>> score2 = DrinkListDivider.divideInThree(scoreList.get(2));

        if (scoreList.get(0).isEmpty()&& scoreList.get(1).isEmpty()&&scoreList.get(2).isEmpty()){
            model.addAttribute(TITLE, NO_RESULTS);
            return NO_RESULTS_TEMPLATE;
        }
        List<Drink> score0one = score0.get(0);
        List<Drink> score0two = score0.get(1);
        List<Drink> score0three = score0.get(2);

        List<Drink> score1one = score1.get(0);
        List<Drink> score1two = score1.get(1);
        List<Drink> score1three = score1.get(2);

        List<Drink> score2one = score2.get(0);
        List<Drink> score2two = score2.get(1);
        List<Drink> score2three = score2.get(2);

        model.addAttribute("score0one", score0one);
        model.addAttribute("score0two", score0two);
        model.addAttribute("score0three", score0three);

        model.addAttribute("score1one", score1one);
        model.addAttribute("score1two", score1two);
        model.addAttribute("score1three", score1three);

        model.addAttribute("score2one", score2one);
        model.addAttribute("score2two", score2two);
        model.addAttribute("score2three", score2three);

        model.addAttribute(TITLE, SEARCH_RESULTS);

        return "surpriseme";
    }

    @PostMapping(value = "byingredient")
    public String byIngredient (Model model, @ModelAttribute @RequestParam(required=false) String[] strIngredients){

        if (strIngredients==null){
            return "redirect:/whatcanimake";
        }

        StringBuilder search = new StringBuilder();

        for (String i:strIngredients) {
            if (!i.equals(strIngredients[strIngredients.length-1])) {
                search.append(SpaceToUnderscoreConverter.convert(i) + ",");
            }
            else {
                search.append(SpaceToUnderscoreConverter.convert(i));
            }
        }

        if (Deserializer.searchDrinkByMultipleIngredients(search.toString())==""){
            model.addAttribute(TITLE, NO_RESULTS);
            return NO_RESULTS_TEMPLATE;
        }

        List<Drink> drinks = (ArrayList<Drink>) Deserializer.searchDrinkByMultipleIngredients(search.toString());
        List<List<Drink>> lists = DrinkListDivider.divideInThree(drinks);
        List<Drink> one = lists.get(0);
        List<Drink> two = lists.get(1);
        List<Drink> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute(TITLE, SEARCH_RESULTS);
        return RESULTS_TEMPLATE;
    }
}
