package com.livenne.onlinelearn.page.explorepage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
