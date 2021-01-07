package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("drinks")
public class DrinksController extends UserController {

  @GetMapping(value = "{idDrink}")
  public String displayDrink(@PathVariable int idDrink, Model model, HttpServletRequest request) {

    Drink drink = DeserializerUtils.searchDrinkById(idDrink);

    drinkDao.save(drink);

    model.addAttribute("name", drink.getName());
    model.addAttribute("ingredients", drink.getIngredientNames());
    model.addAttribute("measures", drink.getIngredientMeasures());
    model.addAttribute("instructions", drink.getInstructions());
    model.addAttribute("image", drink.getThumbnail());
    model.addAttribute("title", drink.getName());

    return "details";
  }
}
