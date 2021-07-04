package com.example.aplikasirentalmotor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TipeAdapter extends ArrayAdapter<String> {

    String mMotor = "";

    public TipeAdapter(Context context, ArrayList<String> listTipe) {
        super(context, R.layout.row_type, listTipe);
    }

    public void setMotor(String Motor) {
        this.mMotor = Motor;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return getCustomView(position,view,parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        if(view == null )
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_type, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.textView8);
        tvTitle.setText(getItem(position).substring(0,1));
        TextView tvKota = (TextView) view.findViewById(R.id.textView9);
        tvKota.setText(getItem(position));
        TextView tvProvinsi =(TextView) view.findViewById(R.id.textView10);
        tvProvinsi.setText(mMotor);
        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        return getCustomView(position,view,parent);
    }
}
