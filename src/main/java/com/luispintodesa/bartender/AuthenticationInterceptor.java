package com.luispintodesa.bartender;

import com.luispintodesa.bartender.controllers.UserController;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
  @Autowired UserDao userDao;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {

    // Authentication white list; add all publicly visible pages here
    List<String> nonAuthPages = Arrays.asList("/index", "/login", "/register", "/");

    // Require sign-in for auth pages
    if (!nonAuthPages.contains(request.getRequestURI())) {

      Integer userId = (Integer) request.getSession().getAttribute(UserController.USER_SESSION_KEY);

      if (userId != null) {
        User user = userDao.findById(userId).orElse(null);

        if (user != null) return true;
      }

      response.sendRedirect("/login");
      return false;
    }

    return true;
  }
}
