package com.luispintodesa.bartender.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
public class User {

  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Id @GeneratedValue private int id;

  @NotNull
  @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Invalid username")
  private String username;

  @NotNull private String pwHash;

  @ManyToMany private List<Ingredient> ingredients;

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

  public boolean isMatchingPassword(String password) {
    return encoder.matches(password, pwHash);
  }

  public List<String> getLowerCaseIngredientNames() {
    return ingredients.stream()
            .map(Ingredient::getName)
            .map(WordUtils::capitalizeFully)
            .collect(Collectors.toList());
  }
}
