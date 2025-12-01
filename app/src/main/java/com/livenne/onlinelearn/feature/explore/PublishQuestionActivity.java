package com.livenne.onlinelearn.feature.explore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Question;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.core.common.utils.StringUtils;

public class PublishQuestionActivity extends AppCompatActivity {

    private ImageView comeBack;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publish_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v -> finish());

        editText = findViewById(R.id.question);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String text = editText.getText().toString();

            Question questionModel = new Question();
            questionModel.setQuestion(text);
            finish();
            NetworkUtils.loadResource("/user/question/publish",
                    StringUtils.toJson(questionModel),
                    new TypeReference<Long>() {},
                    result -> {
                Intent intent = new Intent(PublishQuestionActivity.this, QuestionDetailActivity.class);
                intent.putExtra("questionId",result);
                startActivity(intent);
            });

        });
    }
}