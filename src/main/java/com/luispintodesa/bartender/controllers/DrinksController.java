package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.deserializers.SearchDrinkByIdDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("drinks")
public class DrinksController extends AbstractController{

    @RequestMapping(value="{idDrink}", method= RequestMethod.GET)
    public String displayDrink (@PathVariable int idDrink, Model model, HttpServletRequest request){

        Drink drink = (Drink) SearchDrinkByIdDeserializer.convert(idDrink);

        User theUser = getUserFromSession(request.getSession());

        ArrayList<String> ingredients = new ArrayList<String>();

        ingredients.add(drink.getStrIngredient1());
        ingredients.add(drink.getStrIngredient2());
        ingredients.add(drink.getStrIngredient3());
        ingredients.add(drink.getStrIngredient4());
        ingredients.add(drink.getStrIngredient5());
        ingredients.add(drink.getStrIngredient6());
        ingredients.add(drink.getStrIngredient7());
        ingredients.add(drink.getStrIngredient8());
        ingredients.add(drink.getStrIngredient9());
        ingredients.add(drink.getStrIngredient10());
        ingredients.add(drink.getStrIngredient11());
        ingredients.add(drink.getStrIngredient12());
        ingredients.add(drink.getStrIngredient13());
        ingredients.add(drink.getStrIngredient14());
        ingredients.add(drink.getStrIngredient15());

        ArrayList<String> measures = new ArrayList<String>();

        measures.add(drink.getStrMeasure1());
        measures.add(drink.getStrMeasure2());
        measures.add(drink.getStrMeasure3());
        measures.add(drink.getStrMeasure4());
        measures.add(drink.getStrMeasure5());
        measures.add(drink.getStrMeasure6());
        measures.add(drink.getStrMeasure7());
        measures.add(drink.getStrMeasure8());
        measures.add(drink.getStrMeasure9());
        measures.add(drink.getStrMeasure10());
        measures.add(drink.getStrMeasure11());
        measures.add(drink.getStrMeasure12());
        measures.add(drink.getStrMeasure13());
        measures.add(drink.getStrMeasure14());
        measures.add(drink.getStrMeasure15());

        model.addAttribute("name", drink.getStrDrink());
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("measures",measures);
        model.addAttribute("instructions", drink.getStrInstructions());
        model.addAttribute("image",drink.getStrDrinkThumb());
        model.addAttribute("title",drink.getStrDrink());

        return "details";
    }
}
