package ca.i3th.capstonii.Fragments.WLR;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

public class Welcome extends Fragment {

    private static final String TAG = "Welcome_Fragment";
    private Button btnSignin, btnSignup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);

//        // Background
//        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_WF);
//        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(2000);
//        animationDrawable.setExitFadeDuration(4000);
//        animationDrawable.start();
//        // Background end

        btnSignin = (Button) view.findViewById(R.id.signin_btn);
        btnSignup = (Button) view.findViewById(R.id.signup_btn);

        Log.d(TAG, "OnCreateView: Started.");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Direct to Login.", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(5);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Direct to Register 1.", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).setViewPager(1);

            }
        });
        return view;
    }
}
