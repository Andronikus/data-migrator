package pt.andronikus.utils;

public class WhenNullValueThen {
    public static String setStringOrInAbsence(String original, String defaultValue){
        return original == null ? defaultValue : original;
    }
}
