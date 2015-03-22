package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Category {

    private String cat_id;
    private String cat_name;
    private String cat_desc;

    public Category(){
        this.cat_id = "";
        this.cat_name = "";
        this.cat_desc = "";
    }

    public Category(String cat_id, String cat_name, String cat_desc){
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_desc = cat_desc;
    }

    public Category(String cat_id, String cat_name){
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public Category(String cat_id){
        this.cat_id = cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public void setCat_desc(String cat_desc) {
        this.cat_desc = cat_desc;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_desc() {
        return cat_desc;
    }
}
