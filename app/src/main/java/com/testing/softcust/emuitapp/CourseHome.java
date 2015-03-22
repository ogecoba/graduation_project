package com.testing.softcust.emuitapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class CourseHome extends Activity {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputCourseID;
    EditText inputCourseName;
    EditText inputCourseRefCode;
    EditText inputCourseEMUCredit;
    EditText inputCourseECTSCredit;
    EditText inputPrerequisite;
    EditText inputLabHour;
    EditText inputLectureHour;
    EditText inputTutorialHour;
    EditText inputCourseDescription;
    Spinner inputCourseCategory;
    Spinner inputSemester;

    ProgressBar pb ;

    Button btnSaveCourse;
    List<String> tastlist;
    private static final String TAG_SUCCESS = "success";

    private static String url_create_course = "https://emu-itapplication.herokuapp.com/create_courses.php";

    private static String url_get_category = "https://emu-itapplication.herokuapp.com/getAll_course_category.php";
    private  static String url_get_semester = "https://emu-itapplication.herokuapp.com/getAll_semester.php";
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Semester> semesterArrayList;

    JSONArray categories = null;
    JSONArray semester = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_home);

        inputCourseID = (EditText) findViewById(R.id.txtCourseID);
        inputCourseName = (EditText) findViewById(R.id.txtCourseName);
        inputCourseRefCode = (EditText) findViewById(R.id.txtRefCode);
        inputCourseEMUCredit = (EditText) findViewById(R.id.txtEmuCredit);
        inputCourseECTSCredit = (EditText) findViewById(R.id.txtEctsCredit);
        inputPrerequisite = (EditText) findViewById(R.id.txtPrerequisites);
        inputLabHour = (EditText) findViewById(R.id.txtLabHour);
        inputLectureHour = (EditText) findViewById(R.id.txtLectureHour);
        inputTutorialHour = (EditText) findViewById(R.id.txtTutorialHour);
        inputCourseDescription = (EditText) findViewById(R.id.txtCourseDescription);

        tastlist = new ArrayList<>();
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        btnSaveCourse = (Button) findViewById(R.id.btnSaveCourse);

        categoryArrayList = new ArrayList<Category>();
        semesterArrayList = new ArrayList<Semester>();

        TabHost tabHost = (TabHost) findViewById(R.id.tabCourses);
        tabHost.setup();

        // Tab For Add Category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("addCourse");
        tabSpec.setContent(R.id.tabAddCourse);
        tabSpec.setIndicator("Add Course");
        tabHost.addTab(tabSpec);


        // Tab For List Category
        tabSpec = tabHost.newTabSpec("listCourses");
        tabSpec.setContent(R.id.tabListCourses);
        tabSpec.setIndicator("List Courses");
        tabHost.addTab(tabSpec);

        // Tab For Delete Category
        tabSpec = tabHost.newTabSpec("delCourse");
        tabSpec.setContent(R.id.tabDeleteCourse);
        tabSpec.setIndicator("Delete Course");
        tabHost.addTab(tabSpec);
        pDialog = new ProgressDialog(CourseHome.this);


        btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateCourse().execute();
            }
        });


        // Executing Categories Download
        new GetCategories().execute();
        new GetSemester().execute();
    }

    // Method To Populate Semester Spinner
    private void populateSemesterSpinner(){
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < semesterArrayList.size(); i++) {
            lables.add(semesterArrayList.get(i).getSemesterName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputSemester = (Spinner) findViewById(R.id.drpCourseSemester);
        // attaching data adapter to spinner
        inputSemester.setAdapter(spinnerAdapter);
    }

    // Class To Get Semesters From Database
    class GetSemester extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */

        protected void onPreExecute() {
            super.onPreExecute();
           // if(tastlist.size()==0){

                pDialog.setMessage("Loading Data ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
               // pb.setVisibility(View.VISIBLE);

           // }
            tastlist.add("first task");
        }


        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONParser jsonParser = new JSONParser();
            String json = jsonParser.makeHttpRequest(url_get_semester, "GET", params);

            return json;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tastlist.remove("first task");
            if(tastlist.size()==0){
                if (pDialog.isShowing())
                    pDialog.dismiss();
               // pb.setVisibility(View.INVISIBLE);
            }


            Log.e("Response: ", "> " + result);

            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    if (jsonObj != null) {
                        semester = jsonObj
                                .getJSONArray("semester");

                        for (int i = 0; i < semester.length(); i++) {
                            JSONObject semObj = (JSONObject) semester.get(i);
                            Semester sem = new Semester(semObj.getString("semester_id"),semObj.getString("semester_name"));
                            semesterArrayList.add(sem);
                        }
                        populateSemesterSpinner();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

        }
    }




    // Method To Populate Category Spinner
    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < categoryArrayList.size(); i++) {
            lables.add(categoryArrayList.get(i).getCat_name());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputCourseCategory = (Spinner) findViewById(R.id.drpCourseCategory);
        // attaching data adapter to spinner
        inputCourseCategory.setAdapter(spinnerAdapter);
    }


    // Class To Get Categories From Database
    class GetCategories extends AsyncTask<Void, Void, Void>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            //if(tastlist.size()==0){

                pDialog.setMessage("Loading Data ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
               // pb.setVisibility(View.VISIBLE);
            //}
            tastlist.add("second task");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONParser jsonParser = new JSONParser();
            String json = jsonParser.makeHttpRequest(url_get_category, "GET", params);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        categories = jsonObj
                                .getJSONArray("course_category");

                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject catObj = (JSONObject) categories.get(i);
                            Category cat = new Category(catObj.getString("cat_id"),catObj.getString("cat_name"));
                            categoryArrayList.add(cat);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tastlist.remove("second task");
            if(tastlist.size()==0){
                if (pDialog.isShowing())
                    pDialog.dismiss();
                // pb.setVisibility(View.INVISIBLE);
            }



            populateSpinner();
        }
    }
    


    // Class To Add Course To Database
    class CreateCourse extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CourseHome.this);
            pDialog.setMessage("Adding Course Info..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String course_id = inputCourseID.getText().toString();
            String course_name = inputCourseName.getText().toString();
            String ref_code = inputCourseRefCode.getText().toString();
            String emu_credit = inputCourseEMUCredit.getText().toString();
            String ects_credit = inputCourseECTSCredit.getText().toString();
            String prerequisite = inputPrerequisite.getText().toString();
            String lab_hour = inputLabHour.getText().toString();
            String lecture_hour = inputLectureHour.getText().toString();
            String desc = inputCourseDescription.getText().toString();
            String tutorial_hour = inputTutorialHour.getText().toString();
            String category_id = inputCourseCategory.getSelectedItem().toString();
            String semester_id = inputSemester.getSelectedItem().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("course_id", course_id));
            params.add(new BasicNameValuePair("course_name", course_name));
            params.add(new BasicNameValuePair("ref_code", ref_code));
            params.add(new BasicNameValuePair("emu_credit",emu_credit));
            params.add(new BasicNameValuePair("ects_credit",ects_credit));
            params.add(new BasicNameValuePair("prerequisite",prerequisite));
            params.add(new BasicNameValuePair("lab_hour",lab_hour));
            params.add(new BasicNameValuePair("lecture_hour",lecture_hour));
            params.add(new BasicNameValuePair("desc",desc));
            params.add(new BasicNameValuePair("tutorial_hour",tutorial_hour));
            params.add(new BasicNameValuePair("category_id",category_id));
            params.add(new BasicNameValuePair("semester_id",semester_id));
            // getting JSON Object
            // Note that create product url accepts POST method
            String content = jsonParser.makeHttpRequest(url_create_course,
                    "POST", params);
            return content;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            Log.d("Post Execute returned", file_url);
            try {
                //  JSONArray ar = new JSONArray(file_url);
                //  for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = new JSONObject(file_url);

                int success = obj.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // successfully created product
                    Log.d("in success","");
                    Intent k = new Intent(getApplicationContext(), AdminPanel.class);
                    startActivity(k);
                    // closing this screen
                    finish();
                } else {
                    // failed to create product

                    Toast.makeText(CourseHome.this, "Error: Course Info Could not be added!!!", Toast.LENGTH_LONG).show();
                }
                //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(CourseHome.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_home, menu);
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
