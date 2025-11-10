package com.livenne.onlinelearn.page.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Course;
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.page.homepage.adapter.BannerAdapter;
import com.livenne.onlinelearn.page.homepage.adapter.GridViewAdapter;
import com.livenne.onlinelearn.page.homepage.activity.SearchActivity;

import java.util.List;


public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private View search;
    private GridView gridView;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(currentItem + 1, true);
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        search = inflate.findViewById(R.id.search_show);
        search.setOnClickListener(v -> startActivity(new Intent(inflate.getContext(), SearchActivity.class)));

        viewPager = inflate.findViewById(R.id.banner);
        gridView = inflate.findViewById(R.id.gridview);


        NetworkUtils.loadResource("/course/list", new TypeReference<List<Course>>() {
        }, result -> {
            GridViewAdapter gridViewAdapter = new GridViewAdapter(inflate.getContext(),result,4);
            gridView.setAdapter(gridViewAdapter);

            BannerAdapter bannerAdapter = new BannerAdapter(result);
            viewPager.setAdapter(bannerAdapter);
        });


        ConstraintLayout food = inflate.findViewById(R.id.food);
        food.setOnClickListener(v -> Toast.makeText(inflate.getContext(),"排毒美食",Toast.LENGTH_LONG).show());
        ConstraintLayout medicine = inflate.findViewById(R.id.medicine);
        medicine.setOnClickListener(v -> Toast.makeText(inflate.getContext(),"医药卫生",Toast.LENGTH_LONG).show());
        ConstraintLayout nursing = inflate.findViewById(R.id.nursing);
        nursing.setOnClickListener(v -> Toast.makeText(inflate.getContext(),"内科护理",Toast.LENGTH_LONG).show());
        ConstraintLayout more = inflate.findViewById(R.id.more);
        more.setOnClickListener(v -> Toast.makeText(inflate.getContext(),"更多书籍",Toast.LENGTH_LONG).show());

        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(autoScrollRunnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(autoScrollRunnable);
    }
}