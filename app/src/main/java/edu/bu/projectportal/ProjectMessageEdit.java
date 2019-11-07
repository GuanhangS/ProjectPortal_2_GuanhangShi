package edu.bu.projectportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.bu.projectportal.database.ProjectDao;

public class ProjectMessageEdit  extends Fragment {
    int projectId;
    private Button button;
    private EditText editText;
    public ProjectMessageEdit(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_edit, container, false);

        if (getArguments()!= null) {
            projectId = getArguments().getInt("projectid");
        }

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button = (Button)getActivity().findViewById(R.id.MessageSubimitButton);
        editText =(EditText)getActivity().findViewById(R.id.messageditText);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String inputText=editText.getText().toString();
                Toast.makeText(getActivity(), inputText, Toast.LENGTH_SHORT).show();
                ProjectDao projectDao = ProjectDao.getInstance(getContext());
                Project project = projectDao.getProjectById(projectId);
                project.setSummary(inputText);
                projectDao.updateProjectById(project,projectId);
                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra("projectid", projectId);
                startActivity(intent);

            }
        });
    }

}

