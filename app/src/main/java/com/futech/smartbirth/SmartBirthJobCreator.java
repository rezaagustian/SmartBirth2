package com.futech.smartbirth;


import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SmartBirthJobCreator implements JobCreator {

    @Override
    @Nullable
    public Job create(@NonNull String tag) {
        switch (tag) {
            case SmartBirthSyncJob.TAG:
                return new SmartBirthSyncJob();
            default:
                return null;
        }
    }
}
