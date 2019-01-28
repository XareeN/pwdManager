package com.example.xareen.pwdmanager_2;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        ### Handling device rotations
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPwdsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_myPwds);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_myPwds) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPwdsFragment()).commit();

        } else if (id == R.id.nav_pwdGen) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GeneratorFragment()).commit();
            Toast.makeText(this, "Pwd generator może w kolejnej wersji", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_backupRestore) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BackupRestoreFragment()).commit();

        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();

//            ### Code below creates second actvity
//            Intent myIntent = new Intent(MainActivity.this, SettingsFragment.class); //intents are used to connect together separate components
//            startActivity(myIntent);
//            return false;
        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
