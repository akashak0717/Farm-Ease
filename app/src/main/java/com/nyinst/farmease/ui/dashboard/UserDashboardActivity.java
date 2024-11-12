package com.nyinst.farmease.ui.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nyinst.farmease.R;
import com.nyinst.farmease.ui.news.NewsActivity;

public class UserDashboardActivity extends AppCompatActivity {

    LinearLayout viewNews;
    LinearLayout diseaseComplaint;
    LinearLayout myComplaint;
    LinearLayout findCrop;
    LinearLayout allCrops;
    LinearLayout agricultureOffice;
    LinearLayout myProfile;
    LinearLayout feedback;
    LinearLayout viewFeedback;
    LinearLayout takeCropPicture;

    Button button;

    private static final int pic_id = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        button = findViewById(R.id.takePic);
        button.setOnClickListener(view -> {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, pic_id);
        });

        takeCropPicture = findViewById(R.id.crop_picture);
        takeCropPicture.setOnClickListener(view2 -> {
            Intent camera_intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent1, pic_id);
        });

        diseaseComplaint = findViewById(R.id.disease_complaint);
        diseaseComplaint.setOnClickListener(view -> {
            Intent diseaseComplaint = new Intent(this, DiseaseComplaintActivity.class);
            startActivity(diseaseComplaint);
        });

        viewNews = findViewById(R.id.manage_news);
        viewNews.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewsActivity.class);
            intent.putExtra("canAdd", true);
            startActivity(intent);
        });

        myComplaint = findViewById(R.id.my_complaint);
        myComplaint.setOnClickListener(view -> {
            Intent myComplaint = new Intent(this, MyComplaintActivity.class);
            startActivity(myComplaint);
        });

        findCrop = findViewById(R.id.find_crop);
        findCrop.setOnClickListener(view -> {
            Intent findCrop = new Intent(this, FindCropActivity.class);
            startActivity(findCrop);
        });

        allCrops = findViewById(R.id.all_crops);
        allCrops.setOnClickListener(view -> {
            Intent allCrops = new Intent(this, ViewCropActivity.class);
            startActivity(allCrops);
        });

        agricultureOffice = findViewById(R.id.agri_office);
        agricultureOffice.setOnClickListener(view -> {
            Intent agriOffice = new Intent(this, AgricultureOfficeListActivity.class);
            agriOffice.putExtra("canAdd", false);
            startActivity(agriOffice);
        });

        myProfile = findViewById(R.id.user_profile);
        myProfile.setOnClickListener(view -> {
            Intent myProfile = new Intent(this, MyProfileActivity.class);
            startActivity(myProfile);
        });

        feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(view -> {
            Intent feedback = new Intent(this, FeedbackActivity.class);
            startActivity(feedback);
        });

        viewFeedback = findViewById(R.id.user_view_feedback);
        viewFeedback.setOnClickListener(view -> {
            Intent viewFeedback = new Intent(this, ViewFeedbackActivity.class);
            startActivity(viewFeedback);
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            //click_image_id.setImageBitmap(photo);
            openDialogtoShowClickedImage(photo);
        }
    }

    private void openDialogtoShowClickedImage(Bitmap photo) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.clicked_image_dialog);

        ImageView imageView = dialog.findViewById(R.id.image);
        Button submit = dialog.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboardActivity.this, FIndCropDiseaseByImage.class);
                intent.putExtra("image", photo);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        imageView.setImageBitmap(photo);


        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}