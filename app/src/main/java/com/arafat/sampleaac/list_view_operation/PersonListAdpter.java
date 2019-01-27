package com.arafat.sampleaac.list_view_operation;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arafat.sampleaac.R;
import com.arafat.sampleaac.list_view_operation.Person;

import java.util.ArrayList;

public class PersonListAdpter extends ArrayAdapter {

    private Context context;
    private ArrayList<Person> personList = new ArrayList<>();
    private ArrayList<String> NameList = new ArrayList<>();

    public PersonListAdpter(Context context, ArrayList<String> NameList) {
        super(context, R.layout.list_each_row_person,NameList);

        this.context = context;
        this.NameList = NameList;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        if(convertView == null){
            LayoutInflater.from(context).inflate(R.layout.list_each_row_person,parent,false);
        }

        Log.d("Name: ",NameList.get(position));

//        Person personInfo = personList.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvGender = convertView.findViewById(R.id.tvGender);
        TextView tvBirth = convertView.findViewById(R.id.tvBirthDate);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);

        tvName.setText(NameList.get(position));
        /*tvGender.setText("Gender: "+personList.get(position));
        tvBirth.setText("BirthDate: "+personInfo.getBirthDate());
        tvEmail.setText("Email: "+personInfo.getEmail());*/

        return convertView;
    }
}
