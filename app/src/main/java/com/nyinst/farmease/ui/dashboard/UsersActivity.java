package com.nyinst.farmease.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.adapter.FeedbackAdapter;
import com.nyinst.farmease.adapter.UsersAdapter;
import com.nyinst.farmease.model.FeedbackModel;
import com.nyinst.farmease.model.UsersModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    ImageView back;
    ArrayList<UsersModel> usersList = new ArrayList<>();
    RecyclerView recyclerView;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userId = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE).getString("userId", "");


    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    void fetchData(){
        NetworkUtils.sendGetRequest(this, Constants.API_ENDPOINT + "farmease/Users", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    usersList = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        UsersModel userModel = new UsersModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("email"),
                                jsonObject.getString("mobile")
                        );
                        usersList.add(userModel);
                    }

                    UsersAdapter adapter = new UsersAdapter(usersList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                }
            }
            @Override
            public void onError(String error) {

            }
        });
    }
}
