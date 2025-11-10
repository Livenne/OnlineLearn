package com.livenne.onlinelearn.page.homepage.coursepage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livenne.onlinelearn.R;

public class CourseContentFragment extends Fragment {
    private Long courseId;

    public CourseContentFragment(Long courseId) {
        this.courseId = courseId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_course_content, container, false);
        return inflate;
    }
}