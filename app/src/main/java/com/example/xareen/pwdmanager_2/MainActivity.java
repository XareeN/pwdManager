package com.example.xareen.pwdmanager_2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private long backPressedTime;
    private Toast backToast;
    private boolean showSaveCancelMenu = false;
    private boolean showPwdSortingMenu = false;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ### take toolbar and put it in the place of actionbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("");

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
            toolbar.setTitle("Logins");
        }

//        ### Realm initial setup
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            backToast.cancel();
//            super.onBackPressed();
//        } else {
//            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
//            backToast.show();
//        }
//        backPressedTime = System.currentTimeMillis();

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.save_tbar_btn) {
//
//            //TODO: save obv
//
//        } else if (id == R.id.cancel_tbar_btn) {
//            getSupportFragmentManager().popBackStack();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (showSaveCancelMenu) {
            getMenuInflater().inflate(R.menu.save_cancel_menu, menu);
        }
        if (showPwdSortingMenu) {
            getMenuInflater().inflate(R.menu.pwd_sorting_menu, menu);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        ExitActivity.exitApplication(this);
        super.onDestroy();
    }

    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public boolean isShowSaveCancelMenu() {
        return showSaveCancelMenu;
    }

    public void setShowSaveCancelMenu(boolean showSaveCancelMenu) {
        this.showSaveCancelMenu = showSaveCancelMenu;
    }

    public boolean isShowPwdSortingMenu() {
        return showPwdSortingMenu;
    }

    public void setShowPwdSortingMenu(boolean showPwdSortingMenu) {
        this.showPwdSortingMenu = showPwdSortingMenu;
    }

    public Realm getRealm() {
        return realm;
    }


}
