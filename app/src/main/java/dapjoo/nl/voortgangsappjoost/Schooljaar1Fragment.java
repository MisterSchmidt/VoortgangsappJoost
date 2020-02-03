package dapjoo.nl.voortgangsappjoost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class Schooljaar1Fragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private VakkenAdapter mAdapter;
    private int schooljaar = 1;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schooljaar1, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VakkenDBHelper dbHelper = new VakkenDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();


        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new VakkenAdapter(getActivity(), getAllItems());
        recyclerview.setAdapter(mAdapter);

        TextView tv = view.findViewById(R.id.schooljaar1_ec);
        tv.setText(getReachedEC() + "/" + getTotalEC());

        mAdapter.swapCursor(getAllItems());

        mAdapter.setOnItemClickListener(new VakkenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long sqltag) {
                int sql = (int) sqltag;
                Log.d(TAG, "Adapter position: " + position + " - SQL Tag: " + sql);
                Toast.makeText(getActivity(), "Adapter position: " + position + " - SQL Tag: " + sql, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                VakkenContract.VakkenEntry.TABLE_NAME,
                null,
                VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR + " = 1",
                null,
                null,
                null,
                VakkenContract.VakkenEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    public Cursor fetchEC(int schooljaar, double minCijfer) {
        return mDatabase.rawQuery(
                "SELECT " + VakkenContract.VakkenEntry.COLUMN_EC +
                " FROM " + VakkenContract.VakkenEntry.TABLE_NAME +
                " WHERE " + VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR + " = " + schooljaar +
                " AND " + VakkenContract.VakkenEntry.COLUMN_CIJFER + " > " + minCijfer
                ,null);
    }

    private int getTotalEC(){
        Cursor cTotalEC = fetchEC(schooljaar, -1);
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

    private int getReachedEC(){
        Cursor cReachedEC = fetchEC(schooljaar, 5.5);
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
