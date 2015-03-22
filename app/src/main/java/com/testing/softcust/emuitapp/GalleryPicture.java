package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class GalleryPicture {
    private String photo_id;
    private String photo_name;
    private String pic;
    private String date;
    private String description;

    public GalleryPicture()
    {
        this.photo_id = "";
        this.photo_name = "";
        this.pic = "";
        this.date = "";
        this.description = "";
    }

    public GalleryPicture(String photo_id, String photo_name, String pic, String date, String description){
        this.photo_id = photo_id;
        this.photo_name = photo_name;
        this.pic = pic;
        this.date = date;
        this.description = description;
    }

    public GalleryPicture(String photo_id){
        this.photo_id = photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public String getPic() {
        return pic;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
