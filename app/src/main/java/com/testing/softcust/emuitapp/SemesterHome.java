package com.testing.softcust.emuitapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SemesterHome extends ActionBarActivity {

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    EditText inputSemesterID, inputSemesterName, inputSemesterSession, inputStartDate, inputEndDate;
    Button btnSaveSemester, btnDeleteSemester;


    ArrayList<HashMap<String, String>> semesterList;

    // url to get all products list
    private static String url_all_semester = "https://emu-itapplication.herokuapp.com/getAll_semester.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    private static String url_create_semester = "https://emu-itapplication.herokuapp.com/create_semester.php";

    private static final String TAG_SEMESTER = "semester";
    private static final String TAG_SID = "sem_id";
    private static final String TAG_NAME = "sem_name";

    // semester JSONArray
    JSONArray semesters = null;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_home);

        TabHost tabHost = (TabHost) findViewById(R.id.tabSemester);
        tabHost.setup();

        // Tab For Add Category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("addSemester");
        tabSpec.setContent(R.id.tabAddSemester);
        tabSpec.setIndicator("Add Semester");
        tabHost.addTab(tabSpec);


        // Tab For List Category
        tabSpec = tabHost.newTabSpec("listSemester");
        tabSpec.setContent(R.id.tabListSemester);
        tabSpec.setIndicator("List Semester");
        tabHost.addTab(tabSpec);

        // Tab For Delete Category
        tabSpec = tabHost.newTabSpec("delSemester");
        tabSpec.setContent(R.id.tabDeleteSemester);
        tabSpec.setIndicator("Delete Semester");
        tabHost.addTab(tabSpec);


        inputSemesterID = (EditText) findViewById(R.id.txtSemesterID);
        inputSemesterName = (EditText) findViewById(R.id.txtSemesterName);
        inputSemesterSession = (EditText) findViewById(R.id.txtSession);
        inputStartDate = (EditText) findViewById(R.id.txtStartDate);
        inputEndDate = (EditText) findViewById(R.id.txtEndDate);


        btnSaveSemester = (Button) findViewById(R.id.btnSaveSemester);
        btnSaveSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateSemester().execute();
            }
        });

        semesterList = new ArrayList<HashMap<String, String>>();



        // Get listview
        lv = (ListView) findViewById(R.id.list_item);
        // Loading products in Background Thread
        new LoadAllSemesters().execute();
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllSemesters extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SemesterHome.this);
            pDialog.setMessage("Loading Semesters. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            String json = jsonParser.makeHttpRequest(url_all_semester, "GET", params);


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            // Check your log cat for JSON reponse
            Log.d("All Products: ", file_url.toString());

            try {
                JSONObject json = new JSONObject(file_url);
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    semesters = json.getJSONArray(TAG_SEMESTER);

                    // looping through All Products
                    for (int i = 0; i < semesters.length(); i++) {
                        JSONObject c = semesters.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_SID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_SID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        semesterList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            SemesterHome.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            SemesterHome.this, semesterList,
                            R.layout.all_semester, new String[] { TAG_SID,
                            TAG_NAME},
                            new int[] { R.id.lblListSemesterID, R.id.lblListSemesterName });
                    // updating listview
                  lv.setAdapter(adapter);
                }
            });

        }

    }

    class CreateSemester extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SemesterHome.this);
            pDialog.setMessage("Adding Semester..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String sem_id = inputSemesterID.getText().toString();
            String sem_name = inputSemesterName.getText().toString();
            String session = inputSemesterSession.getText().toString();
            String start_date = inputStartDate.getText().toString();
            String end_date = inputEndDate.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sem_id", sem_id));
            params.add(new BasicNameValuePair("sem_name", sem_name));
            params.add(new BasicNameValuePair("session", session));
            params.add(new BasicNameValuePair("start_date", start_date));
            params.add(new BasicNameValuePair("end_date", end_date));
            // getting JSON Object
            // Note that create product url accepts POST method
            String content = jsonParser.makeHttpRequest(url_create_semester,
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
                    Toast.makeText(SemesterHome.this, "Error Checking the response", Toast.LENGTH_LONG).show();
                }
                //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(SemesterHome.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_semester_home, menu);
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
