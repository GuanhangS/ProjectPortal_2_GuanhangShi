package edu.bu.projectportal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.bu.projectportal.database.ProjectDao;

public class ProjectDetailActivity extends AppCompatActivity {
    ProjectDao projectDao;

    ProjectDetailFragment projectDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        projectDao = ProjectDao.getInstance(getApplicationContext());
        projectDao.openDb();

        //add fragments dynamically
        //create a fragment object
        projectDetailFragment = new ProjectDetailFragment();
        projectDetailFragment.setArguments(getIntent().getExtras());

        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.proDetailfragContainer, projectDetailFragment);
        // commit the transaction.
        transaction.commit();
    }

    @Override
     protected void onDestroy(){
        super.onDestroy();
        projectDao.closeDB();

    }


    public void deleteProject(View v){

        projectDao.delectProjectById(projectDetailFragment.getProjectId());
        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }
}