package com.luispintodesa.bartender.models.forms;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterForm extends LoginForm {

  @NotNull(message = "Passwords do not match")
  private String verifyPassword;

  @Override
  public void setPassword(String password) {
    super.setPassword(password);
    checkPasswordForRegistration();
  }

    public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
    checkPasswordForRegistration();
  }

  private void checkPasswordForRegistration() {
    if (!getPassword().equals(verifyPassword)) {
      verifyPassword = null;
    }
  }
}
