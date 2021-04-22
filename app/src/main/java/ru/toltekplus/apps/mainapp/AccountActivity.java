package ru.toltekplus.apps.mainapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//© Антипин А.Ф.

public class AccountActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,Fragment_News.class.newInstance()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        navigationView.setCheckedItem(R.id.nav_news); //пункт меню по умолчанию
        setTitle(navigationView.getCheckedItem().getTitle());
        navigationView.setNavigationItemSelectedListener(this);

        ((ImageView) navigationView.getHeaderView(0).findViewById(R.id.userImage))
                .setImageResource(R.drawable.user_m);
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userFName)).setText("Иванов Иван");
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userEMail)).setText("ivanov@ya.ru");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Class fragmentClass = null;
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_exit:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Toast.makeText(getApplicationContext(),getString(R.string.logout_mess),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AccountActivity.this, HelloActivity.class));
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                (new AlertDialog.Builder(this)).setMessage(getText(R.string.dlg_text_1))
                        .setPositiveButton(getText(R.string.dlg_text_Y),dialogClickListener)
                        .setNegativeButton(getText(R.string.dlg_text_N),dialogClickListener).show();
                break;
            case R.id.nav_info: fragmentClass = Fragment_Info.class; break;
            case R.id.nav_myCourses: fragmentClass = Fragment_MyCourses.class; break;
            case R.id.nav_timeTable: fragmentClass = Fragment_TimeTable.class; break;
            case R.id.nav_payment: fragmentClass = Fragment_Payment.class; break;
            case R.id.nav_about: fragmentClass = Fragment_About.class; break;
            case R.id.nav_news: fragmentClass = Fragment_News.class; break;
            case R.id.nav_schools: fragmentClass = Fragment_Schools.class; break;
            case R.id.nav_rewards: fragmentClass = Fragment_Rewards.class; break;
            case R.id.nav_contacts: fragmentClass = Fragment_Contacts.class; break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //изменение текущего фрагмента
        if (fragmentClass != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
