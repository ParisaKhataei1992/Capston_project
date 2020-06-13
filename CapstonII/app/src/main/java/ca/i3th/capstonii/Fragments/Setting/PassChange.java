package ca.i3th.capstonii.Fragments.Setting;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.R;
import ca.i3th.capstonii.SettingActivity;

public class PassChange extends Fragment {
    private static final String TAG = "PassChange_Fragment";
    EditText oldPass, newPass, reNewPass;
    Button btnPassChange;
    private boolean chkPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.passchange_fragment, container, false);

        oldPass = view.findViewById(R.id.oldPass);
        newPass = view.findViewById(R.id.newPass);
        reNewPass = view.findViewById(R.id.confirmPass);
        btnPassChange = view.findViewById(R.id.btnPassUpdate);

        btnPassChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    if (((SettingActivity)getActivity()).checkPassword(((SettingActivity)getActivity()).getId(), oldPass.getText().toString().trim())) {
                        ((SettingActivity)getActivity()).updatePassword(((SettingActivity)getActivity()).getId(), newPass.getText().toString().trim());
                    }
                    else {
                        Log.d(TAG, "onClick: change password ----------else---------xxxxxxxxxxxx>");
                        Toast.makeText(getActivity(), "It is Not Correct ", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "It is Not Correct ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private boolean checkValidation() {
        boolean flag = false;

    // check not none of them empty
        if (TextUtils.isEmpty(oldPass.getText().toString())) {
            oldPass.setHintTextColor( Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(newPass.getText().toString())) {
            newPass.setHintTextColor( Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(reNewPass.getText().toString())) {
            reNewPass.setHintTextColor( Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }

        //match new pass with renew
        if (!(newPass.getText().toString()).equals(reNewPass.getText().toString())) {
            newPass.setHintTextColor( Color.RED);
            reNewPass.setHintTextColor( Color.RED);
            Toast.makeText(getActivity(), "Passwords are NOT Matches", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        return true;
    }
}
