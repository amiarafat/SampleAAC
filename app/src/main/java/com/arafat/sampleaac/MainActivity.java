package com.arafat.sampleaac;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.arafat.sampleaac.aac.view_model.SampleViewModel;
import com.arafat.sampleaac.list_view_operation.Person;
import com.arafat.sampleaac.list_view_operation.PersonListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String API = "https://randomname.de/?format=json&count=1&email=random";
    FloatingActionButton fab;
    ListView lvData;

    Person person;
    ArrayList<Person> personList =new ArrayList<>();
    private ArrayList<String> NameList = new ArrayList<>();

    PersonListAdapter adapter;

    SampleViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        model.getPersonInfo().observe(this,persons);

    }

    /*final Observer<List<Person>> persons = new Observer<List<Person>>() {
        @Override
        public void onChanged(@Nullable Person person) {

            personList.add(person);
            Log.d("data::",personList.size()+"");

            adapter = new PersonListAdapter(MainActivity.this,personList);
            lvData.setAdapter(adapter);
        }
    };
*/

    final Observer<ArrayList<Person>> persons = new Observer<ArrayList<Person>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Person> people) {

            personList=people;
            Log.d("data::",personList.size()+"");

            adapter = new PersonListAdapter(MainActivity.this,personList);
            lvData.setAdapter(adapter);

        }
    };
    private void initView() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        lvData = findViewById(R.id.lvData);
        model = ViewModelProviders.of(MainActivity.this).get(SampleViewModel.class);
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

                    //personList.clear();
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0;i<jsonArray.length();i++){

                        JSONObject object = jsonArray.getJSONObject(i);
                        person = new Person();

                        String firstName = object.getString("firstname");
                        String lastName = object.getString("lastname");
                        String gender = object.getString("gender");
                        String birthDate = object.getString("birthday");
                        String email = object.getString("email");

                        person.setFirstName(firstName);
                        person.setLastName(lastName);
                        person.setGender(gender);
                        person.setBirthDate(birthDate);
                        person.setEmail(email);

                        personList.add(person);

                    }

                    model.personInfo.setValue(personList);


                    for (int i = 0;i<personList.size();i++){
                        Log.d("name::", personList.get(i).getFirstName());
                    }



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
