package dapjoo.nl.voortgangsappjoost;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import static android.content.ContentValues.TAG;

public class DialogVakBewerken extends AppCompatDialogFragment {

    private EditText cijferBewerken;
    private EditText notitieBewerken;
    private DialogVakBewerkenListener listener;
    private double mCijfer;
    private String mNotitie;
    private String mNaam;
    private Long mId;
    private int mSchooljaar;
    private SQLiteDatabase mDatabase;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_vak_bewerken, null);

        cijferBewerken = view.findViewById(R.id.vak_bewerken_cijfer);
        notitieBewerken = view.findViewById(R.id.vak_bewerken_notitie);

        Bundle mArgs = getArguments();
        mId = mArgs.getLong("id");
        mNotitie = mArgs.getString("notitie");
        mNaam = mArgs.getString("naam");
        mCijfer = mArgs.getDouble("cijfer");
        mSchooljaar = mArgs.getInt("schooljaar");

        Log.d(TAG, " SQL Tag: " + mId);

        cijferBewerken.setText(Double.toString(mCijfer));
        notitieBewerken.setText(mNotitie);

        if (mSchooljaar == 0) {
            builder.setView(view)
                    .setTitle(mNaam)
                    .setPositiveButton("Bewerken", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String cijferString = cijferBewerken.getText().toString();
                            try {
                                mCijfer = Double.parseDouble(cijferString); // Make use of autoboxing.  It's also easier to read.
                                if (mCijfer > 10) {
                                    mCijfer = 10.0;
                                }
                                if (mCijfer < 1.0) {
                                    mCijfer = 1.0;
                                }
                                mCijfer = Math.round(mCijfer * 10) / 10.0;

                            } catch (NumberFormatException e) {
                                Toast.makeText(getActivity(), "FOUTMELDING - Noteer het cijfer als 7 of 7.0", Toast.LENGTH_LONG).show();
                            }
                            mNotitie = notitieBewerken.getText().toString();
                            listener.editVak(mId, mCijfer, mNotitie, mSchooljaar);
                        }
                    })
                    .setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNeutralButton("Verwijderen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.deleteVak(mId);
                }

            });
        } else {
            builder.setView(view)
                    .setTitle(mNaam)
                    .setPositiveButton("Bewerken", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String cijferString = cijferBewerken.getText().toString();
                            try {

                                mCijfer = Double.parseDouble(cijferString); // Make use of autoboxing.  It's also easier to read.
                                if (mCijfer > 10) {
                                    mCijfer = 10.0;
                                }
                                if (mCijfer < 1.0) {
                                    mCijfer = 1.0;
                                }
                                mCijfer = Math.round(mCijfer * 10) / 10.0;

                            } catch (NumberFormatException e) {
                                Toast.makeText(getActivity(), "FOUTMELDING - Noteer het cijfer als 7 of 7.0", Toast.LENGTH_LONG).show();
                            }
                            mNotitie = notitieBewerken.getText().toString();
                            listener.editVak(mId, mCijfer, mNotitie, mSchooljaar);
                        }
                    })
                    .setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
        }


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogVakBewerkenListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogVakBewerkenistener");
        }
    }


    public interface DialogVakBewerkenListener {
        void editVak(long mId, double mCijfer, String mNotitie, int mSchooljaar);

        void deleteVak(long mId);
    }
}

