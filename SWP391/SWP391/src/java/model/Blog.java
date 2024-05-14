/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 84355
 */
public class Blog {
    int id;
    int user_id;
    int blog_cate_id;
    String title;
    String context;
    String cover_img;
    String main_img;
    String description;
    Date created_at;
    Date modified_at;

    public Blog() {
    }

    public Blog(int id, int user_id, int blog_cate_id, String title, String context, String cover_img, String main_img, String description, Date created_at, Date modified_at) {
        this.id = id;
        this.user_id = user_id;
        this.blog_cate_id = blog_cate_id;
        this.title = title;
        this.context = context;
        this.cover_img = cover_img;
        this.main_img = main_img;
        this.description = description;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBlog_cate_id() {
        return blog_cate_id;
    }

    public void setBlog_cate_id(int blog_cate_id) {
        this.blog_cate_id = blog_cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }
    
    
    
}
