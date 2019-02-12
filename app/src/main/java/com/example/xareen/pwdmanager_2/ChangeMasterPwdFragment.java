package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.model.Master;
import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import java.util.ArrayList;

import io.realm.Realm;

public class ChangeMasterPwdFragment extends Fragment {
    private MainActivity mainActivity;
    private EditText currentPwd;
    private EditText newPwd;
    private EditText newPwd2;
    private Button saveBtn;
    private ArrayList<Master> master;
    private RealmCRUD crud;
    private Realm realm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_master_pwd, container, false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.getToolbar().setTitle("Change master password");
        mainActivity.getToolbar().setNavigationIcon(null);
        mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        realm = mainActivity.getRealm();
        crud = new RealmCRUD(realm);
        master = crud.retrieveMaster();

        currentPwd = (EditText) view.findViewById(R.id.et_current_master_pwd);
        newPwd = (EditText) view.findViewById(R.id.et_new_master_pwd);
        newPwd2 = (EditText) view.findViewById(R.id.et_new_master_pwd2);
        saveBtn = (Button) view.findViewById(R.id.btn_save_master_pwd);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(master.get(0).getMaster_pwd().equals(currentPwd.getText().toString())){

                    //TODO: update

                    mainActivity.closeKeyboard();
                    Toast.makeText(mainActivity, "Master password updated!", Toast.LENGTH_SHORT).show();
                }

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
