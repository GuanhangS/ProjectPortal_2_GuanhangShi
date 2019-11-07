package edu.bu.projectportal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import edu.bu.projectportal.database.ProjectDao;
import edu.bu.projectportal.database.ProjectPortalDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends Fragment {

    int projectId, nextProjectId;
    TextView titleTextView, summaryTextView, authorNameTextView, keywordsTextView, linkTextView;
    Switch switchComponent;
    Button btn;
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
        Project project = projectDao.getProjectById(projectId );

        titleTextView.setText(project.getId() + ": " + project.getTitle());
        summaryTextView.setText(project.getSummary());
        authorNameTextView.setText(project.getAuthorName());
        keywordsTextView.setText(project.getKeywords());
        linkTextView.setText(project.getLink());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int ID = this.projectId;
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
                if(isChecked){
                    Toast.makeText(getActivity(), "You like it.", Toast.LENGTH_SHORT).show();
                    ProjectDao projectDaoLike = ProjectDao.getInstance(getContext());
                    Project projectLike = projectDaoLike.getProjectById(projectId);
                    projectLike.setFavourite(1);
                    projectDaoLike.updateProjectById(projectLike,projectId);

                }else{
                    Toast.makeText(getActivity(), "You don't like it.", Toast.LENGTH_SHORT).show();
                    ProjectDao projectDaoLike = ProjectDao.getInstance(getContext());
                    Project projectLike = projectDaoLike.getProjectById(projectId);
                    projectLike.setFavourite(0);
                    projectDaoLike.updateProjectById(projectLike,projectId);
                }
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


    public int getProjectId(){
        return projectId;
    }


    public int getNextProjectId() {
        return nextProjectId;
    }


}
