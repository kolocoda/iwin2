package com.ugarsoft.chyma.iwin.models;

/**
 * Created by Chyma on 5/7/2016.
 */
import com.j256.ormlite.field.DatabaseField;

public class AppUser {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long code;
    @DatabaseField
    private String userId;
    @DatabaseField
    private String email;
    @DatabaseField
    private String name;
    @DatabaseField
    private String username;
    @DatabaseField
    private String usertype;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}

