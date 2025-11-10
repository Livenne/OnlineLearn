package com.livenne.onlinelearn.page.learnpage.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.page.learnpage.activity.CourseCommentActivity;

public class LearnCourseCommentFragment extends Fragment {

    private Long courseId;

    public LearnCourseCommentFragment(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_learn_course_comment, container, false);

        Button button = null;
        button.setOnClickListener(v -> {
            Intent intent = new Intent(inflate.getContext(), CourseCommentActivity.class);
            intent.putExtra("courseId", 1);
            startActivity(intent);
        });

        return inflate;
    }
}