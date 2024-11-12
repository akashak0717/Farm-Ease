package com.nyinst.farmease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nyinst.farmease.MainActivity;
import com.nyinst.farmease.R;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.ui.dashboard.AdminDashboardActivity;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminLoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(AdminLoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                NetworkUtils.sendPostRequest(AdminLoginActivity.this, Constants.API_ENDPOINT + "farmease/AdminLogin", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(AdminLoginActivity.this, result, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if(jsonObject.getString("status").equals("login_success")){
                                Intent intent = new Intent(AdminLoginActivity.this, AdminDashboardActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(AdminLoginActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}