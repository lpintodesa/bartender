package com.luispintodesa.bartender.models.manipulation;

public class SpaceToUnderscoreConverter {

  private SpaceToUnderscoreConverter() {}

  public static String convert(String string) {

    return string.replaceAll("\\s", "_");
  }
}
