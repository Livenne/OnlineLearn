package com.livenne.onlinelearn.page.learnpage;

import android.os.Bundle;
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
import com.livenne.onlinelearn.common.model.Course;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

public class CourseActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ImageView comeBack;
    private TextView title;
    private TextView name;
    private ImageView imageView;
    private Long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());
        courseId = getIntent().getLongExtra("courseId",1);
        tabLayout = findViewById(R.id.tabLayout4);
        viewPager = findViewById(R.id.view_pager4);

        title = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView14);
        name = findViewById(R.id.textView23);

        NetworkUtils.loadResource("/course/" + courseId, new TypeReference<Course>() {
        },course->{
            title.setText(course.getName()+" - 课程学习");
            name.setText(course.getName());
            Glide.with(CourseActivity.this)
                    .load(NetworkUtils.baseUrl + "/image/" + course.getCoverUrl())
                    .into(imageView);
        });


        LearnCourseAdapter learnCourseAdapter = new LearnCourseAdapter(this);
        viewPager.setAdapter(learnCourseAdapter);

        new TabLayoutMediator(tabLayout,viewPager,(tab,position)->{
            switch (position){
                case 1:
                    tab.setText("线下实训");
                    break;
                case 2:
                    tab.setText("评论");
                    break;
                default:
                    tab.setText("目录");
            }
        }).attach();

    }
}