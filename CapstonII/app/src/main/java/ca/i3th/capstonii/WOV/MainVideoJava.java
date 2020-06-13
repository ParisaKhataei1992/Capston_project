package ca.i3th.capstonii.WOV;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ca.i3th.capstonii.Classes.HTTPC.Constants;
import ca.i3th.capstonii.R;

public class MainVideoJava extends AppCompatActivity {
    public static String TAG;
    private Button btnBack;
    private TextView textView, textView2;
    private String videoPath;
    private int btn;
    private String sex;
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    private String id, sT, eT, cDate, col;
    private String activityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        btnBack = (Button) findViewById(R.id.btnBack);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        sT = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                Calendar.getInstance().get(Calendar.MINUTE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackMaActivity();
            }
        });
        Intent intent = getIntent();
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");
        btn = intent.getIntExtra("btn", 0);
        sex = intent.getStringExtra("sex");
        id = intent.getStringExtra("db_R_ID");
        Log.d(TAG, "onCreate: ----------------btn+sex---->" + id);

        VideoView videoView;
        videoView = findViewById(R.id.video_view);

        switch (btn) {
            case 1:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.back_m;
                textView.setText(R.string.descr_t);
                textView2.setText(R.string.Title_descr_t);
                activityTitle = textView2.getText().toString();
                break;
            case 2:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.glutes_m;
                textView.setText(R.string.glutes_m);
                textView2.setText(R.string.Title_glutes_m);
                activityTitle = textView2.getText().toString();
                break;

            case 5:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.forearm_m;
                textView.setText(R.string.forearm_m);
                textView2.setText(R.string.Title_forearm_m);
                activityTitle = textView2.getText().toString();
                break;
            case 6:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.treceps_m;
                textView.setText(R.string.triceps_m);
                textView2.setText(R.string.Title_triceps_m);
                activityTitle = textView2.getText().toString();
                break;

            case 8:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.core_m;
                textView.setText(R.string.core_m);
                textView2.setText(R.string.Title_core_m);
                activityTitle = textView2.getText().toString();
                break;
            case 9:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.shoulders_m;
                textView.setText(R.string.shoulders_m);
                textView2.setText(R.string.Title_shoulders_m);
                activityTitle = textView2.getText().toString();
                break;
            case 10:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.chest_m;
                textView.setText(R.string.chest_m);
                textView2.setText(R.string.Title_chest_m);
                activityTitle = textView2.getText().toString();
                break;
            case 11:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.backf;
                textView.setText(R.string.descr_t);
                textView2.setText(R.string.Title_descr_t);
                activityTitle = textView2.getText().toString();
                break;
            case 12:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.glutesf;
                textView.setText(R.string.glutesf);
                textView2.setText(R.string.Title_glutesf);
                activityTitle = textView2.getText().toString();
                break;
            case 13:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.legs_f;
                textView.setText(R.string.legs_f);
                textView2.setText(R.string.Title_legs_f);
                activityTitle = textView2.getText().toString();
                break;
            case 14:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.legs_f;
                textView.setText(R.string.legs_f);
                textView2.setText(R.string.Title_legs_f);
                activityTitle = textView2.getText().toString();
                break;
            case 15:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.forearm_f;
                textView.setText(R.string.forearm_f);
                textView2.setText(R.string.Title_forearm_f);
                activityTitle = textView2.getText().toString();
                break;
            case 16:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.triceps_f;
                textView.setText(R.string.triceps_f);
                textView2.setText(R.string.Title_triceps_f);
                activityTitle = textView2.getText().toString();
                break;

            case 18:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.core_f;
                textView.setText(R.string.core_f);
                textView2.setText(R.string.Title_core_f);
                activityTitle = textView2.getText().toString();
                break;
            case 19:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.shoulders_f;
                textView.setText(R.string.shoulder_f);
                textView2.setText(R.string.Title_shoulder_f);
                activityTitle = textView2.getText().toString();
                break;
            case 20:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.chest_f;
                textView.setText(R.string.chest_f);
                textView2.setText(R.string.Title_chest_f);
                activityTitle = textView2.getText().toString();
                break;
            default:
                Log.d(TAG, "onCreate: ---------------> btn is null");
                break;
        }
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.back_m;
            //  String videoPath ="android.resource://" +getPackageName() +"/" +R.raw.backf;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.start();
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
        }

    public void openBackMaActivity() {

        eT = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                Calendar.getInstance().get(Calendar.MINUTE);
        cDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        insertActivityC(id);
        if (sex.equals("1")) {
            Intent intent = new Intent(this, VideoButtonsMale.class);
            intent.putExtra("sex", sex);
            intent.putExtra("fullName", fullName);
            intent.putExtra("contact", contact);
            intent.putExtra("bodyInfo", bodyInfo);
            intent.putExtra("db_R_ID", id);
            startActivity(intent);
        }
        else if (sex.equals("0")) {
            Intent intent = new Intent(this, VideoButtonsFemale.class);
            intent.putExtra("sex", sex);
            intent.putExtra("fullName", fullName);
            intent.putExtra("contact", contact);
            intent.putExtra("bodyInfo", bodyInfo);
            intent.putExtra("db_R_ID", id);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, VideoButtonsMale.class);
            intent.putExtra("sex", sex);
            intent.putExtra("fullName", fullName);
            intent.putExtra("contact", contact);
            intent.putExtra("bodyInfo", bodyInfo);
            intent.putExtra("db_R_ID", id);
            startActivity(intent);
        }
    }

    //insert Activity Cardio
    public void insertActivityC(String idP){
        final String userID = idP.trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_S_ACTIVITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("msg", "-2---1-----1----> " + jsonObject.getString("error"));
                            Log.d("msg", "-2---1-----2----> " + jsonObject.getString("message"));


                            if (jsonObject.getString("error").equals("false")){
                                Log.d("msg", "insertActivityC-2---1-----1----> TestPASS" );

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

                        Log.d(TAG, "insertActivityC: ---->" + error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", userID);
                params.put("type", "Cardio");
                params.put("startTime", sT);
                params.put("endTime", eT);
                params.put("speed", "0");
                params.put("distance", "0");
                params.put("longitude", activityTitle);
                params.put("latitude", "0");
                params.put("col", "31");
                params.put("date", cDate);
                Log.d("msg" ,"--------------------"+ params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("msg" ,"insertActivityC>>>--------------------"+ stringRequest.getBodyContentType());

    }
}