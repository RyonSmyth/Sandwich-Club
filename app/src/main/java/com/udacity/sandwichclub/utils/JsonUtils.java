package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwhich = new Sandwich();

        try {
            JSONObject sandwhichObject = new JSONObject(json);
            JSONObject sandwhichName = sandwhichObject.getJSONObject("name");
            String name = sandwhichName.getString("mainName");
            JSONArray JsonKnownAs = sandwhichName.getJSONArray("alsoKnownAs");
            String placeOfOrigin = sandwhichObject.getString("placeOfOrigin");
            String description = sandwhichObject.getString("description");
            String image = sandwhichObject.getString("image");
            JSONArray JsonIngredients = sandwhichObject.getJSONArray("ingredients");

            List<String> alsoKnownAs = new ArrayList<>();
            List<String> ingredients = new ArrayList<>();

            for (int i = 0; i < JsonKnownAs.length(); i++ ) {
                alsoKnownAs.add(JsonKnownAs.getString(i));
            }

            for (int i = 0; i < JsonIngredients.length(); i++ ) {
                ingredients.add(JsonIngredients.getString(i));
            }

            sandwhich.setMainName(name);
            sandwhich.setAlsoKnownAs(alsoKnownAs);
            sandwhich.setPlaceOfOrigin(placeOfOrigin);
            sandwhich.setDescription(description);
            sandwhich.setImage(image);
            sandwhich.setIngredients(ingredients);

            System.out.print(sandwhich);

            return sandwhich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }



    }
}
