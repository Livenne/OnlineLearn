package com.livenne.onlinelearn.feature.main;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.feature.home.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.primary));
        MainAdapter mainAdapter = new MainAdapter(this);
        viewPager.setAdapter(mainAdapter);
        new TabLayoutMediator(tabLayout,viewPager,(tab,position)->{
            switch (position){
                case 1:
                    tab.setText("学习").setIcon(R.drawable.ic_learn);
                    break;
                case 2:
                    tab.setText("发现").setIcon(R.drawable.ic_explore);
                    break;
                case 3:
                    tab.setText("我的").setIcon(R.drawable.ic_profile);
                    break;
                default:
                    tab.setText("首页").setIcon(R.drawable.ic_home);
                    break;
            }
        }).attach();
    }
}