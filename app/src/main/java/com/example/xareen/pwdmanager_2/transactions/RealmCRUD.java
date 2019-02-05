package com.example.xareen.pwdmanager_2.transactions;

import android.util.Log;
import android.widget.Toast;

import com.example.xareen.pwdmanager_2.MainActivity;
import com.example.xareen.pwdmanager_2.model.Logins;

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
    public ArrayList<String> retrieve() {
        ArrayList<String> loginsList = new ArrayList<>();
        RealmResults<Logins> logins = realm.where(Logins.class).findAll();

        for (Logins l : logins) {
            loginsList.add(l.getTitle());
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
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmResults<Logins> results = realm.where(Logins.class).equalTo("id", id).findAll();
//                results.deleteAllFromRealm();
//            }
//        });
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

    public void saveMaster(Realm realm) {

    }
}
