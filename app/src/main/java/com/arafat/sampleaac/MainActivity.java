package com.arafat.sampleaac;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arafat.sampleaac.list_view_operation.Person;
import com.arafat.sampleaac.list_view_operation.PersonListAdpter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String API = "https://randomname.de/?format=json&count=1&email=random";
    FloatingActionButton fab;
    ListView lvData;
    PersonListAdpter adapter;

    Person person;
    ArrayList<Person> personList =new ArrayList<>();
    private ArrayList<String> NameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();


    }

    private void initView() {
        person = new Person();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        lvData = findViewById(R.id.lvData);
    }


    @Override
    public void onClick(View v) {

        if(v == fab){
            getInformation();
        }
    }

    private void getInformation() {

        StringRequest request = new StringRequest(Request.Method.GET, API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("res::",response);
                try {


                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0;i<jsonArray.length();i++){

                        JSONObject object = jsonArray.getJSONObject(i);

                        String firstName = object.getString("firstname");
                        String lastName = object.getString("lastname");
                        String gender = object.getString("gender");
                        String birthDate = object.getString("birthday");
                        String email = object.getString("email");

                       /* person.setFirstName(firstName);
                        person.setLastName(lastName);
                        person.setGender(gender);
                        person.setLastName(birthDate);
                        person.setLastName(email);

                        personList.add(person);*/

                       NameList.add(firstName+" "+lastName);

                    }

                    adapter = new PersonListAdpter(MainActivity.this,NameList);
                    lvData.setAdapter(adapter);


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
