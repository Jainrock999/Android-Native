package com.example.sbs.livetestapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.livetestapp.R;
import com.example.sbs.livetestapp.Url;
import com.example.sbs.livetestapp.json.Helper;
import com.example.sbs.livetestapp.json.JSONParser;
import com.example.sbs.livetestapp.model.LoginModel;
import com.example.sbs.livetestapp.prefrence.SaveData;
import com.example.sbs.livetestapp.validation.UserAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtLoginSignUpId, txtLoginSignInId;
    LoginModel loginModel;
    EditText etLoginNameId, etLogiPassId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLoginSignUpId = findViewById(R.id.txtLoginSignUpId);
        etLoginNameId = findViewById(R.id.etLoginNameId);
        txtLoginSignInId = findViewById(R.id.txtLoginSignInId);
        etLogiPassId = findViewById(R.id.etLogiPassId);
        txtLoginSignUpId.setOnClickListener(this);
        txtLoginSignInId.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtLoginSignInId:
                if (UserAccount.isEmpty(etLoginNameId, etLogiPassId)) {
                    getlogin();
                } else {
                    UserAccount.EditTextPointer.setError("Can't be Empty!");
                    UserAccount.EditTextPointer.requestFocus();
                }
                break;
            case R.id.txtLoginSignUpId:
                Intent intent = new Intent(LoginActivity.this, RegistraionActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getlogin() {
        new JSONParser(LoginActivity.this).parseVollyStringRequest(Url.LOGIN_URL, 1, getParam(), new Helper() {
            public void backResponse(String response) {
                Log.e("111", "" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    String Status=object.getString("status");
                    if (Status.equals("True")) {
                        SaveData.setUserName(getApplicationContext(), object.getString("id"));
                        Toast.makeText(LoginActivity.this, "Login SuccessFully Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Please Enter Vaild Id and Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("cate",""+e);
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_name", etLoginNameId.getText().toString());
        params.put("password", etLogiPassId.getText().toString());
        return params;

    }

}
