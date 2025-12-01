package com.livenne.onlinelearn.feature.home.coursepage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Course;
import com.livenne.onlinelearn.core.network.NetworkUtils;

public class CourseIntroductionFragment extends Fragment {

    private Long courseId;
    public CourseIntroductionFragment(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_course_introduction, container, false);

        TextView teacher = inflate.findViewById(R.id.textView);
        TextView desc = inflate.findViewById(R.id.textView2);

        NetworkUtils.loadResource("/course?courseId=" + courseId, new TypeReference<Course>() {
        }, result -> {
            teacher.setText(result.getTeacher());
            desc.setText(result.getIntroduction());
        });

        return inflate;
    }
}