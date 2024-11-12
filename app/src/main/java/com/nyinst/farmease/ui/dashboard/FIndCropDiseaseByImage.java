package com.nyinst.farmease.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FIndCropDiseaseByImage extends AppCompatActivity {

    ImageView back, uploadedImage, supplementImage;
    TextView titleTv, descTv, preventationTv, supplementNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_crop_disease_by_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        uploadedImage = findViewById(R.id.uploaded_image);
        titleTv = findViewById(R.id.title);
        descTv = findViewById(R.id.description);
        preventationTv = findViewById(R.id.preventation);
        supplementNameTv = findViewById(R.id.supplement_name);
        supplementImage = findViewById(R.id.supplement_image);


        Bundle ex = getIntent().getExtras();
        if(ex != null){
            Bitmap bmp = ex.getParcelable("image");
            uploadedImage.setImageBitmap(bmp);
            Map<String, String> param = new HashMap<>();
            NetworkUtils.sendPostRequestWithImage(this, "http://" + Constants.ServerIp + ":5000/submit", param, bmp, new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject response = new JSONObject(result);
                        titleTv.setText(response.getString("title"));
                        descTv.setText(response.getString("description"));
                        preventationTv.setText(response.getString("prevent"));
                        supplementNameTv.setText(response.getString("supplement_name"));
                        Picasso.get().load(response.getString("supplement_image_url")).into(supplementImage);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(FIndCropDiseaseByImage.this, result, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(FIndCropDiseaseByImage.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }


        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });
    }
}