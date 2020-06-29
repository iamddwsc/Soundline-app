package com.saber.Soundline.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.saber.Soundline.Activities.AuthActivity;
import com.saber.Soundline.Activities.HomeActivity;
import com.saber.Soundline.Constant;
import com.saber.Soundline.IOnBackPressed;
import com.saber.Soundline.MyWebViewClient;
import com.saber.Soundline.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthFragment extends Fragment implements IOnBackPressed {

    private WebView webView;
    private View view;
    private String currentURL;
    private String code;
    private ProgressDialog dialog;

    public AuthFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_auth, container, false);
        init();
        auth();
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        MyWebViewClient mWeb = new MyWebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                String temp = String.valueOf(webView.getProgress());
                Log.i("abcz", temp);
                if (!webView.getUrl().contains("login?continue")) {
                    webView.setVisibility(View.VISIBLE);
                } else if (webView.getUrl().contains("login?continue")){
                    if (webView.getProgress()<100) {
                        webView.setVisibility(View.GONE);
                    }
                }
                if (webView.getUrl().contains("callback?code=")) {
                    webView.setVisibility(View.GONE);
                    dialog.setMessage("Logging in...");
                    dialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (webView.getUrl().contains("login?continue")) {
                    if (webView.getProgress() == 100) {
                        webView.setVisibility(View.VISIBLE);
                        dialog.dismiss();

                    }
                }
                if (webView.getUrl().contains("callback?code=")) {
                    callback();
                }
                currentURL = webView.getUrl();
            }

        };
        webView.setWebViewClient(mWeb);
    }

    @Override
    public boolean onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return false;
        }
    }

    private void auth() {
        dialog.setMessage("Redirect to Spotify Login Page...");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constant.AUTH, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    webView.loadUrl(object.getString("AuthUrl"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();

        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

    private void callback() {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.CALLBACK, response -> {
            try {

                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token",object.getString("token"));
                    editor.putString("token2",object.getString("token2"));
                    editor.putInt("expires", object.getInt("expires"));
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    startActivity(new Intent((AuthActivity)getContext(), HomeActivity.class));
                    ((AuthActivity)(getContext())).finish();
                }
                String temp = object.getString("token");
                int exp = object.getInt("expires");
                Log.i("abcd", "Exp = "+exp+" || Token = " + temp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }, error -> {
            error.printStackTrace();
            dialog.dismiss();
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                if (currentURL.contains("code")) {
                    int i = currentURL.indexOf("=");
                    if (currentURL.contains("#")) {
                        int j = currentURL.indexOf("#");
                        code = currentURL.substring(i+1,j);
                    } else {
                        code = currentURL.substring(i+1);
                    }
                }
                map.put("code", code);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
