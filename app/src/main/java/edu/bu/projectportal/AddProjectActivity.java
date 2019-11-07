package edu.bu.projectportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.bu.projectportal.database.ProjectDao;

public class AddProjectActivity extends AppCompatActivity {
    ProjectDao projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        projectDao = ProjectDao.getInstance(getApplicationContext());
        projectDao.openDb();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        projectDao.closeDB();
    }

    public void onClickSubmit(View view){
        ProjectDao projectDao = ProjectDao.getInstance(getApplicationContext());
        EditText titleEditView =  (EditText)findViewById(R.id.titleEditTextId);
        String title = titleEditView.getText().toString();

        EditText descEditView= (EditText)findViewById(R.id.descEditTextId);
        String summary = descEditView.getText().toString();

        EditText authorNameEditView = (EditText)findViewById(R.id.authorNameEditTextId);
        String author_name = authorNameEditView.getText().toString();

        EditText keywordsEditView = (EditText)findViewById(R.id.keywordsEditTextId);
        String keywords = keywordsEditView.getText().toString();

        EditText linkEditView = (EditText)findViewById(R.id.linkEditTextId);
        String link = linkEditView.getText().toString();

        projectDao.insertProject(new Project(title, summary,author_name, keywords, link));
        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View v){
        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }


}
