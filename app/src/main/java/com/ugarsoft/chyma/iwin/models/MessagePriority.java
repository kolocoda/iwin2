package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/30/2016.
 */
public class MessagePriority {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
