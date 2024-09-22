package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.luispintodesa.bartender.models.Constants.INGREDIENTS;
import static com.luispintodesa.bartender.models.Constants.NO_RESULTS;
import static com.luispintodesa.bartender.models.Constants.NO_RESULTS_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.REDIRECT_PATH;
import static com.luispintodesa.bartender.models.Constants.RESULTS_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.SEARCH_RESULTS;
import static com.luispintodesa.bartender.models.Constants.TITLE;
import static com.luispintodesa.bartender.models.utils.ArrayAndStringUtils.*;
import static com.luispintodesa.bartender.models.utils.DeserializerUtils.searchDrinkByMultipleIngredients;
import static com.luispintodesa.bartender.models.utils.DeserializerUtils.searchDrinkByName;
import static java.util.Objects.nonNull;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class WhatCanIMakeController extends UserController {

  @GetMapping(value = "whatcanimake")
  public String myBarForm(Model model) {
    model.addAttribute(new WhatCanIMakeForm());
    model.addAttribute(INGREDIENTS, DeserializerUtils.listAllIngredients());
    model.addAttribute(TITLE, "What Can I Make?");
    return "whatcanimake";
  }

  @PostMapping(value = "whatcanimake")
  public String myBar(Model model, @ModelAttribute WhatCanIMakeForm form) {

    String cocktailName = replaceWhitespaceWithUnderscore(form.getCocktailName());

    List<Drink> drinks = searchDrinkByName(cocktailName);

    if (drinks.isEmpty()) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    var lists = divideInThree(drinks);

    model.addAttribute("one", lists.get(0));
    model.addAttribute("two", lists.get(1));
    model.addAttribute("three", lists.get(2));
    model.addAttribute(TITLE, SEARCH_RESULTS);
    return RESULTS_TEMPLATE;
  }

  @PostMapping(value = "whatcanimake/surpriseme")
  public String surpriseMe(Model model, HttpServletRequest request) {

    User theUser = getUserFromSession(request.getSession());

    var drinks = ingredientsAndDrinksManager.matchUserIngredientsToDrinks(theUser);

    if (drinks.isEmpty()) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    var drinksWithPerfectMatch = nonNull(drinks.get(0)) ? divideInThree(drinks.get(0)) : null;

    var drinksWithOneMissingIngredient = nonNull(drinks.get(1)) ? divideInThree(drinks.get(1)) : null;

    var drinksWithTwoMissingIngredients = nonNull(drinks.get(2)) ? divideInThree(drinks.get(2)) : null;

    model.addAttribute("perfectMatch", drinksWithPerfectMatch);
    model.addAttribute("oneMissing", drinksWithOneMissingIngredient);
    model.addAttribute("twoMissing", drinksWithTwoMissingIngredients);

    model.addAttribute(TITLE, SEARCH_RESULTS);

    return "surpriseme";
  }

  @PostMapping(value = "byingredient")
  public String byIngredient(
      Model model, @ModelAttribute @RequestParam(required = false) String[] ingredientNames) {

    if (ingredientNames == null) {
      return REDIRECT_PATH + "/whatcanimake";
    }

    String ingredientNamesCommaSeparated = processMultiIngredientSearchInput(Arrays.toString(ingredientNames));

    List<Drink> drinks = searchDrinkByMultipleIngredients(ingredientNamesCommaSeparated);

    if (drinks.isEmpty()) {
      model.addAttribute(TITLE, NO_RESULTS);
      return NO_RESULTS_TEMPLATE;
    }

    var lists = divideInThree(drinks);

    model.addAttribute("one", lists.get(0));
    model.addAttribute("two", lists.get(1));
    model.addAttribute("three", lists.get(2));
    model.addAttribute(TITLE, SEARCH_RESULTS);
    return RESULTS_TEMPLATE;
  }
}
