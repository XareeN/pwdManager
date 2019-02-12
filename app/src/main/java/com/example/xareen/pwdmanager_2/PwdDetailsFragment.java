package com.example.xareen.pwdmanager_2;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.model.LoginHolder;

@SuppressLint("ValidFragment")
public class PwdDetailsFragment extends Fragment {
    private MainActivity mainActivity;
    private long id;
    private TextView title;
    private TextView username;
    private TextView pwd;
    private TextView pwd2;
    private TextView email;
    private TextView website;
    private TextView notes;
    private TextView when_created;
    private TextView last_mod;
    private LoginHolder loginHolder;
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    @SuppressLint("ValidFragment")
    public PwdDetailsFragment(LoginHolder loginHolder) {
        this.loginHolder = loginHolder;
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
        mainActivity.getToolbar().setNavigationIcon(null);
        mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        title = (TextView) view.findViewById(R.id.tv_title);
        username = (TextView) view.findViewById(R.id.tv_username);
        pwd = (TextView) view.findViewById(R.id.tv_pwd);
        pwd2 = (TextView) view.findViewById(R.id.tv_pwd2);
        email = (TextView) view.findViewById(R.id.tv_email);
        website = (TextView) view.findViewById(R.id.tv_website);
        notes = (TextView) view.findViewById(R.id.tv_notes);
        last_mod = (TextView) view.findViewById(R.id.tv_last_mod);
        when_created = (TextView) view.findViewById(R.id.tv_created_on);

        clipboardManager = (ClipboardManager) mainActivity.getSystemService(mainActivity.CLIPBOARD_SERVICE);

        if (loginHolder.getTitle().isEmpty() || loginHolder.getTitle() == null) {
            title.setHeight(0);
        } else {
            title.setText(loginHolder.getTitle());
        }
        if (loginHolder.getUsername().isEmpty() || loginHolder.getUsername() == null) {
            username.setHeight(0);
        } else {
            username.setText(loginHolder.getUsername());
        }
        if (loginHolder.getPwd().isEmpty() || loginHolder.getPwd() == null) {
            pwd.setHeight(0);
        } else {
            pwd.setText(loginHolder.getPwd());
            pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (loginHolder.getPwd2().isEmpty() || loginHolder.getPwd2() == null) {
            pwd2.setHeight(0);
        } else {
            pwd2.setText(loginHolder.getPwd2());
            pwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (loginHolder.getEmail().isEmpty() || loginHolder.getEmail() == null) {
            email.setHeight(0);
        } else {
            email.setText(loginHolder.getEmail());
        }
        if (loginHolder.getWebsite().isEmpty() || loginHolder.getWebsite() == null) {
            website.setHeight(0);
        } else {
            website.setText(loginHolder.getWebsite());
        }
        if (loginHolder.getNotes().isEmpty() || loginHolder.getNotes() == null) {
            notes.setHeight(0);
        } else {
            notes.setText(loginHolder.getNotes());
        }
        if (loginHolder.getLast_mod().isEmpty() || loginHolder.getLast_mod() == null) {
            last_mod.setHeight(0);
        } else {
            last_mod.setText(loginHolder.getLast_mod());
        }
        if (loginHolder.getWhen_created().isEmpty() || loginHolder.getWhen_created() == null) {
            when_created.setHeight(0);
        } else {
            when_created.setText(loginHolder.getWhen_created());
        }

        title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("titleClip", title.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Title copied to clipboard: " + title.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        username.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("usernameClip", username.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Username copied to clipboard: " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        pwd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("pwdClip", pwd.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Pwd copied to clipboard: " + pwd.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        pwd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("pwd2Clip", pwd2.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Pwd2 copied to clipboard: " + pwd2.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        email.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("emailClip", email.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Email copied to clipboard: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        website.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("websiteClip", website.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Website copied to clipboard: " + website.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        notes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clipData = ClipData.newPlainText("notesClip", notes.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mainActivity, "Notes copied to clipboard: " + notes.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd.getTransformationMethod() == SingleLineTransformationMethod.getInstance()){
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pwd.setTransformationMethod(SingleLineTransformationMethod.getInstance());
                }
            }
        });

        pwd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd2.getTransformationMethod() == SingleLineTransformationMethod.getInstance()){
                    pwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pwd2.setTransformationMethod(SingleLineTransformationMethod.getInstance());
                }
            }
        });

        Toast.makeText(mainActivity, "Object id: " + loginHolder.getId(), Toast.LENGTH_SHORT).show();
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
