package com.livenne.onlinelearn.page.homepage.coursepage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CourseShowAdapter extends FragmentStateAdapter {

    private Long courseId;
    public CourseShowAdapter(@NonNull FragmentActivity fragmentActivity,Long courseId) {
        super(fragmentActivity);
        this.courseId = courseId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new CourseContentFragment(courseId);
            case 2: return new CourseCommentFragment(courseId);
            default: return new CourseIntroductionFragment(courseId);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
