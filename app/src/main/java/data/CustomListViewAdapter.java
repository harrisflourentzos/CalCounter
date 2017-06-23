package data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        return super.getView(position, convertView, parent);
    }

    public class ViewHolder {
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}
