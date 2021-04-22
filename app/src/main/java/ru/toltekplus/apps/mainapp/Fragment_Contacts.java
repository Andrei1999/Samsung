package ru.toltekplus.apps.mainapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class Fragment_Contacts extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        WebView wV = view.findViewById(R.id.mapView);
        wV.getSettings().setJavaScriptEnabled(true);
        wV.loadUrl("file:///android_asset/map.html");
        return view;
    }

}