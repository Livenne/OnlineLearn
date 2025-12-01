package com.livenne.onlinelearn.feature.home.coursepage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livenne.onlinelearn.R;

public class CourseCommentFragment extends Fragment {

    private Long courseId;

    public CourseCommentFragment(Long courseId) {
        this.courseId =courseId;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_course_comment, container, false);
        return inflate;
    }
}