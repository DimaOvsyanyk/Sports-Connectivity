package com.dimaoprog.sportsconnectivity;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

public class FragmentNaviManager {

    private ShowNewFragmentListener showNewFragmentListener;

    public interface ShowNewFragmentListener {
        void showFragmentFromActivity(Fragment fragment);
        void showMainFragmentsFromActivity(Fragment fragment, boolean isBottomNaviView);
    }

    @Inject
    public FragmentNaviManager(ShowNewFragmentListener showNewFragmentListener) {
        this.showNewFragmentListener = showNewFragmentListener;
    }

    public void showNewFragment(Fragment newFragment) {
        showNewFragmentListener.showFragmentFromActivity(newFragment);
    }

    public void showNewMainFragment(Fragment newFragment, boolean isBottomNaviView) {
        showNewFragmentListener.showMainFragmentsFromActivity(newFragment, isBottomNaviView);
    }

}
