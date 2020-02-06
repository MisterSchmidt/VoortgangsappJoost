package dapjoo.nl.voortgangsappjoost;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogVakToevoegen extends AppCompatDialogFragment {

    private DialogVakToevoegenListener listener;
    private EditText naamToevoegen;
    private EditText ecToevoegen;
    private String mNaam;
    private int mEc;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_vak_toevoegen, null);

        naamToevoegen = view.findViewById(R.id.vak_toevoegen_naam);
        ecToevoegen = view.findViewById(R.id.vak_toevoegen_ec);

        builder.setView(view)
                .setTitle("Keuzevak Toevoegen")
                .setPositiveButton("Toevoegen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mNaam = naamToevoegen.getText().toString();
                        String ec;
                        ec = ecToevoegen.getText().toString();

                        try {
                            mEc = Integer.parseInt(ec);
                            if (mEc > 30) {
                                mEc = 30;
                            }
                            if (mEc < 1) {
                                mEc = 1;
                            }
                            listener.addKeuzevak(mNaam, mEc);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getActivity(), "FOUTMELDING - Noteer het aantal EC als 1 of 3", Toast.LENGTH_LONG).show();
                        }

                    }
                }).setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogVakToevoegenListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogFirstListener");
        }
    }

    public interface DialogVakToevoegenListener {
        void addKeuzevak(String mNaam, int mEc);
    }

}
