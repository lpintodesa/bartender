package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.forms.LoginForm;
import com.luispintodesa.bartender.models.forms.RegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static com.luispintodesa.bartender.models.Constants.EMPTY_STRING;
import static com.luispintodesa.bartender.models.Constants.INDEX;
import static com.luispintodesa.bartender.models.Constants.LOGIN_PATH;
import static com.luispintodesa.bartender.models.Constants.LOGIN_TEMPLATE;
import static com.luispintodesa.bartender.models.Constants.LOGIN_TITLE;
import static com.luispintodesa.bartender.models.Constants.LOGOUT_PATH;
import static com.luispintodesa.bartender.models.Constants.MY_BAR_PATH;
import static com.luispintodesa.bartender.models.Constants.PASSWORD;
import static com.luispintodesa.bartender.models.Constants.REDIRECT_PATH;
import static com.luispintodesa.bartender.models.Constants.REGISTER;
import static com.luispintodesa.bartender.models.Constants.REGISTER_PATH;
import static com.luispintodesa.bartender.models.Constants.TITLE;
import static com.luispintodesa.bartender.models.Constants.USERNAME;

@Controller
@AllArgsConstructor
public class AuthenticationController extends UserController {

  @GetMapping(value = EMPTY_STRING)
  public String index(Model model) {
    model.addAttribute(TITLE, "The E-Bartender");
    return INDEX;
  }

  @GetMapping(value = REGISTER_PATH)
  public String registerForm(Model model) {
    model.addAttribute(new RegisterForm());
    model.addAttribute(TITLE, "Registration");
    return REGISTER;
  }

  @PostMapping(value = REGISTER_PATH)
  public String register(
      @ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

    if (errors.hasErrors()) {
      return REGISTER;
    }

    User existingUser = userManager.findUserByUsername(form.getUsername());

    if (existingUser != null) {
      errors.rejectValue(USERNAME, "username.alreadyexists", "A user with that username already exists");
      return REGISTER;
    }

    User newUser = new User(form.getUsername(), form.getPassword());
    userManager.saveUser(newUser);
    setUserInSession(request.getSession(), newUser);

    return REDIRECT_PATH + MY_BAR_PATH;
  }

  @GetMapping(value = LOGIN_PATH)
  public String login(Model model) {
    model.addAttribute(new LoginForm());
    model.addAttribute(TITLE, LOGIN_TITLE);
    return LOGIN_TEMPLATE;
  }

  @PostMapping(value = LOGIN_PATH)
  public String login(
      Model model,
      @ModelAttribute @Valid LoginForm form,
      Errors errors,
      HttpServletRequest request) {

    if (errors.hasErrors()) {
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    User theUser = userManager.findUserByUsername(form.getUsername());
    String password = form.getPassword();

    if (null == theUser) {
      errors.rejectValue(USERNAME, "user.invalid", "The given username does not exist");
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    if (!theUser.isMatchingPassword(password)) {
      errors.rejectValue(PASSWORD, "password.invalid", "Invalid password");
      model.addAttribute(TITLE, LOGIN_TITLE);
      return LOGIN_TEMPLATE;
    }

    setUserInSession(request.getSession(), theUser);

    ingredientsAndDrinksManager.updateDrinksForAllIngredients(theUser);

    return REDIRECT_PATH + MY_BAR_PATH;
  }

  @GetMapping(value = LOGOUT_PATH)
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return REDIRECT_PATH;
  }
}
