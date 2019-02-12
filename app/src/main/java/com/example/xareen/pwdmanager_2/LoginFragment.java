package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.model.Master;
import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import java.util.ArrayList;

import io.realm.Realm;

public class LoginFragment extends Fragment {
    private MainActivity mainActivity;
    private EditText editText;
    private TextView textView;
    private ArrayList<Master> master;
    private RealmCRUD crud;
    private Realm realm;
    private int wrongPwdCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        realm = mainActivity.getRealm();
        crud = new RealmCRUD(realm);
        master = crud.retrieveMaster();

        //Turn off drawer and its icon on toolbar
        if (mainActivity.getToolbar().getNavigationIcon() != null) {
            mainActivity.getToolbar().setNavigationIcon(null);
            mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        textView = (TextView) view.findViewById(R.id.tv_hint);
        editText = (EditText) view.findViewById(R.id.et_master_pwd_login);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (getFragmentManager() != null) {

                        if(wrongPwdCounter >= 3){
                            textView.setText("Hint: " + master.get(0).getMaster_hint());
                        }
                        //TODO: (un)comment 61,65-68 to enable login check

                        if(master.get(0).getMaster_pwd().equals(editText.getText().toString())){
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPwdsFragment()).commit();
                            mainActivity.closeKeyboard();
                            Toast.makeText(mainActivity, "Welcome", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mainActivity, "Wrong password!", Toast.LENGTH_SHORT).show();
                            wrongPwdCounter++;
                        }



                    }
                    handled = true;
                }
                return handled;
            }
        });

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
        super.onDestroy();
    }
}
