package com.example.xareen.pwdmanager_2.transactions;

import android.util.Log;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.MainActivity;
import com.example.xareen.pwdmanager_2.model.Logins;
import com.example.xareen.pwdmanager_2.model.Master;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmCRUD {
    private Realm realm;

    public RealmCRUD(Realm realm) {
        this.realm = realm;
    }

    //Write
    public void save(final Logins logins) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Logins l = realm.copyToRealm(logins);

            }
        });
    }

    //Read
    public ArrayList<Logins> retrieve() {
        ArrayList<Logins> loginsList = new ArrayList<>();
        RealmResults<Logins> logins = realm.where(Logins.class).findAll();

        for (Logins l : logins) {
            loginsList.add(l);
        }
        return loginsList;
    }

    //Update
    public void update(Logins logins) {
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Logins l = realm.where(Logins.class).equalTo("id",logins.getId()).findFirst();
//                l.set
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.e("upadteTag ", "update successful");
//
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//            }
//        });
    }

    //Delete
    public void delete(long id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Logins> results = realm.where(Logins.class).equalTo("id", id).findAll();
                results.deleteAllFromRealm();
            }
        });
    }

    public void deleteAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Logins> results = realm.where(Logins.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }

    //Check for master presence
    public boolean isMaster() {
//        ArrayList<Integer> masterList = new ArrayList<>();
        RealmResults<Master> master = realm.where(Master.class).findAll();

        return !master.isEmpty();
    }

    public void saveMaster(Master master) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Master m = realm.copyToRealm(master);

            }
        });
    }

    public ArrayList<Master> retrieveMaster() {
        ArrayList<Master> masterList = new ArrayList<>();
        RealmResults<Master> master = realm.where(Master.class).findAll();

        for (Master m : master) {
            masterList.add(m);
        }
        return masterList;
    }

    public void udpateMasterPwd(){

    }
    public void updateMasterHint(){

    }

    public void deleteMaster() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Master> results = realm.where(Master.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}
