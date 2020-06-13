package ca.i3th.capstonii.Fragments.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.R;
import ca.i3th.capstonii.SettingActivity;

public class ClientInfoChange extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ClientInfoChange_Fragment";
    EditText edFName, edLName, edHeight, edWeight;
    Button btnUpdateClient;
    private String[] fullName = new String[2];
    private String[] bodyInfo = new String[4];
    private String[] msgErrorBodyInfo, msgErrorFullName = new String[2];
    private RadioGroup radioSexGroup;
    private RadioButton radioSexMale, radioSexFemale, radioSexActive;
    private View viewP;
    private Spinner spinnerBT;
    private String spinnerBTText;
    private ArrayAdapter<String> spinnerAdapter;
    private int selectionPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clientinfochange_fragment, container, false);
        viewP = view;

        edFName = view.findViewById(R.id.edFName);
        edLName = view.findViewById(R.id.edLName);
        edHeight = view.findViewById(R.id.edHeight);
        edWeight = view.findViewById(R.id.edWeight);
        radioSexGroup = view.findViewById(R.id.radioSex);
        radioSexMale = view.findViewById(R.id.radioMale);
        radioSexFemale = view.findViewById(R.id.radioFemale);
        spinnerBT = view.findViewById(R.id.spinnerBodyType);
        btnUpdateClient = view.findViewById(R.id.btnUpdateClient);

        spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinnerBodyTypeItems));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBT.setAdapter(spinnerAdapter);
        spinnerBT.setOnItemSelectedListener(this);

        getClientCache();

        btnUpdateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                setServerUpdate ();
                getServerUpdate();
                setClientCache();
            }
        });
        return view;
    }

    //get fullName and bodyInfo form Home Activity by Setting Activity
    private void getClientCache() {
        fullName = ((SettingActivity)getActivity()).getFullName();
        bodyInfo = ((SettingActivity)getActivity()).getBodyInfo();
        fillData();
        //
        ((SettingActivity)getActivity()).setFullName(fullName);
        ((SettingActivity)getActivity()).setBodyInfo(bodyInfo);
    }
    //set fullName and bodyInfo for Setting Activity
    private void setClientCache() {
        ((SettingActivity)getActivity()).setFullName(fullName);
        ((SettingActivity)getActivity()).setBodyInfo(bodyInfo);
    }
    //get fullName and bodyInfo form Server by Home Activity
    private void getServerUpdate() {
//        Log.d(TAG, "getServerUpdate: ----------->>>>>>>>>>>" + ((SettingActivity)getActivity()).getId());
        fullName = ((SettingActivity)getActivity()).getClient(((SettingActivity)getActivity()).getId());
        bodyInfo = ((SettingActivity)getActivity()).getBodyInfo(((SettingActivity)getActivity()).getId());
        fillData();
    }
    // update Server
    private void setServerUpdate () {
        ((SettingActivity)getActivity()).updateBodyInfo(((SettingActivity)getActivity()).getId(),
                bodyInfo);
        //////
        msgErrorBodyInfo = ((SettingActivity)getActivity()).getMsgError();

        //////////////////////////////////////////////
        ((SettingActivity)getActivity()).updateFullName(((SettingActivity)getActivity()).getId(),
                fullName);
        //////
        msgErrorFullName = ((SettingActivity)getActivity()).getMsgError();

        Log.d(TAG, "setServerUpdate: BodyInfo---------> " + msgErrorBodyInfo[0] + " " + msgErrorBodyInfo[1]);
        Log.d(TAG, "setServerUpdate: FullName---------> " + msgErrorFullName[0] + " " + msgErrorFullName[1]);
    }

    private void checkData() {
        if (edFName.getText().toString().equals("")) { fullName[0] = edFName.getHint().toString().trim(); }
        else { fullName[0] = edFName.getText().toString().trim(); }
        if (edLName.getText().toString().equals("")) { fullName[1] = edLName.getHint().toString().trim(); }
        else { fullName[1] = edLName.getText().toString().trim(); }

        if (edHeight.getText().toString().equals("")) { bodyInfo[0] = edHeight.getHint().toString().trim(); }
        else { bodyInfo[0] = edHeight.getText().toString().trim(); }
        if (edWeight.getText().toString().equals("")) { bodyInfo[1] = edWeight.getHint().toString().trim(); }
        else { bodyInfo[1] = edWeight.getText().toString().trim(); }

        switch (spinnerBTText) {
            case "ECTOMORPH" :
                bodyInfo[2] = "1";
                break;
            case "MESOMORPH" :
                bodyInfo[2] = "2";
                break;
            case "ENDOMORPH" :
                bodyInfo[2] = "3";
                break;
            default:
                bodyInfo[2] = "99";
                break;
        }

        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexActive = viewP.findViewById(selectedId);
        if (radioSexActive.getText().toString().equals("Male")) {
            bodyInfo[3] = "1";
        } else if (radioSexActive.getText().toString().equals("Female")) {
            bodyInfo[3] = "0";
        } else {
            bodyInfo[3] = "99";
        }
    }

    private void fillData() {

        edFName.setText("");
        edLName.setText("");
        edHeight.setText("");
        edWeight.setText("");

        edFName.setHint(fullName[0]);
        edLName.setHint(fullName[1]);
        edHeight.setHint(bodyInfo[0]);
        edWeight.setHint(bodyInfo[1]);

        Log.d(TAG, "fillData: kkkkkkkkkkkkkkkk " + bodyInfo[2]);

        if (bodyInfo[2].equals("1") || bodyInfo[2].equals("2") || bodyInfo[2].equals("3") ||
                bodyInfo[2].equals("99"))
        {
            if (bodyInfo[2].equals("1")) { bodyInfo[2] = "ECTOMORPH"; }
            else if (bodyInfo[2].equals("2")) { bodyInfo[2] = "MESOMORPH"; }
            else if (bodyInfo[2].equals("3")) { bodyInfo[2] = "ENDOMORPH"; }
            else { bodyInfo[2] = "NONE"; }
        }

        Log.d(TAG, "fillData: +++++++++++++++++++++ >" + selectionPosition + " " + bodyInfo[2]);

        switch (bodyInfo[2]) {
            case "ECTOMORPH" :
                selectionPosition = spinnerAdapter.getPosition("ECTOMORPH");
                break;
            case "MESOMORPH" :
                selectionPosition = spinnerAdapter.getPosition("MESOMORPH");
                break;
            case "ENDOMORPH" :
                selectionPosition = spinnerAdapter.getPosition("ENDOMORPH");
                break;
            default:
                selectionPosition = spinnerAdapter.getPosition("NONE");
                break;
        }
        Log.d(TAG, "fillData: +++++++++++++++++++++ >" + selectionPosition + " " + bodyInfo[2]);
        spinnerBT.setSelection(selectionPosition);

        if (bodyInfo[3].equals("0")) {
            radioSexFemale.setChecked(true);
            radioSexMale.setChecked(false);
        } else if (bodyInfo[3].equals("1")) {
            radioSexMale.setChecked(true);
            radioSexFemale.setChecked(false);
        } else {
            radioSexFemale.setChecked(false);
            radioSexMale.setChecked(false);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerBTText = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}