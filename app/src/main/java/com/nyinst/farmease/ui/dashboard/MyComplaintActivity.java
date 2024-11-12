package com.nyinst.farmease.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.adapter.MyComplaintListAdapter;
import com.nyinst.farmease.adapter.NewsListAdapter;
import com.nyinst.farmease.interfaces.ComplaintClickListener;
import com.nyinst.farmease.model.MyComplaintModel;
import com.nyinst.farmease.model.NewsModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyComplaintActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ImageView back;

    ArrayList<MyComplaintModel> complaintArrayList = new ArrayList<>();

    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_complaint);
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


        fetchComplaints();

    }

    private void fetchComplaints() {
        String endpoint = Constants.API_ENDPOINT + "farmease/MyComplaint?user_id="+userId;
        NetworkUtils.sendGetRequest(this, endpoint, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    complaintArrayList = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        MyComplaintModel complaintModel = new MyComplaintModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("created_by"),
                                jsonObject.getString("crop_name"),
                                jsonObject.getString("crop_type"),
                                jsonObject.getString("current_duration"),
                                jsonObject.getString("soil_type"),
                                jsonObject.getString("disease_details"),
                                jsonObject.getString("previous_history"),
                                jsonObject.getString("symptoms"),
                                jsonObject.getString("status"),
                                jsonObject.getString("reason"),
                                jsonObject.getString("medicines"),
                                jsonObject.getString("solution")
                                );
                        complaintArrayList.add(complaintModel);
                    }

                    MyComplaintListAdapter adapter = new MyComplaintListAdapter(complaintArrayList, new ComplaintClickListener() {
                        @Override
                        public void onComplaintClicked(MyComplaintModel complaintModel) {

                        }
                    });
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