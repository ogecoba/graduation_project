package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Staffs {
    // Variables

    private String empId;
    private String fName;
    private String lName;
    private String dob;
    private String email;
    private String officeNo;
    private String phoneNo;
    private String purl;
    private String priv;

    public Staffs()
    {
        empId = "";
        fName = "";
        lName = "";
        dob = "";
        email = "";
        officeNo = "";
        phoneNo = "";
        purl = "";
        priv = "";
    }

    public Staffs(String empid,String fname, String lname, String dob, String email, String officeno, String phoneno, String purl, String priv){
        this.empId = empid;
        this.fName = fname;
        this.lName = lname;
        this.dob = dob;
        this.email = email;
        this.officeNo = officeno;
        this.phoneNo = phoneno;
        this.purl = purl;
        this.priv = priv;
    }

    public Staffs(String empid){
        this.empId = empid;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getEmpId() {
        return empId;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPurl() {
        return purl;
    }

    public String getPriv() {
        return priv;
    }
}
