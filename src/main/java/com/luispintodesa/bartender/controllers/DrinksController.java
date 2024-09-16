package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.DETAILS;
import static com.luispintodesa.bartender.models.Constants.DRINKS;
import static com.luispintodesa.bartender.models.Constants.IMAGE;
import static com.luispintodesa.bartender.models.Constants.INGREDIENTS;
import static com.luispintodesa.bartender.models.Constants.INSTRUCTIONS;
import static com.luispintodesa.bartender.models.Constants.MEASURES;
import static com.luispintodesa.bartender.models.Constants.NAME;
import static com.luispintodesa.bartender.models.Constants.TITLE;

@Controller
@RequestMapping(DRINKS)
@AllArgsConstructor
public class DrinksController extends UserController {

  @GetMapping(value = "{idDrink}")
  public String displayDrink(@PathVariable int idDrink, Model model, HttpServletRequest request) {

    Drink drink = DeserializerUtils.searchDrinkById(idDrink);

    ingredientsAndDrinksManager.saveDrink(drink);

    List<String> ingredientNames = drink.getIngredientNames();
    List<String> ingredientMeasures = drink.getIngredientMeasures();

    while (ingredientMeasures.size()<ingredientNames.size()){
      ingredientMeasures.add("N/A");
    }

    model.addAttribute(NAME, drink.getName());
    model.addAttribute(INGREDIENTS, ingredientNames);
    model.addAttribute(MEASURES, ingredientMeasures);
    model.addAttribute(INSTRUCTIONS, drink.getInstructions());
    model.addAttribute(IMAGE, drink.getThumbnail());
    model.addAttribute(TITLE, drink.getName());

    return DETAILS;
  }
}
