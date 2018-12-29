package com.futech.smartbirth;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PerkembanganViewModel extends AndroidViewModel {

    private PerkembanganRepository perkembanganRepository;
    private LiveData<List<Data>> mAllData;

    public PerkembanganViewModel(Application application){
        super(application);
        perkembanganRepository = new PerkembanganRepository(application);
        mAllData = perkembanganRepository.getAllData();

    }

    LiveData<List<Data>> getAllData() {
        return mAllData;
    }

    public void insert(Data data){
        perkembanganRepository.insert(data);
    }
}
