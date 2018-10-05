package com.example.sbs.livetestapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.livetestapp.R;
import com.example.sbs.livetestapp.Url;
import com.example.sbs.livetestapp.json.Helper;
import com.example.sbs.livetestapp.json.JSONParser;
import com.example.sbs.livetestapp.prefrence.SaveData;
import com.example.sbs.livetestapp.validation.UserAccount;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistraionActivity extends AppCompatActivity implements View.OnClickListener {
    CountryCodePicker ccp;
    TextView txtRegSignInId, txtRegistrationSignInId;
    EditText etRegistraionNameId, etRegistraionFullNameId, etRegistraionPasswordId, etRegistraionConfirmPassId, etRegistraionContactId, etRegistraionEmailId, etRegistraionDobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);
        etRegistraionNameId = findViewById(R.id.etRegistraionNameId);
        etRegistraionFullNameId = findViewById(R.id.etRegistraionFullNameId);
        etRegistraionPasswordId = findViewById(R.id.etRegistraionPasswordId);
        etRegistraionConfirmPassId = findViewById(R.id.etRegistraionConfirmPassId);
        etRegistraionContactId = findViewById(R.id.etRegistraionContactId);
        etRegistraionEmailId = findViewById(R.id.etRegistraionEmailId);
        etRegistraionDobId = findViewById(R.id.etRegistraionDobId);
        txtRegSignInId = findViewById(R.id.txtRegSignInId);
        txtRegistrationSignInId = findViewById(R.id.txtRegistrationSignInId);
        txtRegistrationSignInId.setOnClickListener(this);
        txtRegSignInId.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtRegSignInId:
                if (UserAccount.isEmpty(etRegistraionFullNameId, etRegistraionNameId, etRegistraionPasswordId,
                        etRegistraionConfirmPassId, etRegistraionContactId, etRegistraionEmailId,
                        etRegistraionDobId)) {
                    if (UserAccount.isEmailValid(etRegistraionEmailId)) {
                        if (etRegistraionPasswordId.getText().toString().trim().equals(etRegistraionConfirmPassId.getText().toString().trim())) {
                            getRegistration();
                        } else {
                            UserAccount.EditTextPointer.setError("Password Not Match!");
                            UserAccount.EditTextPointer.requestFocus();
                        }

                    } else {
                        UserAccount.EditTextPointer.setError("Please Enter Vaild Email!");
                        UserAccount.EditTextPointer.requestFocus();
                    }
                } else {
                    UserAccount.EditTextPointer.setError("Can't be Empty!");
                    UserAccount.EditTextPointer.requestFocus();
                }
                break;
            case R.id.txtRegistrationSignInId:
                Intent intent = new Intent(RegistraionActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getRegistration() {
        new JSONParser(RegistraionActivity.this).parseVollyStringRequest(Url.REG_URL, 1, getParam(), new Helper() {
            public void backResponse(String response) {
                Log.e("111", "" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    String Status = object.getString("status");
                    if (Status.equals("True")) {
                        Toast.makeText(RegistraionActivity.this, "Registration SuccessFully Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistraionActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(RegistraionActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("cate", "" + e);
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("full_name", etRegistraionFullNameId.getText().toString());
        params.put("user_name", etRegistraionNameId.getText().toString());
        params.put("email", etRegistraionEmailId.getText().toString());
        params.put("dob", etRegistraionDobId.getText().toString());
        params.put("password", etRegistraionPasswordId.getText().toString());
        return params;

    }

}

