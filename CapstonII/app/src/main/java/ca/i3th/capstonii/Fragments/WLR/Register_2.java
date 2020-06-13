package ca.i3th.capstonii.Fragments.WLR;

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

import ca.i3th.capstonii.Classes.UserRegister;
import ca.i3th.capstonii.MainActivity;
import ca.i3th.capstonii.R;

public class Register_2 extends Fragment {

    private static final String TAG = "Register_02_Fragment";
    private Button btnNext;
    private EditText edPass, edRepass, edFname, edLname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_02, container, false);

        // Background
        RelativeLayout relativeLayout = view.findViewById(R.id.bgGradient_RF2);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Background end


        btnNext = view.findViewById(R.id.nextR_2_btn);
        edPass = view.findViewById(R.id.password_ed);
        edRepass = view.findViewById(R.id.repassword_ed);
        edFname = view.findViewById(R.id.fName_ed);
        edLname = view.findViewById(R.id.lName_ed);

        Log.d(TAG, "OnCreateView: Started.");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (CheckTextFiles()) {
                    case 1:
                            UserRegister userRegister = new UserRegister(((MainActivity) getActivity()).getUserName());
                            userRegister.setPassword(edPass.getText().toString().trim());
                            userRegister.setFirstName(edFname.getText().toString().trim());
                            userRegister.setLastName(edLname.getText().toString().trim());
                            ((MainActivity) getActivity()).setUser(2, userRegister);
                            ((MainActivity) getActivity()).setViewPager(3);
                        break;
                    case 2:
                        Toast.makeText(getContext(), "Passwords are NOT Match!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        edPass.setHintTextColor( Color.RED);
                        edRepass.setHintTextColor( Color.RED);
                        Toast.makeText(getContext(), "TextBox Should NOT be Empty!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        edFname.setHintTextColor( Color.RED);
                        edLname.setHintTextColor( Color.RED);
                        Toast.makeText(getContext(), "TextBox Should NOT be Empty!!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
    private int CheckTextFiles() {
        int flag = 1;

        if (!(edPass.getText().toString()).equals(edRepass.getText().toString())) {
            return 2;
        }
        if (TextUtils.isEmpty(edPass.getText()) && TextUtils.isEmpty(edRepass.getText())) {
            return 3;
        }
        if (TextUtils.isEmpty(edFname.getText()) && TextUtils.isEmpty(edLname.getText())) {
            return 4;
        }

        return  flag;
    }
}


