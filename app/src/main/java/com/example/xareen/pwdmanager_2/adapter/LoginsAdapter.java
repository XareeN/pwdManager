package com.example.xareen.pwdmanager_2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xareen.pwdmanager_2.R;
import com.example.xareen.pwdmanager_2.model.LoginHolder;
import com.example.xareen.pwdmanager_2.model.Logins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginsAdapter extends ArrayAdapter<LoginHolder> {
//    private ArrayList<LoginHolder> loginsArrayList = new ArrayList<>();


    public LoginsAdapter(@NonNull Context context, ArrayList<LoginHolder> loginHolder) {
        super(context, 0, loginHolder);
//        loginsArrayList = loginHolder;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //Get the data item for this position
        LoginHolder logins = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_template, parent, false);
        }
        //Lookup view for data population
        TextView tvTitleTemplate = (TextView) convertView.findViewById(R.id.tv_title_template);
        TextView tvLastModTemplate = (TextView) convertView.findViewById(R.id.tv_last_mod_template);

        // Populate the data into the template view using the data object
        tvTitleTemplate.setText(logins.getTitle());
        tvLastModTemplate.setText(logins.getLast_mod());

        // Return the completed view to render on screen
        return convertView;

//        return super.getView(position, convertView, parent);
    }

    public void removeFromList(long id) {


//        for (Iterator<LoginHolder> it = loginsArrayList.iterator(); it.hasNext();){
//            LoginHolder login = it.next();
//            if( login.getId() == id){
//                it.remove();
//            }
//        }
//        for (LoginHolder l : loginsArrayList) {
//            if(l.getId() == id){
//                loginsArrayList.remove(l);
//            }
//        }
        notifyDataSetChanged();
    }


    //    @Override
//    public int getPosition(Logins item) {
//        return super.getPosition(item);
//    }
}
