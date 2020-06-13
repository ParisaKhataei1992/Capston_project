package ca.i3th.capstonii.Fragments.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.i3th.capstonii.R;

public class Statistic extends Fragment {

    private static final String TAG = "Statistic_Fragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_fragment, container, false);

        Toast.makeText(getActivity(), "Statistic Fragment", Toast.LENGTH_SHORT).show();

        return view;
    }
}
