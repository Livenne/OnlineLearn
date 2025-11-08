package com.livenne.onlinelearn.page.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Course;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<Course> courseList;

    public BannerAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
        return new BannerViewHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {

        Glide.with(holder.context)
                .load(NetworkUtils.baseUrl + "/image/" +courseList.get(position % 3).getCoverUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private Context context;
        public BannerViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            this.context = context;
        }
    }
}
