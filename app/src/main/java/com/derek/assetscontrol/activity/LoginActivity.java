package com.derek.assetscontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.manager.AccountManager;
import com.derek.assetscontrol.manager.Constants;
import com.derek.assetscontrol.model.User;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;

/**
 * Created by derek on 16/4/11.
 */
public class LoginActivity extends BaseActivity {

    private EditText accountText;
    private EditText passwordText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountText = (EditText) findViewById(R.id.accountText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        AccountManager.getInstance().registerObserver(this);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountText.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please input staff id", Toast.LENGTH_SHORT)
                        .show();
                    return;
                }
                if (passwordText.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please input password", Toast.LENGTH_SHORT)
                        .show();
                    return;
                }
                showDialog();
                AccountManager.getInstance()
                    .login(accountText.getText().toString(), passwordText.getText().toString());
            }
        });
    }

    @Override
    public void onUIResult(String key, UIResult uiResult) {
        super.onUIResult(key, uiResult);
        switch (key) {
            case Constants.LOGIN:
                if(uiResult.getRet() == NetConstants.UI_NEW_DATA) {
                    User user = (User) uiResult.getData();
                    Intent intent = new Intent();
                    if (user.getType().equals("U")) {
                        intent.setClass(this, StaffHomeActivity.class);
                    } else {
                        intent.setClass(this, ManagerHomeActivity.class);
                    }
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "staff id or password wrong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AccountManager.getInstance().unregisterObserver(this);
    }
}
