package com.example.xareen.pwdmanager_2;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.model.Logins;
import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import java.util.Calendar;

import io.realm.Realm;

public class AddPwdFragment extends Fragment {
    private MainActivity mainActivity;
    private long id;
    private EditText title;
    private EditText username;
    private EditText pwd;
    private EditText pwd2;
    private EditText email;
    private EditText website;
    private EditText notes;
    private MenuItem saveBtn;
    private Realm realm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pwd, container, false);
        setHasOptionsMenu(true);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setShowSaveCancelMenu(true);
//        mainActivity.setShowPwdSortingMenu(false);

        mainActivity.getToolbar().setTitle("Create login");
        mainActivity.getToolbar().setNavigationIcon(null);
        mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mainActivity.invalidateOptionsMenu();


        realm = mainActivity.getRealm();

        title = (EditText) view.findViewById(R.id.et_title);
        username = (EditText) view.findViewById(R.id.et_username);
        pwd = (EditText) view.findViewById(R.id.et_password);
        pwd2 = (EditText) view.findViewById(R.id.et_password2);
        email = (EditText) view.findViewById(R.id.et_email);
        website = (EditText) view.findViewById(R.id.et_website);
        notes = (EditText) view.findViewById(R.id.et_notes);


//        saveBtn = getActivity().findViewById(R.id.save_tbar_btn);
//        realm = Realm.getDefaultInstance();
//        saveBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                //Get data
//                Logins l = new Logins();
//
//                l.setId(1);
//                l.setTitle(title.getText().toString());
//                l.setUsername(username.getText().toString());
//                l.setPwd(pwd.getText().toString());
//                l.setPwd2(pwd2.getText().toString());
//                l.setEmail(email.getText().toString());
//                l.setWebsite(website.getText().toString());
//                l.setNotes(notes.getText().toString());
//                l.setWhen_created(String.valueOf(Calendar.getInstance().getTime()));
//                l.setLast_mod(String.valueOf(Calendar.getInstance().getTime()));
//
//                //Save
//                RealmCRUD crud = new RealmCRUD(realm);
//                crud.save(l);
//
//
//                //Retrieve
//                //logins = crud.retrieve();
//
//
////                realm.beginTransaction();
////
////                Logins logins = realm.createObject(Logins.class);
////
//////                logins.setId();
////                logins.setTitle(String.valueOf(title));
////                logins.setUsername(String.valueOf(username));
////                logins.setPwd(String.valueOf(pwd));
////                logins.setPwd2(String.valueOf(pwd2));
////                logins.setEmail(String.valueOf(email));
////                logins.setWebsite(String.valueOf(website));
////                logins.setNotes(String.valueOf(notes));
////                logins.setWhen_created(String.valueOf(Calendar.getInstance().getTime()));
////                logins.setLast_mod(String.valueOf(Calendar.getInstance().getTime()));
////                realm.commitTransaction();
//
//                return false;
//            }
//        });


        return view;
    }

    @Override
    public void onDestroy() {
        //Bring back drawer and its icon on toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mainActivity, mainActivity.getDrawer(), mainActivity.getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainActivity.getDrawer().addDrawerListener(toggle);
        toggle.syncState();

        mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mainActivity.setShowSaveCancelMenu(false);
        mainActivity.invalidateOptionsMenu();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_tbar_btn) {

            Number currentIdNum = realm.where(Logins.class).max("id");
            int nextId;

            //Get data
            Logins l = new Logins();
            if (currentIdNum == null) {
                nextId = 1;
            } else {
                nextId = currentIdNum.intValue() + 1;
            }
            l.setId(nextId);
            l.setTitle(title.getText().toString());
            l.setUsername(username.getText().toString());
            l.setPwd(pwd.getText().toString());
            l.setPwd2(pwd2.getText().toString());
            l.setEmail(email.getText().toString());
            l.setWebsite(website.getText().toString());
            l.setNotes(notes.getText().toString());
            l.setWhen_created(String.valueOf(Calendar.getInstance().getTime()));
            l.setLast_mod(String.valueOf(Calendar.getInstance().getTime()));

            //Save
            RealmCRUD crud = new RealmCRUD(realm);
            crud.save(l);
            Toast.makeText(mainActivity, "Item added", Toast.LENGTH_SHORT).show();
            mainActivity.closeKeyboard();
            mainActivity.getSupportFragmentManager().popBackStack();


        } else if (id == R.id.cancel_tbar_btn) {
            mainActivity.closeKeyboard();
            mainActivity.getSupportFragmentManager().popBackStack();
        }

        return true;
    }
}
