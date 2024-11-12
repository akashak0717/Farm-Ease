package com.nyinst.farmease.ui.dashboard;

import static android.content.ContentValues.TAG;

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
import com.nyinst.farmease.adapter.ViewCropAdapter;
import com.nyinst.farmease.model.ViewCropModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewCropActivity extends AppCompatActivity {

    ImageView back;
    ArrayList<ViewCropModel> viewCropList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_crop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        if (getIntent().getBooleanExtra("canAdd", false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    void fetchData(){
        NetworkUtils.sendGetRequest(this, Constants.API_ENDPOINT + "farmease/Crop", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    viewCropList = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ViewCropModel viewCropModel = new ViewCropModel(
                                jsonObject.getString("category_id"),
                                jsonObject.getString("seed_category"),
                                jsonObject.getString("soil_type"),
                                jsonObject.getString("water_irrigation"),
                                jsonObject.getString("farm_mechanization"),
                                jsonObject.getString("crop_protection"),
                                jsonObject.getString("deficiency_symptoms"),
                                jsonObject.getString("harvest"),
                                jsonObject.getString("cost_of_cultivation")
                        );
                        viewCropList.add(viewCropModel);
                    }

                    ViewCropAdapter adapter = new ViewCropAdapter(viewCropList);
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