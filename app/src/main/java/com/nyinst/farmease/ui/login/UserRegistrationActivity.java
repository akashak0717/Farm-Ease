package com.nyinst.farmease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nyinst.farmease.R;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.ui.dashboard.UserDashboardActivity;
import com.nyinst.farmease.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText nameInput, mobileInput, emailInput, passwordInput;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameInput = findViewById(R.id.name);
        mobileInput = findViewById(R.id.mobile);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String name = nameInput.getText().toString();
            String mobile = mobileInput.getText().toString();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                Map<String, String> params= new HashMap<>();
                params.put("name", name);
                params.put("mobile", mobile);
                params.put("email", email);
                params.put("password", password);

                NetworkUtils.sendPostRequest(UserRegistrationActivity.this, Constants.API_ENDPOINT+"farmease/UserRegistration", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(UserRegistrationActivity.this, "Registration Success, Login with Credential", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UserRegistrationActivity.this, UserDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });

    }
}