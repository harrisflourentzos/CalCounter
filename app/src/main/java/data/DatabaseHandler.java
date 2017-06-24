package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;

import model.Food;

/**
 * Created by harri on 23/06/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private ArrayList<Food> foodList = new ArrayList<>();

    public DatabaseHandler(Context context){
        super(context,Constants.TABLE_NAME,null,Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create our table
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.TITLE_NAME +
                " TEXT, " + Constants.CALORIES_NAME + " INT, " + Constants.DATE_NAME + " LONG);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        //create a new one
        onCreate(db);

    }

    public int getTotalItems(){
        int numberOfItems = 0;

        String query = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase dba = this.getReadableDatabase();
        Cursor cursor = dba.rawQuery(query,null);

        numberOfItems = cursor.getCount();

        cursor.close();
        dba.close();

        return numberOfItems;

    }

    public int getTotalCalories(){
        int numberOfCalories = 0;

        String query = " SELECT SUM( " + Constants.CALORIES_NAME + " ) " + " FROM " + Constants.TABLE_NAME;
        SQLiteDatabase dba = this.getReadableDatabase();
        Cursor cursor = dba.rawQuery(query, null);

        if(cursor.moveToFirst()){
            numberOfCalories = cursor.getInt(0);
        }

        cursor.close();
        dba.close();

        return numberOfCalories;
    }

    public void deleteFoodItem(int id){

        SQLiteDatabase dba = this.getWritableDatabase();
        dba.delete(Constants.TABLE_NAME, Constants.KEY_ID + " =?", new String[]{String.valueOf(id)});

        dba.close();
    }

    public void addFoodItem(Food food){

        SQLiteDatabase dba = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.TITLE_NAME, food.getFoodName());
        values.put(Constants.CALORIES_NAME, food.getCalories());
        values.put(Constants.DATE_NAME, System.currentTimeMillis());

        dba.insert(Constants.TABLE_NAME, null, values);

        Log.v("food item added!", "goleba");

        dba.close();
    }

    public ArrayList<Food> getAllFoodItems(){

        foodList.clear();

        SQLiteDatabase dba = this.getReadableDatabase();

        Cursor cursor = dba.query(Constants.TABLE_NAME, new String[]{Constants.TITLE_NAME, Constants.CALORIES_NAME,
                Constants.KEY_ID, Constants.DATE_NAME}, null, null, null, null, Constants.DATE_NAME + " DESC ");

        if(cursor.moveToFirst()){
            do {
                Food food = new Food();
                food.setFoodName(cursor.getString(cursor.getColumnIndex(Constants.TITLE_NAME)));
                food.setCalories(cursor.getInt(cursor.getColumnIndex(Constants.CALORIES_NAME)));
                food.setFoodId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String date = dateFormat.format(cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME)));

                food.setRecordDate(date);

                foodList.add(food);

            }while(cursor.moveToNext());
        }

        cursor.close();
        dba.close();

        return foodList;
    }

}
