package com.example.supunnethmal.slide.Forum;

/**
 * Created by supun nethmal on 25-Jun-18.
 */

public class dataModel {

    String postid;
    String postType;
    String postTittle;
    String postTags;
    String base64;
    String correct;
    String incorrect;

    public dataModel() {
    }

    public dataModel(String postid, String postType, String postTittle, String postTags, String base64, String correct, String incorrect) {
        this.postid = postid;
        this.postType = postType;
        this.postTittle = postTittle;
        this.postTags = postTags;
        this.base64 = base64;
        this.correct = correct;
        this.incorrect = incorrect;

    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTittle() {
        return postTittle;
    }

    public void setPostTittle(String postTittle) {
        this.postTittle = postTittle;
    }

    public String getPostTags() {
        return postTags;
    }

    public void setPostTags(String postTags) {
        this.postTags = postTags;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }
}
