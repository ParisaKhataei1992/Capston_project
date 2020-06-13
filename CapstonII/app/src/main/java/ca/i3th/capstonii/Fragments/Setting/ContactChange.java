package ca.i3th.capstonii.Fragments.Setting;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.R;
import ca.i3th.capstonii.SettingActivity;

public class ContactChange extends Fragment {
    private static final String TAG = "ContactChange_Fragment";
    EditText email, country, region, city, postalCode;
    Button btnContactChange;
    private String[] contact = new String[5];
    private String[] msgContactError = new String[2];
    private int selectionPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contactchange_fragment, container, false);

        email = view.findViewById(R.id.emailChange);
        country = view.findViewById(R.id.countryChange);
        region = view.findViewById(R.id.regionChange);
        city = view.findViewById(R.id.cityChange);
        postalCode = view.findViewById(R.id.postalCodeChange);
        btnContactChange = view.findViewById(R.id.btnContentUpdate);

        getClientCache();

        btnContactChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkContact()){
                checkData();
                setServerUpdate();
                getServerUpdate();
                setClientCache();
            }
                else {
                    Toast.makeText(getActivity(), "It is Not Correct ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    //get Contact Information form Home Activity by Setting Activity
    private void getClientCache() {
        contact = ((SettingActivity) getActivity()).getContact();
        fillData();
        ((SettingActivity) getActivity()).setContact(contact);
    }

    //set Contact Information for Setting Activity
    private void setClientCache() {
        ((SettingActivity) getActivity()).setContact(contact);

    }

    //get Contact Information form Server by Home Activity
    private void getServerUpdate() {
        contact = ((SettingActivity) getActivity()).getContact(((SettingActivity) getActivity()).getId());
        fillData();
    }

    // update Server
    private void setServerUpdate() {
        //  msgErrorBodyInfo = ((SettingActivity)getActivity()).getMsgError();

        //////////////////////////////////////////////
        ((SettingActivity) getActivity()).updateContact(((SettingActivity) getActivity()).getId(),
                contact);
        //////
        msgContactError = ((SettingActivity) getActivity()).getMsgError();

        Log.d(TAG, "setServerUpdate: ContactInfo---------> " + msgContactError[0] + " " + msgContactError[1]);
    }

    private void checkData() {
        if (email.getText().toString().equals("")) {
            contact[0] = email.getHint().toString().trim();
        } else {
            contact[0] = email.getText().toString().trim();
        }
        if (country.getText().toString().equals("")) {
            contact[1] = country.getHint().toString().trim();
        } else {
            contact[1] = country.getText().toString().trim();
        }
        if (region.getText().toString().equals("")) {
            contact[2] = region.getHint().toString().trim();
        } else {
            contact[2] = region.getText().toString().trim();
        }
        if (city.getText().toString().equals("")) {
            contact[3] = city.getHint().toString().trim();
        } else {
            contact[3] = city.getText().toString().trim();
        }
        if (postalCode.getText().toString().equals("")) {
            contact[4] = postalCode.getHint().toString().trim();
        } else {
            contact[4] = postalCode.getText().toString().trim();
        }
    }

    private void fillData() {

        email.setText("");
        country.setText("");
        region.setText("");
        city.setText("");
        postalCode.setText("");

        email.setHint(contact[0]);
        country.setHint(contact[1]);
        region.setHint(contact[2]);
        city.setHint(contact[3]);
        postalCode.setHint(contact[4]);


        Log.d(TAG, "fillData: +++++++++++++++++++++ >" + selectionPosition + " " + contact[4]);


    }

    private boolean checkContact() {
        boolean flag = false;

        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setHintTextColor(Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(country.getText().toString())) {
            country.setHintTextColor(Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(region.getText().toString())) {
            region.setHintTextColor(Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(city.getText().toString())) {
            city.setHintTextColor(Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        if (TextUtils.isEmpty(postalCode.getText().toString())) {
            postalCode.setHintTextColor(Color.RED);
            Toast.makeText(getActivity(), "Please fill out the container!!", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            flag = true;
        }
        return  true;
    }

}