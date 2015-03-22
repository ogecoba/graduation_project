package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Courses {

    private String course_id;
    private String course_name;
    private String ref_code;
    private String emu_credit;
    private String ects_credit;
    private String prerequisites;
    private String lab_hour;
    private String lecture_hour;
    private String course_desc;

    public Courses(){
        this.course_id = "";
        this.course_name = "";
        this.ref_code = "";
        this.emu_credit = "";
        this.ects_credit = "";
        this.prerequisites = "";
        this.lab_hour = "";
        this.lecture_hour = "";
        this.course_desc = "";
    }

    public Courses(String course_id, String course_name, String ref_code, String emu_credit, String ects_credit, String prerequisites, String lab_hour, String lecture_hour, String course_desc)
    {
        this.course_id = course_id;
        this.course_name = course_name;
        this.ref_code = ref_code;
        this.emu_credit = emu_credit;
        this.ects_credit = ects_credit;
        this.prerequisites = prerequisites;
        this.lab_hour = lab_hour;
        this.lecture_hour = lecture_hour;
        this.course_desc = course_desc;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public void setEmu_credit(String emu_credit) {
        this.emu_credit = emu_credit;
    }

    public void setEcts_credit(String ects_credit) {
        this.ects_credit = ects_credit;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setLab_hour(String lab_hour) {
        this.lab_hour = lab_hour;
    }

    public void setLecture_hour(String lecture_hour) {
        this.lecture_hour = lecture_hour;
    }

    public void setCourse_desc(String course_desc) {
        this.course_desc = course_desc;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getRef_code() {
        return ref_code;
    }

    public String getEmu_credit() {
        return emu_credit;
    }

    public String getEcts_credit() {
        return ects_credit;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getLab_hour() {
        return lab_hour;
    }

    public String getLecture_hour() {
        return lecture_hour;
    }

    public String getCourse_desc() {
        return course_desc;
    }
}
