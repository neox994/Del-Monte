package com.example.neboja.tasky;

/**
 * Created by Nebojša on 13/4/2017.
 */

public class Task {


    private String tTitle;
    private String tDescription;
    private String tPriority;
    private String tCategory;

    public Task(String title, String description, String priority, String category) {
        tTitle = title;
        tDescription = description;
        tPriority = priority;
        tCategory = category;
    }

    public String gettTitle() {
        return tTitle;
    }

    public String gettDescription() {return tDescription;}


    public String gettPriority() {return tPriority;}

    public String gettCategory() {return tCategory;}
}