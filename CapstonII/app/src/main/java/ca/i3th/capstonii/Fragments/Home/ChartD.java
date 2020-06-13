package ca.i3th.capstonii.Fragments.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import ca.i3th.capstonii.R;

public class ChartD extends Fragment {

    //initial bar chart
    BarChart barChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment, container, false);

//start bar chart
        barChart = view.findViewById(R.id.braGraph);
        ArrayList<BarEntry> barEntity = new ArrayList<>();
        barEntity.add(new BarEntry(44f, 0));
        barEntity.add(new BarEntry(88f, 1));
        barEntity.add(new BarEntry(66f, 2));
        barEntity.add(new BarEntry(12f, 3));
        barEntity.add(new BarEntry(12f, 4));
        barEntity.add(new BarEntry(55f, 5));
        BarDataSet barDataSet = new BarDataSet(barEntity, "Dates");

        ArrayList<String> theDate = new ArrayList<>();
        theDate.add("Apr");
        theDate.add("May");
        theDate.add("Jun");
        theDate.add("Jul");
        theDate.add("Aug");
        theDate.add("Sep");

        BarData barData = new BarData(barDataSet, barDataSet);
        barChart.setData(barData);

        //Setting
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        return view;
    }
}
