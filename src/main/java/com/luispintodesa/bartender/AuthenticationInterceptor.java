package com.luispintodesa.bartender;

import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.INDEX_PATH;
import static com.luispintodesa.bartender.models.Constants.LOGIN_PATH;
import static com.luispintodesa.bartender.models.Constants.REGISTER_PATH;
import static com.luispintodesa.bartender.models.Constants.USER_SESSION_KEY;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
  @Autowired UserDao userDao;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {

    List<String> nonAuthPages = Arrays.asList(INDEX_PATH, LOGIN_PATH, REGISTER_PATH, "/");

    if (!nonAuthPages.contains(request.getRequestURI())) {

      Integer userId = (Integer) request.getSession().getAttribute(USER_SESSION_KEY);

      if (userId != null) {
        User user = userDao.findById((int) userId);
        if (user != null) return true;
      }
      response.sendRedirect(LOGIN_PATH);
      return false;
    }
    return true;
  }
}
