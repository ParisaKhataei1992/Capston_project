package ca.i3th.capstonii.Classes.HTTPC;

import android.app.ProgressDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Registartion {
//    private ProgressDialog progressDialog;
//    private void registerUser(){
//        final String fname = ed_fn.getText().toString().trim(),
//                lname = ed_ln.getText().toString().trim(),
//                email = ed_email.getText().toString().trim(),
//                pass = ed_p.getText().toString().trim(),
//                cpass = ed_cp.getText().toString().trim(),
//                country = ed_co.getText().toString().trim(),
//                city = ed_ci.getText().toString().trim(),
//                street = ed_s.getText().toString().trim(),
//                tel = ed_t.getText().toString().trim();
//
//
//        progressDialog.setMessage("Registering user..");
//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//
////                            Toast.makeText(getApplicationContext(), jsonObject.getString
////                                    ("message"), Toast.LENGTH_LONG).show();
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.hide();
////                        Toast.makeText(getApplicationContext(), error.getMessage(),
////                                Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("fname", fname);
//                params.put("lname", lname);
//                params.put("email", email);
//                params.put("password", pass);
//                params.put("country", country);
//                params.put("city", city);
//                params.put("street", street);
//                params.put("tel", tel);
//
//
//                //Log.d("msg" ,"--------------------"+ params);
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<>();
//                params.put("accept", "application/json");
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //Log.d("msg" ,"--------------------"+ stringRequest.getBodyContentType());
//        requestQueue.add(stringRequest);
//    }
}
