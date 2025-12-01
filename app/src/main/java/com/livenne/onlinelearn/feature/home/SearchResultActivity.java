package com.livenne.onlinelearn.feature.home;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Course;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.home.adapter.GridViewAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultActivity extends AppCompatActivity {

    private ImageView comeBack;
    private GridView gridView;
    private String searchCond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());

        gridView = findViewById(R.id.gridview2);
        searchCond = getIntent().getStringExtra("searchCond");


        NetworkUtils.loadResource("/course/list", new TypeReference<List<Course>>() {
        }, result -> {
            GridViewAdapter gridViewAdapter = new GridViewAdapter(
                    SearchResultActivity.this,
                    result.stream().filter(e->e.getName().contains(searchCond)).collect(Collectors.toList()));
            gridView.setAdapter(gridViewAdapter);
        });

    }
}