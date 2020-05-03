package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import com.luispintodesa.bartender.models.Drink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("drinks")
public class DrinksController extends UserController {

  @GetMapping(value = "{idDrink}")
  public String displayDrink(@PathVariable int idDrink, Model model, HttpServletRequest request) {

    Drink drink = DeserializerUtils.searchDrinkById(idDrink);

    ArrayList<String> ingredients = new ArrayList<>();

    ingredients.add(drink.getNameIngredient1());
    ingredients.add(drink.getNameIngredient2());
    ingredients.add(drink.getNameIngredient3());
    ingredients.add(drink.getNameIngredient4());
    ingredients.add(drink.getNameIngredient5());
    ingredients.add(drink.getNameIngredient6());
    ingredients.add(drink.getNameIngredient7());
    ingredients.add(drink.getNameIngredient8());
    ingredients.add(drink.getNameIngredient9());
    ingredients.add(drink.getNameIngredient10());
    ingredients.add(drink.getNameIngredient11());
    ingredients.add(drink.getNameIngredient12());
    ingredients.add(drink.getNameIngredient13());
    ingredients.add(drink.getNameIngredient14());
    ingredients.add(drink.getNameIngredient15());

    ArrayList<String> measures = new ArrayList<>();

    measures.add(drink.getMeasureIngredient1());
    measures.add(drink.getMeasureIngredient2());
    measures.add(drink.getMeasureIngredient3());
    measures.add(drink.getMeasureIngredient4());
    measures.add(drink.getMeasureIngredient5());
    measures.add(drink.getMeasureIngredient6());
    measures.add(drink.getMeasureIngredient7());
    measures.add(drink.getMeasureIngredient8());
    measures.add(drink.getMeasureIngredient9());
    measures.add(drink.getMeasureIngredient10());
    measures.add(drink.getMeasureIngredient11());
    measures.add(drink.getMeasureIngredient12());
    measures.add(drink.getMeasureIngredient13());
    measures.add(drink.getMeasureIngredient14());
    measures.add(drink.getMeasureIngredient15());

    model.addAttribute("name", drink.getName());
    model.addAttribute("ingredients", ingredients);
    model.addAttribute("measures", measures);
    model.addAttribute("instructions", drink.getInstructions());
    model.addAttribute("image", drink.getThumbnail());
    model.addAttribute("title", drink.getName());

    return "details";
  }
}
