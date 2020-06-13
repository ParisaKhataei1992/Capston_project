package ca.i3th.capstonii.WOV;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ca.i3th.capstonii.R;
import ca.i3th.capstonii.WorkoutActivity;

public class VideoButtonsMale extends AppCompatActivity {
    private String sex = "1", id;
    private static String TAG = "VideoButtonsFemale";
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    private Button btnBackActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_male);

        btnBackActivity = findViewById(R.id.btn_back_activity);
        btnBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoButtonsMale.this, WorkoutActivity.class);
                startActivity(intent);
                intent.putExtra("sex" ,  sex);
                intent.putExtra("fullName", fullName);
                intent.putExtra("contact", contact);
                intent.putExtra("bodyInfo", bodyInfo);
                intent.putExtra("db_R_ID", id);
            }
        });
        Intent intent = getIntent();
        fullName = intent.getStringArrayExtra("fullName");
        contact = intent.getStringArrayExtra("contact");
        bodyInfo = intent.getStringArrayExtra("bodyInfo");
        sex = bodyInfo[3];
        id = intent.getStringExtra("db_R_ID");
        Log.d(TAG, "onCreate: ------------id-----------> " + id);
        Button btn1 ;
        btn1=(Button)findViewById(R.id.btn_back_m);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this, MainVideoJava.class);
                open.putExtra("btn" ,  1);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn2 ;
        btn2=(Button)findViewById(R.id.btn_glutes_m);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  2);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn3 ;
        btn3=(Button)findViewById(R.id.btn_claves_m);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(VideoButtonsMale.this, "Coming soon ...", Toast.LENGTH_SHORT).show();
            }

        });
        Button btn4 ;
        btn4=(Button)findViewById(R.id.btn_upperleg_m);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(VideoButtonsMale.this, "Coming soon ...", Toast.LENGTH_SHORT).show();
            }


        });
        Button btn5 ;
        btn5=(Button)findViewById(R.id.btn_forearm_n);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  5);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn6 ;
        btn6=(Button)findViewById(R.id.btn_triceps_m);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  6);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn7 ;
        btn7=(Button)findViewById(R.id.btn_biceps_m);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(VideoButtonsMale.this, "Coming soon ...", Toast.LENGTH_SHORT).show();
            }


        });
        Button btn8 ;
        btn8=(Button)findViewById(R.id.btn_core_m);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  8);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn9 ;
        btn9=(Button)findViewById(R.id.btn_shoulders_m);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  9);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("sex" ,  sex);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }
        });
        Button btn10 ;
        btn10=(Button)findViewById(R.id.btn_chest_m);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsMale.this,MainVideoJava.class);
                open.putExtra("btn" ,  10);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });


    }
}
