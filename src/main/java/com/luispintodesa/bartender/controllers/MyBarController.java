package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.forms.MyBarForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.luispintodesa.bartender.models.Constants.ERROR;
import static com.luispintodesa.bartender.models.Constants.INGREDIENTS;
import static com.luispintodesa.bartender.models.Constants.MY_BAR;
import static com.luispintodesa.bartender.models.Constants.MY_BAR_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.TITLE;

@Controller
@RequestMapping(MY_BAR_TEMPLATE)
public class MyBarController extends UserController {

  @Autowired private IngredientDao ingredientDao;

  @GetMapping(value = "")
  public String myBarForm(Model model) {
    model.addAttribute(INGREDIENTS, DeserializerUtils.listAllIngredients());
    model.addAttribute(new MyBarForm());
    model.addAttribute(TITLE, MY_BAR);
    return MY_BAR_TEMPLATE;
  }

  @PostMapping(value = "")
  public String myBar(Model model, @ModelAttribute MyBarForm form, HttpServletRequest request) {

    ArrayList<IngredientInList> list =
        (ArrayList<IngredientInList>) DeserializerUtils.listAllIngredients();

    User theUser = getUserFromSession(request.getSession());
    List<Ingredient> ingredientsInMyBar = theUser.getIngredients();

    if (!form.checkIngredientInList(list)) {
      model.addAttribute(INGREDIENTS, DeserializerUtils.listAllIngredients());
      model.addAttribute(new MyBarForm());
      model.addAttribute(ERROR, "invalid");
      model.addAttribute(TITLE, MY_BAR);
      return MY_BAR_TEMPLATE;
    }

    if (form.checkIngredient(ingredientsInMyBar)) {
      model.addAttribute(INGREDIENTS, DeserializerUtils.listAllIngredients());
      model.addAttribute(new MyBarForm());
      model.addAttribute(ERROR, "duplicate");
      model.addAttribute(TITLE, MY_BAR);
      return MY_BAR_TEMPLATE;
    }

    Ingredient newIngredient = DeserializerUtils.searchIngredientByName(form.getIngredientName());
    ingredientDao.save(newIngredient);
    theUser.addItem(newIngredient);
    userDao.save(theUser);

    model.addAttribute("ingredients", DeserializerUtils.listAllIngredients());
    model.addAttribute(new MyBarForm());
    model.addAttribute(ERROR, "false");
    model.addAttribute(TITLE, MY_BAR);
    return MY_BAR_TEMPLATE;
  }

  @PostMapping(value = "remove")
  public String remove(
      @ModelAttribute @RequestParam(required = false) int[] ingredientIDs,
      Model model,
      HttpServletRequest request) {

    if (ingredientIDs == null) {
      return "redirect:./";
    }

    User theUser = getUserFromSession(request.getSession());

    for (int ingredientID : ingredientIDs) {

      ListIterator<Ingredient> iter = theUser.getIngredients().listIterator();

      while (iter.hasNext()) {
        if (iter.next().getId() == ingredientID) {
          iter.remove();
        }
      }
    }
    userDao.save(theUser);
    return "redirect:./";
  }
}
