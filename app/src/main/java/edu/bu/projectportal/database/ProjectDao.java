package edu.bu.projectportal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.bu.projectportal.Project;

/**
 * Created by danazh on 4/18/18.
 */

public class ProjectDao {
    public static ProjectDao instance;

    public ProjectPortalDBHelper projectPortalDBHelper;
    public SQLiteDatabase mReadableDB, mWritableDB;


    public ProjectDao(Context context){
        projectPortalDBHelper = new ProjectPortalDBHelper(context);
    }

    public void openDb(){
        mReadableDB = projectPortalDBHelper.getReadableDatabase();
        mWritableDB = projectPortalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        mReadableDB.close();
        mWritableDB.close();
    }

    public static ProjectDao getInstance(Context context){
        if (instance == null)
            instance = new ProjectDao(context);
        return instance;
    }

    public long insertProject(Project project) {

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name,
                project.getAuthorName());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword,
                project.getKeywords());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link,
                project.getLink());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite,
                project.getFavourite());

        return mWritableDB.insert(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                null, projectValue);
    }

    public long delectProjectById(int projectId) {
        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId+""};

        return mWritableDB.delete(ProjectPortalDBContract.ProjectContract.TABLE_NAME ,
                selection,selectionArgs);

    }

    public int updateProjectById(Project project, int projectId){
        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId+""};

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name,
                project.getAuthorName());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword,
                project.getKeywords());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link,
                project.getLink());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite,
                project.getFavourite());

        return mWritableDB.update(ProjectPortalDBContract.ProjectContract.TABLE_NAME,projectValue,selection,selectionArgs);
    }


    public List<Project> getAllProject() {
            // mWritableDB.execSQL("DELETE FROM projects");

            String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite
                    };

            Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                    projection,
                    null, null, null, null,null);

            List<Project> projects = new ArrayList<Project>();

            while(cursor.moveToNext()) {
                int projectId = cursor.getInt(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
                String projectTitle = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
                String projectSum = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
                String projectAuthorName = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name));
                String projectKeywords = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword));
                String projectLink = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link));
                int projectIsFavourite = cursor.getInt(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite));

                projects.add(new Project(projectId, projectTitle,projectSum,projectAuthorName,projectKeywords,projectLink,projectIsFavourite));
            }

            cursor.close();

            return projects;
    }

    public Project getProjectById(int projectId) {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {Integer.toString(projectId)};

        Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);

        cursor.moveToFirst();

        int ProjectId = cursor.getInt(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
        String projectTitle = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
        String projectSum = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
        String projectAuthorName = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name));
        String projectKeywords = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword));
        String projectLink = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link));
        int projectIsFavourite = cursor.getInt(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite));


        Project project = new Project(ProjectId, projectTitle,projectSum, projectAuthorName, projectKeywords, projectLink,projectIsFavourite);
        cursor.close();

        return project;
    }

    public List<Project> getFavouriteProject() {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite
        };

        Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection,
                null, null, null, null,null);

        List<Project> projects = new ArrayList<Project>();

        while(cursor.moveToNext()) {
            int projectId = cursor.getInt(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
            String projectTitle = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
            String projectSum = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
            String projectAuthorName = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Author_name));
            String projectKeywords = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Keyword));
            String projectLink = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Link));
            int projectIsFavourite = cursor.getInt(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_IsFavourite));
            if(projectIsFavourite == 1) {
                projects.add(new Project(projectId, projectTitle, projectSum, projectAuthorName, projectKeywords, projectLink, projectIsFavourite));
            }
        }

        cursor.close();

        return projects;
    }

    public Cursor getProjectByTitle(String projectTitle) {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title + "LIKE ?";
        String[] selectionArgs = {projectTitle};

        return mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);
    }


    public long updateProjectById(SQLiteDatabase sqLiteDatabase, Project project, int projectId) {

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId +""};

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        return mWritableDB.update(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projectValue, selection, selectionArgs);

    }
}
