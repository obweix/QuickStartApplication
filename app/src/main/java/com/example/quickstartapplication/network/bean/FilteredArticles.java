package com.example.quickstartapplication.network.bean;

import java.util.ArrayList;
import java.util.List;

public class FilteredArticles {
    public String mTitle;
    public List<Articles> mArticles = new ArrayList<>();

    public FilteredArticles(String title){
        mTitle = title;
    }
}
