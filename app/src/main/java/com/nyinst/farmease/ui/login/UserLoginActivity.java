package com.nyinst.farmease.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                Map<String, String> params= new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                NetworkUtils.sendPostRequest(UserLoginActivity.this, Constants.API_ENDPOINT+"farmease/UserLogin", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String status = jsonObject.getString("status");

                            if (status.equals("login_success")) {
                                String userId = jsonObject.getString("id");

                                // Store user ID in SharedPreferences
                                saveUserIdToSharedPreferences(userId);

                                // Proceed to dashboard or next activity
                                Intent intent = new Intent(UserLoginActivity.this, UserDashboardActivity.class);
                                startActivity(intent);
                                finish(); // Finish LoginActivity to prevent going back on back press
                            } else {
                                Toast.makeText(UserLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserLoginActivity.this, "JSON parsing error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(UserLoginActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void saveUserIdToSharedPreferences(String userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }
}
