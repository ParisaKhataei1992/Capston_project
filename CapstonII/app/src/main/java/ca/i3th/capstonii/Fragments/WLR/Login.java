package ca.i3th.capstonii.Fragments.WLR;

import android.content.Intent;
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

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

public class Login extends Fragment {

    private static final String TAG = "Login_Fragment";
    private Button btnLogin;
    private EditText edUsername, edPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        // Background
        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_LF);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Background end

        btnLogin = view.findViewById(R.id.login_btn);
        edUsername = view.findViewById(R.id.username_ed);
        edPassword = view.findViewById(R.id.password_ed);

        Log.d(TAG, "OnCreateView: Started.");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "><><><><><<><><><>"+edUsername.getText());
                if (!(edUsername.getText().toString()).equals("") && !(edPassword.getText().toString()).equals("")) {
//                    Toast.makeText(getActivity(), "Direct to Home.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), HomeActivity.class);
//                    startActivity(intent);
                    ((MainActivity)getActivity()).authUser(
                            edUsername.getText().toString().trim(),
                            edPassword.getText().toString().trim());
                }
                else {
                    Toast.makeText(getActivity(), "Credential is wrong.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }
}
