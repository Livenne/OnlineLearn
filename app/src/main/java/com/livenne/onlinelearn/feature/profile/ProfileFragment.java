package com.livenne.onlinelearn.feature.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.vo.UserVo;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.core.common.utils.StringUtils;
import com.livenne.onlinelearn.feature.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    private ConstraintLayout questionSet;
    private ConstraintLayout favorite;
    private ConstraintLayout message;
    private ConstraintLayout training;
    private ConstraintLayout shoppingCart;
    private ConstraintLayout order;
    private ConstraintLayout logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);

        questionSet = inflate.findViewById(R.id.constraintLayout6);
        questionSet.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), MyQuestionActivity.class));
        });
        favorite = inflate.findViewById(R.id.constraintLayout7);
        favorite.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), MyFavoriteActivity.class));
        });
        message = inflate.findViewById(R.id.constraintLayout8);
        message.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), MessageActivity.class));
        });
        training = inflate.findViewById(R.id.constraintLayout12);
        training.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), TrainingActivity.class));
        });
        shoppingCart = inflate.findViewById(R.id.constraintLayout9);
        shoppingCart.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), ShoppingCartActivity.class));
        });
        order = inflate.findViewById(R.id.constraintLayout10);
        order.setOnClickListener(v -> {
            inflate.getContext().startActivity(new Intent(inflate.getContext(), OrderActivity.class));
        });
        logout = inflate.findViewById(R.id.constraintLayout11);
        logout.setOnClickListener(v->{
            SharedPreferences preferences = inflate.getContext().getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.clear();
            edit.commit();
            NetworkUtils.token = "";
            Intent intent = new Intent(inflate.getContext(), LoginActivity.class);
            startActivity(intent);
        });

        ImageView imageView = inflate.findViewById(R.id.imageView);
        TextView username = inflate.findViewById(R.id.username);
        TextView userId = inflate.findViewById(R.id.userid);
        NetworkUtils.loadResource(
                "/user?userId=" + StringUtils.getDecoded().getIssuer(),
                new TypeReference<UserVo>() {},
                result -> {
                    username.setText(result.getUsername());
                    userId.setText(result.getUserId().toString());
                    Glide.with(inflate.getContext())
                            .load(NetworkUtils.baseUrl + "/image?imageName=" + result.getAvatarUrl())
                            .into(imageView);
                });

        return inflate;
    }
}