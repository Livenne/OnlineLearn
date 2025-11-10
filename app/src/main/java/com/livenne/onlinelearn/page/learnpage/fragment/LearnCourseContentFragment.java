package com.livenne.onlinelearn.page.learnpage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livenne.onlinelearn.R;

public class LearnCourseContentFragment extends Fragment {

    private Long courseId;

    public LearnCourseContentFragment(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn_course_content, container, false);
    }
}