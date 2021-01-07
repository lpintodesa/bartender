package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.forms.LoginForm;
import com.luispintodesa.bartender.models.forms.RegisterForm;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.LOGIN_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.LOGIN_TITLE;
import static com.luispintodesa.bartender.models.Constants.REGISTER;
import static com.luispintodesa.bartender.models.Constants.TITLE;

@Controller
public class AuthenticationController extends UserController {

  @GetMapping(value = "")
  public String index(Model model) {
    model.addAttribute(TITLE, "The E-Bartender");
    return "index";
  }

  @GetMapping(value = "/register")
  public String registerForm(Model model) {
    model.addAttribute(new RegisterForm());
    model.addAttribute(TITLE, "Registration");
    return REGISTER;
  }

  @PostMapping(value = "/register")
  public String register(
      @ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

    if (errors.hasErrors()) {
      return REGISTER;
    }

    User existingUser = userDao.findByUsername(form.getUsername());

    if (existingUser != null) {
      errors.rejectValue(
          "username", "username.alreadyexists", "A user with that username already exists");
      return REGISTER;
    }

    User newUser = new User(form.getUsername(), form.getPassword());
    userDao.save(newUser);
    setUserInSession(request.getSession(), newUser);

    return "redirect:/mybar";
  }

  @GetMapping(value = "/login")
  public String login(Model model) {
    model.addAttribute(new LoginForm());
    model.addAttribute(TITLE, LOGIN_TITLE);
    return LOGIN_TEMPLATE;
  }

  @PostMapping(value = "/login")
  public String login(
      Model model,
      @ModelAttribute @Valid LoginForm form,
      Errors errors,
      HttpServletRequest request) {

    if (errors.hasErrors()) {
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    User theUser = userDao.findByUsername(form.getUsername());
    String password = form.getPassword();

    if (theUser == null) {
      errors.rejectValue("username", "user.invalid", "The given username does not exist");
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    if (!theUser.isMatchingPassword(password)) {
      errors.rejectValue("password", "password.invalid", "Invalid password");
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    setUserInSession(request.getSession(), theUser);

    for (Ingredient ingredient : theUser.getIngredients()) {
      List<Integer> drinkIds = DeserializerUtils.searchDrinkIdsBySingleIngredient(ingredient);
      List<Integer> newIds =
          (List<Integer>) CollectionUtils.removeAll(drinkIds, ingredient.getDrinkIds());

      for (Drink drink : ingredient.getDrinks()) {
        if (!drinkIds.contains(drink.getId())) {
          ingredient.removeDrink(drink);
        }
      }

      for (Integer id : drinkIds) {
        if (newIds.contains(id)) {
          Drink newDrink = DeserializerUtils.searchDrinkById(id);
          drinkDao.save(newDrink);
          ingredient.addDrink(newDrink);
        }
      }
      ingredientDao.save(ingredient);
    }

    return "redirect:/mybar";
  }

  @GetMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:";
  }
}
