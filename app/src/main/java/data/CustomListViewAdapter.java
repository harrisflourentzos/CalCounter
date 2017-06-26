package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.calcounterapplication.com.calcounter.FoodDetails;
import app.calcounterapplication.com.calcounter.R;
import model.Food;

/**
 * Created by harri on 23/06/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<Food> {

    private int layoutResource;
    private Activity activity;
    private ArrayList<Food> foodList = new ArrayList<>();

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        layoutResource = resource;
        activity = act;
        foodList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public int getPosition(@Nullable Food item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if(row == null || (row.getTag() == null) ){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new ViewHolder();

            holder.foodName = (TextView) row.findViewById(R.id.listRowFoodNameId);
            holder.foodCalories = (TextView) row.findViewById(R.id.listRowCaloriesId);
            holder.foodDate = (TextView) row.findViewById(R.id.listRowDateId);

            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();
        }

        holder.food = getItem(position);

        holder.foodName.setText(holder.food.getFoodName());
        holder.foodCalories.setText(String.valueOf(holder.food.getCalories()));
        holder.foodDate.setText(holder.food.getRecordDate());

        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, FoodDetails.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("userObj", finalHolder.food);
                i.putExtras(bundle);

                activity.startActivity(i);
            }
        });

        return row;
    }

    public class ViewHolder {
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}
