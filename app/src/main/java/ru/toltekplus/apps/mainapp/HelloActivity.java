package ru.toltekplus.apps.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class HelloActivity extends AppCompatActivity {

    SliderLayout mDemoSlider;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    intent = new Intent(HelloActivity.this, NewsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_info:
                    intent = new Intent(HelloActivity.this, InfoActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_contacts:
                    intent = new Intent(HelloActivity.this, ContactsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_0);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        findViewById(R.id.button_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelloActivity.this, AccountActivity.class));
            }
        });

        //slider_code
        mDemoSlider = (SliderLayout)findViewById(R.id.sliderHello);
        HashMap<String,String> file_maps = new HashMap<>();
        file_maps.put("Комментарий к рис. 1", "file:///android_asset/slides_hello/slide_1.jpg");
        file_maps.put("Комментарий к рис. 2", "file:///android_asset/slides_hello/slide_2.jpg");
        file_maps.put("Комментарий к рис. 3", "file:///android_asset/slides_hello/slide_3.jpg");
        file_maps.put("Комментарий к рис. 4", "file:///android_asset/slides_hello/slide_4.jpg");
        file_maps.put("Комментарий к рис. 5", "file:///android_asset/slides_hello/slide_5.jpg");
        file_maps.put("Комментарий к рис. 6", "file:///android_asset/slides_hello/slide_6.jpg");
        file_maps.put("Комментарий к рис. 7", "file:///android_asset/slides_hello/slide_7.jpg");
        file_maps.put("Комментарий к рис. 8", "file:///android_asset/slides_hello/slide_8.jpg");
        file_maps.put("Комментарий к рис. 9", "file:///android_asset/slides_hello/slide_9.jpg");
        for(String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getBaseContext());
            textSliderView.description(name).image(file_maps.get(name)).setScaleType(BaseSliderView.ScaleType.CenterCrop);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setDuration(4000);
    }

    @Override
    protected void onStop() {
        //slider_code
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    protected void onResume() {
        //slider_code
        mDemoSlider.startAutoCycle();
        super.onResume();
    }
}
