package com.luispintodesa.bartender.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginForm {

  @NotNull
  @Pattern(
      regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}",
      message =
          "Usernames must be between 5 and 12 characters, start with a letter, and contain only letters and numbers")
  private String username;

  @NotNull
  @Pattern(regexp = "(\\S){4,20}", message = "Password must have 4-20 non-whitespace characters")
  private String password;
}
