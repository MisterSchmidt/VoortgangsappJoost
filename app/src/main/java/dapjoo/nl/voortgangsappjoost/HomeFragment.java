package dapjoo.nl.voortgangsappjoost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VakkenDBHelper dbHelper = new VakkenDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        TextView tv = view.findViewById(R.id.home_ec);
        tv.setText(getReachedEC() + "/" + getTotalEC() + " EC");

        //Aanmaken piechart
        PieChart pieChart = view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        //Input van de piechart
        List<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(getReachedEC(), "Behaald"));
        list.add(new PieEntry(getTotalEC() - getReachedEC(), "Niet Behaald"));
        PieDataSet pieDataSet = new PieDataSet(list, "EC");
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(25f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);

        //Text onder de piechart
        Description desk = new Description();
        desk.setText("");
        pieChart.setDescription(desk);

        //Gat aanpassen van de piechart
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(10f);
        pieChart.setHoleColor(Color.TRANSPARENT);

        //functie om % erbij te krijgen
        pieData.setValueFormatter(new PercentFormatter());

        //Custom colors piechart
        final int[] MijnKleur = {
                Color.rgb(83,85,114),
                Color.rgb(72,73,98)};

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: MijnKleur) colors.add(c);
        pieDataSet.setColors(colors);

        //Animatie piechart vertragen tot 1400ms
        pieChart.animateXY(1400,1440);
    }

    public Cursor fetchEC(double minCijfer) {
        return mDatabase.rawQuery(
                "SELECT " + VakkenContract.VakkenEntry.COLUMN_EC +
                        " FROM " + VakkenContract.VakkenEntry.TABLE_NAME +
                        " WHERE " + VakkenContract.VakkenEntry.COLUMN_CIJFER + " > " + minCijfer
                , null);
    }

    private int getTotalEC() {
        Cursor cTotalEC = fetchEC(-1);
        int i = 0;
        try {
            while (cTotalEC.moveToNext()) {
                i = i + cTotalEC.getInt(cTotalEC.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_EC));
            }
        } finally {
            cTotalEC.close();
            return i;
        }
    }

    private int getReachedEC() {
        Cursor cReachedEC = fetchEC(5.4);
        int i = 0;
        try {
            while (cReachedEC.moveToNext()) {
                i = i + cReachedEC.getInt(cReachedEC.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_EC));
            }
        } finally {
            cReachedEC.close();
            return i;
        }
    }
}
