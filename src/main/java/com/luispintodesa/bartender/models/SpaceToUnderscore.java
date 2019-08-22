package com.luispintodesa.bartender.models;

public class SpaceToUnderscore {

    public static String convert(String string){
        String processedName="";

        for (char c: string.toCharArray()){
            if (Character.isWhitespace(c)){
                processedName+="_";
            } else {
                processedName+=c;
            }
        }
        return processedName;
    }
}
