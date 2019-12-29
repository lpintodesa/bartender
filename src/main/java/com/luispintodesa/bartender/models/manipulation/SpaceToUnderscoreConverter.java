package com.luispintodesa.bartender.models.manipulation;

public class SpaceToUnderscoreConverter {

    public static String convert(String string){

        StringBuilder processedName = new StringBuilder();

        for (char c: string.toCharArray()){
            if (Character.isWhitespace(c)){
                processedName.append("_");
            } else {
                processedName.append(c);
            }
        }
        return processedName.toString();
    }

    private SpaceToUnderscoreConverter() {
    }
}
