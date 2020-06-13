package ca.i3th.capstonii;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ca.i3th.capstonii.Fragments.History.*;
import ca.i3th.capstonii.Classes.HistoryRequire.SectionPageAdapter_H;


public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";
    private SectionPageAdapter_H sectionPageAdapter_h;
    private ViewPager viewPager;
    private ProgressDialog progressDialog;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        sectionPageAdapter_h = new SectionPageAdapter_H(getSupportFragmentManager());

        // setup the ViewPager with the section adapter

        viewPager = findViewById(R.id.vp_container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Log.d(TAG, "onCreate: Started");

        drawerLayout = findViewById(R.id.dlHistoryActivity);
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

        final NavigationView navigationView = findViewById(R.id.nav_viewh);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                Intent intent;
                switch (itemID) {
                    case R.id.navHome :
//                        Toast.makeText(HomeActivity.this, "navHome", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navActivity :
                        //Toast.makeText(HistoryActivity.this, "navActivity", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), WorkoutActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navCalendar :
                        //Toast.makeText(HistoryActivity.this, "navCalendar", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), CalenderActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navRecord :
//                        Toast.makeText(HistoryActivity.this, "navHistory", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), RecordsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navSetting :
//                        Toast.makeText(HistoryActivity.this, "navSetting", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplication(), SettingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navLogout :
//                        Toast.makeText(HistoryActivity.this, "navLogout", Toast.LENGTH_SHORT).show();
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

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter_H adapter_h = new SectionPageAdapter_H(getSupportFragmentManager());
        adapter_h.addFragment(new History(), "History");
        adapter_h.addFragment(new Statistic(), "Statistic");

        viewPager.setAdapter(adapter_h);
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
                Toast.makeText(HistoryActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HistoryActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }


}