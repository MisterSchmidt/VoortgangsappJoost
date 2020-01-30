package dapjoo.nl.voortgangsappjoost;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Schooljaar1Fragment extends Fragment {


    private SQLiteDatabase mDatabase;
    private VakkenAdapter mAdapter;


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

        /*String naam = "IOPR3";
        double cijfer = 4.5;
        int schooljaar = 4;
        int keuzevak = 1;
        int ec = 3;
        String notitie = "ik ben gek";

        ContentValues cv = new ContentValues();
        cv.put(VakkenContract.VakkenEntry.COLUMN_NAAM, naam);
        cv.put(VakkenContract.VakkenEntry.COLUMN_CIJFER, cijfer);
        cv.put(VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR, schooljaar);
        cv.put(VakkenContract.VakkenEntry.COLUMN_KEUZEVAK, keuzevak);
        cv.put(VakkenContract.VakkenEntry.COLUMN_EC, ec);
        cv.put(VakkenContract.VakkenEntry.COLUMN_NOTITIE, notitie);

        mDatabase.insert(VakkenContract.VakkenEntry.TABLE_NAME, null, cv);*/

        mAdapter.swapCursor(getAllItems());

    }

    private Cursor getAllItems(){
        return mDatabase.query(
                VakkenContract.VakkenEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                VakkenContract.VakkenEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
