package com.futech.smartbirth;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PerkembanganRepository {

    private PerkembanganDao mPerkembanganDao;
    private LiveData<List<Data>> mAllData;


    PerkembanganRepository(Application application) {
        PerkembanganRoomDatabase db = PerkembanganRoomDatabase.getDatabase(application);
        mPerkembanganDao = db.perkembanganDao();
        mAllData = mPerkembanganDao.getAllData();
    }

    LiveData<List<Data>> getAllData() {
        return mAllData;
    }

    public void insert (Data data) {
        new insertAsyncTask(mPerkembanganDao).execute(data);
    }

    private static class insertAsyncTask extends AsyncTask<Data, Void, Void> {

        private PerkembanganDao mAsyncTaskDao;

        insertAsyncTask(PerkembanganDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Data... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
