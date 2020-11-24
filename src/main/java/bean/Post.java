package bean;

import com.google.gson.Gson;

/**
 * 动态文章数据
 */
public class Post {
    String postID;
    String postTitle;
    String postContent;
    User author;
    String category;
    String photo_src;
    int views;
    long postTime;
    String isPublished;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhoto_src(String photo_src) {
        this.photo_src = photo_src;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        if (isPublished)
            this.isPublished = "1";
        else
            this.isPublished = "0";
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
