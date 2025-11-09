package com.livenne.onlinelearn.page;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.dto.UserLoginDTO;
import com.livenne.onlinelearn.common.model.dto.UserRegisterDTO;
import com.livenne.onlinelearn.common.utils.NetworkCallable;
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.common.utils.StringUtils;
import com.livenne.onlinelearn.page.homepage.MainActivity;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText2);
        confirmPassword = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(v -> finish());

        button.setOnClickListener(v -> {
            UserRegisterDTO userRegisterDTO = new UserRegisterDTO(
                    username.getText().toString(),
                    password.getText().toString(),
                    confirmPassword.getText().toString()
            );
            NetworkUtils.loadResource("/auth/register",
                    StringUtils.toJson(userRegisterDTO),
                    new TypeReference<String>() {},
                    result -> {
                        if (result == null || result.trim().isEmpty()) return;
                        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token",result);
                        editor.apply();
                        NetworkUtils.token = result;
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    });
        });
    }
}