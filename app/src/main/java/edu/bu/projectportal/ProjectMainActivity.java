package edu.bu.projectportal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import 	android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by danazh on 4/24/18.
 */

public class ProjectMainActivity extends AppCompatActivity implements ProjectListAdapter.Listener {
    CheckBox checkbox;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkbox = (CheckBox)findViewById(R.id.isFavouriteCheckBox);
        context = this;
        checkbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    ProjectsListFragment projectsListFragment = new ProjectsListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("data",isChecked);
                    projectsListFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ListFragment,projectsListFragment);
                    fragmentTransaction.commit();



//                    Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();

//                    ProjectsListFragment projectsListFragment = (ProjectsListFragment)getSupportFragmentManager().findFragmentById(R.id.ListFragment);
//                    projectsListFragment.update(isChecked);

            }

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(int id, int position) {
        ProjectDetailFragment projectDetailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailfragment);
        if (projectDetailFragment != null) {
            projectDetailFragment.setProject(id);
        }

        else {
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("Projectid", id);
            intent.putExtra("Projectposition", position);
            startActivity(intent);
        }

    }

}
