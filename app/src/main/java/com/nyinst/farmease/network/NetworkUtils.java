package com.nyinst.farmease.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Proprietary of Xcel Solution Corp
 * <p>
 * Author : Avinash Kumar Singh, 21-Jun-2024
 * <p>
 * Description :
 */
public class NetworkUtils {



    public static void sendGetRequest(Context context, String url, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                callback::onSuccess,
                error -> callback.onError(error.toString())) {
        };

        SingletonRequest.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendPostRequest(Context context, String url, final Map<String, String> params, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                callback::onSuccess,
                error -> callback.onError(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        SingletonRequest.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendPutRequest(Context context, String url, final Map<String, String> params, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                callback::onSuccess,
                error -> callback.onError(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        SingletonRequest.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendDeleteRequest(Context context, String url, final Map<String, String> params, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                callback::onSuccess,
                error -> callback.onError(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        SingletonRequest.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendPostRequestWithImage(Context context, String url, final Map<String, String> params, Bitmap bitmap, final VolleyCallback callback){
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, response ->
                callback.onSuccess(new String(response.data, StandardCharsets.UTF_8)),
                error -> callback.onError(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                //params.put("avatar", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
                //params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));
                params.put("image", new DataPart("image.jpg", getFileByteArray(bitmap)));

                return params;
            }
        };

        SingletonRequest.getInstance(context).addToRequestQueue(multipartRequest);
    }


    public static byte[] getFileByteArray(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
        //String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //return encodedImage;

    }

}
