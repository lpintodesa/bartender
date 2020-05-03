package com.luispintodesa.bartender.models.manipulation;

public class SpaceToUnderscoreConverter {

    public static String convert(String string){

        return string.replaceAll("\\s", "_");
    }

    private SpaceToUnderscoreConverter() {
    }
}
