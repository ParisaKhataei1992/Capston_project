package ca.i3th.capstonii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.Classes.HistoryRequire.RecordsAdapter;
import ca.i3th.capstonii.Classes.Records;

public class RecordsActivity extends AppCompatActivity implements RecordsAdapter.OnRecordsClickListener {

    private static final String TAG = "RecordsActivity";
    RecyclerView recyclerView;
    private String id;
    List<Records> recordsList = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Intent intent = getIntent();
        id = intent.getStringExtra("db_R_ID");
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");

        recyclerView = findViewById(R.id.recyclerViewR);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        test(id);

        drawerLayout = findViewById(R.id.dlRecordsActivity);
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
        final NavigationView navigationView = findViewById(R.id.nav_viewRA);
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
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navCalendar :
                       // Toast.makeText(WorkoutActivity.this, "navCalendar", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), CalenderActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);
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

    ///get getRecords
    public void getRecords(String id){
        final String userID = "74";//id.trim();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, Constants.URL_G_ACTIVITY, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Records>>(){}.getType();
                        recordsList = gson.fromJson(response.toString(), type);
                        RecordsAdapter adapter = new RecordsAdapter(RecordsActivity.this,
                                recordsList, RecordsActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", userID);
//                Log.d("msg" ,"--------------------"+ params);
//                return params;
//            }
//        };

        Volley.newRequestQueue(this).add(request);

    }
//////////////////////////////////////////////////////////////test start
///get client
    public void test(String id){
    final String userID = id.trim();


    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_ACTIVITY,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Records>>(){}.getType();
                    recordsList = gson.fromJson(response, type);
                    RecordsAdapter adapter = new RecordsAdapter(RecordsActivity.this,
                            recordsList, RecordsActivity.this);
                    recyclerView.setAdapter(adapter);

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", userID);
            Log.d("msg" ,"--------------------"+ params);
            return params;
        }
    };
    Volley.newRequestQueue(this).add(stringRequest);

}
/////////////////////////////////////////////////////////test end

    @Override
    public void onRecordClicked(int position) {
        if (recordsList.get(position).getType().equals("Cardio") || recordsList.get(position).getType().equals("cardio")) {
            Toast.makeText(this
                    , "Date: " + recordsList.get(position).getDate() + "  Activity: " +
                            recordsList.get(position).getLongitude() + "  Calorie: " +
                            recordsList.get(position).getCol(), Toast.LENGTH_SHORT).show();
        }
        else if (recordsList.get(position).getType().equals("Running") || recordsList.get(position).getType().equals("running")) {
            Toast.makeText(this
                    , "Date: " + recordsList.get(position).getDate() + "  Speed: " +
                            recordsList.get(position).getSpeed() + "  Distance: " +
                            recordsList.get(position).getDistance() + "  Calorie: " +
                            recordsList.get(position).getCol(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RecordsActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(RecordsActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }
}
