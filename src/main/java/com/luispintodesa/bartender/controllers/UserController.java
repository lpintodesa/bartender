package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.managers.IngredientsAndDrinksManager;
import com.luispintodesa.bartender.models.managers.UserManager;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.luispintodesa.bartender.models.Constants.USER_SESSION_KEY;
import static java.util.Optional.ofNullable;

public abstract class UserController {

  protected IngredientsAndDrinksManager ingredientsAndDrinksManager;
  protected UserManager userManager;

  public User getUserFromSession(HttpSession session) {

    return ofNullable(session.getAttribute(USER_SESSION_KEY))
            .map(Integer.class::cast)
            .map(userManager::findUserById)
            .orElse(null);
  }

  protected void setUserInSession(HttpSession session, User user) {
    session.setAttribute(USER_SESSION_KEY, user.getId());
  }

  @ModelAttribute("user")
  public User getUserForModel(HttpServletRequest request) {
    return getUserFromSession(request.getSession());
  }
}
