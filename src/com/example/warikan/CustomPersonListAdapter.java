package com.example.warikan;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomPersonListAdapter extends ArrayAdapter<PersonList> {
    Context context;
    LayoutInflater inflater;

    public CustomPersonListAdapter(Context context, ArrayList<PersonList> objects) {
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonList person = (PersonList)getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.person_list, null);
        }
        CheckBox chkPaid = (CheckBox)convertView.findViewById(R.id.row_chk_Paid);
        TextView txtview = (TextView)convertView.findViewById(R.id.row_PersonText);
        
        chkPaid.setChecked(person.isPaidChecked());
        txtview.setText(person.getPersonName());
        return convertView;
    }
}

