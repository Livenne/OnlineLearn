package com.livenne.onlinelearn.feature.explore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.livenne.onlinelearn.feature.explore.fragment.ConsultationFragment;
import com.livenne.onlinelearn.feature.explore.fragment.SquareFragment;

public class ExploreAdapter extends FragmentStateAdapter {
    public ExploreAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new SquareFragment();
            default: return new ConsultationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
