package edu.bu.projectportal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    boolean isFavourite;



    public ProjectsListFragment() {
        // Required empty public constructor
    }

    public void update(boolean isFavourite){
        this.isFavourite = isFavourite;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_projects_list, container, false);

        RecyclerView projectsListRecyclerView = (RecyclerView)
                (v.findViewById(R.id.projectlist_recyclerview));


        Bundle bundle = this.getArguments();

        if(this.getArguments() != null){
//            Toast.makeText(getActivity(), "Got the data", Toast.LENGTH_SHORT).show();
             isFavourite = bundle.getBoolean("data");

        }

        ProjectDao projectDao = ProjectDao.getInstance(getContext());
        projectDao.openDb();
        List<Project> projects;
        ProjectListAdapter projectListAdapter;


        if(isFavourite){
            projects = projectDao.getFavouriteProject();
            projectListAdapter = new ProjectListAdapter(projects);
        }else{
            projects = projectDao.getAllProject();
            projectListAdapter = new ProjectListAdapter(projects);
        }


        projectsListRecyclerView.setAdapter(projectListAdapter);
        projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        projectsListRecyclerView.setLayoutManager(mLayoutManager);
        projectListAdapter.notifyDataSetChanged();



        return v;
    }


}



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v =  inflater.inflate(R.layout.fragment_projects_list, container, false);
//
//
//        int numOfProjects = Project.projects.length;
//        String[] projectNameList = new String[numOfProjects];
//
//        for (int i=0; i< numOfProjects; i++){
//            projectNameList[i] =  Project.projects[i].getTitle();
//        }
//
//        ArrayAdapter<String> projectsListAdapter =
//                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, projectNameList);
//
//        ListView projectsListView = (ListView) (v.findViewById(R.id.projectsList_view));
//        projectsListView.setAdapter(projectsListAdapter);
//
//        projectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), ProjectDetailActivity.class);
//                intent.putExtra("projectid",i );
//                startActivity(intent);
//
//            }
//        });
//
//        return v;
//    }
//}
