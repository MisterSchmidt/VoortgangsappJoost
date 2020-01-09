package dapjoo.nl.voortgangsappjoost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MijnSettings";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.buttonToInvoeren);
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



    }

    public void openInvoeren() {
        Intent intent = new Intent(this, Invoeren.class);
        startActivity(intent); // code om nieuwe activity met intent te laden
    }

    public void saveSettings(){
        // Store a value named silentMode ( of type: boolean ) :
        EditText editText = (EditText) findViewById(R.id.editField);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit(); // EDIT MODE
        editor.putString("setting", editText.getText().toString()); // WRITE STUFF
        editor.commit(); // SAVE IT
    }

    public void loadSettings(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nieuweWaarde = settings.getString("setting", "Default");

        EditText editText = (EditText) findViewById(R.id.editField);
        editText.setText(nieuweWaarde);
    }
}
