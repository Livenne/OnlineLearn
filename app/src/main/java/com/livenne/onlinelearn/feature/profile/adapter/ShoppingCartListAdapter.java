package com.livenne.onlinelearn.feature.profile.adapter;

import android.content.Context;
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
import com.livenne.onlinelearn.data.model.UserShoppingCart;
import com.livenne.onlinelearn.core.network.NetworkUtils;

import java.util.List;

public class ShoppingCartListAdapter extends BaseAdapter {

    private Context context;
    private List<UserShoppingCart> userShoppingCartList;

    public ShoppingCartListAdapter(Context context, List<UserShoppingCart> userShoppingCartList) {
        this.context = context;
        this.userShoppingCartList = userShoppingCartList;
    }

    @Override
    public int getCount() {
        return userShoppingCartList.size();
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

        UserShoppingCart userShoppingCart = userShoppingCartList.get(position);

        convertView = LayoutInflater.from(context)
                .inflate(R.layout.cart_item, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.name);
        TextView teacher = convertView.findViewById(R.id.teacher);
        TextView score = convertView.findViewById(R.id.score);

        NetworkUtils.loadResource("/course?courseId="+userShoppingCart.getCourseId(), new TypeReference<Course>() {
        },result -> {
            Glide.with(context)
                    .load(NetworkUtils.baseUrl + "/image?imageName=" + result.getCoverUrl())
                    .into(imageView);
            name.setText(result.getName());
            teacher.setText(result.getTeacher());
            score.setText(result.getPrice().toString());
        });

        return convertView;
    }
}
