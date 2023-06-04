package com.example.quickstartapplication.network.bean;
import java.util.List;

/**
 * Auto-generated: 2023-05-28 14:44:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class NavigateData {

    private List<Articles> articles;
    private int cid;
    private String name;
    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
    public List<Articles> getArticles() {
        return articles;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getCid() {
        return cid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
