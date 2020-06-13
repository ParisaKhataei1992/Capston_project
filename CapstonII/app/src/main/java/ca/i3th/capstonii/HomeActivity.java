package ca.i3th.capstonii;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.Fragments.History.History;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private String id;
    private String msgNotice = "1 Notification is available.";
    private static final String TAG = "HomeActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    public String[] notification = new String[2];
    private Button notificationBTN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        notificationBTN = findViewById(R.id.btnNotification);
        getNotification();
//        msgNotice = notification[0];
        notificationBTN.setText(msgNotice);
        notificationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, notification[1], Toast.LENGTH_LONG).show();
            }
        });



        Intent intent = getIntent();
        id = intent.getStringExtra("db_R_ID");
        Log.d(TAG, "onCreate: Started----------> " + id);

        fullName = getClient(id);
        contact = getContact(id);
        bodyInfo = getBodyInfo(id);

        drawerLayout = findViewById(R.id.dlHomeActivity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        Log.d(TAG, "----------------------" + getSupportActionBar());
        ActionBar actionBar = this.getSupportActionBar();
//        Log.d(TAG, "----------------------" + actionBar);
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
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navActivity :
//                        Toast.makeText(HomeActivity.this, "navActivity", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), WorkoutActivity.class);
//                        intent.putExtra("bodyInfo", bodyInfo[3]);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("sex", bodyInfo[3]);
                        intent.putExtra("db_R_ID", id);
                        startActivity(intent);
                        break;
                    case R.id.navCalendar :
                        Toast.makeText(HomeActivity.this, "navCalendar", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), CalenderActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;
                    case R.id.navRecord :
//                        Toast.makeText(HomeActivity.this, "navHistory", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), RecordsActivity.class);
                        intent.putExtra("db_R_ID", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        startActivity(intent);
                        break;

                    case R.id.navSetting :
//                        Toast.makeText(HomeActivity.this, "navSetting", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), SettingActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("contact", contact);
                        intent.putExtra("bodyInfo", bodyInfo);
                        intent.putExtra("db_R_ID", id);

                        startActivity(intent);
                        break;
                    case R.id.navLogout :
//                        Toast.makeText(HomeActivity.this, "navLogout", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(HomeActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HomeActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

    ///get client
    public String[] getClient(String id){
        final String userID = id.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "getClient: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_CLIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getClient: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("firstName"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("lastName"));

                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                fullName[0] = jsonObject.getString("firstName");
                                fullName[1] = jsonObject.getString("lastName");
                                Log.d("msg", "-2---1-----2----> " + jsonObject.getString("error") + fullName[0] + fullName[1]);
                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            progressDialog.hide();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getClient: ---->" + e1);
                        }
                        Log.d(TAG, "getClient: ---->" + error.getMessage());
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"getClient>>>--------------------"+ stringRequest.getBodyContentType());
        return fullName;
    }

    ///get contact
    public String[] getContact(String id){
        final String userID = id.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "getContact: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_CONTACT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getContact: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("email"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("country"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("region"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("city"));
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("postalCode"));

                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                contact[0] = jsonObject.getString("email");
                                contact[1] = jsonObject.getString("country");
                                contact[2] = jsonObject.getString("region");
                                contact[3] = jsonObject.getString("city");
                                contact[4] = jsonObject.getString("postalCode");

                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            progressDialog.hide();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getContact: ---->" + e1);
                        }
                        Log.d(TAG, "getContact: ---->" + error.getMessage());
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"getContact>>>--------------------"+ stringRequest.getBodyContentType());
        return contact;
    }

    ///get body info
    public String[] getBodyInfo(String id){
        final String userID = id.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "getBodyInfo: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_BODY_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getBodyInfo: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
//                            Log.d("msg", "-2---1-----2----> " + jsonObject.getString("hight"));
//                            Log.d("msg", "-2---1-----3----> " + jsonObject.getString("wight"));
//                            Log.d("msg", "-2---1-----4----> " + jsonObject.getString("Type"));
//                            Log.d("msg", "-2---1-----5----> " + jsonObject.getString("sex"));

                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                bodyInfo[0] = jsonObject.getString("hight");
                                bodyInfo[1] = jsonObject.getString("wight");
                                bodyInfo[2] = jsonObject.getString("Type");
                                bodyInfo[3] = jsonObject.getString("sex");

                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            progressDialog.hide();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "getBodyInfo: ---->" + e1);
                        }
                        Log.d(TAG, "getBodyInfo: ---->" + error.getMessage());
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"getBodyInfo>>>--------------------"+ stringRequest.getBodyContentType());
        return bodyInfo;
    }

    ///get Notification
    public void getNotification(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_NOTICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("title"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("text"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                notification[0] = jsonObject.getString("title");
                                notification[1] = jsonObject.getString("text");
                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "getClient: ---->" + error.getMessage());
                    }
                })
        {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"getClient>>>--------------------"+ stringRequest.getBodyContentType());

    }
    public String[] getBroadCast(){
        return notification;
    }
}
