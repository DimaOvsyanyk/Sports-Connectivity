package com.dimaoprog.sportsconnectivity.dagger;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager.ShowNewFragmentListener;

public class AppComponentBuild {

    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }

    public static void createAppComponent(ShowNewFragmentListener showNewFragmentListener) {
        component = buildAppComponent(showNewFragmentListener);
    }

    private static AppComponent buildAppComponent(ShowNewFragmentListener showNewFragmentListener) {
        return DaggerAppComponent.builder()
                .naviModule(new NaviModule(showNewFragmentListener))
                .build();
    }

}
