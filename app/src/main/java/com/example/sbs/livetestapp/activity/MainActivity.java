package com.example.sbs.livetestapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.livetestapp.R;
import com.example.sbs.livetestapp.prefrence.SaveData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtMainLogoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMainLogoutId = findViewById(R.id.txtMainLogoutId);
        txtMainLogoutId.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtMainLogoutId:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Logout...");
                alertDialog.setMessage("Are you sure you want to logout this app ?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SaveData.logout(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Log out SuccessFully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
                break;
        }

    }
}
