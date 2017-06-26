package app.calcounterapplication.com.calcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class FoodDetails extends AppCompatActivity {

    private TextView name, calories, date;
    private Button shareButton;
    private Button deleteButton;
    private int foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        name = (TextView) findViewById(R.id.foodDetailsFoodNameId);
        calories = (TextView) findViewById(R.id.foodDetailsCaloriesId);
        date = (TextView) findViewById(R.id.foodDetailsDateId);

        shareButton = (Button) findViewById(R.id.foodDetailsButtonShareId);
        deleteButton = (Button) findViewById(R.id.buttonDeleteId);

        Food food = (Food) getIntent().getSerializableExtra("userObj");

        name.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        date.setText(food.getRecordDate());

        foodId = food.getFoodId();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                dba.deleteFoodItem(foodId);
                Toast.makeText(getApplicationContext(), "This Item was deleted", Toast.LENGTH_LONG);

                startActivity(new Intent(FoodDetails.this, DisplayFoods.class));
                FoodDetails.this.finish();
            }
        });

    }
}
