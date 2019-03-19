package com.webstarterz.happycheetah.pop_up_design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CarAdapter extends ArrayAdapter<Car> {

    List<Car>  personList;
    private Context mContext;

    public CarAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.personList = objects;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        Car currentPerson = getItem(position);

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.model_list, parent, false);
        }

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.model_text_view);
        nameTextView.setText(currentPerson.getModel());


        return listItemView;
    }

    @Override
    public void remove(Car object) {
    }

    public Car getItem(int position) {
        return personList.get(position);
    }
}
