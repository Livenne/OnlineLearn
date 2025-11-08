package com.livenne.onlinelearn.page.learnpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LearnCourseAdapter extends FragmentStateAdapter {
    public LearnCourseAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new LearnCourseTrainingFragment();
            case 2: return new LearnCourseCommentFragment();
            default: return new LearnCourseContentFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
