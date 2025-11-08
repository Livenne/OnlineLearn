package com.livenne.onlinelearn.page.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.livenne.onlinelearn.R;

public class SearchActivity extends AppCompatActivity {

    private ImageView comeBack;
    private EditText editText;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v->finish());
        editText = findViewById(R.id.editText);

        search = findViewById(R.id.button);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("searchCond", editText.getText().toString());
            startActivity(intent);
        });
    }
}