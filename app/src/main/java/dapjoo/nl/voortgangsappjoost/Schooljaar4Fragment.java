package dapjoo.nl.voortgangsappjoost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class Schooljaar4Fragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private VakkenAdapter mAdapter;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schooljaar4, container, false);

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

        mAdapter.swapCursor(getAllItems());

        mAdapter.setOnItemClickListener(new VakkenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long help) {
                int y = (int) help;
                Log.d(TAG, "Adapter position: " + position + " - SQL Tag: " + y);
            }
        });

    }

    private Cursor getAllItems(){
        return mDatabase.query(
                VakkenContract.VakkenEntry.TABLE_NAME,
                null,
                VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR + " = 4",
                null,
                null,
                null,
                VakkenContract.VakkenEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
