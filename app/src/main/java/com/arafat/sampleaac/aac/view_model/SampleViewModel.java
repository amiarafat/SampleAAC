package com.arafat.sampleaac.aac.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.arafat.sampleaac.list_view_operation.Person;

import java.util.ArrayList;

public class SampleViewModel extends AndroidViewModel {

    public SampleViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData <ArrayList<Person>> personInfo;
    public MutableLiveData<ArrayList<Person>> getPersonInfo(){

        if(personInfo == null){
            personInfo = new MutableLiveData<>();
        }
        return personInfo;
    }
}
