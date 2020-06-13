package ca.i3th.capstonii.Fragments.WLR;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.Classes.ContactInfo;
import ca.i3th.capstonii.Classes.UserRegister;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

import static android.widget.Toast.*;

public class Register_3 extends Fragment {

    private static final String TAG = "Register_03_Fragment";
    private Button btnNext;
    private EditText edCountry, edRegion, edCity, edPCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_03, container, false);
        // Background
        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_RF3);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Background end

        btnNext = (Button) view.findViewById(R.id.nextR_3_btn);
        edCountry = view.findViewById(R.id.country);
        edRegion = view.findViewById(R.id.region);
        edCity = view.findViewById(R.id.city);
        edPCode = view.findViewById(R.id.pCode);

        Log.d(TAG, "OnCreateView: Started.");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckTextFiles()) {
                    ContactInfo contactInfo = new ContactInfo(
                            edCountry.getText().toString().trim(),
                            edRegion.getText().toString().trim(),
                            edCity.getText().toString().trim(),
                            edPCode.getText().toString().trim());

                    UserRegister userRegister = new UserRegister(((MainActivity) getActivity()).getUserName());
                    userRegister.setContactInfo(contactInfo);
                    ((MainActivity) getActivity()).setUser(3, userRegister);
                    ((MainActivity) getActivity()).registerUser();
                    ((MainActivity) getActivity()).setViewPager(4);
                }
                else {
                    Toast.makeText(getActivity(), "Please Fill out your Information", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private boolean CheckTextFiles() {
        boolean flag = false;

        if (TextUtils.isEmpty(edCountry.getText().toString())) {
            edCountry.setHintTextColor( Color.RED);
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(edRegion.getText().toString())) {
            edRegion.setHintTextColor( Color.RED);
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(edCity.getText().toString())) {
            edCity.setHintTextColor( Color.RED);
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(edPCode.getText().toString())) {
            edPCode.setHintTextColor( Color.RED);
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

}


