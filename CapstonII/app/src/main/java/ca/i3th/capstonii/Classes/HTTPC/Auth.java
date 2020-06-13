package ca.i3th.capstonii.Classes.HTTPC;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.MainActivity;

public class Auth {
    private int id;
    public Auth (int id) {
        this.id = id;
    }
    public void authUser(String e, String p){
        final String
                email = e,
                pass = p;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AUTH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("message"));
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("db_R_ID"));
                            if (jsonObject.getString("error").equals("false")){

                                Log.d("msg", "-2---1-----2----> " + jsonObject.getString("db_R_ID"));

                            }
                            else {

                                Log.d("msg", "-2---1-----3----> " + jsonObject.getString("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


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

//        RequestQueue requestQueue = Volley.newRequestQueue();


//        requestQueue.add(stringRequest);
        Log.d("msg" ,">>>--------------------"+ stringRequest.getBodyContentType());
    }
}
