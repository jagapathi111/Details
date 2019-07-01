package com.example.android.details.Lunch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.details.Lunch.Lunch;
import com.example.android.details.Lunch.LunchListAdapter;
import com.example.android.details.R;

import java.util.ArrayList;


public class LunchListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    public ArrayList<Lunch> foodsList;




    public LunchListAdapter(Context context, int layout, ArrayList<Lunch> foodsList) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView txtName;
    }



    @Override
    public View getView(final int position, final View view, final ViewGroup viewGroup) {


        View row = view;
        com.example.android.details.Lunch.LunchListAdapter.ViewHolder holder = new com.example.android.details.Lunch.LunchListAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (com.example.android.details.Lunch.LunchListAdapter.ViewHolder) row.getTag();
        }



        final Lunch food = foodsList.get(position);

        holder.txtName.setText(food.getName());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;





    }



}