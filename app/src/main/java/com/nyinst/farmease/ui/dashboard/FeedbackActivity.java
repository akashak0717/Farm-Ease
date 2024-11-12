package com.nyinst.farmease.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.FeedbackModel;
import com.nyinst.farmease.model.NewsModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    EditText name, ratings, feedback;
    ImageView back;
    Button submit;
    ArrayList<FeedbackModel> FeedbackList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
       submit = findViewById(R.id.submit_feedback);

        name = findViewById(R.id.name);
        ratings = findViewById(R.id.ratings);
        feedback = findViewById(R.id.feedback);


        back.setOnClickListener(view -> {
            finish();
        });

        submit.setOnClickListener(view -> {
            String name_text = name.getText().toString();
            String ratings_text = ratings.getText().toString();
            String feedback_text = feedback.getText().toString();

            if (name_text.isEmpty() || ratings_text.isEmpty() || feedback_text.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Retrieve created_by from SharedPreferences
                String createdBy = getCreatedByFromSharedPreferences();

                // Prepare POST request parameters
                Map<String, String> params = new HashMap<>();
                params.put("name", name_text);
                params.put("ratings", ratings_text);
                params.put("feedback", feedback_text);

                // Send POST request to server
                NetworkUtils.sendPostRequest(FeedbackActivity.this, Constants.API_ENDPOINT + "farmease/Feedback", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(FeedbackActivity.this, result, Toast.LENGTH_LONG).show();

                        Intent feedback = new Intent(FeedbackActivity.this, ViewFeedbackActivity.class);
                        startActivity(feedback);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(FeedbackActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private String getCreatedByFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", "");
    }
}