package dapjoo.nl.voortgangsappjoost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

        /*Button button = (Button)findViewById(R.id.buttonToInvoeren);
        Button saveButton = (Button)findViewById(R.id.saveSettings);

        loadSettings();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvoeren();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
        */
    }
}