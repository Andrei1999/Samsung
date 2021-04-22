package ru.toltekplus.apps.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

public class ContactsActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    intent = new Intent(ContactsActivity.this, NewsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_info:
                    intent = new Intent(ContactsActivity.this, InfoActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_home:
                    intent = new Intent(ContactsActivity.this, HelloActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_3);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        WebView wV = findViewById(R.id.mapView);
        wV.getSettings().setJavaScriptEnabled(true);
        wV.loadUrl("file:///android_asset/map.html");
    }

}
