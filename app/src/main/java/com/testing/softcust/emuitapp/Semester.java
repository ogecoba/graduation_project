package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Semester {
    private String semester_id;
    private String semesterName;
    private String session;
    private String startDate;
    private String endDate;


    public Semester()
    {
        this.semester_id = "";
        this.semesterName = "";
        this.session = "";
        this.startDate = "";
        this.endDate = "";
    }

    public Semester(String semester_id, String semesterName, String session, String startDate, String endDate){
        this.semester_id = semester_id;
        this.semesterName = semesterName;
        this.session = session;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Semester(String semester_id, String semesterName){
        this.semester_id = semester_id;
        this.semesterName = semesterName;
    }



    public Semester(String semester_id){
        this.semester_id = semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSemester_id() {
        return semester_id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public String getSession() {
        return session;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
