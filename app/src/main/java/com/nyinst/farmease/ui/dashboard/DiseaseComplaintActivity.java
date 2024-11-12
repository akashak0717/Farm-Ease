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
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class DiseaseComplaintActivity extends AppCompatActivity {

    EditText cropName, cropType, currentDuration, soilType, diseaseDetails, previousHistory, symptom;
    ImageView back;
    Button submit;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_disease_complaint);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
        submit = findViewById(R.id.complaint_disease);

        cropName = findViewById(R.id.crop_name);
        cropType = findViewById(R.id.crop_type);
        currentDuration = findViewById(R.id.current_duration);
        soilType = findViewById(R.id.soil_type);
        diseaseDetails = findViewById(R.id.disease_details);
        previousHistory = findViewById(R.id.previous_history);
        symptom = findViewById(R.id.symptoms);

        back.setOnClickListener(view -> {
            finish();
        });

        submit.setOnClickListener(view -> {
            String crop_name = cropName.getText().toString();
            String crop_type = cropType.getText().toString();
            String current_duration = currentDuration.getText().toString();
            String soil_type = soilType.getText().toString();
            String disease_details = diseaseDetails.getText().toString();
            String previous_history = previousHistory.getText().toString();
            String symptoms = symptom.getText().toString();

            if (crop_name.isEmpty() || crop_type.isEmpty() || current_duration.isEmpty() || soil_type.isEmpty() || disease_details.isEmpty() || previous_history.isEmpty() || symptoms.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Retrieve created_by from SharedPreferences
                String createdBy = getCreatedByFromSharedPreferences();

                // Prepare POST request parameters
                Map<String, String> params = new HashMap<>();
                params.put("created_by", createdBy);
                params.put("crop_name", crop_name);
                params.put("crop_type", crop_type);
                params.put("current_duration", current_duration);
                params.put("soil_type", soil_type);
                params.put("disease_details", disease_details);
                params.put("previous_history", previous_history);
                params.put("symptoms", symptoms);

                // Send POST request to server
                NetworkUtils.sendPostRequest(DiseaseComplaintActivity.this, Constants.API_ENDPOINT + "farmease/MyComplaint", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(DiseaseComplaintActivity.this, result, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(DiseaseComplaintActivity.this, MyComplaintActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(DiseaseComplaintActivity.this, error, Toast.LENGTH_LONG).show();
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
