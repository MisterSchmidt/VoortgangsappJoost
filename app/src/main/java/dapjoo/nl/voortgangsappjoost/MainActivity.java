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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements DialogFirst.DialogFirstListener, NavigationView.OnNavigationItemSelectedListener, DialogVakBewerken.DialogVakBewerkenListener {

    private DrawerLayout drawer;
    private TextView tvGebruiker;
    private TextView tvEmail;
    private TextView mTitle;
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

        //Laad database
        VakkenDBHelper dbHelper = new VakkenDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        // Toolbar gedeelte en navigation drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle.setText("Voortgangs App");

        // Drawer maken
        drawer = findViewById(R.id.drawer_layeout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Koppel TextViews uit nav_view
        headerView = navigationView.getHeaderView(0);
        tvGebruiker = (TextView) headerView.findViewById(R.id.tv_gebruiker);
        tvEmail = (TextView) headerView.findViewById(R.id.tv_email);

        //Maak toolbar knop aan.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Start dialog of laad settigs
        if(firstStart){
            createDefaultVakken();
            showDialogFirst();
        }else{
            insertPrefs();
        }

        //Open eerste pagina zonder er op te klikken
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        /////* Toolbar gedeelte en navigation drawer */////
    }

    //MENU - Wat gebeurt er als je op een knop klikt (opent een nieuwe fragment of voert code uit)
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.nav_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                mTitle.setText("Voortgangs App");
                break;

            case R.id.nav_schooljaar1:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar1Fragment()).commit();
                mTitle.setText("Schooljaar 1");
                break;

            case R.id.nav_schooljaar2:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar2Fragment()).commit();
                mTitle.setText("Schooljaar 2");
                break;

            case R.id.nav_schooljaar3:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar3Fragment()).commit();
                mTitle.setText("Schooljaar 3");
                break;

            case R.id.nav_schooljaar4:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar4Fragment()).commit();
                mTitle.setText("Schooljaar 4");
                break;

            case R.id.nav_keuzevakken:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new KeuzevakkenFragment()).commit();
                mTitle.setText("Keuzevakken");
                break;

            case R.id.nav_upload:
                Toast.makeText(this,"No network", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_download:
                Toast.makeText(this,"No network", Toast.LENGTH_SHORT).show();
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

    //Inladen van settings voor de navigation drawer
    private void insertPrefs(){
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String usr = prefs.getString("user", "");
        String eml = prefs.getString("email", "");
        tvGebruiker.setText(usr);
        tvEmail.setText(eml);
    }

    //Laten zien van de "one time" dialog
    private void showDialogFirst(){
        DialogFirst dialogFirst = new DialogFirst();
        dialogFirst.show(getSupportFragmentManager(), "Dialog First");

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    //opslaan van dialog in settings file "prefs"
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

    //invoeren van de standaard vakken in SQLITE bij eerste keer opstarten
    public void createDefaultVakken(){

        String[] naam = new String[] {"IPMEDT1", "IMHTB", "IOPR1", "IPBIT1", "ISMI", "IIBPM", "IRDB", "IPSEN1", "IMUML", "IOPR2", "ITIM", "IPFIT1",
        "IFIT", "IWEB", "INST", "ICOMMP", "ISLP", /*17*/ "IRDBMS", "IPMEDT2", "IMTD1", "IQUA", "IPMEDT4", "IMTPMD", "IITORG", "IPMEDT3", "IMTUE", "ISEC",
        "IPMEDT5", "IMTHE1", "ICOMMH", "ISLH1", /*31*/ "IETH", "ISCRIP", "IPMEDTH", "IMTCM", "IMTHMI", "IMTMC", "ISLH2", "IKM30", /*39*/ "ISLH3", "IWLS",
        "IWLA" /*42*/};

        int[] ec = new int[] {6, 3, 4, 5, 3, 3, 3, 5, 3, 4, 3, 5, 3, 3, 3, 3, 1, /*17*/ 3, 6, 3, 3, 6, 3, 3, 6, 3, 3, 6, 3, 3, 1, /*31*/
        3, 3, 9, 3, 3, 3, 1, 30, /*39*/ 1, 30, 30 /*42*/};

        int[] schooljaar = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1, /*17*/ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, /*31*/
        3, 3, 3, 3, 3, 3, 3, 3, /*39*/ 4, 4, 4 /*42*/};

        double cijfer = 0.0;
        String notitie = "";

        for(int i = 0; i < 42; i++){
            ContentValues cv = new ContentValues();
            cv.put(VakkenContract.VakkenEntry.COLUMN_NAAM, naam[i]);
            cv.put(VakkenContract.VakkenEntry.COLUMN_CIJFER, cijfer);
            cv.put(VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR, schooljaar[i]);
            cv.put(VakkenContract.VakkenEntry.COLUMN_EC, ec[i]);
            cv.put(VakkenContract.VakkenEntry.COLUMN_NOTITIE, notitie);

            mDatabase.insert(VakkenContract.VakkenEntry.TABLE_NAME, null, cv);
        }
    }

    // Voor het updaten van een item
    @Override
    public void editVak(long id, double cijfer, String notitie, int schooljaar) {

        mDatabase.execSQL( "UPDATE " + VakkenContract.VakkenEntry.TABLE_NAME +
                " SET " + VakkenContract.VakkenEntry.COLUMN_NOTITIE + " = '" + notitie +
                "' WHERE " + VakkenContract.VakkenEntry._ID + " = " + id);

        mDatabase.execSQL( "UPDATE " + VakkenContract.VakkenEntry.TABLE_NAME +
                " SET " + VakkenContract.VakkenEntry.COLUMN_CIJFER + " = '" + cijfer +
                "' WHERE " + VakkenContract.VakkenEntry._ID + " = " + id);

        if(schooljaar == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new KeuzevakkenFragment()).commit();
            mTitle.setText("Keuzevakken");
        }
        else if(schooljaar == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar1Fragment()).commit();
            mTitle.setText("Schooljaar 1");
        }
        else if(schooljaar == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar2Fragment()).commit();
            mTitle.setText("Schooljaar 2");
        }
        else if(schooljaar == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar3Fragment()).commit();
            mTitle.setText("Schooljaar 3");
        }
        else if(schooljaar == 4){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Schooljaar4Fragment()).commit();
            mTitle.setText("Schooljaar 4");
        }
    }
}

