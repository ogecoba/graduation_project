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
import android.widget.Toast;


public class CourseCategory extends Activity {


    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputCategoryID;
    EditText inputCategoryName;
    EditText inputCategoryDesc;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    private static String url_create_course_category = "https://emu-itapplication.herokuapp.com/create_course_category.php";

    //"http://marpawm.com/AndroidAppConnection/create_course_category.php"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_category);

        inputCategoryID = (EditText) findViewById(R.id.txtCategoryID);
        inputCategoryName = (EditText) findViewById(R.id.txtCategoryName);
        inputCategoryDesc = (EditText) findViewById(R.id.txtCategoryDesc);

        Button btnSaveInfo = (Button) findViewById(R.id.btnSaveCategory);

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateCourseCategory().execute();
            }
        });
    }

    class CreateCourseCategory extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CourseCategory.this);
            pDialog.setMessage("Adding Course Category..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String cat_id = inputCategoryID.getText().toString();
            String cat_name = inputCategoryName.getText().toString();
            String cat_desc = inputCategoryDesc.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("cat_id", cat_id));
            params.add(new BasicNameValuePair("cat_name", cat_name));
            params.add(new BasicNameValuePair("cat_desc", cat_desc));
            // getting JSON Object
            // Note that create product url accepts POST method
            String content = jsonParser.makeHttpRequest(url_create_course_category,
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
                        Toast.makeText(CourseCategory.this, "Error Checking the response", Toast.LENGTH_LONG).show();
                    }
              //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(CourseCategory.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_category, menu);
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
