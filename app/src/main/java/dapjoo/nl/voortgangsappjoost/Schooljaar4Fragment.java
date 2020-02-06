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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Schooljaar4Fragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private VakkenAdapter mAdapter;
    private int schooljaar = 4;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schooljaar4, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Koppel de Database
        VakkenDBHelper dbHelper = new VakkenDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        // Genereer de recycleview
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new VakkenAdapter(getActivity(), fetchAllItems());
        recyclerview.setAdapter(mAdapter);

        // Genereerd het aantal EC bovenin de pagina
        TextView tv = view.findViewById(R.id.schooljaar4_ec);
        tv.setText(getReachedEC(schooljaar, 5.4) + "/" + getTotalEC(schooljaar, -1));

        // Voert het refresh commando uit voor de lijst
        mAdapter.swapCursor(fetchAllItems());

        // OnClickListener, haalt gegevens van item uit de database en opent een dialog
        mAdapter.setOnItemClickListener(new VakkenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long sqltag) {

                String notitie = getNotitie(sqltag);
                String naam = getNaam(sqltag);
                double cijfer = getCijfer(sqltag);

                Bundle args = new Bundle();
                args.putLong("id", sqltag);
                args.putString("notitie", notitie);
                args.putString("naam", naam);
                args.putDouble("cijfer", cijfer);
                args.putInt("schooljaar", schooljaar);

                DialogVakBewerken dialogVakBewerken = new DialogVakBewerken();
                dialogVakBewerken.setArguments(args);
                dialogVakBewerken.show(getActivity().getSupportFragmentManager(), "Dialog Vak Bewerken");
            }
        });
    }

    // Voert een DB entry uit en genereerd een lijst van items op volgorde voor de Recyclerview
    private Cursor fetchAllItems() {
        return mDatabase.query(
                VakkenContract.VakkenEntry.TABLE_NAME,
                null,
                VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR + " = " + schooljaar,
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
                , null);
    }

    public Cursor fetchNaam(long id) {
        return mDatabase.rawQuery(
                "SELECT " + VakkenContract.VakkenEntry.COLUMN_NAAM +
                        " FROM " + VakkenContract.VakkenEntry.TABLE_NAME +
                        " WHERE " + VakkenContract.VakkenEntry._ID + " = " + id
                , null);
    }


    public Cursor fetchCijfer(long id) {
        return mDatabase.rawQuery(
                "SELECT " + VakkenContract.VakkenEntry.COLUMN_CIJFER +
                        " FROM " + VakkenContract.VakkenEntry.TABLE_NAME +
                        " WHERE " + VakkenContract.VakkenEntry._ID + " = " + id
                , null);
    }

    public Cursor fetchNotitie(long id) {
        return mDatabase.rawQuery(
                "SELECT " + VakkenContract.VakkenEntry.COLUMN_NOTITIE +
                        " FROM " + VakkenContract.VakkenEntry.TABLE_NAME +
                        " WHERE " + VakkenContract.VakkenEntry._ID + " = " + id
                , null);
    }

    private String getNaam(long id) {
        String i = "Default";
        Cursor c = fetchNaam(id);
        try {
            while (c.moveToNext()) {
                i = c.getString(c.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_NAAM));
            }
        } finally {
            c.close();
            return i;
        }
    }

    private double getCijfer(long id) {
        double i = 0.0;
        Cursor c = fetchCijfer(id);
        try {
            while (c.moveToNext()) {
                i = c.getDouble(c.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_CIJFER));
            }
        } finally {
            c.close();
            return i;
        }
    }

    private String getNotitie(long id) {
        String i = "Default";
        Cursor c = fetchNotitie(id);
        try {
            while (c.moveToNext()) {
                i = c.getString(c.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_NOTITIE));
            }
        } finally {
            c.close();
            return i;
        }
    }

    private int getTotalEC(int sj, double mc) {
        Cursor c = fetchEC(sj, mc);
        int i = 0;
        try {
            while (c.moveToNext()) {
                i = i + c.getInt(c.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_EC));
            }
        } finally {
            c.close();
            return i;
        }
    }

    private int getReachedEC(int sj, double mc) {
        Cursor c = fetchEC(sj, mc);
        int i = 0;
        try {
            while (c.moveToNext()) {
                i = i + c.getInt(c.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_EC));
            }
        } finally {
            c.close();
            return i;
        }
    }
}
