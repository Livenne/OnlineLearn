package com.livenne.onlinelearn.page.homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Course;
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.page.homepage.coursepage.CourseShowActivity;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private Integer count;


    private List<Course> courseList;
    public GridViewAdapter(Context context,List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }
    public GridViewAdapter(Context context,List<Course> courseList,int count){
        this(context,courseList);
        this.count = count;
    }

    @Override
    public int getCount() {
        return count == null ? courseList.size() : count;
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
                .inflate(R.layout.course_card, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.textView4);
        TextView price = convertView.findViewById(R.id.textView5);
        TextView purchase = convertView.findViewById(R.id.textView7);

        Course course = courseList.get(position);
        Glide.with(context)
                .load(NetworkUtils.baseUrl + "/image/" + course.getCoverUrl())
                .into(imageView);
        name.setText(course.getName());
        price.setText(course.getPrice().toString());
        purchase.setText(course.getPurchase().toString());
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseShowActivity.class);
            intent.putExtra("courseId",course.getCourseId());
            context.startActivity(intent);
        });
        return convertView;
    }
}
