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
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.Classes.HistoryRequire.SectionPageAdapter_H;
import ca.i3th.capstonii.Fragments.Setting.ClientInfoChange;
import ca.i3th.capstonii.Fragments.Setting.ContactChange;
import ca.i3th.capstonii.Fragments.Setting.PassChange;


public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "SettingActivity";
    private SectionPageAdapter_H sectionPageAdapter_h;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ProgressDialog progressDialog;
    private String id;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    private String[] msgError = new String[2];
    private boolean passChk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();
        id = intent.getStringExtra("db_R_ID");
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");

        sectionPageAdapter_h = new SectionPageAdapter_H(getSupportFragmentManager());
        // setup the ViewPager with the section adapter

        viewPager = findViewById(R.id.vp_containers);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabss);
        tabLayout.setupWithViewPager(viewPager);

        Log.d(TAG, "onCreate: Started");

        drawerLayout = findViewById(R.id.dlSettingActivity);
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
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter_H adapter_h = new SectionPageAdapter_H(getSupportFragmentManager());
        adapter_h.addFragment(new ClientInfoChange(), "Client_Info");
        adapter_h.addFragment(new PassChange(), "Password");
        adapter_h.addFragment(new ContactChange(), "Contact");

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
                Toast.makeText(SettingActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SettingActivity.this, "Bad job!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

    public String[] getFullName() { return fullName; }
    public String[] getContact() { return contact; }
    public String[] getBodyInfo() { return bodyInfo; }
    public void setFullName(String[] fullName) { this.fullName = fullName; }
    public void setContact(String[] contact) { this.contact = contact; }
    public void setBodyInfo(String[] fullName) { this.fullName = fullName; }
    public String getId() { return id; }

    public String[] getMsgError() { return msgError; }

    //update body info
    public String[] updateBodyInfo(final String idP, String[] bodyInfoP){
        final String userID = idP.trim();
        bodyInfo = bodyInfoP;
        Log.d(TAG, "updateBodyInfo: ZZZZZZZZZZZZZZZZZZ " + bodyInfo[0]);
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "updateBodyInfo: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_BODY_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "updateBodyInfo: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "updateBodyInfo---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "updateBodyInfo---1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "updateBodyInfo------------> TestPASS" );
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
                            }
                            else {
                                Log.d("msg", "updateBodyInfo-----3----> " + jsonObject.getString("error" + ": " + "message"));
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
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
                            Log.d(TAG, "updateBodyInfo: ---->" + e1);
                        }
                        Log.d(TAG, "updateBodyInfo: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hight", bodyInfo[0]);
                params.put("wight", bodyInfo[1]);
                params.put("Type", bodyInfo[2]);
                params.put("sex", bodyInfo[3]);
                params.put("id", userID);
                Log.d("msg" ,"updateBodyInfo params--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"updateBodyInfo>>>--------------------"+ stringRequest.getBodyContentType());
        return msgError;
    }
    //update client name
    public String[] updateFullName(final String idP, String[] fullNameP){
        final String userID = idP.trim();
        fullName = fullNameP;
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "updateFullName: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_CLIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "updateFullName: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
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
                            Log.d(TAG, "updateFullName: ---->" + e1);
                        }
                        Log.d(TAG, "updateFullName: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", fullName[0]);
                params.put("lastName", fullName[1]);
                params.put("id", userID);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"updateFullName>>>--------------------"+ stringRequest.getBodyContentType());
        return msgError;
    }
    //update Password
    public String[] updatePassword(final String idP, String newPassword){
        final String userID = idP.trim();
        final String newPass = newPassword.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "updatePassword: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_PASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "updatePassword: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "updatePassword--1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "updatePassword--1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "updatePassword---1-----1----> TestPASS" );
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
                            }
                            else {
                                Log.d("msg", "-updatePassword---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
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
                            Log.d(TAG, "updatePassword: ---->" + e1);
                        }
                        Log.d(TAG, "updatePassword: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", userID);
                params.put("newpass", newPass);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"updatePassword>>>--------------------"+ stringRequest.getBodyContentType());
        return msgError;
    }
    //Contact Update
    public String[] updateContact(final String idP, String[] contactP){
        final String userID = idP.trim();
         contact = contactP;
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "updateContact: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_CONTACT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "updateContact: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
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
                            Log.d(TAG, "updateContact: ---->" + e1);
                        }
                        Log.d(TAG, "updateContact: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", contact[0]);
                params.put("country", contact[1]);
                params.put("region", contact[2]);
                params.put("city", contact[3]);
                params.put("pCode", contact[4]);
                params.put("id", userID);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"updateContact>>>--------------------"+ stringRequest.getBodyContentType());
        return msgError;
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

    ///check password
    public boolean checkPassword(String id, String oldPassword){
        final String userID = id.trim();
        final String oldPass  = oldPassword.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "checkPassword: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_G_PASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "checkPassword: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "checkPassword---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "checkPassword---1-----2----> " + jsonObject.getString("message"));

                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );
//                                msgError[0] = jsonObject.getString("error");
//                                msgError[1] = jsonObject.getString("message");
                                passChk = true;
                            }
                            else {
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("error" + ": " + "message"));
                                msgError[0] = jsonObject.getString("error");
                                msgError[1] = jsonObject.getString("message");
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
                            Log.d(TAG, "checkPassword: ---->" + e1);
                        }
                        Log.d(TAG, "checkPassword: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", userID);
                params.put("pass", oldPass);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"checkPassword>>>--------------------"+ stringRequest.getBodyContentType());
        return passChk;
    }
}