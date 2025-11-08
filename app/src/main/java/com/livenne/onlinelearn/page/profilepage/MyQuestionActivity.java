package com.livenne.onlinelearn.page.profilepage;

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
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.page.explorepage.SquareListViewAdapter;

import java.util.List;

public class MyQuestionActivity extends AppCompatActivity {

    private ImageView comeBack;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());
        listView = findViewById(R.id.listView);

        NetworkUtils.loadResource("/user/question/list", new TypeReference<List<Question>>() {
        }, questionList -> {

            SquareListViewAdapter squareListViewAdapter = new SquareListViewAdapter(this, questionList);
            listView.setAdapter(squareListViewAdapter);
        });
    }
}