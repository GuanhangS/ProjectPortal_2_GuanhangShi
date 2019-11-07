package edu.bu.projectportal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ProjectMessageEditActivity extends AppCompatActivity {

    ProjectMessageEdit ProjectMessageEdit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_message_edit);

        //add fragments dynamically
        //create a fragment object
        ProjectMessageEdit = new ProjectMessageEdit();
        ProjectMessageEdit.setArguments(getIntent().getExtras());

        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.proMessageEditfragContainer, ProjectMessageEdit);
        // commit the transaction.
        transaction.commit();
    }

}
