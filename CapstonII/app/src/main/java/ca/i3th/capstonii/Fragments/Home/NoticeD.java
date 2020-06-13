package ca.i3th.capstonii.Fragments.Home;

import android.os.Bundle;
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

import ca.i3th.capstonii.HomeActivity;
import ca.i3th.capstonii.R;

public class NoticeD extends Fragment {

    private EditText editText;
    private Button notice;
//    private String[] notification = new String[2];
    private static final String TAG = "NoticeD";
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_fragment, container, false);
        notice = view.findViewById(R.id.btnNotice);
        ((HomeActivity)getActivity()).getNotification();
        Log.d(TAG, "onCreateView:yyyyyyyyyyyyyyyyy " + ((HomeActivity)getActivity()).getBroadCast());
//        notice.setText(notification[1]);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), notification[1], Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
