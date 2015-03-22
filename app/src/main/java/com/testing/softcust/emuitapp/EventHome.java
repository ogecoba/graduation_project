package com.testing.softcust.emuitapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class EventHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        TabHost tabHost = (TabHost) findViewById(R.id.tabEvent);
        tabHost.setup();

        // Tab For Add Category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("addEvent");
        tabSpec.setContent(R.id.tabAddEvent);
        tabSpec.setIndicator("Add Event");
        tabHost.addTab(tabSpec);


        // Tab For List Category
        tabSpec = tabHost.newTabSpec("listEvent");
        tabSpec.setContent(R.id.tabListEvent);
        tabSpec.setIndicator("List Event");
        tabHost.addTab(tabSpec);

        // Tab For Delete Category
        tabSpec = tabHost.newTabSpec("delEvent");
        tabSpec.setContent(R.id.tabDeleteEvent);
        tabSpec.setIndicator("Delete Event");
        tabHost.addTab(tabSpec);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_home, menu);
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
