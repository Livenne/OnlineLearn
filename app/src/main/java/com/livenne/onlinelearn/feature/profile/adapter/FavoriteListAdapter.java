package com.livenne.onlinelearn.feature.profile.adapter;

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
import com.livenne.onlinelearn.data.model.Course;
import com.livenne.onlinelearn.data.model.UserFavorite;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.home.coursepage.activity.CourseShowActivity;

import java.util.List;

public class FavoriteListAdapter extends BaseAdapter {

    private Context context;
    private List<UserFavorite> userFavoriteList;

    public FavoriteListAdapter(Context context, List<UserFavorite> userFavoriteList) {
        this.context = context;
        this.userFavoriteList = userFavoriteList;
    }

    @Override
    public int getCount() {
        return userFavoriteList.size();
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

        UserFavorite userFavorite = userFavoriteList.get(position);

        convertView = LayoutInflater.from(context)
                .inflate(R.layout.favorite_item, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.name);
        TextView teacher = convertView.findViewById(R.id.teacher);
        TextView score = convertView.findViewById(R.id.score);

        NetworkUtils.loadResource("/course?courseId=" + userFavorite.getCourseId(), new TypeReference<Course>() {
        },result -> {
            Glide.with(context)
                    .load(NetworkUtils.baseUrl + "/image?imageName=" + result.getCoverUrl())
                    .into(imageView);
            name.setText(result.getName());
            teacher.setText(result.getTeacher());
            score.setText(result.getPrice().toString());
        });
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseShowActivity.class);
            intent.putExtra("courseId", userFavorite.getCourseId());
            context.startActivity(intent);
        });

        return convertView;
    }
}
