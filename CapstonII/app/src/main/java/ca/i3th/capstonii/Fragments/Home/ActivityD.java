package ca.i3th.capstonii.Fragments.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.R;
import ca.i3th.capstonii.WOV.MainVideoJava;
import ca.i3th.capstonii.WOV.VideoButtonsMale;

public class ActivityD extends Fragment {

    private EditText editText;
    private Button btnBroadcast;

    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);


        btnBroadcast = view.findViewById(R.id.btnHDFA);
        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(getActivity().getApplication(), MainVideoJava.class);
                open.putExtra("btn" ,  8);
                startActivity(open);
            }
        });

        return view;
    }
}
