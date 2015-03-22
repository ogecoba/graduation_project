package com.testing.softcust.emuitapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AdminPanel extends ActionBarActivity {

    Button btnStaff, btnEvent, btnPhoto, btnCurriculum, btnCategory, btnCourses, btnSemester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnStaff = (Button) findViewById(R.id.imgStaff);
        btnEvent = (Button) findViewById(R.id.imgevent);
        btnPhoto = (Button) findViewById(R.id.imgPhoto);
        btnCurriculum = (Button) findViewById(R.id.imgcurriculum);
        btnCategory = (Button) findViewById(R.id.imgCategory);
        btnCourses = (Button) findViewById(R.id.imgCourse);
        btnSemester = (Button) findViewById(R.id.imgsemester);

        btnSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSemesterHome = new Intent(getApplicationContext(), SemesterHome.class);
                startActivity(openSemesterHome);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryHome = new Intent(getApplicationContext(),GalleryHome.class);
                startActivity(openGalleryHome);
            }
        });

        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCourseHome = new Intent(getApplicationContext(),CourseHome.class);
                startActivity(openCourseHome);
            }
        });

        btnCurriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCurriculumHome = new Intent(getApplicationContext(),CurriculumHome.class);
                startActivity(openCurriculumHome);
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEventHome = new Intent(getApplicationContext(), EventHome.class);
                startActivity(openEventHome);
            }
        });

        // Open Category Home Activity
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCatHome = new Intent(getApplicationContext(), CategoryHome.class);
                startActivity(openCatHome);
            }
        });

        // Open Staff Home Activity
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStaffHome = new Intent(getApplicationContext(), StaffHome.class);
                startActivity(openStaffHome);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
