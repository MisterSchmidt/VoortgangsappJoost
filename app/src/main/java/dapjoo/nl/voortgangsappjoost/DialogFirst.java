package dapjoo.nl.voortgangsappjoost;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFirst extends AppCompatDialogFragment {

    private EditText etUser;
    private EditText etEmail;
    private DialogFirstListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Genereer de dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_first, null);

        //Haal de EditViews binnen
        etUser = view.findViewById(R.id.et_naam);
        etEmail = view.findViewById(R.id.et_user);

        //Bouw de dialog
        builder.setView(view)
                .setTitle("Jouw gegevens")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String usr = etUser.getText().toString();
                        String eml = etEmail.getText().toString();
                        listener.applyTexts(usr, eml);
                    }
                });

        return builder.create();
    }

    //Genereer een listener

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {
            listener = (DialogFirstListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogFirstListener");
        }
    }

    public interface DialogFirstListener {
        void applyTexts(String usr, String eml);
    }

}
