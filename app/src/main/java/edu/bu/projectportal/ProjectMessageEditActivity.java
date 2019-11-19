package edu.bu.projectportal;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.bu.projectportal.database.ProjectDao;

public class ProjectMessageEditActivity extends AppCompatActivity {
    private int projectId;
    private EditText editText;
    private String inputText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_message_edit);


        if (getIntent()!= null) {
            Intent i = getIntent();
            projectId = i.getIntExtra("projectid",1);
        }

        Button button = (Button)findViewById(R.id.MessageSubimitButton);
        editText =(EditText)findViewById(R.id.messageditText);
        ProjectDao projectDao = ProjectDao.getInstance(getApplicationContext());
        Project project = projectDao.getProjectById(projectId);
        editText.setText(project.getSummary());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputText();
                Intent intent = new Intent(getApplicationContext(), editMessIntentService.class);
                intent.putExtra("projectid",projectId);
                intent.putExtra("inputText",inputText);
                startService(intent);
            }
        });


    }
    private void setInputText(){
        inputText=editText.getText().toString();
    }

}
