package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.utils.CustomArrayAndStringUtils;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import com.luispintodesa.bartender.models.utils.IngredientToDrinksMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.NO_RESULTS;
import static com.luispintodesa.bartender.models.Constants.NO_RESULTS_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.RESULTS_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.SEARCH_RESULTS;
import static com.luispintodesa.bartender.models.Constants.TITLE;

@Controller
@RequestMapping("")
public class WhatCanIMakeController extends UserController {

  @GetMapping(value = "whatcanimake")
  public String myBarForm(Model model) {
    model.addAttribute(new WhatCanIMakeForm());
    model.addAttribute("ingredients", DeserializerUtils.listAllIngredients());
    model.addAttribute(TITLE, "What Can I Make?");
    return "whatcanimake";
  }

  @PostMapping(value = "whatcanimake")
  public String myBar(Model model, @ModelAttribute WhatCanIMakeForm form) {

    String cocktailName = CustomArrayAndStringUtils.convert(form.getCocktailName());

    if (DeserializerUtils.searchDrinkByName(cocktailName) == null) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    List<Drink> drinks = DeserializerUtils.searchDrinkByName(cocktailName);

    List<List<Drink>> lists = CustomArrayAndStringUtils.divideInThree(drinks);
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
  public String surpriseMe(
      Model model,
      HttpServletRequest request,
      @ModelAttribute @RequestParam(required = true) int numberOfMissingIngredients) {

    User theUser = getUserFromSession(request.getSession());

    List<Drink> drinks =
        IngredientToDrinksMatcher.matchUserIngredientsToDrinks(theUser, numberOfMissingIngredients);

    if (drinks.isEmpty()) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    List<List<Drink>> listOfListsOfDrinks = CustomArrayAndStringUtils.divideInThree(drinks);

    List<Drink> firstColumn = listOfListsOfDrinks.get(0);
    List<Drink> secondColumn = listOfListsOfDrinks.get(1);
    List<Drink> thirdColumn = listOfListsOfDrinks.get(2);

    model.addAttribute("firstColumn", firstColumn);
    model.addAttribute("secondColumn", secondColumn);
    model.addAttribute("thirdColumn", thirdColumn);
    model.addAttribute("score", numberOfMissingIngredients);

    model.addAttribute(TITLE, SEARCH_RESULTS);

    return "surpriseme";
  }

  @PostMapping(value = "byingredient")
  public String byIngredient(
      Model model, @ModelAttribute @RequestParam(required = false) String[] strIngredients) {

    if (strIngredients == null) {
      return "redirect:/whatcanimake";
    }

    StringBuilder search = new StringBuilder();

    for (String i : strIngredients) {
      if (!i.equals(strIngredients[strIngredients.length - 1])) {
        search.append(CustomArrayAndStringUtils.convert(i) + ",");
      } else {
        search.append(CustomArrayAndStringUtils.convert(i));
      }
    }

    if (DeserializerUtils.searchDrinkByMultipleIngredients(search.toString()).isEmpty()) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    List<Drink> drinks = DeserializerUtils.searchDrinkByMultipleIngredients(search.toString());
    List<List<Drink>> lists = CustomArrayAndStringUtils.divideInThree(drinks);
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
