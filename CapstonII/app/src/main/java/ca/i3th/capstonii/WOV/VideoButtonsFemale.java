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

import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;
import ca.i3th.capstonii.WorkoutActivity;

import static android.view.View.INVISIBLE;

public class VideoButtonsFemale extends AppCompatActivity {
    private String sex = "0", id;
    private static String TAG = "VideoButtonsFemale";
    private String[] fullName = new String[2];
    private String[] contact = new String[5];
    private String[] bodyInfo = new String[4];
    private Button btnBackActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_female);

        btnBackActivity = findViewById(R.id.btn_back_activity);
        btnBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoButtonsFemale.this, WorkoutActivity.class);
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
        Log.d(TAG, "onCreate: -----------------------> " + sex);

        Button btn1 ;
        btn1=(Button)findViewById(R.id.btn_back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  11);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn2 ;
        btn2=(Button)findViewById(R.id.btnglutesf);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  12);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn3 ;
        btn3=(Button)findViewById(R.id.btn_calves);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  13);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn4 ;
        btn4=(Button)findViewById(R.id.btn_legs);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  14);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn5 ;
        btn5=(Button)findViewById(R.id.btn_forearm);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  15);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn6 ;
        btn6=(Button)findViewById(R.id.btn_triceps);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  16);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn7 ;
        btn7=(Button)findViewById(R.id.btn_biceps);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(VideoButtonsFemale.this, "Coming soon ...", Toast.LENGTH_SHORT).show();
            }


        });
        Button btn8 ;
        btn8=(Button)findViewById(R.id.btn_core);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  18);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn9 ;
        btn9=(Button)findViewById(R.id.btn_shoulders);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  19);
                open.putExtra("sex" ,  sex);
                open.putExtra("fullName", fullName);
                open.putExtra("contact", contact);
                open.putExtra("bodyInfo", bodyInfo);
                open.putExtra("db_R_ID", id);
                startActivity(open);
            }


        });
        Button btn10 ;
        btn10=(Button)findViewById(R.id.btn_chest);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(VideoButtonsFemale.this,MainVideoJava.class);
                open.putExtra("btn" ,  20);
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
