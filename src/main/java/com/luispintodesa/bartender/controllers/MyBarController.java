package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.forms.MyBarForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.EMPTY_STRING;
import static com.luispintodesa.bartender.models.Constants.ERROR;
import static com.luispintodesa.bartender.models.Constants.INGREDIENTS;
import static com.luispintodesa.bartender.models.Constants.MY_BAR;
import static com.luispintodesa.bartender.models.Constants.MY_BAR_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.REDIRECT_PATH;
import static com.luispintodesa.bartender.models.Constants.TITLE;
import static com.luispintodesa.bartender.models.utils.DeserializerUtils.listAllIngredients;
import static com.luispintodesa.bartender.models.utils.DeserializerUtils.searchIngredientByName;

@Controller
@RequestMapping(MY_BAR_TEMPLATE)
@AllArgsConstructor
public class MyBarController extends UserController {

  @GetMapping(value = EMPTY_STRING)
  public String myBarForm(Model model) {

    model.addAttribute(INGREDIENTS, listAllIngredients());
    model.addAttribute(new MyBarForm());
    model.addAttribute(TITLE, MY_BAR);
    return MY_BAR_TEMPLATE;
  }

  @PostMapping(value = EMPTY_STRING)
  public String myBar(Model model, @ModelAttribute MyBarForm form, HttpServletRequest request) {

    List<IngredientInList> list = listAllIngredients();

    User theUser = getUserFromSession(request.getSession());

    List<Ingredient> ingredientsInMyBar = theUser.getIngredients();

    model.addAttribute(new MyBarForm());
    model.addAttribute(TITLE, MY_BAR);

    String error = !form.checkIngredientInList(list) ? "invalid"
                    : form.checkIngredient(ingredientsInMyBar) ? "duplicate"
                    : "false";

    model.addAttribute(ERROR, error);

    if (!error.equals("false")) {
      model.addAttribute(INGREDIENTS, list);
      return MY_BAR_TEMPLATE;
    }

    Ingredient newIngredient = searchIngredientByName(form.getIngredientName());

    ingredientsAndDrinksManager.addNewDrinksForNewIngredient(newIngredient);

    userManager.addIngredientAndSave(theUser, newIngredient);

    model.addAttribute(INGREDIENTS, listAllIngredients());
    return MY_BAR_TEMPLATE;
  }

  @PostMapping(value = "remove")
  public String remove( @ModelAttribute @RequestParam(required = false) Integer[] ingredientIDs,
      Model model,
      HttpServletRequest request) {

    if (ingredientIDs == null) {
      return REDIRECT_PATH+"./";
    }

    User theUser = getUserFromSession(request.getSession());

    userManager.removeIngredientsAndSave(theUser, ingredientIDs);

    return REDIRECT_PATH+"./";
  }
}
