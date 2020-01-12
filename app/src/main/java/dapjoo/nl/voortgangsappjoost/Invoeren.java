package dapjoo.nl.voortgangsappjoost;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;


//import static dapjoo.nl.voortgangsappjoost.MainActivity.PREFS_NAME;

public class Invoeren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoeren);

        /*Ophalen Shared Preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); //Find
        boolean silent = settings.getBoolean("silentMode", false); // Read
        Log.d(" Found this value :",""+ silent +"" ); // Use

        loadSettings();

        //Button om terug te gaan naar mainactivity
        Button button = (Button)findViewById(R.id.buttonToMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });*/
    }
    /*
    public void openMain(){ //method om terug te gaan naar main
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSettings(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nieuweWaarde = settings.getString("setting", "Default");

        //Textview om te kunnen kijken wat er gebeurd
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText(nieuweWaarde);

    }*/
}
