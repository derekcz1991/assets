package com.derek.assetscontrol.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.derek.assetscontrol.R;
import com.derek.basemodule.model.UIObserver;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;

/**
 * Created by derek on 16/4/9.
 */
public class BaseActivity extends AppCompatActivity implements UIObserver {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
    }

    final public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void onUIResult(String key, UIResult uiResult) {
        progressDialog.hide();
        if (uiResult.getRet() == NetConstants.UI_NET_ERROR) {
            Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();
        } else if (uiResult.getRet() == NetConstants.UI_NO_DATA) {
            //Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
}
