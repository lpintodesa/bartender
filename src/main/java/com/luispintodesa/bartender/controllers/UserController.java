package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.managers.IngredientsAndDrinksManager;
import com.luispintodesa.bartender.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.luispintodesa.bartender.models.Constants.USER_SESSION_KEY;

public abstract class UserController {

  @Autowired
  IngredientsAndDrinksManager ingredientsAndDrinksManager;
  @Autowired
  UserManager userManager;

  public User getUserFromSession(HttpSession session) {

    User user = null;

    Integer id = (Integer) session.getAttribute(USER_SESSION_KEY);

    if (null != id){
      user = userManager.findUserById((int) session.getAttribute(USER_SESSION_KEY));
    }
    return user;
  }

  protected void setUserInSession(HttpSession session, User user) {
    session.setAttribute(USER_SESSION_KEY, user.getId());
  }

  @ModelAttribute("user")
  public User getUserForModel(HttpServletRequest request) {
    return getUserFromSession(request.getSession());
  }
}
