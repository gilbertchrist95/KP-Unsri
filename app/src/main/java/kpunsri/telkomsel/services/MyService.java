package kpunsri.telkomsel.services;


import java.util.ArrayList;

import kpunsri.telkomsel.callbacks.TelegramLoadedListener;
import kpunsri.telkomsel.pojo.Telegram;
import kpunsri.telkomsel.task.TaskLoadTelegramBot;
import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by Gilbert on 1/21/2016.
 */
public class MyService extends JobService implements TelegramLoadedListener {

    private JobParameters jobParameters;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        this.jobParameters = jobParameters;
        new TaskLoadTelegramBot(this).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    public void onTelegramBotLoaded(ArrayList<Telegram> listTelegram){
        jobFinished(jobParameters, false);
    };
}
