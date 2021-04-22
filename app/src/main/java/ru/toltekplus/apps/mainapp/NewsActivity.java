package ru.toltekplus.apps.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(NewsActivity.this, HelloActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_info:
                    intent = new Intent(NewsActivity.this, InfoActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_contacts:
                    intent = new Intent(NewsActivity.this, ContactsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        WebView wV = findViewById(R.id.newsView);
        wV.getSettings().setJavaScriptEnabled(true);
        wV.loadUrl("file:///android_asset/VK.html");
    }

}
