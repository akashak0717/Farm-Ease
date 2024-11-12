package com.nyinst.farmease.ui.news;

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

public class AddNewsActivity extends AppCompatActivity {

    Button submitNews;

    ImageView back;

    EditText title;
    EditText content;
    EditText created_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_news);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            submitNews = findViewById(R.id.submitNews);

            title = findViewById(R.id.title);
            content = findViewById(R.id.content);
            created_by = findViewById(R.id.created_by);


            submitNews.setOnClickListener(view -> {
                String titleStr = title.getText().toString();
                String contentStr = content.getText().toString();
                String uploadedBy = created_by.getText().toString();

                if(titleStr.isEmpty() || contentStr.isEmpty() || uploadedBy.isEmpty()){
                    Toast.makeText(this, "Enter all input field", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String, String> params = new HashMap<>();
                    params.put("title", titleStr);
                    params.put("content", contentStr);
                    params.put("created_by", uploadedBy);

                    NetworkUtils.sendPostRequest(AddNewsActivity.this, Constants.API_ENDPOINT+"farmease/News", params, new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(AddNewsActivity.this, "News Added Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(AddNewsActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            back = findViewById(R.id.back);

            back.setOnClickListener(view1 -> {
                finish();
            });

            });
        }
    }