package com.example.xareen.pwdmanager_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PwdDetailsFragment extends Fragment {
    private long id;
    private MainActivity mainActivity;

    @SuppressLint("ValidFragment")
    public PwdDetailsFragment(long id) {
        this.id = id;
    }

    //    public PwdDetailsFragment(long id) {
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pwd_details, container, false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.getToolbar().setTitle("Details");

        Toast.makeText(mainActivity, "Object id: " + id, Toast.LENGTH_SHORT).show();



        return view;
    }
}
