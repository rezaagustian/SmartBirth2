package com.futech.smartbirth;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class SmartBirthSyncJob extends Job {

    public static final String TAG = "job_smartbirth_sync";

    @Override
    @NonNull
    protected Result onRunJob(@NonNull Params params) {

        Langkah langkah = new Langkah("100","2 Des 2018");
        SharedPrefManager.getInstance(getContext()).setLangkah(langkah);
        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(SmartBirthSyncJob.TAG);
        if (!jobRequests.isEmpty()) {
            return;
        }
        new JobRequest.Builder(SmartBirthSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(7))
                .setUpdateCurrent(true) // calls cancelAllForTag(NoteSyncJob.TAG) for you
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .build()
                .schedule();
    }
}