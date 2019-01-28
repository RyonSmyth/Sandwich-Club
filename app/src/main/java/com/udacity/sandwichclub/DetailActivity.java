package com.udacity.sandwichclub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView knownAsTextView = findViewById(R.id.also_known_tv);
        TextView originTextView = findViewById(R.id.origin_tv);
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        TextView descriptionTextView = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        originTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());

//        for (String ingredient : sandwich.getIngredients()) {
//            allIngrdients += ingredient;
//        }
//
//        for (String altName : sandwich.getAlsoKnownAs()) {
//            allAltNames += altName;
//        }

        List<String> ingredientsList = sandwich.getIngredients();
        String allIngrdients = String.join(", ", ingredientsList);

        List<String> altNamesList = sandwich.getAlsoKnownAs();
        String allAltNames = String.join(", ", altNamesList);

        ingredientsTextView.setText(allIngrdients);
        knownAsTextView.setText(allAltNames);


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
