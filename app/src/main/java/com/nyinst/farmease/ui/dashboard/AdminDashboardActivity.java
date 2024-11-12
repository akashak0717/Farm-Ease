package com.nyinst.farmease.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nyinst.farmease.R;
import com.nyinst.farmease.ui.news.NewsActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    LinearLayout manageNews, category, complaint, agricultureOffice, feedback_text, users, cropInfo,viewCrop;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        manageNews = findViewById(R.id.manage_news);
        complaint = findViewById(R.id.complaint);
        users = findViewById(R.id.users);
        category = findViewById(R.id.category);

        users.setOnClickListener(view -> {
            Intent users = new Intent(this, UsersListActivity.class);
            startActivity(users);
        });

        manageNews.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewsActivity.class);
            intent.putExtra("canAdd", true);
            startActivity(intent);
        });

        category.setOnClickListener(view -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        });

        complaint.setOnClickListener(view -> {
            Intent complaint = new Intent(this, ComplaintActivity.class);
            startActivity(complaint);
        });

        agricultureOffice = findViewById(R.id.agriculture_office);
        agricultureOffice.setOnClickListener(view -> {
            Intent intent = new Intent(this, AgricultureOfficeListActivity. class);
            intent.putExtra("canAdd", true);
            startActivity(intent);
        });

        viewCrop = findViewById(R.id.viewCrop);
        viewCrop.setOnClickListener(view -> {
            Intent crop = new Intent(this, ViewCropActivity.class);
            startActivity(crop);
        });

        feedback_text = findViewById(R.id.view_feedback);
        feedback_text.setOnClickListener(view -> {
            Intent feedback = new Intent(this, ViewFeedbackActivity. class);
            startActivity(feedback);
        });

        cropInfo = findViewById(R.id.cropInfo);
        cropInfo.setOnClickListener(view -> {
            Intent crop_info = new Intent(this, AllCropsActivity.class);
            startActivity(crop_info);
        });

    }
}