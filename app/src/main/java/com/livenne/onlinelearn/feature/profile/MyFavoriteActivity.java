package com.livenne.onlinelearn.feature.profile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.UserFavorite;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.profile.adapter.FavoriteListAdapter;

import java.util.List;

public class MyFavoriteActivity extends AppCompatActivity {

    private ImageView comeBack;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_favorite);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());
        NetworkUtils.loadResource("/user/course/favorite/list", new TypeReference<List<UserFavorite>>() {
        },result -> {
            FavoriteListAdapter favoriteListAdapter = new FavoriteListAdapter(MyFavoriteActivity.this, result);
            listView.setAdapter(favoriteListAdapter);
        });
    }
}