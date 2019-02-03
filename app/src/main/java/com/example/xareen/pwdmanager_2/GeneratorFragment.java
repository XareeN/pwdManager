package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GeneratorFragment extends Fragment {
    private MainActivity mainActivity;

    @Override
    public void onResume() {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getToolbar().setTitle("Password Generator");
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generator, container, false);
    }
}
