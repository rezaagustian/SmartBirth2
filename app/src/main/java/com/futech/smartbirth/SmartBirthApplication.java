package com.futech.smartbirth;

import android.app.Application;

import com.evernote.android.job.JobManager;

public class SmartBirthApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new SmartBirthJobCreator());
    }
}
