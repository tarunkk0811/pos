package application.controllers;

public class ApplicationMainController {
    public String doubleToStringF(Double d){
        return String.format("%.2f",d);
    }
    public String capitalize(String word)
    {
        String nameCapitalized = "";
        String s1 = word.substring(0, 1).toUpperCase();
        nameCapitalized = s1 + word.substring(1).toLowerCase();
        return nameCapitalized;
    }
}
