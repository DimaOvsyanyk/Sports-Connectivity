package com.dimaoprog.sportsconnectivity.dagger;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager.ShowNewFragmentListener;

import dagger.Module;
import dagger.Provides;

@Module
public class NaviModule {

    private ShowNewFragmentListener naviListener;

    public NaviModule(ShowNewFragmentListener naviListener) {
        this.naviListener = naviListener;
    }

    @Provides
    public ShowNewFragmentListener getNaviListener() {
        return naviListener;
    }
}
