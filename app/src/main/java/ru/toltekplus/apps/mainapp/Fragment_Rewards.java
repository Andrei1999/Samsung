package ru.toltekplus.apps.mainapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class Fragment_Rewards extends Fragment {

    SliderLayout rewardsSlider;
    boolean loadingFinished, redirect;
    ProgressDialog pDialog;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage(getString(R.string.pDialogText_3));
        showDialog();

        WebView webAbout = view.findViewById(R.id.webAbout);
        webAbout.getSettings().setJavaScriptEnabled(true);

        loadingFinished = true;
        redirect = false;

        webAbout.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect){
                    loadingFinished = true;
                }

                if(loadingFinished && !redirect){
                    hideDialog();
                } else{
                    redirect = false;
                }

            }
        });

        webAbout.loadUrl("https://vk.com/toltekbgu"); //основной сайт

        //slider_Rewards_code
        rewardsSlider = view.findViewById(R.id.sliderRewards);
        HashMap<String,String> file_r_maps = new HashMap<>();
        file_r_maps.put("Комментарий к награде №1", "file:///android_asset/rewards/reward_1.jpg");
        file_r_maps.put("Комментарий к награде №2", "file:///android_asset/rewards/reward_2.jpg");
        file_r_maps.put("Комментарий к награде №3", "file:///android_asset/rewards/reward_3.jpg");
        file_r_maps.put("Комментарий к награде №4", "file:///android_asset/rewards/reward_4.jpg");
        for(String name : file_r_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(name).image(file_r_maps.get(name)).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            rewardsSlider.addSlider(textSliderView);
        }
        rewardsSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        rewardsSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        rewardsSlider.setDuration(4000);

        //tabHost setup
        TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tabAbout);
        tabSpec.setIndicator(getString(R.string.title_tabAbout));
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
                        rewardsSlider.stopAutoCycle(); break;
                    case "tag2":
                        rewardsSlider.startAutoCycle();
                }
            }
        });

        tabHost.setCurrentTab(0);

        return view;
    }

    @Override
    public void onStop() {
        rewardsSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onResume() {
        rewardsSlider.startAutoCycle();
        super.onResume();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

