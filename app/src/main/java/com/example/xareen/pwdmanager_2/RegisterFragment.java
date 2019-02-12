package com.example.xareen.pwdmanager_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.model.Master;
import com.example.xareen.pwdmanager_2.transactions.RealmCRUD;

import io.realm.Realm;

public class RegisterFragment extends Fragment {
    private MainActivity mainActivity;
    @NonNull
    private EditText masterPwdReg;
    @NonNull
    private EditText masterPwd2Reg;
    @NonNull
    private EditText masterPwdHintReg;
    private Realm realm;
    private Button saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        realm = mainActivity.getRealm();

        //Turn off drawer and its icon on toolbar
        mainActivity.getToolbar().setNavigationIcon(null);
        mainActivity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        masterPwdReg = (EditText) view.findViewById(R.id.et_master_pwd_register);
        masterPwd2Reg = (EditText) view.findViewById(R.id.et_master_pwd2_register);
        masterPwdHintReg = (EditText) view.findViewById(R.id.et_master_pwd_hint);

        saveBtn = (Button) view.findViewById(R.id.btn_save_register);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {

                    //TODO: uncomment if below

//                    if (masterPwdReg.getText().length() > 5){
                        if (masterPwdReg.getText().toString().equals(masterPwd2Reg.getText().toString())) {
                            if(masterPwdHintReg.getText().toString().isEmpty()){
                                Toast.makeText(mainActivity, "Please, fill in the Hint field", Toast.LENGTH_SHORT).show();
                            } else {
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();

                                Number currentIdNum = realm.where(Master.class).max("master_id");
                                int nextId;

                                //Get data
                                Master m = new Master();
                                if (currentIdNum == null) {
                                    nextId = 1;
                                } else {
                                    nextId = currentIdNum.intValue() + 1;
                                }
                                m.setMaster_id(nextId);
                                m.setMaster_pwd(masterPwdReg.getText().toString());
                                m.setMaster_hint(masterPwdHintReg.getText().toString());

                                //Save
                                RealmCRUD crud = new RealmCRUD(realm);
                                crud.saveMaster(m);
                            }
                        } else {
                            Toast.makeText(mainActivity, "Password fields does not match", Toast.LENGTH_SHORT).show();
                        }
//                    } else {
//                        Toast.makeText(mainActivity, "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
//                    }



                    //TODO: remove master (1,1,1)

                }
            }
        });

        return view;

    }

    //No need to brind back drawer/tb settings_menu because the fragment being inflated after registration will always be login

}
