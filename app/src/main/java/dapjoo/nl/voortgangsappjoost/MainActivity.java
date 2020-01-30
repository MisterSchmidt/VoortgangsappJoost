package dapjoo.nl.voortgangsappjoost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements DialogFirst.DialogFirstListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView tvGebruiker;
    private TextView tvEmail;
    private View headerView;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Aanmaken van het menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Zet dit als eerste keer opstarten
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        VakkenDBHelper dbHelper = new VakkenDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        // Toolbar gedeelte en navigation drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Drawer maken
        drawer = findViewById(R.id.drawer_layeout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Koppel TextViews uit nav_view
        headerView = navigationView.getHeaderView(0);
        tvGebruiker = (TextView) headerView.findViewById(R.id.tv_gebruiker);
        tvEmail = (TextView) headerView.findViewById(R.id.tv_email);

        createDefaultVakken();

        // Start dialog en save prefs of insert prefs
        if(firstStart){
            showDialogFirst();
        }else{
            insertPrefs();
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Open eerste pagina zonder er op te klikken
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        /////* Toolbar gedeelte en navigation drawer */////
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

    //Weg faden van drawer
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void insertPrefs(){
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String usr = prefs.getString("user", "");
        String eml = prefs.getString("email", "");
        tvGebruiker.setText(usr);
        tvEmail.setText(eml);
    }

    private void showDialogFirst(){
        DialogFirst dialogFirst = new DialogFirst();
        dialogFirst.show(getSupportFragmentManager(), "Dialog First");

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void applyTexts(String usr, String eml) {
        tvGebruiker.setText(usr);
        tvEmail.setText(eml);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user", usr);
        editor.putString("email", eml);
        editor.apply();
    }

    public void createDefaultVakken(){

        String naam = "IOPR5";
        double cijfer = 5.5;
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

        mDatabase.insert(VakkenContract.VakkenEntry.TABLE_NAME, null, cv);
    }
}

