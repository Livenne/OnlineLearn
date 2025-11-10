package com.livenne.onlinelearn.page.homepage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.page.explorepage.adapter.ExploreAdapter;

public class ExploreFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_explore, container, false);

        viewPager = inflate.findViewById(R.id.view_pager2);
        tabLayout = inflate.findViewById(R.id.tabLayout2);

        ExploreAdapter exploreAdapter = new ExploreAdapter(this.getActivity());
        viewPager.setAdapter(exploreAdapter);

        new TabLayoutMediator(tabLayout,viewPager,(tab,position)->{
            switch (position){
                case 1:
                    tab.setText("村民广场");
                    break;
                default: tab.setText("咨询");
            }
        }).attach();

        return inflate;
    }
}