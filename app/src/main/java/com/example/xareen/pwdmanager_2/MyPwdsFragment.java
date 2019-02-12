package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.adapter.LoginsAdapter;
import com.example.xareen.pwdmanager_2.model.LoginHolder;
import com.example.xareen.pwdmanager_2.model.Logins;
import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import java.util.ArrayList;

import io.realm.Realm;

public class MyPwdsFragment extends Fragment {
    private MainActivity mainActivity;
    private Realm realm;
    private ArrayList<Logins> logins;
    private ArrayList<LoginHolder> loginHolder = new ArrayList<>();
    private LoginsAdapter adapter;
    private ListView lv;
    private EditText et;
    private FloatingActionButton fab;
    private RealmCRUD crud;
    private long itemId;

    @Override
    public void onResume() {
        mainActivity.getToolbar().setTitle("Loginz");
        loginHolder.clear();
        logins = crud.retrieve();
        for (Logins l : logins) {
            loginHolder.add(new LoginHolder(l));
        }
        adapter = new LoginsAdapter(mainActivity, loginHolder);

        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
//        mainActivity.setShowPwdSortingMenu(true);

        realm = mainActivity.getRealm();
        crud = new RealmCRUD(realm);


        View view = inflater.inflate(R.layout.fragment_my_pwds, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add);

        lv = (ListView) view.findViewById(R.id.lv_my_pwds);

        //Retrieve data
        logins = crud.retrieve();

        //Bind custom adapter
        for (Logins l : logins) {
            loginHolder.add(new LoginHolder(l));
        }
        adapter = new LoginsAdapter(mainActivity, loginHolder);
        lv.setAdapter(adapter);

        //Item clicks
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoginHolder loginsItem = adapter.getItem(position);

                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PwdDetailsFragment(loginsItem)).addToBackStack(null).commit();
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Delete
                LoginHolder loginsItem = adapter.getItem(position);

                long idToDelete = loginsItem.getId();
                loginHolder.remove(loginsItem);
                adapter = new LoginsAdapter(mainActivity, loginHolder);
                lv.setAdapter(adapter);
                crud.delete(idToDelete);
                Toast.makeText(mainActivity, "Item Deleted " + idToDelete, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddPwdFragment()).addToBackStack(null).commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        mainActivity.setShowPwdSortingMenu(false);
        super.onDestroy();
    }


}
