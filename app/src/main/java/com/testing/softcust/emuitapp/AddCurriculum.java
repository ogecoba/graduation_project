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


public class AddCurriculum extends ActionBarActivity {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputCurriculumID, inputCurriculumRefCode, inputCurriculumName, inputCurriculumDesc;
    Button btnSaveCurriculum;

    private static final String TAG_SUCCESS = "success";

    private static String url_create_Curriculum = "https://emu-itapplication.herokuapp.com/create_curriculum.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curriculum);

        inputCurriculumID = (EditText) findViewById(R.id.txtCurriculumID);
        inputCurriculumName = (EditText) findViewById(R.id.txtCurriculumName);
        inputCurriculumRefCode = (EditText) findViewById(R.id.txtCurRefCode);
        inputCurriculumDesc = (EditText) findViewById(R.id.txtCurriculumDesc);

        btnSaveCurriculum = (Button) findViewById(R.id.btnSaveCurriculum);
        btnSaveCurriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class CreateCurriculum extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddCurriculum.this);
            pDialog.setMessage("Adding Curriculum Info..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String curriculum_id = inputCurriculumID.getText().toString();
            String curriculum_name = inputCurriculumName.getText().toString();
            String ref_code = inputCurriculumRefCode.getText().toString();
            String description = inputCurriculumDesc.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("curriculum_id", curriculum_id));
            params.add(new BasicNameValuePair("curriculum_name", curriculum_name));
            params.add(new BasicNameValuePair("ref_code", ref_code));
            params.add(new BasicNameValuePair("description",description));
            // getting JSON Object
            // Note that create product url accepts POST method
            String content = jsonParser.makeHttpRequest(url_create_Curriculum,
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
                    Toast.makeText(AddCurriculum.this, "Error Checking the response", Toast.LENGTH_LONG).show();
                }
                //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(AddCurriculum.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_curriculum, menu);
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
