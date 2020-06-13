package ca.i3th.capstonii;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class CalenderActivity extends AppCompatActivity {
    private static final String TAG = "CalenderActivity";
    CalendarView calendarView;
    TextView myDate;
    private String sex;
    private String id;
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        Intent intent = getIntent();
        id = intent.getStringExtra("db_R_ID");
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = findViewById(R.id.btnSaveDate);
        drawerLayout = findViewById(R.id.dlcalenderActivity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CalenderActivity.this, "No Activity Available.", Toast.LENGTH_SHORT).show();
            }
        });
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Log.d(TAG, "----------------------" + getSupportActionBar());
        ActionBar actionBar = this.getSupportActionBar();
        Log.d(TAG, "----------------------" + actionBar);
        //this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        else {
            Log.d(TAG, "---------------------->>" + actionBar);
        }

        final NavigationView navigationView = findViewById(R.id.nav_views);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                Intent intent;
                switch (itemID) {
                    case R.id.navHome :
//                        Toast.makeText(HomeActivity.this, "navHome", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), HomeActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navActivity :
                        //Toast.makeText(HistoryActivity.this, "navActivity", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), WorkoutActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navCalendar :
                        //Toast.makeText(HistoryActivity.this, "navCalendar", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), CalenderActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navRecord :
                        intent = new Intent(getApplication(), RecordsActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navSetting :
                        //Toast.makeText(HistoryActivity.this, "navSetting", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), SettingActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navLogout :
                        //Toast.makeText(HistoryActivity.this, "navLogout", Toast.LENGTH_SHORT).show();
                        showAlert();
                        break;
//                    default:
//                        Log.d(TAG, "onCreate: Nothing Touch");
//                        break;
                }
                return true;
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void showAlert () {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Exit");
        alert.setMessage("Do you want to Exit");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CalenderActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CalenderActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }
}
