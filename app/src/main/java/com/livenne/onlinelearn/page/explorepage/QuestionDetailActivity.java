package com.livenne.onlinelearn.page.explorepage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Question;
import com.livenne.onlinelearn.common.model.vo.UserVo;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionDetailActivity extends AppCompatActivity {

    private ImageView comeBack;
    private ImageView imageView;
    private TextView username;
    private TextView time;
    private TextView content;
    private Long questionId;
    private ImageView likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v -> finish());
        questionId = getIntent().getLongExtra("questionId", 1);
        imageView = findViewById(R.id.imageView);
        username = findViewById(R.id.username);
        time = findViewById(R.id.time);
        content = findViewById(R.id.question);

        likes = findViewById(R.id.imageView21);

//        likes.setOnClickListener(v -> {
//            NetworkUtils.loadResource("/user/question/like/" + questionId, new TypeReference<String>() {
//            }, result -> {
//                Toast.makeText(QuestionDetailActivity.this, result, Toast.LENGTH_LONG).show();
//            });
//        });


        NetworkUtils.loadResource("/question?questionId=" + questionId , new TypeReference<Question>() {
        }, question -> {
            content.setText(question.getQuestion());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            time.setText(format.format(new Date(question.getCreateTime())));
            NetworkUtils.loadResource("/user?userId=" + question.getUserId() , new TypeReference<UserVo>() {
            }, user -> {
                username.setText(user.getUsername());
                Glide.with(QuestionDetailActivity.this)
                        .load(NetworkUtils.baseUrl + "/image?imageName=" + user.getAvatarUrl())
                        .into(imageView);
            });
        });

    }
}