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
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

public class Congratulation extends Fragment {

    private static final String TAG = "Congratulation_Fragment";
    private Button btnFinish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.congratulation_fragment, container, false);
        // Background
        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_CF);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Background end
        btnFinish = (Button) view.findViewById(R.id.finish_btn);


        Log.d(TAG, "OnCreateView: Started.");


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Please Check your email.", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        return view;
    }
}
