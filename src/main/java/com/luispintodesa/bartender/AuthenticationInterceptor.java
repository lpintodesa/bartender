package com.luispintodesa.bartender;

import com.luispintodesa.bartender.models.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.INDEX_PATH;
import static com.luispintodesa.bartender.models.Constants.LOGIN_PATH;
import static com.luispintodesa.bartender.models.Constants.REGISTER_PATH;
import static com.luispintodesa.bartender.models.Constants.USER_SESSION_KEY;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
  private final UserDao userDao;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

    List<String> nonAuthPages = Arrays.asList(INDEX_PATH, LOGIN_PATH, REGISTER_PATH, "/");

    if (!nonAuthPages.contains(request.getRequestURI())) {

      boolean userFound = ofNullable(request.getSession().getAttribute(USER_SESSION_KEY))
              .map(Integer.class::cast)
              .map(userDao::findById)
              .isPresent();

      if (!userFound) {
        response.sendRedirect(LOGIN_PATH);
      }
      return userFound;
    }
    return true;
  }
}
