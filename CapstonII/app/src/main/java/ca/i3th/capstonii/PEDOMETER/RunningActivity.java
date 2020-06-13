package ca.i3th.capstonii.PEDOMETER;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.R;

public class RunningActivity extends AppCompatActivity {
    //    public static String TAG;
    private TextView step, data;
    private  double magnitudePrv = 0;
    private int stepCounter = 0, weightKg = 56;
    private Button start, stop;
    private double speed, distance;
    private String id, sT, eT, cDate, col;
    ///////////////////////////////////////////
    private final static String TAG = "StepDetector";
    private float   mLimit = 10;
    private float   mLastValues[] = new float[3*2];
    private float   mScale[] = new float[2];
    private float   mYOffset;

    private float   mLastDirections[] = new float[3*2];
    private float   mLastExtremes[][] = { new float[3*2], new float[3*2] };
    private float   mLastDiff[] = new float[3*2];
    private int     mLastMatch = -1;
    ////////////////////////////////////////
    private SensorManager sensorManager;
    private Sensor sensor;
    private ProgressDialog progressDialog;
    /////////////////////////////////////
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Intent intent = getIntent();
        id = intent.getStringExtra("db_R_ID");
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");

        step = findViewById(R.id.step);
        data = findViewById(R.id.pout);
        start = findViewById(R.id.btnStartR);
        stop = findViewById(R.id.btnStopR);


        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ******************" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                         Calendar.getInstance().get(Calendar.MINUTE));
                sT = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                        Calendar.getInstance().get(Calendar.MINUTE);
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                SensorEventListener sensorEventListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        if (event != null) {
                            float x_acceleration = event.values[0];
                            float y_acceleration = event.values[1];
                            float z_acceleration = event.values[2];

//                    Log.d(TAG, "onSensorChanged: x -------------> " + Math.sqrt(x_acceleration));
//                    Log.d(TAG, "onSensorChanged: y -------------> " + Math.sqrt(y_acceleration));
//                    Log.d(TAG, "onSensorChanged: z -------------> " + Math.sqrt(z_acceleration));


                            double magnitude = Math.sqrt((x_acceleration * x_acceleration) + (y_acceleration * y_acceleration) +
                                    (z_acceleration * z_acceleration));
                            double magnitudeDelta = magnitude - magnitudePrv;
                            magnitudePrv = magnitude;
//                    Log.d(TAG, "onSensorChanged: magnitude ---------->" + magnitude);
//                    Log.d(TAG, "onSensorChanged: magnitudePrv ---------->" + magnitudePrv);
                            if (magnitudeDelta > 6) {
                                stepCounter++;
                                Log.d(TAG, "onSensorChanged: stepCounter ---------->" + stepCounter);
                            }

                            step.setText(String.valueOf(stepCounter));
//                            data.setText("0" + "     " + "0" + "     " + "0" + "     ");
                            speed = 0.2;
                            distance = (stepCounter * 0.4);

                            col = String.format("%.2f",(speed * weightKg / 400));
                            Log.d(TAG, "onSensorChanged: COOOOOOOOOOOOOOOOOOOOOOl" + col);

                        }

                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {

                    }
                };

                sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                magnitudePrv = 0;
                eT = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                        Calendar.getInstance().get(Calendar.MINUTE);
                cDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Log.d(TAG, "onClick: &&&&&&&&&&&&&&& : " + cDate);
                data.setText(speed + "                 " + distance + "                  " + col );
                insertActivityR(id);
            }
        });

    }

    //insert Activity running
    public void insertActivityR(final String idP){
        final String userID = idP.trim();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
        catch(Exception e) {
            Log.d(TAG, "insertActivityR: ---->" + e);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_ACTIVITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressDialog.dismiss();
                        }
                        catch(Exception e1) {
                            Log.d(TAG, "insertActivityR: ---->" + e1);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "-2---1-----1----> TestPASS" );

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
                            Log.d(TAG, "insertActivityR: ---->" + e1);
                        }
                        Log.d(TAG, "insertActivityR: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", userID);
                params.put("type", "Running");
                params.put("startTime", sT);
                params.put("endTime", eT);
                params.put("speed", String.valueOf(speed));
                params.put("distance", String.valueOf(distance));
                params.put("longitude", "0");
                params.put("latitude", "0");
                params.put("col", String.valueOf(col));
                params.put("date", cDate);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"updateFullName>>>--------------------"+ stringRequest.getBodyContentType());

    }
}
