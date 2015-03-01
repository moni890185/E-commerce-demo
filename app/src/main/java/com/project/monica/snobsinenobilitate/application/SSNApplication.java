package com.project.monica.snobsinenobilitate.application;

import android.app.Application;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;

/**
 * Created by monica on 14/01/2015.
 */
public class SSNApplication extends Application {
  private JobManager jobManager;
  @Override
  public void onCreate() {
    super.onCreate();
    configureJobManager();

  }

  private void configureJobManager() {
    Configuration configuration = new Configuration.Builder(this)
        .minConsumerCount(1)//always keep at least one consumer alive
        .maxConsumerCount(3)//up to 3 consumers at a time
        .loadFactor(3)//3 jobs per consumer
        .consumerKeepAlive(120)//wait 2 minute
        .build();
    jobManager = new JobManager(this, configuration);
  }

  public JobManager getJobManager() {
    return jobManager;
  }

}
