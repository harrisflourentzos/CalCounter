package app.calcounterapplication.com.calcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler dba;
    private EditText foodName, foodCalories;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseHandler(MainActivity.this);

        foodName = (EditText) findViewById(R.id.mainEditTextFoodId);
        foodCalories = (EditText) findViewById(R.id.mainEditTextCalId);

        submitButton = (Button) findViewById(R.id.mainButtonId);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOnDatabase();
            }
        });
    }

    private void saveOnDatabase() {

        Food food = new Food();
        String name = foodName.getText().toString().trim();
        String calString = foodCalories.getText().toString().trim();
        int calInt = Integer.parseInt(calString);

        if(name == "" || calString == ""){
            Toast.makeText(getApplicationContext(), "Please make sure you fill both fields!!", Toast.LENGTH_LONG).show();
        }else{
            food.setFoodName(name);
            food.setCalories(calInt);
        }
        dba.addFoodItem(food);

        //clear EditText fields:
        foodName.setText("");
        foodCalories.setText("");

        //take users to the ListView of the submitted foods:
//        startActivity(new Intent(MainActivity.this, DisplayFoods.class));
    }
}
