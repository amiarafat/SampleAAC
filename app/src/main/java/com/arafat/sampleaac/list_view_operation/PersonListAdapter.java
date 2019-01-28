package com.arafat.sampleaac.list_view_operation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arafat.sampleaac.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Person> personList =new ArrayList<>();

    public PersonListAdapter(Context context, ArrayList<Person> personList) {
        super(context, R.layout.list_each_row_person,personList);

        this.context = context;
        this.personList = personList;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_each_row_person, parent, false);
        }

        TextView tvName,tvGender,tvBirthDate,tvEmail;

        tvName = convertView.findViewById(R.id.tvName);
        tvGender = convertView.findViewById(R.id.tvGender);
        tvBirthDate = convertView.findViewById(R.id.tvBirthDate);
        tvEmail = convertView.findViewById(R.id.tvEmail);


        tvName.setText("Name: "+personList.get(position).getFirstName()+" "+personList.get(position).getLastName());
        tvGender.setText("Gender: "+personList.get(position).getGender());
        tvBirthDate.setText("BirthDate: "+personList.get(position).getBirthDate());
        tvEmail.setText("Email: "+personList.get(position).getEmail());

        return convertView;
    }
}
