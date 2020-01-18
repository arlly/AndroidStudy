package com.example.mybasicapplication;

public class ListItem {
    private long id = 0;
    private String title = null;
    private String tag = null;
    private String desc = null;

    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
