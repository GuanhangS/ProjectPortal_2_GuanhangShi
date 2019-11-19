package edu.bu.projectportal;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import edu.bu.projectportal.database.ProjectDao;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class editMessIntentService extends IntentService {
    private int projectId;
    private String inputText;

    public editMessIntentService(){
        super("editMessIntentService");
    }
    private static final String TAG = "editMessIntentService";

    public Handler mHandler = null;



    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Intent Service onHandleIntent");

        // do you background job such as update database
        ProjectDao projectDao = ProjectDao.getInstance(getApplicationContext());
        Project project = projectDao.getProjectById(projectId);
        project.setSummary(inputText);
        projectDao.updateProjectById(project,projectId);
        showToastByRunnable(this,"successed", 1);
        Intent newIntent = new Intent(getApplicationContext(), ProjectMainActivity.class);
//        newIntent.putExtra("projectid", projectId);
        startActivity(newIntent);


//        mHandler.post(new Runnable() {
//            public void run() {
//                Toast.makeText(getApplicationContext(),
//                        "service started", Toast.LENGTH_LONG);
//            }
//
//        });
//        new LongOperation().longWait(TAG, 0);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand");
        if (mHandler == null) {
            mHandler = new Handler();
        }
        projectId = intent.getIntExtra("projectid",1);
        inputText = intent.getStringExtra("inputText");
        Log.d(TAG, projectId + "   " + inputText);

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Service onCreate");

        // mHandler = new Handler();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service onDestroy");
    }


    private void showToastByRunnable(final IntentService context, final CharSequence text, final int duration) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, duration).show();
            }
        });


    }
}




