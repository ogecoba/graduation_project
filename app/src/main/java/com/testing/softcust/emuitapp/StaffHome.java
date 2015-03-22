package com.testing.softcust.emuitapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class StaffHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        TabHost tabHost = (TabHost) findViewById(R.id.tabStaff);
        tabHost.setup();

        // Tab For Add Category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("addStaff");
        tabSpec.setContent(R.id.tabAddStaff);
        tabSpec.setIndicator("Add Staff");
        tabHost.addTab(tabSpec);


        // Tab For List Category
        tabSpec = tabHost.newTabSpec("listStaff");
        tabSpec.setContent(R.id.tabListStaff);
        tabSpec.setIndicator("List Staff");
        tabHost.addTab(tabSpec);

        // Tab For Delete Category
        tabSpec = tabHost.newTabSpec("delStaff");
        tabSpec.setContent(R.id.tabDeleteStaff);
        tabSpec.setIndicator("Delete Staff");
        tabHost.addTab(tabSpec);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_staff_home, menu);
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
