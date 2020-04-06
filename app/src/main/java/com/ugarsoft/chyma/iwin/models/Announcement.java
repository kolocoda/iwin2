package com.ugarsoft.chyma.iwin.models;

/**
 * Created by Chyma on 5/10/2016.
 */
import com.j256.ormlite.field.DatabaseField;

public class Announcement {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long postId;
    @DatabaseField
    private Long postDate;
    @DatabaseField
    private String postTitle;
    @DatabaseField
    private String postContent;
    @DatabaseField
    private String postBy;
    @DatabaseField
    private String postImage;
    @DatabaseField
    private String discoCode;
    @DatabaseField
    private boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostBy() {
        return postBy;
    }

    public void setPostBy(String postBy) {
        this.postBy = postBy;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDiscoCode() {
        return discoCode;
    }

    public void setDiscoCode(String discoCode) {
        this.discoCode = discoCode;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
