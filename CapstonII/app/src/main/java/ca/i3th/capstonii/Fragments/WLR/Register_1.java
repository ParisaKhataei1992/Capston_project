package ca.i3th.capstonii.Fragments.WLR;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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

import ca.i3th.capstonii.Classes.UserRegister;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

public class Register_1 extends Fragment {

    private static final String TAG = "Register_01_Fragment";
    private Button btnNext;
    private EditText edEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_01, container, false);

        // Background
        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_RF1);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Background end
        btnNext = view.findViewById(R.id.nextR_1_btn);
        edEmail = view.findViewById(R.id.email_ed);

        Log.d(TAG, "OnCreateView: Started.");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String email = edEmail.getText().toString().trim();

                if ((edEmail.getText().toString()).equals("") || (edEmail.getText().toString()).equals(null)) {
                    edEmail .setHintTextColor( Color.RED);
                    Toast.makeText(getContext(), "Filled should NOT be Empty!!!", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        if (!email.matches(emailPattern)) {
                            Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    } else {
                            if (!((MainActivity) getActivity()).checkUser(edEmail.getText().toString().trim())) {

                                Toast.makeText(getContext(), "The User is Already Exist!!", Toast.LENGTH_SHORT).show();
                        }
                         else {

                            UserRegister userRegister = new UserRegister(edEmail.getText().toString().trim());
                            ((MainActivity) getActivity()).setUser(1, userRegister);
                            ((MainActivity) getActivity()).setViewPager(2);
                        }
                    }
                }
                            }
        });
        return view;

    }
}