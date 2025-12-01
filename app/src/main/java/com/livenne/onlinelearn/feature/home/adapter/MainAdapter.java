package com.livenne.onlinelearn.feature.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.livenne.onlinelearn.feature.explore.ExploreFragment;
import com.livenne.onlinelearn.feature.home.HomeFragment;
import com.livenne.onlinelearn.feature.learn.LearnFragment;
import com.livenne.onlinelearn.feature.profile.ProfileFragment;

public class MainAdapter extends FragmentStateAdapter {
    public MainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1: return new LearnFragment();
//            case 2: return new ExploreFragment();
//            case 3: return new ProfileFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
