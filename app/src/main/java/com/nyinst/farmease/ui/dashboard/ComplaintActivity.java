package com.nyinst.farmease.ui.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.adapter.MyComplaintListAdapter;
import com.nyinst.farmease.interfaces.ComplaintClickListener;
import com.nyinst.farmease.model.MyComplaintModel;
import com.nyinst.farmease.network.NetworkUtils;
import com.nyinst.farmease.network.VolleyCallback;
import com.nyinst.farmease.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComplaintActivity extends AppCompatActivity {

    private static final String TAG = "ComplaintActivity";

    ImageView back;
    RecyclerView recyclerView;
    String userId;
    ArrayList<MyComplaintModel> complaintArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_complaint);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            back = findViewById(R.id.back);
            back.setOnClickListener(view -> finish());

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            userId = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE).getString("userId", "");
            fetchComplaints();

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
        }
    }

    private void fetchComplaints() {
        try {
            String endpoint = Constants.API_ENDPOINT + "farmease/Complaint";
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
                                openDialogToUpdateStatus(complaintModel);
                            }
                        });
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    }
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Network error: " + error);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error in fetchComplaints: " + e.getMessage());
        }
    }

    void openDialogToUpdateStatus(MyComplaintModel complaintModel){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.complaint_update_dialog_layout);


        EditText status = dialog.findViewById(R.id.status);
        EditText reason = dialog.findViewById(R.id.reason);
        EditText medicines = dialog.findViewById(R.id.medicine);
        EditText solution = dialog.findViewById(R.id.solution);
        Button submit = dialog.findViewById(R.id.update);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int complaintId = complaintModel.getId();
                String statusStr = status.getText().toString();
                String reasonStr = reason.getText().toString();
                String medicinesStr = medicines.getText().toString();
                String solutionStr = solution.getText().toString();

                Map<String, String> param = new HashMap<>();
                param.put("complaint_id", complaintId+"");
                param.put("status", statusStr);
                param.put("reason", reasonStr);
                param.put("medicine", medicinesStr);
                param.put("solution", solutionStr);
                NetworkUtils.sendPostRequest(ComplaintActivity.this, Constants.API_ENDPOINT + "farmease/Complaint", param, new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        fetchComplaints();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });


        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
