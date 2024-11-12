package com.nyinst.farmease.ui.dashboard;

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
import com.nyinst.farmease.adapter.AgricultureOfficeAdapter;
import com.nyinst.farmease.model.AgricultureOfficeModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AgricultureOfficeListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AgricultureOfficeModel> agricultureOfficeList = new ArrayList<>();
    ImageView back;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agriculture_office_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
        addButton = findViewById(R.id.add_button);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(view -> {
            finish();
        });

        addButton.setOnClickListener(view -> {
            Intent complaint = new Intent(this, AgricultureOfficeActivity.class);
            startActivity(complaint);
        });

        if(getIntent().getBooleanExtra("canAdd", false)){
            addButton.setVisibility(View.VISIBLE);
        }else{
            addButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    void fetchData(){
        NetworkUtils.sendGetRequest(this, Constants.API_ENDPOINT + "farmease/AgricultureOffice", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    agricultureOfficeList = new ArrayList<>();
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        AgricultureOfficeModel agricultureOfficeModel = new AgricultureOfficeModel(
                                jsonObject.getString("name"),
                                jsonObject.getString("category"),
                                jsonObject.getString("address"),
                                jsonObject.getString("location"),
                                jsonObject.getString("pincode"),
                                jsonObject.getString("mobile")
                        );
                        agricultureOfficeList.add(agricultureOfficeModel);
                    }

                    AgricultureOfficeAdapter adapter = new AgricultureOfficeAdapter(agricultureOfficeList, AgricultureOfficeListActivity.this);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(String error) {
                // Handle error
            }
        });
    }
}