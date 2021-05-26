package application.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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

    public void writeToJson(JSONObject jsonObject) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/settings/settings.json");
        byte[] strToBytes = jsonObject.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public JSONObject getJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject settings = (JSONObject) parser.parse(new FileReader("src/settings/settings.json"));
        return settings;
    }


    public JSONObject getJsonObject(String objname,JSONObject jsonobject){
        return (JSONObject) jsonobject.get(objname);
    }
}
