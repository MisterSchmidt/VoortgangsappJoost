package dapjoo.nl.voortgangsappjoost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dapjoo.nl.voortgangsappjoost.model.Vak;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public static final String PREFS_NAME = "vakData";
    ArrayList<Vak> vakken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Aanmaken van het menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();



        /////* Toolbar gedeelte en navigation drawer */////
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layeout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("vakData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(vakken);
        editor.putString("objectJson", json);
        editor.apply();
    }

    //Laadt data van app
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("vakData", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("objectJson", null);
        Type type = new TypeToken<ArrayList<Vak>>() {}.getType();
        vakken = gson.fromJson(json, type);

        if(vakken == null){
            addDefaultCourses();
        }
    }

    //Genereer standaard vakken
    public void addDefaultCourses(){
        vakken = new ArrayList<>();

        Vak IOPR2 = new Vak("IOPR2",0, false, "", false,1,3);
        Vak IMTPMD = new Vak("IMTPMD",0, false, "", false,2,3);

        vakken.add(IOPR2);
        vakken.add(IMTPMD);

        saveData();
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
}
