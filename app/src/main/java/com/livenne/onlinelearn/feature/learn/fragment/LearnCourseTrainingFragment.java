
package com.livenne.onlinelearn.feature.learn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livenne.onlinelearn.R;

public class LearnCourseTrainingFragment extends Fragment {

    private Long courseId;

    public LearnCourseTrainingFragment(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn_course_training, container, false);
    }
}