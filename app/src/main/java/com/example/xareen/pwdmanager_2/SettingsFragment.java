package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment {
    private MainActivity mainActivity;
    private Button changePwd;
    private Button changeHint;

    @Override
    public void onResume() {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getToolbar().setTitle("Settings");
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        changePwd = (Button) view.findViewById(R.id.btn_changeMasterPwd);
        changeHint = (Button) view.findViewById(R.id.btn_changeMasterPwdHint);

        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChangeMasterPwdFragment()).addToBackStack(null).commit();
                }
            }
        });

        changeHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChangeMasterPwdHintFragment()).addToBackStack(null).commit();
                }
            }
        });

        return view;
    }
}
