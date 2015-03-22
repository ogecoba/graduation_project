package com.testing.softcust.emuitapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class GalleryHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_home);

        TabHost tabHost = (TabHost) findViewById(R.id.tabGallery);
        tabHost.setup();

        // Tab For Add Category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("addPicture");
        tabSpec.setContent(R.id.tabAddPicture);
        tabSpec.setIndicator("Add Picture");
        tabHost.addTab(tabSpec);


        // Tab For List Category
        tabSpec = tabHost.newTabSpec("listPicture");
        tabSpec.setContent(R.id.tabListPicture);
        tabSpec.setIndicator("List Picture");
        tabHost.addTab(tabSpec);

        // Tab For Delete Category
        tabSpec = tabHost.newTabSpec("delPicture");
        tabSpec.setContent(R.id.tabDeletePicture);
        tabSpec.setIndicator("Delete Picture");
        tabHost.addTab(tabSpec);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery_home, menu);
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
