package com.luispintodesa.bartender.models;

import org.apache.commons.text.WordUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {

  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Id @GeneratedValue private int id;

  @NotNull
  @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Invalid username")
  private String username;

  @NotNull private String pwHash;

  @ManyToMany private List<Ingredient> ingredients;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.pwHash = hashPassword(password);
  }

  private static String hashPassword(String password) {
    return encoder.encode(password);
  }

  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  public String getUsername() {
    return username;
  }

  public boolean isMatchingPassword(String password) {
    return encoder.matches(password, pwHash);
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public List<String> getLowerCaseIngredientNames() {
    return ingredients.stream()
        .map(ingredient -> WordUtils.capitalizeFully(ingredient.getName()))
        .collect(Collectors.toList());
  }

  public int getId() {
    return this.id;
  }
}
