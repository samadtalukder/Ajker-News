package com.samadtalukder.blognewsapp.model;

public class NewsModel {
    private String article_id;
    private String publishedTime;
    private String headLine;
    private String body;
    private String news_type;
    private String image_mobile;


    public NewsModel() {
    }

    public NewsModel(String article_id, String publishedTime, String headLine, String body,String news_type, String image_mobile) {
        this.article_id = article_id;
        this.publishedTime = publishedTime;
        this.headLine = headLine;
        this.body = body;
        this.news_type = news_type;
        this.image_mobile = image_mobile;
    }

    public String getArticle_id() {
        return article_id;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getBody() {
        return body;
    }

    public String getNews_type() {
        return news_type;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public void setImage_mobile(String image_mobile) {
        this.image_mobile = image_mobile;
    }


}
