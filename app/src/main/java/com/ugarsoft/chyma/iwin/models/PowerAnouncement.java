package com.ugarsoft.chyma.iwin.models;

/**
 * Created by Chyma on 5/10/2016.
 */
import com.j256.ormlite.field.DatabaseField;

public class PowerAnouncement {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long postDate;
    @DatabaseField
    private String postTitle;
    @DatabaseField
    private String postContent;
    @DatabaseField
    private String postBy;
    @DatabaseField
    private  String isRead;

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
