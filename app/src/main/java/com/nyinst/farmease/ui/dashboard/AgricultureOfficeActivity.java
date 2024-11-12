package com.nyinst.farmease.ui.dashboard;

import android.content.Intent;
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

import com.nyinst.farmease.R;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class AgricultureOfficeActivity extends AppCompatActivity {

    EditText name, category, address, location, pincode, mobile;
    Button submit;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agriculture_office);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        category = findViewById(R.id.category);
        address = findViewById(R.id.address);
        location = findViewById(R.id.location);
        pincode = findViewById(R.id.pincode);
        mobile = findViewById(R.id.mobile);

        submit = findViewById(R.id.submit);

        back = findViewById(R.id.back);

        back.setOnClickListener(view -> {
            finish();
        });


        submit.setOnClickListener(view -> {
            String name_text = name.getText().toString();
            String category_text = category.getText().toString();
            String address_text = address.getText().toString();
            String location_text = location.getText().toString();
            String pincode_text = pincode.getText().toString();
            String mobile_no = mobile.getText().toString();


            if (name_text.isEmpty() || category_text.isEmpty() || address_text.isEmpty() || location_text.isEmpty() || pincode_text.isEmpty() || mobile_no.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Map<String, String> params = new HashMap<>();
                params.put("name", name_text);
                params.put("category", category_text);
                params.put("address", address_text);
                params.put("location", location_text);
                params.put("pincode", pincode_text);
                params.put("mobile", mobile_no);

                NetworkUtils.sendPostRequest(AgricultureOfficeActivity.this, Constants.API_ENDPOINT + "farmease/AgricultureOffice", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(AgricultureOfficeActivity.this, result, Toast.LENGTH_LONG).show();

                        finish();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(AgricultureOfficeActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }
}