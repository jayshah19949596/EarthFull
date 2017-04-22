package com.example.jaysh.earthfull.volunteer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaysh.earthfull.LogInHome;
import com.example.jaysh.earthfull.R;
import com.example.jaysh.earthfull.data.DBMgr;

public class VolunteerHome extends AppCompatActivity {
    TextView textName, textNoOfEvents, textCredits, textPendingEvents;
    String nameValue, creditValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_home);
        textName = (TextView)findViewById(R.id.textViewNameValue);
        textNoOfEvents = (TextView)findViewById(R.id.textNumberOfEventsValue);
        textCredits = (TextView)findViewById(R.id.textViewCreditsValue);
        textPendingEvents = (TextView)findViewById(R.id.textPendingEventsValue);

        nameValue = LogInHome.userCursor.getString(LogInHome.userCursor.getColumnIndex(DBMgr.USER_NAME));
        textName.setText(nameValue);

        creditValue = LogInHome.userCursor.getString(LogInHome.userCursor.getColumnIndex(DBMgr.USER_POINTS));
        textCredits.setText(" "+String.valueOf(creditValue));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_user_profile: {

                Intent intentSearch = new Intent(getApplicationContext(), Search.class);
                //intentViewCart.putExtra("cartDishList", cartDishList);
                startActivity(intentSearch);
                return true;
            }

            case R.id.offers_user_profile: {

                Intent intentViewCart = new Intent(VolunteerHome.this, LogInHome.class);
                //intentViewCart.putExtra("cartDishList", cartDishList);
                startActivity(intentViewCart);
                return true;
            }

            case R.id.options_user_profile_logout: {
                Intent intentLogout = new Intent(VolunteerHome.this, LogInHome.class);
                startActivity(intentLogout);
                Toast.makeText(VolunteerHome.this,
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
