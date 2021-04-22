package ru.toltekplus.apps.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TabHost;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class InfoActivity extends AppCompatActivity {

    SliderLayout schoolsSlider, rewardsSlider;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    intent = new Intent(InfoActivity.this, NewsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_home:
                    intent = new Intent(InfoActivity.this, HelloActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_contacts:
                    intent = new Intent(InfoActivity.this, ContactsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //slider_Schools_code
        schoolsSlider = (SliderLayout)findViewById(R.id.sliderSchools);
        HashMap<String,String> file_s_maps = new HashMap<String, String>();
        file_s_maps.put("Комментарий к школе №1", "file:///android_asset/schools/school_1.jpg");
        file_s_maps.put("Комментарий к школе №2", "file:///android_asset/schools/school_2.jpg");
        file_s_maps.put("Комментарий к школе №3", "file:///android_asset/schools/school_3.jpg");
        file_s_maps.put("Комментарий к школе №4", "file:///android_asset/schools/school_4.jpg");
        for(String name : file_s_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getBaseContext());
            textSliderView.description(name).image(file_s_maps.get(name)).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            schoolsSlider.addSlider(textSliderView);
        }
        schoolsSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        schoolsSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        schoolsSlider.setDuration(4000);

        //slider_Rewards_code
        rewardsSlider = (SliderLayout)findViewById(R.id.sliderRewards);
        HashMap<String,String> file_r_maps = new HashMap<String, String>();
        file_r_maps.put("Комментарий к награде №1", "file:///android_asset/rewards/reward_1.jpg");
        file_r_maps.put("Комментарий к награде №2", "file:///android_asset/rewards/reward_2.jpg");
        file_r_maps.put("Комментарий к награде №3", "file:///android_asset/rewards/reward_3.jpg");
        file_r_maps.put("Комментарий к награде №4", "file:///android_asset/rewards/reward_4.jpg");
        for(String name : file_r_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getBaseContext());
            textSliderView.description(name).image(file_r_maps.get(name)).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            rewardsSlider.addSlider(textSliderView);
        }
        rewardsSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        rewardsSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        rewardsSlider.setDuration(4000);

        //tabHost setup
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tabSchools);
        tabSpec.setIndicator(getString(R.string.title_tabSchools));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabRewards);
        tabSpec.setIndicator(getString(R.string.title_tabRewards));
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId){
                    case "tag1":
                        schoolsSlider.startAutoCycle();
                        rewardsSlider.stopAutoCycle(); break;
                    case "tag2":
                        rewardsSlider.startAutoCycle();
                        schoolsSlider.stopAutoCycle(); break;
                }
            }
        });

        tabHost.setCurrentTab(0);
    }

    @Override
    protected void onStop() {
        //slider_code
        schoolsSlider.stopAutoCycle();
        rewardsSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    protected void onResume() {
        //slider_code
        schoolsSlider.startAutoCycle();
        rewardsSlider.startAutoCycle();
        super.onResume();
    }

}
