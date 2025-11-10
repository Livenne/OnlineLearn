package com.livenne.onlinelearn.page.learnpage.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.livenne.onlinelearn.page.learnpage.fragment.LearnCourseCommentFragment;
import com.livenne.onlinelearn.page.learnpage.fragment.LearnCourseContentFragment;
import com.livenne.onlinelearn.page.learnpage.fragment.LearnCourseTrainingFragment;

public class LearnCourseAdapter extends FragmentStateAdapter {

    private Long courseId;
    public LearnCourseAdapter(@NonNull FragmentActivity fragmentActivity,Long courseId) {
        super(fragmentActivity);
        this.courseId = courseId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new LearnCourseTrainingFragment(courseId);
            case 2: return new LearnCourseCommentFragment(courseId);
            default: return new LearnCourseContentFragment(courseId);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
