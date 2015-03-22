package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Curriculum {

    private String c_code;
    private String ref_code;
    private String curriculum_name;
    private String cur_description;


    public Curriculum(){
        this.c_code = "";
        this.ref_code = "";
        this.curriculum_name = "";
        this.cur_description = "";
    }

    public Curriculum(String c_code, String curriculum_name, String ref_code, String cur_description){
        this.c_code = c_code;
        this.curriculum_name = curriculum_name;
        this.ref_code = ref_code;
        this.cur_description = cur_description;
    }

    public Curriculum(String c_code){
        this.c_code = c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public void setCurriculum_name(String curriculum_name) {
        this.curriculum_name = curriculum_name;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public void setCur_description(String cur_description) {
        this.cur_description = cur_description;
    }

    public String getC_code() {
        return c_code;
    }

    public String getCurriculum_name() {
        return curriculum_name;
    }

    public String getRef_code() {
        return ref_code;
    }

    public String getCur_description() {
        return cur_description;
    }
}
