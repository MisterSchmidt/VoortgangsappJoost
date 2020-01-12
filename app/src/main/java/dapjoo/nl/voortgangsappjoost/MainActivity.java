package dapjoo.nl.voortgangsappjoost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    //public static final String PREFS_NAME = "MijnSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Aanmaken van het menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layeout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Open eerste pagina zonder er op te klikken
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    //MENU - Wat gebeurt er als je op een knop klikt (opent een nieuwe fragment)
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.nav_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            case R.id.nav_schooljaar1:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar1Fragment()).commit();
                break;

            case R.id.nav_schooljaar2:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar2Fragment()).commit();
                break;

            case R.id.nav_schooljaar3:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar3Fragment()).commit();
                break;

            case R.id.nav_schooljaar4:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar4Fragment()).commit();
                break;

            case R.id.nav_keuzevakken:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new KeuzevakkenFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    /*
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
        showToastMessage(editText.getText().toString());
    }

    public void loadSettings(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nieuweWaarde = settings.getString("setting", "Default");

        EditText editText = (EditText) findViewById(R.id.editField);
        editText.setText(nieuweWaarde);
    }

    public void showToastMessage(String toastMessage) {	// Laat een toast message zien
        CharSequence tm = toastMessage;
        int duration = Toast.LENGTH_LONG;      // kort = 0.5 sec // long = 1 sec
        Toast toast = Toast.makeText(getApplicationContext(), tm, duration);
        toast.show();
    }*/


}
