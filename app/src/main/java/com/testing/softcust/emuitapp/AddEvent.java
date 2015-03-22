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


public class AddEvent extends ActionBarActivity {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();


    EditText inputEventName, inputEventType, inputEventStartDate, inputEventEndDate, inputEventDesc;
    Spinner inputEventSem;
    Button btnSaveEvent;

    private static final String TAG_SUCCESS = "success";

    private static String url_create_event = "http://sctwebservice.herokuapp.com/abdul/create_event.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        inputEventName = (EditText) findViewById(R.id.txtEventName);
        inputEventType = (EditText) findViewById(R.id.txtEventType);
        inputEventStartDate = (EditText) findViewById(R.id.txtEventStartDate);
        inputEventEndDate = (EditText) findViewById(R.id.txtEventEndDate);
        inputEventDesc = (EditText) findViewById(R.id.txtEventDesc);
        inputEventSem = (Spinner) findViewById(R.id.drp_event_semester_id);

        btnSaveEvent = (Button) findViewById(R.id.btnSaveEvent);
        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class CreateEvent extends AsyncTask<String, String, String>{

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddEvent.this);
            pDialog.setMessage("Adding Course Info..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String event_name = inputEventName.getText().toString();
            String event_type = inputEventType.getText().toString();
            String start_date = inputEventStartDate.getText().toString();
            String end_date = inputEventEndDate.getText().toString();
            String desc = inputEventDesc.getText().toString();
            String event_sem_id = inputEventSem.getSelectedItem().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("event_name", event_name));
            params.add(new BasicNameValuePair("event_type", event_type));
            params.add(new BasicNameValuePair("start_date",start_date));
            params.add(new BasicNameValuePair("end_date",end_date));
            params.add(new BasicNameValuePair("desc",desc));
            params.add(new BasicNameValuePair("event_sem_id",event_sem_id));

            // getting JSON Object
            // Note that create product url accepts POST method
            String content = jsonParser.makeHttpRequest(url_create_event,
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
                    Toast.makeText(AddEvent.this, "Error Checking the response", Toast.LENGTH_LONG).show();
                }
                //  }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(AddEvent.this,"An Exception Occur ...", Toast.LENGTH_LONG).show();
                Log.d("",file_url);
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
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
