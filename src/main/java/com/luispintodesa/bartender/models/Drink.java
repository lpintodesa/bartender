package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Drink {

  @JsonProperty("idDrink")
  private int id;

  @JsonProperty("strDrink")
  private String name;

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getNameIngredient1() {
    return nameIngredient1;
  }

  public void setNameIngredient1(String nameIngredient1) {
    this.nameIngredient1 = nameIngredient1;
  }

  public String getNameIngredient2() {
    return nameIngredient2;
  }

  public void setNameIngredient2(String nameIngredient2) {
    this.nameIngredient2 = nameIngredient2;
  }

  public String getNameIngredient3() {
    return nameIngredient3;
  }

  public void setNameIngredient3(String nameIngredient3) {
    this.nameIngredient3 = nameIngredient3;
  }

  public String getNameIngredient4() {
    return nameIngredient4;
  }

  public void setNameIngredient4(String nameIngredient4) {
    this.nameIngredient4 = nameIngredient4;
  }

  public String getNameIngredient5() {
    return nameIngredient5;
  }

  public void setNameIngredient5(String nameIngredient5) {
    this.nameIngredient5 = nameIngredient5;
  }

  public String getNameIngredient6() {
    return nameIngredient6;
  }

  public void setNameIngredient6(String nameIngredient6) {
    this.nameIngredient6 = nameIngredient6;
  }

  public String getNameIngredient7() {
    return nameIngredient7;
  }

  public void setNameIngredient7(String nameIngredient7) {
    this.nameIngredient7 = nameIngredient7;
  }

  public String getNameIngredient8() {
    return nameIngredient8;
  }

  public void setNameIngredient8(String nameIngredient8) {
    this.nameIngredient8 = nameIngredient8;
  }

  public String getNameIngredient9() {
    return nameIngredient9;
  }

  public void setNameIngredient9(String nameIngredient9) {
    this.nameIngredient9 = nameIngredient9;
  }

  public String getNameIngredient10() {
    return nameIngredient10;
  }

  public void setNameIngredient10(String nameIngredient10) {
    this.nameIngredient10 = nameIngredient10;
  }

  public String getNameIngredient11() {
    return nameIngredient11;
  }

  public void setNameIngredient11(String nameIngredient11) {
    this.nameIngredient11 = nameIngredient11;
  }

  public String getNameIngredient12() {
    return nameIngredient12;
  }

  public void setNameIngredient12(String nameIngredient12) {
    this.nameIngredient12 = nameIngredient12;
  }

  public String getNameIngredient13() {
    return nameIngredient13;
  }

  public void setNameIngredient13(String nameIngredient13) {
    this.nameIngredient13 = nameIngredient13;
  }

  public String getNameIngredient14() {
    return nameIngredient14;
  }

  public void setNameIngredient14(String nameIngredient14) {
    this.nameIngredient14 = nameIngredient14;
  }

  public String getNameIngredient15() {
    return nameIngredient15;
  }

  public void setNameIngredient15(String nameIngredient15) {
    this.nameIngredient15 = nameIngredient15;
  }

  public String getMeasureIngredient1() {
    return measureIngredient1;
  }

  public void setMeasureIngredient1(String measureIngredient1) {
    this.measureIngredient1 = measureIngredient1;
  }

  public String getMeasureIngredient2() {
    return measureIngredient2;
  }

  public void setMeasureIngredient2(String measureIngredient2) {
    this.measureIngredient2 = measureIngredient2;
  }

  public String getMeasureIngredient3() {
    return measureIngredient3;
  }

  public void setMeasureIngredient3(String measureIngredient3) {
    this.measureIngredient3 = measureIngredient3;
  }

  public String getMeasureIngredient4() {
    return measureIngredient4;
  }

  public void setMeasureIngredient4(String measureIngredient4) {
    this.measureIngredient4 = measureIngredient4;
  }

  public String getMeasureIngredient5() {
    return measureIngredient5;
  }

  public void setMeasureIngredient5(String measureIngredient5) {
    this.measureIngredient5 = measureIngredient5;
  }

  public String getMeasureIngredient6() {
    return measureIngredient6;
  }

  public void setMeasureIngredient6(String measureIngredient6) {
    this.measureIngredient6 = measureIngredient6;
  }

  public String getMeasureIngredient7() {
    return measureIngredient7;
  }

  public void setMeasureIngredient7(String measureIngredient7) {
    this.measureIngredient7 = measureIngredient7;
  }

  public String getMeasureIngredient8() {
    return measureIngredient8;
  }

  public void setMeasureIngredient8(String measureIngredient8) {
    this.measureIngredient8 = measureIngredient8;
  }

  public String getMeasureIngredient9() {
    return measureIngredient9;
  }

  public void setMeasureIngredient9(String measureIngredient9) {
    this.measureIngredient9 = measureIngredient9;
  }

  public String getMeasureIngredient10() {
    return measureIngredient10;
  }

  public void setMeasureIngredient10(String measureIngredient10) {
    this.measureIngredient10 = measureIngredient10;
  }

  public String getMeasureIngredient11() {
    return measureIngredient11;
  }

  public void setMeasureIngredient11(String measureIngredient11) {
    this.measureIngredient11 = measureIngredient11;
  }

  public String getMeasureIngredient12() {
    return measureIngredient12;
  }

  public void setMeasureIngredient12(String measureIngredient12) {
    this.measureIngredient12 = measureIngredient12;
  }

  public String getMeasureIngredient13() {
    return measureIngredient13;
  }

  public void setMeasureIngredient13(String measureIngredient13) {
    this.measureIngredient13 = measureIngredient13;
  }

  public String getMeasureIngredient14() {
    return measureIngredient14;
  }

  public void setMeasureIngredient14(String measureIngredient14) {
    this.measureIngredient14 = measureIngredient14;
  }

  public String getMeasureIngredient15() {
    return measureIngredient15;
  }

  public void setMeasureIngredient15(String measureIngredient15) {
    this.measureIngredient15 = measureIngredient15;
  }
}
