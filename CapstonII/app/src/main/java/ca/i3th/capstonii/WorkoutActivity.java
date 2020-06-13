package ca.i3th.capstonii;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import ca.i3th.capstonii.PEDOMETER.RunningActivity;
import ca.i3th.capstonii.WOV.VideoButtonsFemale;
import ca.i3th.capstonii.WOV.VideoButtonsMale;

public class WorkoutActivity extends AppCompatActivity {
    private Button btnWorkOut;
    private  Button btnRunning;
    private String sex;
    private String id;
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];

    private static final String TAG = "WorkoutActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();

        id = intent.getStringExtra("db_R_ID");
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");
        sex = intent.getStringExtra("sex");
        Log.d(TAG, "onCreate: 555555555555555555555555 " + intent.getStringExtra("sex"));
        if(intent.getStringExtra("sex") == null) {
            if(intent.getStringExtra("bodyInfo") == null) {
                sex = "99";
            }
            else {
                sex = bodyInfo[3];
            }
        }

        Log.d(TAG, "onCreate: -----------sex--> " + sex);

        btnWorkOut = (Button) findViewById(R.id.btnWorkOut);
        btnWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkOutActivity();
            }
        });
        btnRunning = findViewById(R.id.btnRunning);
        btnRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RunningActivity.class);
                intent.putExtra("db_R_ID", id);
                intent.putExtra("fullName", fullName);
                intent.putExtra("contact", contact);
                intent.putExtra("bodyInfo", bodyInfo);
                startActivity(intent);
            }
        });


        drawerLayout = findViewById(R.id.dlWorkoutActivity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

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

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                Intent intent;
                switch (itemID) {
                    case R.id.navHome :
//                        Toast.makeText(HomeActivity.this, "navHome", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), HomeActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navActivity :
//                        Toast.makeText(WorkoutActivity.this, "navActivity", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), WorkoutActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navCalendar :
                        Toast.makeText(WorkoutActivity.this, "navCalendar", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), CalenderActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navRecord :
//                        Toast.makeText(WorkoutActivity.this, "navHistory", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), RecordsActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navSetting :
//                        Toast.makeText(WorkoutActivity.this, "navSetting", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), SettingActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navLogout :
//                        Toast.makeText(WorkoutActivity.this, "navLogout", Toast.LENGTH_SHORT).show();
                        showAlert();
                        break;
//                    default:
//                        Log.d(TAG, "onCreate: Nothing Touch");
//                        break;
                }
                return true;
            }
        });


    }

    public void openWorkOutActivity(){

        if(sex.equals("null") || sex.equals("99") || sex == null || sex.isEmpty()) {
//            Log.d(TAG, "openWorkOutActivity: ---------------------> " + sex);
//            Intent intent = new Intent(this, VideoButtonsMale.class);
//            intent.putExtra("sex", "99");
//            intent.putExtra("fullName", fullName);
//            intent.putExtra("contact", contact);
//            intent.putExtra("bodyInfo", bodyInfo);
//            startActivity(intent);
            Toast.makeText(WorkoutActivity.this, "The gender NOT choose, please got to SETTING and set your gender", Toast.LENGTH_SHORT).show();
        }
        else if(sex.equals("0")) {
            Intent intent = new Intent(this, VideoButtonsFemale.class);
            Log.d(TAG, "openWorkOutActivity: ---------------------> " + sex);
            intent.putExtra("sex", sex);
            intent.putExtra("fullName", fullName);
            intent.putExtra("contact", contact);
            intent.putExtra("bodyInfo", bodyInfo);
            intent.putExtra("db_R_ID", id);
            startActivity(intent);
        }
        else if(sex.equals("1")) {
            Intent intent = new Intent(this, VideoButtonsMale.class);
            Log.d(TAG, "openWorkOutActivity: ---------------------> " + sex);
            intent.putExtra("sex", sex);
            intent.putExtra("fullName", fullName);
            intent.putExtra("contact", contact);
            intent.putExtra("bodyInfo", bodyInfo);
            intent.putExtra("db_R_ID", id);
            startActivity(intent);
        }

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
                Toast.makeText(WorkoutActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(WorkoutActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }
}
