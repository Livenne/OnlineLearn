package com.livenne.onlinelearn.feature.home.coursepage.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Course;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.home.coursepage.adapter.CourseShowAdapter;

public class CourseShowActivity extends AppCompatActivity {

    private ImageView comeBack;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private TextView title;
    private TextView name;
    private ImageView imageView;
    private Long courseId;
    private TextView favorite;
    private TextView like;
    private TextView shoppingcart;
    private Button joincart;
    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        courseId = getIntent().getLongExtra("courseId", 1);

        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());

        viewPager = findViewById(R.id.view_pager3);
        tabLayout = findViewById(R.id.tabLayout3);

        title = findViewById(R.id.textView);
        name = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView2);
        favorite = findViewById(R.id.favorite);
        favorite.setOnClickListener(v -> {
            NetworkUtils.loadResource("/course/favorite?courseId=" + courseId, new TypeReference<>() {
            }, result -> {});
        });
        like = findViewById(R.id.like);
        like.setOnClickListener(v -> {
            NetworkUtils.loadResource("/course/like?courseId=" + courseId, new TypeReference<>() {
            }, result -> {});
        });
        shoppingcart = findViewById(R.id.shoppingcart);
        shoppingcart.setOnClickListener(v -> {
        });
        joincart = findViewById(R.id.joincart);
        favorite.setOnClickListener(v -> {
            NetworkUtils.loadResource("/user/shoppingcart/join?courseId=" + courseId, new TypeReference<>() {
            }, result -> {});
        });
        buy = findViewById(R.id.buy);

        NetworkUtils.loadResource("/course?courseId=" + courseId, new TypeReference<Course>() {
        }, result -> {
            Glide.with(CourseShowActivity.this)
                    .load(NetworkUtils.baseUrl + "/image?imageName=" + result.getCoverUrl())
                    .into(imageView);
            title.setText(result.getName()+" - 课程详情");
            name.setText(result.getName());
        });


        CourseShowAdapter courseShowAdapter = new CourseShowAdapter(this,courseId);
        viewPager.setAdapter(courseShowAdapter);
        new TabLayoutMediator(tabLayout,viewPager,(tab,position)->{
            switch (position){
                case 1:
                    tab.setText("目录");
                    break;
                case 2:
                    tab.setText("评价");
                    break;
                default:
                    tab.setText("简介");
            }
        }).attach();

    }
}