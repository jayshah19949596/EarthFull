package com.example.jaysh.earthfull.volunteer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaysh.earthfull.*;
import com.example.jaysh.earthfull.data.DBMgr;


public class Search extends AppCompatActivity {
    Cursor c;
    DBMgr DBMgr;
    SearchAdapter  cursorAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Search","Search");
        setContentView(R.layout.search);

        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        Log.d("MenuActivity","Querying to get menu");

        c = DBMgr.getEventsList("Garbage Collection");
        c.moveToFirst();
        ListView menu_list = (ListView) findViewById(R.id.listViewSearch);
        cursorAdapter = new SearchAdapter(this, c, 0);
        menu_list.setAdapter(cursorAdapter);

        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view , int position, long id)
            {
                Log.d("Search","In on list on click");
                Log.d("Search","Position value: "+String.valueOf(position));
                TextView textViewDescription = (TextView) view.findViewById(R.id.textEventDescValue);
                Cursor c = DBMgr.singleEvent(textViewDescription.getText().toString());
                clickedList(c);
            }
        });

    }

    public void clickedList(Cursor c){
        Intent intentEventDetails = new Intent(Search.this, EventDetails.class);
        startActivity(intentEventDetails);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_seacrh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home: {

                Intent intentSearch = new Intent(Search.this, VolunteerHome.class);
                startActivity(intentSearch);
                return true;
            }

            case R.id.options_event_search_logout: {
                Intent intentLogout = new Intent(Search.this, LogInHome.class);
                startActivity(intentLogout);
                Toast.makeText(getApplicationContext(),
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();
                return true;
            }

            default: {
                return true;
            }

        }


    }
}
