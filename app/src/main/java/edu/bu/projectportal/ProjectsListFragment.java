package edu.bu.projectportal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.bu.projectportal.database.ProjectDao;
import edu.bu.projectportal.database.ProjectPortalDBContract;
import edu.bu.projectportal.database.ProjectPortalDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsListFragment extends Fragment {
    ProjectListAdapter.Listener listener;
    private ProjectDao projectDao;
    private ProjectListAdapter projectListAdapter;
    private RecyclerView projectsListRecyclerView;
    private List<Project> projects;
    private CheckBox checkbox;


    public ProjectsListFragment() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_projects_list, container, false);

        projectsListRecyclerView = (RecyclerView)
                (v.findViewById(R.id.projectlist_recyclerview));


//        projectDao = ProjectDao.getInstance(getContext());
//        projectDao.openDb();
//        projects = projectDao.getAllProject();
//
//        projectListAdapter = new ProjectListAdapter(projects);
//        projectsListRecyclerView.setAdapter(projectListAdapter);
//        projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        projectsListRecyclerView.setLayoutManager(mLayoutManager);
        (new DatabaseAsyncTask()).execute();

        return v;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        projectDao.openDb();
        checkbox = (CheckBox)getActivity().findViewById(R.id.isFavouriteCheckBox);

        checkbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ProjectDao projectDaoChange = ProjectDao.getInstance(getContext());
                projectDaoChange.openDb();

                if(isChecked){
                    projects = projectDaoChange.getFavouriteProject();
                }else{
                    projects = projectDaoChange.getAllProject();
                }
                projectListAdapter = new ProjectListAdapter(projects);
                projectsListRecyclerView.setAdapter(projectListAdapter);
                projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                projectsListRecyclerView.setLayoutManager(mLayoutManager);
            }
        });
    }


    private class DatabaseAsyncTask extends AsyncTask<String,Void,List<Project>> {

        @Override
        protected List<Project> doInBackground(String... strings) {
            projectDao = ProjectDao.getInstance(getContext());
            projectDao.openDb();

//            List<Project> projects;
            projects = projectDao.getAllProject();
            return projects;
        }

        protected void onPostExecute(List<Project> projects) {

            projectListAdapter = new ProjectListAdapter(projects);
            projectsListRecyclerView.setAdapter(projectListAdapter);
            projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            projectsListRecyclerView.setLayoutManager(mLayoutManager);
        }
    }
}




