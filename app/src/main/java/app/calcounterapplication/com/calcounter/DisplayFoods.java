package app.calcounterapplication.com.calcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DatabaseHandler;
import model.Food;

public class DisplayFoods extends AppCompatActivity {

    private DatabaseHandler dba;
    private ArrayList<Food> dbFoods = new ArrayList<>();
    private CustomListViewAdapter foodAdapter;
    private ListView listView;

    private Food myFood;
    private TextView totalItems, totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_foods);

        totalItems = (TextView) findViewById(R.id.displayFoodsFoodItemsId);
        totalCalories = (TextView) findViewById(R.id.displayFoodsTotalCalId);

        listView = (ListView) findViewById(R.id.displayFoodsListViewId);

        refreshData();
    }

    private void refreshData() {

        dbFoods.clear();

        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<Food> foodsFromDatabase = dba.getAllFoodItems();

        totalItems.setText("Total Items: " + dba.getTotalItems());
        totalCalories.setText("Total Calories: " + dba.getTotalCalories());

        for( int i = 0; i < foodsFromDatabase.size(); i++ ){

            String name = foodsFromDatabase.get(i).getFoodName();
            String dateText = foodsFromDatabase.get(i).getRecordDate();
            int cal = foodsFromDatabase.get(i).getCalories();
            int foodId = foodsFromDatabase.get(i).getFoodId();

            myFood = new Food();
            myFood.setFoodName(name);
            myFood.setRecordDate(dateText);
            myFood.setCalories(cal);
            myFood.setFoodId(foodId);

            dbFoods.add(myFood);

        }
        dba.close();

        //Set up adapter
        foodAdapter = new CustomListViewAdapter(DisplayFoods.this, R.layout.list_row, dbFoods);
        listView.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();


    }
}
