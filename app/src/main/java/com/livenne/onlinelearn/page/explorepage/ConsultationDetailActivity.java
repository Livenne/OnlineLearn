package com.livenne.onlinelearn.page.explorepage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Consultation;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultationDetailActivity extends AppCompatActivity {

    private ImageView comeBack;

    private Long consultationId;

    private TextView name;
    private TextView teacher;
    private TextView date;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultation_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        consultationId = getIntent().getLongExtra("consultationId", 1);
        comeBack = findViewById(R.id.comeback);
        comeBack.setOnClickListener(v -> finish());

        name = findViewById(R.id.textView29);
        teacher = findViewById(R.id.textView30);
        date = findViewById(R.id.textView31);
        content = findViewById(R.id.textView32);

        NetworkUtils.loadResource("/consultation/" + consultationId, new TypeReference<Consultation>() {
        }, result -> {
            name.setText(result.getName());
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date.setText(formatDate.format(new Date(result.getCreateTime())));
            teacher.setText(result.getTeacher());
            content.setText(result.getContent());
        });

    }
}