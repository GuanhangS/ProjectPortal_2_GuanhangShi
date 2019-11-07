package edu.bu.projectportal;



public class Project {
    private int id;
    private String title;
    private String summary;
    private String author_name;
    private String keywords;
    private String link;
    private int isFavourite = 0;


    public Project(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public Project(int id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }

    public Project(String title, String summary, String author_name, String keywords, String link) {
        this.title = title;
        this.summary = summary;
        this.author_name = author_name;
        this.keywords = keywords;
        this.link = link;
    }

    public Project(int id, String title, String summary, String author_name, String keywords, String link) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author_name = author_name;
        this.keywords = keywords;
        this.link = link;
    }

    public Project(String title, String summary, String author_name, String keywords, String link, int isFavourite) {
        this.title = title;
        this.summary = summary;
        this.author_name = author_name;
        this.keywords = keywords;
        this.link = link;
        this.isFavourite = isFavourite;
    }

    public Project(int id, String title, String summary, String author_name, String keywords, String link, int isFavourite) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author_name = author_name;
        this.keywords = keywords;
        this.link = link;
        this.isFavourite = isFavourite;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthorName() {return author_name;}

    public String getKeywords() {return keywords;}

    public String getLink() {return link;}

    public void setFavourite(int isFavourite) {this.isFavourite = isFavourite;}

    public int getFavourite(){return isFavourite;}

}
