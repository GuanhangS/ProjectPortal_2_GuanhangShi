package edu.bu.projectportal;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.bu.projectportal.database.ProjectDao;
import edu.bu.projectportal.database.ProjectPortalDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends Fragment {

    int projectId, nextProjectId;
    private TextView titleTextView, summaryTextView, authorNameTextView, keywordsTextView, linkTextView;
    private Switch switchComponent;
    private Button btn;
    private boolean isChecked;
    private ProjectDao projectDaoLike;
    private Project projectLike;
    public ProjectDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        titleTextView = view.findViewById(R.id.projTitleTextViewId);
        summaryTextView = view.findViewById(R.id.projSummaryTextViewId);
        authorNameTextView = view.findViewById(R.id.proAuthorNameTextViewId);
        keywordsTextView = view.findViewById(R.id.proKeywordTextViewId);
        linkTextView = view.findViewById(R.id.proLinkTextViewID);


        if (getArguments()!= null) {
            projectId = getArguments().getInt("Projectid");
            nextProjectId = getArguments().getInt("NextProjectid");
        }



        Log.d("projectid", " " + projectId);
        setProject(projectId);





        return view;
    }

    public void setProject(int projId) {

        projectId = projId;
        ProjectDao projectDao = ProjectDao.getInstance(getContext());
        Project project = projectDao.getProjectById(projectId);

        titleTextView.setText(project.getId() + ": " + project.getTitle());
        summaryTextView.setText(project.getSummary());
        authorNameTextView.setText(project.getAuthorName());
        keywordsTextView.setText(project.getKeywords());
        linkTextView.setText(project.getLink());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switchComponent  = (Switch)getActivity().findViewById(R.id.isFavouriteSwitch);
        ProjectDao projectDao = ProjectDao.getInstance(getContext());
        Project project = projectDao.getProjectById(projectId );
        if(project.getFavourite() == 1) {
            switchComponent.setChecked(true);
        }else{
            switchComponent.setChecked(false);
        }

        switchComponent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                (new DatabaseFavouriteUpdateAsyncTask()).execute();
            }
        });

        btn = (Button) getActivity().findViewById(R.id.messageEditBtrId);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ProjectMessageEditActivity.class);
                intent.putExtra("projectid", projectId);
                startActivity(intent);

            }
        });
    }

    private void btnUpdate(){
        isChecked = switchComponent.isChecked();
    }

    private class DatabaseFavouriteUpdateAsyncTask extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean...Booleans) {
            btnUpdate();
            projectDaoLike = ProjectDao.getInstance(getContext());
            projectLike = projectDaoLike.getProjectById(projectId);

            return isChecked;
        }

        protected void onPostExecute(Boolean isChecked) {

            if(isChecked){
                Toast.makeText(getActivity(), "You like it.", Toast.LENGTH_SHORT).show();
                projectLike.setFavourite(1);
                projectDaoLike.updateProjectById(projectLike,projectId);
            }else{
                Toast.makeText(getActivity(), "You don't like it.", Toast.LENGTH_SHORT).show();
                projectLike.setFavourite(0);
                projectDaoLike.updateProjectById(projectLike,projectId);
            }
        }
    }


    public int getProjectId(){
        return projectId;
    }


    public int getNextProjectId() {
        return nextProjectId;
    }




}
