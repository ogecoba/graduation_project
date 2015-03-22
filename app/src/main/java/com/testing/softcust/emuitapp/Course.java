package com.testing.softcust.emuitapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Course extends ActionBarActivity {

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

    Button btnSaveCourse;

    private static final String TAG_SUCCESS = "success";

    private static String url_create_course = "https://emu-itapplication.herokuapp.com/create_course.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

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
        inputCourseCategory = (Spinner) findViewById(R.id.drp_category);
        inputSemester = (Spinner) findViewById(R.id.drp_semester);

        btnSaveCourse = (Button) findViewById(R.id.btnSaveCourse);
        btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateCourse().execute();
            }
        });
    }


    class CreateCourse extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Course.this);
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
            Log.d("Post Execute returned",file_url);
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
                    Toast.makeText(Course.this, "Error Checking the response", Toast.LENGTH_LONG).show();
                }
                //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Course.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
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
