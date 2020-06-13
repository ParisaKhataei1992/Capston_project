package ca.i3th.capstonii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

import ca.i3th.capstonii.Classes.ContactInfo;
import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.Classes.SectionsStatePagerAdapter_WLR;
import ca.i3th.capstonii.Classes.UserRegister;
import ca.i3th.capstonii.Fragments.WLR.Congratulation;
import ca.i3th.capstonii.Fragments.WLR.Login;
import ca.i3th.capstonii.Fragments.WLR.Register_1;
import ca.i3th.capstonii.Fragments.WLR.Register_2;
import ca.i3th.capstonii.Fragments.WLR.Register_3;
import ca.i3th.capstonii.Fragments.WLR.Welcome;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsStatePagerAdapter_WLR sectionsStatePagerAdapter_WLR;
    private ViewPager viewPager;
    private UserRegister userRegister;
    private ProgressDialog progressDialog;
    public static String TEXT;
    private boolean isUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started");

        // Background
        ConstraintLayout constraintLayout = findViewById(R.id.backG);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();
        // Background end

        sectionsStatePagerAdapter_WLR = new SectionsStatePagerAdapter_WLR(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

    }

    // Setup the Pager
    private void setupViewPager(ViewPager viewPager) {

        SectionsStatePagerAdapter_WLR adapter_WLR = new SectionsStatePagerAdapter_WLR(getSupportFragmentManager());
        adapter_WLR.addFragment(new Welcome(), "Welcome_Fragment");
        adapter_WLR.addFragment(new Register_1(), "Register_1_Fragment");
        adapter_WLR.addFragment(new Register_2(), "Register_2_Fragment");
        adapter_WLR.addFragment(new Register_3(), "Register_3_Fragment");
        adapter_WLR.addFragment(new Congratulation(), "Congratulation_Fragment");
        adapter_WLR.addFragment(new Login(), "Login_Fragment");
        viewPager.setAdapter(adapter_WLR);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
//        try {
//            Log.d(TAG, "setViewPager:M try-------------------> " + this.userRegister.toString());
//        }
//        catch (Exception e) {
//            Log.d(TAG, "setViewPager:M catch -------------------> " + e.getMessage().toString());
//        }

    }

    public void setUser(int option, UserRegister ur) {
        switch (option) {
            case 1: //set the username
//                Log.d(TAG, "setUser:M case 1-------------------> " + ur.getUsername());
                this.userRegister = new UserRegister(ur.getUsername());
//                this.userRegister.setUsername(ur.getUsername());
                break;
            case 2: //set the password and set identity for user
                this.userRegister.setPassword(ur.getPassword());
                this.userRegister.setFirstName(ur.getFirstName());
                this.userRegister.setLastName(ur.getLastName());
                break;
            case 3://set contact info
                this.userRegister.setContactInfo(ur.getContactInfo());
                break;
            default:
                break;
        }
    }

    public String getUserName() {
        return this.userRegister.getUsername();
    }


    //////////////////////////Register Volly
    public void registerUser() {

        try {
            progressDialog.setMessage("Registering user..");
            progressDialog.show();
        } catch (Exception e) {
            Log.d(TAG, "registerUser: --------------->" + e.getMessage());
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        } catch (Exception e) {
                            Log.d(TAG, "onResponse: --------------->" + e.getMessage());
                        }


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString
                                    ("message"), Toast.LENGTH_LONG).show();


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
                        } catch (Exception e) {
                            Log.d(TAG, "Response.ErrorListener: --------------->" + e.getMessage());
                        }

                        Toast.makeText(getApplicationContext(), error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", userRegister.getUsername());
                params.put("userpass", userRegister.getPassword());
                params.put("fname", userRegister.getFirstName());
                params.put("lname", userRegister.getLastName());
                params.put("country", userRegister.getContactInfo().getCountry());
                params.put("region", userRegister.getContactInfo().getRegion());
                params.put("city", userRegister.getContactInfo().getCity());
                params.put("street", userRegister.getContactInfo().getStreet());
                params.put("buldingNumber", String.valueOf(userRegister.getContactInfo().getBuldingNum()));
                params.put("unit", String.valueOf(userRegister.getContactInfo().getUnit()));
                params.put("tel", "0");
                params.put("postalCode", String.valueOf(userRegister.getContactInfo().getPostalCode()));

                Log.d("msg", "params -------------------->" + params);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("accept", "application/json");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Log.d("msg", "stringRequest.getBodyContentType()--------------------" + stringRequest.getBodyContentType());
        requestQueue.add(stringRequest);
    }

    ///auth
    public void authUser(String e, String p){
        final String
                email = e,
                pass = p;

        //Log.d("msg", "-1------------> " + e + p);

    try {
        progressDialog.setMessage("Login user..");
        progressDialog.show();
    }
    catch(Exception e1) {
        Log.d(TAG, "authUser: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AUTH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "authUser: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("message"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("db_R_ID"));
                            if (jsonObject.getString("error").equals("false")){

//                                Log.d("msg", "-2---1-----1----> TestPASS" );

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                i.putExtra("db_R_ID", jsonObject.getString("db_R_ID"));
                                Log.d("msg", "-2---1-----2----> " + jsonObject.getString("db_R_ID"));
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("db_R_ID"));
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
                            Log.d(TAG, "authUser: ---->" + e1);
                        }
                        Toast.makeText(getApplicationContext(), error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", email);
                params.put("userpass", pass);
                params.put("option", "0");



                Log.d("msg" ,"--------------------"+ params);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        requestQueue.add(stringRequest);
        Log.d("msg" ,">>>--------------------"+ stringRequest.getBodyContentType());
    }

    ///check user
    public boolean checkUser(String email){
        final String username = email.trim();

        try {
            progressDialog.setMessage("checking the user...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "checkUser: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AUTH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "checkUser: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("message"));

                            if (jsonObject.getString("error").equals("false")){
//                                Log.d("msg", "-2---1-----1----> TestPASS" );
                                    isUser = true;
                                Log.d("msg", "-2---1-----2----> " + jsonObject.getString("error" + ": " + "message"));
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
                            Log.d(TAG, "checkUser: ---->" + e1);
                        }
                        Log.d(TAG, "checkUser: ---->" + error.getMessage());
                    }
                })
        {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("option", "1");
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"checkUser>>>--------------------"+ stringRequest.getBodyContentType());
        return isUser;
    }
        
}
