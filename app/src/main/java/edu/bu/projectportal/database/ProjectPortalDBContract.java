package edu.bu.projectportal.database;

/**
 * Created by danazh on 4/18/18.
 */

public final class ProjectPortalDBContract {
    public static final String DBName = "projectportal.db";
    public static final int DB_VERSION = 1;

    public static final class ProjectContract{
        public static final String TABLE_NAME = "projects";
        public static final String COLUMN_PROJECT_ID = "project_id";
        public static final String COLUMN_PROJECT_Title = "project_title";
        public static final String COLUMN_PROJECT_SUMMARY = "project_summary";
        public static final String COLUMN_PROJECT_Author_name = "project_author_name";
        public static final String COLUMN_PROJECT_Keyword = "project_keywords";
        public static final String COLUMN_PROJECT_Link = "project_link";
        public static final String COLUMN_PROJECT_IsFavourite = "project_isFavourite";
    }

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE " +
            ProjectPortalDBContract.ProjectContract.TABLE_NAME +
            "(" +
            ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_SUMMARY +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_Author_name +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_Keyword +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_Link +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_IsFavourite +
            " INTEGER);";

    public static final String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS "
            + ProjectPortalDBContract.ProjectContract.TABLE_NAME;
}
