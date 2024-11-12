package com.nyinst.farmease.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nyinst.farmease.R;
import com.nyinst.farmease.adapter.CategoryListAdapter;
import com.nyinst.farmease.model.CategoryListModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCropsActivity extends AppCompatActivity {
    ImageView back;
    Button submit;
    Spinner cropType;
    EditText seedCategory, soilType, waterIrrigation, farmMechanization, cropProtection, deficiencySymptoms, harvest_text, costOfCultivation;
    ArrayList<CategoryListModel> categoryArrayList = new ArrayList<>();

    CategoryListAdapter categoryListAdapter;

    int cropSelectedId = 0;
    String cropSelectedName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_crops);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });


        cropType = findViewById(R.id.crop_spinner);

        fetchCrop();

        submit = findViewById(R.id.submit_crop);

        soilType = findViewById(R.id.soil_type);
        waterIrrigation = findViewById(R.id.watre_irrigation);
        deficiencySymptoms = findViewById(R.id.deficiency_symptoms);
        farmMechanization = findViewById(R.id.farm_mechanization);
        cropProtection = findViewById(R.id.crop_protection);
        harvest_text = findViewById(R.id.harvest);
        costOfCultivation = findViewById(R.id.cost_of_cultivation);

        submit.setOnClickListener(view -> {
            String soil_type = soilType.getText().toString();
            String water_irrigation = waterIrrigation.getText().toString();
            String deficiency_symptoms = deficiencySymptoms.getText().toString();
            String farm_mechanization = farmMechanization.getText().toString();
            String crop_protection = cropProtection.getText().toString();
            String harvest = harvest_text.getText().toString();
            String cost_of_cultivation = costOfCultivation.getText().toString();

            if (cropType.getTouchables().isEmpty() || soil_type.isEmpty() || water_irrigation.isEmpty() || deficiency_symptoms.isEmpty() || farm_mechanization.isEmpty() || crop_protection.isEmpty() || harvest.isEmpty() || cost_of_cultivation.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                // Prepare POST request parameters
                Map<String, String> params = new HashMap<>();
                params.put("category_id", cropSelectedId + "");
                params.put("seed_category", cropSelectedName);
                params.put("soil_type", soil_type);
                params.put("water_irrigation", water_irrigation);
                params.put("deficiency_symptoms", deficiency_symptoms);
                params.put("farm_mechanization", farm_mechanization);
                params.put("crop_protection", crop_protection);
                params.put("harvest", harvest);
                params.put("cost_of_cultivation", cost_of_cultivation);

                // Send POST request to server
                NetworkUtils.sendPostRequest(AllCropsActivity.this, Constants.API_ENDPOINT + "farmease/Crop", params, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(AllCropsActivity.this, result, Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(AllCropsActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void fetchCrop(){
        NetworkUtils.sendGetRequest(this, Constants.API_ENDPOINT + "farmease/Category", new VolleyCallback() {
            public void onSuccess(String result) {
                try {
                    JSONArray response = new JSONArray(result);
                    categoryArrayList = new ArrayList<>();
                    categoryArrayList.add(new CategoryListModel(0, "Select"));
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject areaItem = response.getJSONObject(i);
                        CategoryListModel areaModel = new CategoryListModel(areaItem.getInt("id"), areaItem.getString("category_name"));
                        categoryArrayList.add(areaModel);

                    }

                    categoryListAdapter = new CategoryListAdapter(AllCropsActivity.this, categoryArrayList);

                    cropType.setAdapter(categoryListAdapter);



                    cropType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            // Get the selected country
                            CategoryListModel selectedCrop = (CategoryListModel) parentView.getItemAtPosition(position);
                            // Display a toast with the selected country name
                            if(selectedCrop.getId()>0){
                                cropSelectedId = selectedCrop.getId();
                            }
                            cropSelectedName = selectedCrop.getCategoryName();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // Do nothing
                        }
                    });
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