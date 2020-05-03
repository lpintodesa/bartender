package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

  public static final String USER_SESSION_KEY = "user_id";
  @Autowired protected UserDao userDao;

  protected User getUserFromSession(HttpSession session) {
    Integer userId = (Integer) session.getAttribute(USER_SESSION_KEY);
    return userId == null ? null : userDao.findById(userId).orElse(null);
  }

  protected void setUserInSession(HttpSession session, User user) {
    session.setAttribute(USER_SESSION_KEY, user.getUid());
  }

  @ModelAttribute("user")
  public User getUserForModel(HttpServletRequest request) {
    return getUserFromSession(request.getSession());
  }
}
