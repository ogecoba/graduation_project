package com.testing.softcust.emuitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    //Static variables
    // Database Version
    private static final int DBVersion = 1;

    // Database Name
    private static final String DBName = "EMU_IT";

    // Database table names
    private static final String SemesterTB = "Semester";
    private static final String CourseTB = "Courses";
    private static final String StaffTB = "Staffs";
    private static final String EventTB = "Events";
    private static final String CategoryTB = "Category";
    private static final String CurriculumTB = "Curriculum";
    private static final String PhotoGallery = "Photo_Gallery";
    private static final String UserLoginTB = "User_Login";

    // SemesterTB Table Columns Names
    private static final String SemesterID = "Semester_ID";
    private static final String SemesterName = "Semester_Name";
    private static final String Session = "Session";
    private static final String StartDate = "Start_Date";
    private static final String EndDate = "Start_Date";



    public DBHandler(Context context){
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        // Creating Semester Table
        String CreateSemesterTable = "CREATE TABLE " + SemesterTB + "("
                + SemesterID + " VARCHAR(5) PRIMARY KEY," + SemesterName + " VARCHAR(30),"
                + Session + " VARCHAR(30)," + StartDate +" DATE,"+ EndDate + "DATE" + ")";

        // Creating Course Table
        String CreateCourseTable = "CREATE TABLE COURSES (COURSE_ID CHAR(7) PRIMARY KEY, COURSE_NAME VARCHAR(250), REF_CODE VARCHAR(5), EMU_CREDIT VARCHAR(2), " +
                "ECTS_CREDIT VARCHAR(2), PREREQUISITE CHAR(7), LAB_HOUR CHAR(1), LECTURE_HOUR CHAR(1), COURSE_DESC TEXT)";

        // Creating Staff Table
        String CreateStaffsTable = "CREATE TABLE STAFFS (STAFF_ID VARCHAR(15) PRIMARY KEY, FNAME VARCHAR(30) NOT NULL, LNAME VARCHAR(30) NOT NULL, " +
                "DOB DATE, EMAIL VARCHAR(60), OFFICE_NO VARCHAR(15), PHONE_NO VARCHAR(15), PICTURE_URL VARCHAR(250), PRIVILEGE VARCHAR(20))";

        // Creating Category Table
        String CreateCategoryTable = "CREATE TABLE CATEGORY (CATEGORY_ID CHAR(2) PRIMARY KEY, CATEGORY_NAME VARCHAR(30) NOT NULL, CATEGORY_DESC TEXT)";

        // Creating Events Table
        String CreateEventTable = "CREATE TABLE EVENT (EVENT_ID VARCHAR(5) PRIMARY KEY, EVENT_NAME VARCHAR(50), EVENT_TYPE VARCHAR(30), START_DATE DATE," +
                "END_DATE DATE, EVENT_DESC TEXT, EVENT_SEMESTER_ID VARCHAR(5), FOREIGN KEY EVENT_SEMESTER_ID REFERENCES SEMESTER (SEMESTER_ID))";

        // Creating Curriculum Table
        String CreateCurriculumTable = "CREATE TABLE CURRICULUM (CURRICULUM_ID VARCHAR(5) PRIMARY KEY, CURRICULUM_NAME VARCHAR(30), CURRICULUM_DESC TEXT" +
                "REF_CODE VARCHAR(5))";

        // Creating PictureGallery Table
        String CreatePictureGalleryTable = "CREATE TABLE PICTURE_GALLERY (ID INTEGER PRIMARY KEY, PICTURE_NAME VARCHAR(30), PICTURE_URL VARCHAR(250)," +
                " DATE_ADDED DATE, DESCRIPTION TEXT)";

        // Execute SQL Database Statements
        db.execSQL(CreateSemesterTable);
        db.execSQL(CreateCategoryTable);
        db.execSQL(CreateCourseTable);
        db.execSQL(CreateStaffsTable);
        db.execSQL(CreateEventTable);
        db.execSQL(CreateCurriculumTable);
        db.execSQL(CreatePictureGalleryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + SemesterTB);
        db.execSQL("DROP TABLE IF EXISTS COURSES");
        db.execSQL("DROP TABLE IF EXISTS STAFFS");
        db.execSQL("DROP TABLE IF EXISTS CATEGORY");
        db.execSQL("DROP TABLE IF EXISTS EVENT");
        db.execSQL("DROP TABLE IF EXISTS CURRICULUM");
        db.execSQL("DROP TABLE IF EXISTS PICTURE_GALLERY");


        // Create table again
        onCreate(db);
    }


    public void addStaffs(Staffs staffs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("STAFF_ID", staffs.getEmpId());//Staff ID
        values.put("FNAME", staffs.getfName()); // Staff Firstname
        values.put("LNAME", staffs.getlName()); // Staff Lastname
        values.put("DOB", staffs.getDob()); // Staff Date Of Birth
        values.put("EMAIL", staffs.getEmail()); // Staff Email
        values.put("OFFICE_NO", staffs.getOfficeNo()); // Staff Office Number
        values.put("PHONE_NO", staffs.getPhoneNo()); // Staff Phone Number
        values.put("PICTURE_URL", staffs.getPurl());
        values.put("PRIVILEGE", staffs.getPriv());


        // Inserting Row
        db.insert("Staffs", null, values);
        db.close(); // Closing database connection
    }

    public void addSemester(Semester semester){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
    }
}
