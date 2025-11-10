package com.livenne.onlinelearn.page.homepage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.UserCourse;
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.page.learnpage.adapter.CourseListAdapter;

import java.util.List;

public class LearnFragment extends Fragment {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_learn, container, false);
        listView = inflate.findViewById(R.id.course_list);

        NetworkUtils.loadResource("/user/course/list", new TypeReference<List<UserCourse>>() {
        }, userCourseList -> {
            CourseListAdapter courseListAdapter = new CourseListAdapter(inflate.getContext(),userCourseList);
            listView.setAdapter(courseListAdapter);
        });


        return inflate;
    }
}