package com.gaorui.bean;

/**
 * Created by gaorui on 16/10/18.
 */
public class NodeBean {
    private int nid;
    private int pid;
    private String title;
    private String description;
    private String slug;
    private String pic;
    private int topics;
    private int create_time;
    private int update_time;
    private int is_del;


    public int getIs_del() {
        return is_del;
    }

    public int getNid() {
        return nid;
    }

    public int getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSlug() {
        return slug;
    }

    public String getPic() {
        return pic;
    }

    public int getTopics() {
        return topics;
    }

    public int getCreate_time() {
        return create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

}

