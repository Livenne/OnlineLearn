package com.livenne.onlinelearn.page.learnpage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Course;
import com.livenne.onlinelearn.common.model.UserCourse;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<UserCourse> userCourseList;
    public CourseListAdapter(Context context,List<UserCourse> userCourseList) {
        this.context = context;
        this.userCourseList = userCourseList;
    }

    @Override
    public int getCount() {
        return userCourseList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context)
                .inflate(R.layout.course_item, parent, false);
        UserCourse userCourse = userCourseList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.textView16);
        TextView teacher = convertView.findViewById(R.id.textView17);

        View finalConvertView = convertView;
        NetworkUtils.loadResource("/course/" + userCourse.getCourseId(), new TypeReference<Course>() {
        },course->{

            Glide.with(context)
                    .load(NetworkUtils.baseUrl + "/image/" + course.getCoverUrl())
                    .into(imageView);
            name.setText(course.getName());
            teacher.setText(course.getTeacher());
            finalConvertView.setOnClickListener(v ->{
                Intent intent = new Intent(context, CourseActivity.class);
                intent.putExtra("courseId", course.getCourseId());
                context.startActivity(intent);
            });
        });
        return convertView;
    }
}
