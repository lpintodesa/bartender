package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.WordUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Drink {

  public static final Drink NOT_FOUND = new Drink("Drink Not Found");

  @JsonProperty("idDrink")
  @Id
  private int id;

  @JsonProperty("strDrink")
  private String name;

  @Column(length = 1000)
  @JsonProperty("strInstructions")
  private String instructions;

  @JsonProperty("strDrinkThumb")
  private String thumbnail;

  @JsonProperty("strIngredient1")
  private String nameIngredient1;

  @JsonProperty("strIngredient2")
  private String nameIngredient2;

  @JsonProperty("strIngredient3")
  private String nameIngredient3;

  @JsonProperty("strIngredient4")
  private String nameIngredient4;

  @JsonProperty("strIngredient5")
  private String nameIngredient5;

  @JsonProperty("strIngredient6")
  private String nameIngredient6;

  @JsonProperty("strIngredient7")
  private String nameIngredient7;

  @JsonProperty("strIngredient8")
  private String nameIngredient8;

  @JsonProperty("strIngredient9")
  private String nameIngredient9;

  @JsonProperty("strIngredient10")
  private String nameIngredient10;

  @JsonProperty("strIngredient11")
  private String nameIngredient11;

  @JsonProperty("strIngredient12")
  private String nameIngredient12;

  @JsonProperty("strIngredient13")
  private String nameIngredient13;

  @JsonProperty("strIngredient14")
  private String nameIngredient14;

  @JsonProperty("strIngredient15")
  private String nameIngredient15;

  @JsonProperty("strMeasure1")
  private String measureIngredient1;

  @JsonProperty("strMeasure2")
  private String measureIngredient2;

  @JsonProperty("strMeasure3")
  private String measureIngredient3;

  @JsonProperty("strMeasure4")
  private String measureIngredient4;

  @JsonProperty("strMeasure5")
  private String measureIngredient5;

  @JsonProperty("strMeasure6")
  private String measureIngredient6;

  @JsonProperty("strMeasure7")
  private String measureIngredient7;

  @JsonProperty("strMeasure8")
  private String measureIngredient8;

  @JsonProperty("strMeasure9")
  private String measureIngredient9;

  @JsonProperty("strMeasure10")
  private String measureIngredient10;

  @JsonProperty("strMeasure11")
  private String measureIngredient11;

  @JsonProperty("strMeasure12")
  private String measureIngredient12;

  @JsonProperty("strMeasure13")
  private String measureIngredient13;

  @JsonProperty("strMeasure14")
  private String measureIngredient14;

  @JsonProperty("strMeasure15")
  private String measureIngredient15;

  @ManyToMany(mappedBy = "drinks")
  private List<Ingredient> ingredients;

  @Transient private List<String> missingIngredients;

  public Drink(String name) {
    this.name = name;
  }

  private List<String> getIngredientMeasuresArray() {
    return Arrays.asList(
        measureIngredient1,
        measureIngredient2,
        measureIngredient3,
        measureIngredient4,
        measureIngredient5,
        measureIngredient6,
        measureIngredient7,
        measureIngredient8,
        measureIngredient9,
        measureIngredient10,
        measureIngredient11,
        measureIngredient12,
        measureIngredient13,
        measureIngredient14,
        measureIngredient15);
  }

  private List<String> getIngredientNamesArray() {
    return Arrays.asList(
        nameIngredient1,
        nameIngredient2,
        nameIngredient3,
        nameIngredient4,
        nameIngredient5,
        nameIngredient6,
        nameIngredient7,
        nameIngredient8,
        nameIngredient9,
        nameIngredient10,
        nameIngredient11,
        nameIngredient12,
        nameIngredient13,
        nameIngredient14,
        nameIngredient15);
  }

  public List<String> getTitleCaseIngredientNames() {
    return getIngredientNamesArray().stream()
        .filter(java.util.Objects::nonNull)
        .map(WordUtils::capitalizeFully)
        .collect(Collectors.toList());
  }

  public List<String> getIngredientNames() {
    return getIngredientNamesArray().stream()
        .filter(java.util.Objects::nonNull)
        .collect(Collectors.toList());
  }

  public List<String> getIngredientMeasures() {
    return getIngredientMeasuresArray().stream()
        .filter(java.util.Objects::nonNull)
        .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Drink drink)) {
      return false;
    }
      return id == drink.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
