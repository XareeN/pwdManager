package com.example.xareen.pwdmanager_2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import java.util.ArrayList;

import io.realm.Realm;

public class MyPwdsFragment extends Fragment {
    private MainActivity mainActivity;
    private Realm realm;
    private ArrayList<String> logins;
    private ArrayAdapter<String> adapter;
    private ListView lv;
    private EditText et;
    private RealmCRUD crud;
    private long itemId;

    @Override
    public void onResume() {
        mainActivity.getToolbar().setTitle("Loginz");
        logins = crud.retrieve();
        adapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1, logins);
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
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add);

        lv = (ListView) view.findViewById(R.id.lv_my_pwds);

        //Retrieve data
        RealmCRUD crud = new RealmCRUD(realm);
        logins = crud.retrieve();

        //Bind
        adapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1, logins);
        lv.setAdapter(adapter);

        //Item clicks
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PwdDetailsFragment(id)).addToBackStack(null).commit();
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Delete

//                crud.deleteAll();
//                logins.remove(position);
//                adapter.notifyDataSetChanged();
                Toast.makeText(mainActivity, "All Items Deleted " + id, Toast.LENGTH_SHORT).show();
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
