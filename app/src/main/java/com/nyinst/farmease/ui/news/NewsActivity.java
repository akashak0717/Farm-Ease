package com.nyinst.farmease.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.adapter.NewsListAdapter;
import com.nyinst.farmease.model.NewsModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NewsModel> newsList = new ArrayList<>();
    Button addNews;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addNews = findViewById(R.id.add_button);
        back = findViewById(R.id.back);

        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsActivity.this, AddNewsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(view -> {
            finish();
        });

        if(getIntent().getBooleanExtra("canAdd", false)){
            addNews.setVisibility(View.VISIBLE);
        }else{
            addNews.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    void fetchData(){
        NetworkUtils.sendGetRequest(this, Constants.API_ENDPOINT + "farmease/News", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    newsList = new ArrayList<>();
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        NewsModel newsModel = new NewsModel(jsonObject.getString("title"), jsonObject.getString("content"), jsonObject.getString("created_by"));
                        newsList.add(newsModel);
                    }

                    NewsListAdapter adapter = new NewsListAdapter(newsList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}