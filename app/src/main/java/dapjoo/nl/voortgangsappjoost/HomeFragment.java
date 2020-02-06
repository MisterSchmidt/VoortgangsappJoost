package dapjoo.nl.voortgangsappjoost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        tv.setText(getReachedEC() + "/" + getTotalEC());
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
